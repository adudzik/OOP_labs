package agh.cs.lab8;

import java.io.IOException;

/**
 * This is a main class
 * Created by Arek on 2016-12-01.
 */
public class ConstitutionSystem {
    public static void main(String[] args) {
        try {
            new ConstitutionPrinter().print(args);
        } catch (IllegalArgumentException err) {
            System.out.println(err.getMessage());
        } catch (IOException err) {
            System.out.println(err);
        }
    }
}
