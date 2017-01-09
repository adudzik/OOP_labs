package agh.cs.lab9;

import java.util.regex.Pattern;

/**
 * Created by Arek on 2016-12-16.
 */
public class OptionParser {
    private Options option;
    private int cadence;
    private String name;

    Options getOption() {
        return this.option;
    }

    int getCadence() {
        return this.cadence;
    }

    String getName(){
        return this.name;
    }

    OptionParser getUserOptions(String[] args) {
        return new OptionParser().parse(args);
    }

    private OptionParser parse(String[] args) throws IllegalArgumentException {
        if (args.length < 3 || args.length > 7)
            throw new IllegalArgumentException("You type incorrect number of arguments: " + args.length + " (min=3 and max=7)");

        OptionParser result = new OptionParser();
        int i = 0;

        do {
            if (!(result.findOption(args[i], i)))
                throw new IllegalArgumentException("Find illegal option in this place: " + args[i]);
            i++;
        } while (i < args.length);

        return result;
    }

    private boolean findOption(String arg, int i) throws IllegalArgumentException {
        if (i == 0) {
            Pattern pattern = Pattern.compile("-c");
            return pattern.matcher(arg).matches();
        }

        if (i == 1) {
            Pattern pattern = Pattern.compile("[0-9]");
            if (pattern.matcher(arg).find()) {
                int a = Integer.valueOf(arg);
                if(a==7 || a==8){
                    this.cadence = a;
                    return true;
                } else throw new IllegalArgumentException("This cadence does not exist. You can only choose 7 or 8, but you type: " + a);
            } else return false;
        }

        if (i == 2) {
            Pattern pattern = Pattern.compile("-o");
            return pattern.matcher(arg).matches();
        }

        if (i==3) {
            Pattern pattern = Pattern.compile("[0-9]");
            if (pattern.matcher(arg).matches()) {
                int a = Integer.valueOf(arg);
                if(a >= 1 && a < 8){
                    this.option = setOption(a);
                    return true;
                } else throw new IllegalArgumentException("This option does not exist. You can only choose from 1 to 7, but you type: " + a);
            } else return false;
        }

        if (i==4 || i==5 || i==6){
            Pattern pattern = Pattern.compile("[a-z[A-Z]]");
            if (pattern.matcher(arg).find()) {
                if(i==4)
                    this.name = arg;
                else
                    this.name = this.name + " " + arg;
                return true;
            } else return false;
        }
        return false;
    }

    Options setOption(int i){
        Options result;
        switch(i) {
            case 1:
                result = Options.DeputySpending;
                break;

            case 2:
                result = Options.RepairsSpending;
                break;

            case 3:
                result = Options.CadenceAverage;
                break;

            case 4:
                result = Options.MostForeignTrips;
                break;

            case 5:
                result = Options.MostDayOnTrip;
                break;

            case 6:
                result = Options.MostExpensiveTrip;
                break;

            case 7:
                result = Options.ItalyJourney;
                break;

            default:
                result = null;
                break;
        }
        return result;
    }
}

