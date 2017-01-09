package agh.cs.lab9;



import java.io.IOException;
import java.text.DecimalFormat;
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

    public void calculate(java.lang.String[] args) throws IOException{
        OptionParser userOptions = new OptionParser().getUserOptions(args);
        Parliament p = new JsonDeputy().getAllDeputies(userOptions.getCadence());

        switch(userOptions.getOption()){
            case DeputySpending:
                double d = this.calculateSingleDeputyAllSpending(p, userOptions.getName());
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

    private List<Spending> setSingleDeputySpending(Parliament parliament, String deputyName) throws IOException{
        Deputy deputy = new Deputy();
        for(Deputy d : parliament.getCadenceDeputiesList()) {
            if (d.getName().equals(deputyName)){
                deputy = d;
                break;
            }
        }

        if(deputy.getName() == null)
            throw new IllegalArgumentException("This deputy " + deputyName + " does not exist!");

        deputy.setSpending(new JsonSpending().getDeputySpending(deputy.getId()));
        return deputy.getSpending();
    }

    private Double calculateSingleDeputyAllSpending(Parliament parliament, String deputyName) throws IOException{
       List<Spending> deputySpending = setSingleDeputySpending(parliament,deputyName);
       Double result = 0.0;
       for(Spending spending : deputySpending){
           result += spending.getSpendingSum();
       }

       System.out.println(deputyName + " " + decimalFormat.format(result) + "zł");
       return result;
    }

    private void getSingleDeputyRepairsSpending(Parliament parliament, String deputyName) throws IOException{
        List<Spending> deputySpending = setSingleDeputySpending(parliament, deputyName);
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

    private void getCadenceAverage(Parliament parliament) throws IOException {
        Double average = 0.0;

        for(Deputy d : parliament.getCadenceDeputiesList()){
           average += this.calculateSingleDeputyAllSpending(parliament, d.getName());
        }
        int count = parliament.getCadenceDeputiesList().size();
        System.out.println("Srednia suma wydatków :" + decimalFormat.format(average/count) + "zł.");
    }
}
