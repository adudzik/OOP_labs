package agh.cs.lab9;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.lang.*;

/**
 * Created by Arek on 2016-12-16.
 */
public class SpendingCalculator {
    private static DecimalFormat decimalFormat = new DecimalFormat();

    SpendingCalculator(){
        decimalFormat.setMaximumFractionDigits(2);
    }

    public void calculate(java.lang.String[] args) throws IOException, InterruptedException{
        OptionParser userOptions = new OptionParser().getUserOptions(args);
        Parliament p = this.createParliament(userOptions.getCadence());

        switch(userOptions.getOption()){
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

    private Parliament createParliament(int cadenceNumber) throws InterruptedException{
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
        } catch(InterruptedException err){
              throw new InterruptedException("The thread has been interrupted");
        }


        for(int i=1; i<pages.size(); i++)
            deputies.addAll(pages.get(i));

        return new Parliament(cadenceNumber, deputies);
    }

    private void setSingleDeputySpending(Parliament parliament, String deputyName) throws IOException{
        Deputy deputy = parliament.getDeputy(deputyName);
        if(deputy.getName() == null)
            throw new IllegalArgumentException("This deputy " + deputyName + " does not exist!");

        new JsonSpending(deputy).getDeputySpending();
    }

    private void calculateSingleDeputyAllSpending(Parliament parliament, String deputyName) throws IOException{
        setSingleDeputySpending(parliament,deputyName);
        Deputy deputy = parliament.getDeputy(deputyName);
        Double result = 0.0;
        List<Spending> deputySpending = deputy.getSpending();

        for(Spending spending : deputySpending){
            result += spending.getSpendingSum();
        }

        System.out.println(deputyName + " " + decimalFormat.format(result) + "zł");
    }

    private void getSingleDeputyRepairsSpending(Parliament parliament, String deputyName) throws IOException{
        setSingleDeputySpending(parliament, deputyName);
        Deputy deputy = parliament.getDeputy(deputyName);
        List<Spending> deputySpending = deputy.getSpending();
        Double result = 0.0;
        for(Spending spending: deputySpending){
            int id = spending.getTitles().getTitleID("Koszty drobnych napraw i remont\u00f3w lokalu biura poselskiego");
            if(id == -1)
                result += 0.0;
            else
                result += spending.getSpending(id);
        }

        System.out.println(deputyName + " " + decimalFormat.format(result) + "zł");
    }

    private void getDeputyWithMostTrips(Parliament parliament) throws IOException{
        new JsonTrips().getTripsInfo(parliament);

        String name = "";
        int trips = 0;

        for(Deputy deputy : parliament.getCadenceDeputiesList()){
            if(deputy.getTrips().getTripsCount() > trips){
                trips = deputy.getTrips().getTripsCount();
                name = deputy.getName();
            }
        }

        System.out.println("Najwięcej wyjazdów: " + name + " " + trips + " wyjazdy.");
    }

    private void getDeputiesFromItalianTrip(Parliament parliament) throws IOException{
        List<Deputy> deputiesInItaly = new JsonTrips().getTripsInfo(parliament).getDeputiesInItaly();

        System.out.println("Posłowie we Włoszech:");
        for(Deputy d : deputiesInItaly)
            System.out.println(d.getName());
    }

    private void getDeputyWithLongestTrips(Parliament parliament) throws IOException{
        new JsonTrips().getTripsInfo(parliament);

        String name = "";
        int days = 0;

        for(Deputy deputy : parliament.getCadenceDeputiesList()){
            if(deputy.getTrips().getLongestTripDaysCount() > days){
                days = deputy.getTrips().getLongestTripDaysCount();
                name = deputy.getName();
            }
        }

        System.out.println("Najdłuższą podróż odbył: " + name + " " + days + " dni.");
    }

    private void getDeputyWithMostExpensiveTrip(Parliament parliament) throws IOException{
        new JsonTrips().getTripsInfo(parliament);

        String name = "";
        Double cost = 0.0;

        for(Deputy deputy : parliament.getCadenceDeputiesList()){
            if(deputy.getTrips().getMostExpensiveTripsCost() > cost){
                cost = deputy.getTrips().getMostExpensiveTripsCost();
                name = deputy.getName();
            }
        }

        System.out.println("Najdroższa podróż: " + name + " " + decimalFormat.format(cost) + " zł.");
    }

    private void getCadenceAverage(Parliament parliament) throws IOException, InterruptedException {
        Double sum = 0.0;
        List<Thread> threads = new LinkedList<>();
        for(Deputy d : parliament.getCadenceDeputiesList()){
            threads.add(new Thread(new JsonSpending(d)));
        }
        threads.forEach(Thread::start);
        try {
            for (Thread t : threads)
                t.join();
        } catch(InterruptedException err){
            throw new InterruptedException("Something went wrong in multithreading");
        }

        for(Deputy d : parliament.getCadenceDeputiesList()){
            for(Spending s : d.getSpending())
                sum += s.getSpendingSum();
        }


        int count = parliament.getCadenceDeputiesList().size();
        System.out.println("Srednia suma wydatków :" + decimalFormat.format(sum/count) + "zł.");
    }
}
