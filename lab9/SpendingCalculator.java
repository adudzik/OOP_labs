package agh.cs.lab9;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.lang.*;
import java.util.stream.Collectors;

/**
 * Created by Arek on 2016-12-16.
 * <p>
 * This calculates and prints all data that user wants.
 */
class SpendingCalculator {
    private static DecimalFormat decimalFormat = new DecimalFormat();

    SpendingCalculator() {
        decimalFormat.setMaximumFractionDigits(2);
    }

    void calculate(java.lang.String[] args) throws IOException, InterruptedException {
        OptionParser userOptions = new OptionParser().getUserOptions(args);
        Parliament p = this.createParliament(userOptions.getCadence());

        switch (userOptions.getOption()) {
            case DeputySpending:
                this.calculateSingleDeputyAllSpending(p, userOptions.getName());
                break;

            case RepairsSpending:
                this.getSingleDeputyRepairsSpending(p, userOptions.getName());
                break;

            case CadenceAverage:
                this.getCadenceAverage(p);
                break;

            case MostForeignTrips:
                this.getDeputyWithMostTrips(p);
                break;

            case MostDayOnTrip:
                this.getDeputyWithLongestTrips(p);
                break;

            case MostExpensiveTrip:
                this.getDeputyWithMostExpensiveTrip(p);
                break;

            case ItalyJourney:
                this.getDeputiesFromItalianTrip(p);
                break;
        }
    }

    private Parliament createParliament(int cadenceNumber) throws InterruptedException {
        List<Deputy> deputies = new LinkedList<>();
        int k = 1;
        int lastPageNumber = new JsonDeputy(cadenceNumber, 1).getLastPageNumber();
        List<List<Deputy>> pages = new ArrayList<>();
        List<Thread> threads = new LinkedList<>();
        try {
            do {
                int i = k;
                threads.add(new Thread(() -> pages.add(new JsonDeputy(cadenceNumber, i).getPageJson())));
                k++;
            } while (k <= lastPageNumber);

            threads.forEach(Thread::start);

            for (Thread t : threads)
                t.join();
        } catch (InterruptedException err) {
            throw new InterruptedException("The thread has been interrupted");
        }


        for (int i = 1; i < pages.size(); i++)
            deputies.addAll(pages.get(i));

        return new Parliament(deputies);
    }

    private void getTripsInfo(Parliament parliament) throws InterruptedException {
        List<Thread> threads = new LinkedList<>();
        List<Deputy> deputies = parliament.getCadenceDeputiesList();

        try {
            threads.addAll(deputies.stream().map(d -> new Thread(new JsonTrips(d))).collect(Collectors.toList()));

            threads.forEach(Thread::start);

            for (Thread t : threads)
                t.join();

        } catch (InterruptedException err) {
            throw new InterruptedException("The thread has been interrupted");
        }
    }

    private void setSingleDeputySpending(Parliament parliament, String deputyName) throws IOException {
        Deputy deputy = parliament.getDeputy(deputyName);

        if (deputy == null)
            throw new IllegalArgumentException("This deputy " + deputyName + " does not exist!");

        new JsonSpending(deputy).getDeputySpending();
    }

    private void calculateSingleDeputyAllSpending(Parliament parliament, String deputyName) throws IOException {
        setSingleDeputySpending(parliament, deputyName);

        Deputy deputy = parliament.getDeputy(deputyName);
        Double result = 0.0;

        List<Spending> deputySpending = deputy.getSpending();

        for (Spending spending : deputySpending) {
            result += spending.getSpendingSum();
        }
        System.out.println("<------------------------------------------------------------------>");
        System.out.println(deputyName + " " + decimalFormat.format(result) + "zł");
        System.out.println("<------------------------------------------------------------------>");
    }

