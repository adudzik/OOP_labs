package agh.cs.lab9;

import java.lang.*;
import java.io.IOException;


/**
 * Created by Arek on 2016-12-16.
 * <p>
 * Main class
 */
public class SpendingSystem {
    public static void main(String[] args) {
        try {
            long start = System.nanoTime();

            new SpendingCalculator().calculate(args);

            long end = System.nanoTime();

            System.out.println("Execution time: " + (end - start) / 1000000000.0);

        } catch (IllegalArgumentException err) {
            System.out.println(err.getMessage());
            System.out.println("Correct syntax: ");
            System.out.println("-c [cadence_number] -o [option_number] [deputy_name]");
        } catch (IOException | InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
