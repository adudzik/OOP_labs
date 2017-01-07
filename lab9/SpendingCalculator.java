package agh.cs.lab9;

import java.io.IOException;
import java.util.List;

/**
 * Created by Arek on 2016-12-16.
 */
public class SpendingCalculator {
    public void calculate(String[] args) throws IOException{
        OptionParser userOptions = new OptionParser().getUserOptions(args);
        Parliament p = new JsonDeputy().getAllDeputies(userOptions.getCadence());

        switch(userOptions.getOption()){
            case DeputySpending:
                this.calculateSingleDeputyAllSpending(p,userOptions.getName());
                break;

            case RepairsSpending:
                //this.repair
                break;

            case CadenceAverage:
                break;

            case MostForeignTrips:
                break;

            case LongestTrip:
                break;

            case MostExpensiveTrip:
                break;

            case ItalyJourney:
                break;
        }
    }

    private List<Spending> setSingleDeputySpending(Parliament parliament, String deputyName) throws IOException{
        Deputy deputy = new Deputy();
        for(Deputy d : parliament.getCadenceDeputiesList()) {
            System.out.println(d.getName());
            if (d.getName().contains(deputyName)) {
                deputy = d;
                break;
            }
        }

        if(deputy.getName() == null)
            throw new IllegalArgumentException("This deputy " + deputyName + " does not exist!");

        deputy.setSpending(new JsonSpending().getDeputySpending(deputy.getId()));
        return deputy.getSpending();
    }

    private void calculateSingleDeputyAllSpending(Parliament parliament, String deputyName) throws IOException{
       List<Spending> deputySpending = setSingleDeputySpending(parliament,deputyName);
       Double result = 0.0;
       for(Spending spending : deputySpending){
           result += spending.getSpendingSum();
       }

       System.out.println(deputyName + " " + result + "zł");
    }
}
