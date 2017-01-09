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
        new SpendingCalculator().calculate(args);

        //for(Deputy d : p.getCadenceDeputiesList())
            //System.out.println(d.getName() + " " + d.getId() + " " + d.getTrips().getTripsCount());

    } catch (IOException err){
        System.out.println("bla" + err.getMessage());
    }
    }
}
