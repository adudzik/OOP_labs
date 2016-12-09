package agh.cs.lab8;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * This is a main class
 * Created by Arek on 2016-12-01.
 */
public class ConstitutionSystem {
    public static void main(String[] args) {
        try {
            new ConstitutionPrinter().print(args);

        } catch (NumberFormatException err){
            System.out.println("You type value that is not a decimal number!");
        } catch (IllegalArgumentException err) {
            System.out.println(err.getMessage());
        } catch (FileNotFoundException err){
            System.out.println("File " + args[0] + " is not exist!");
        } catch (IOException err) {
            System.out.println(err.getMessage());
        }
    }
}
