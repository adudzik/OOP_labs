package agh.cs.lab9;

import java.lang.*;
import java.io.IOException;
import java.util.List;


/**
 * Created by Arek on 2016-12-16.
 */
public class SpendingSystem {
    public static void main(String[] args) {
    try {
        long start = System.nanoTime();
        new SpendingCalculator().calculate(args);
        // OptionParser userOptions = new OptionParser().getUserOptions(args);
        //Parliament p = new JsonDeputy().getAllDeputies(userOptions.getCadence());

        long end = System.nanoTime();
        System.out.println((end - start) / 1000000000.0);
        //for(Deputy d : p.getCadenceDeputiesList())
        //System.out.println(d.getName() + " " + d.getId() + " " + d.getTrips().getTripsCount());
    } catch (IllegalArgumentException err){
        System.out.println(err.getMessage());
        System.out.println("Correct syntax: ");
        System.out.println("-c [cadence_number] -o [option_number] [deputy_name]");
    } catch (IOException err) {
        System.out.println(err.getMessage());
    } catch (InterruptedException err){
        System.out.println(err.getMessage());
    }

    }
}