    private void getSingleDeputyRepairsSpending(Parliament parliament, String deputyName) throws IOException {
        setSingleDeputySpending(parliament, deputyName);

        Deputy deputy = parliament.getDeputy(deputyName);
        List<Spending> deputySpending = deputy.getSpending();

        Double result = 0.0;

        for (Spending spending : deputySpending) {
            int id = spending.getTitles().getTitleID("Koszty drobnych napraw i remont\u00f3w lokalu biura poselskiego");
            if (id == -1)
                result += 0.0;
            else
                result += spending.getSpending(id);
        }
        System.out.println("<------------------------------------------------------------------>");
        System.out.println(deputyName + " " + decimalFormat.format(result) + "zł");
        System.out.println("<------------------------------------------------------------------>");
    }

    private void getDeputyWithMostTrips(Parliament parliament) throws IOException, InterruptedException {
        getTripsInfo(parliament);

        String name = "";
        int trips = 0;

        for (Deputy deputy : parliament.getCadenceDeputiesList()) {
            if (deputy.getTrips() != null && deputy.getTrips().getTripsCount() > trips) {
                trips = deputy.getTrips().getTripsCount();
                name = deputy.getName();
            }
        }
        System.out.println("<------------------------------------------------------------------>");
        System.out.println("Najwięcej wyjazdów: " + name + " " + trips + " wyjazdy.");
        System.out.println("<------------------------------------------------------------------>");
    }

    private void getDeputiesFromItalianTrip(Parliament parliament) throws IOException, InterruptedException {
        getTripsInfo(parliament);

        List<Deputy> deputies = parliament.getCadenceDeputiesList();
        System.out.println("<------------------------------------------------------------------>");
        System.out.println("Posłowie we Włoszech:");

        deputies.stream().filter(d -> d.getTrips().hasBeenInItaly()).forEach(d -> System.out.println(d.getName()));
        System.out.println("<------------------------------------------------------------------>");
    }

    private void getDeputyWithLongestTrips(Parliament parliament) throws IOException, InterruptedException {
        getTripsInfo(parliament);

        String name = "";
        int days = 0;

        for (Deputy deputy : parliament.getCadenceDeputiesList()) {
            if (deputy.getTrips().getLongestTripDaysCount() > days) {
                days = deputy.getTrips().getLongestTripDaysCount();
                name = deputy.getName();
            }
        }
        System.out.println("<------------------------------------------------------------------>");
        System.out.println("Najdłuższą podróż odbył: " + name + " " + days + " dni.");
        System.out.println("<------------------------------------------------------------------>");
    }

    private void getDeputyWithMostExpensiveTrip(Parliament parliament) throws IOException, InterruptedException {
        getTripsInfo(parliament);

        String name = "";
        Double cost = 0.0;

        for (Deputy deputy : parliament.getCadenceDeputiesList()) {
            if (deputy.getTrips().getMostExpensiveTripsCost() > cost) {
                cost = deputy.getTrips().getMostExpensiveTripsCost();
                name = deputy.getName();
            }
        }
        System.out.println("<------------------------------------------------------------------>");
        System.out.println("Najdroższa podróż: " + name + " " + decimalFormat.format(cost) + " zł.");
        System.out.println("<------------------------------------------------------------------>");
    }

    private void getCadenceAverage(Parliament parliament) throws IOException, InterruptedException {
        Double sum = 0.0;
        List<Thread> threads = parliament.getCadenceDeputiesList()
                .stream().map(d -> new Thread(new JsonSpending(d))).collect(Collectors.toCollection(LinkedList::new));

        threads.forEach(Thread::start);

        try {
            for (Thread t : threads)
                t.join();
        } catch (InterruptedException err) {
            throw new InterruptedException("Something went wrong in multithreading");
        }

        for (Deputy d : parliament.getCadenceDeputiesList()) {
            for (Spending s : d.getSpending())
                sum += s.getSpendingSum();
        }


        int count = parliament.getCadenceDeputiesList().size();
        System.out.println("<------------------------------------------------------------------>");
        System.out.println("Srednia suma wydatków :" + decimalFormat.format(sum / count) + "zł.");
        System.out.println("<------------------------------------------------------------------>");
    }
}
