package agh.cs.lab8;

import java.io.IOException;
import java.util.regex.PatternSyntaxException;

/**
 * Created by Arek on 2016-12-01.
 */
public class ConstitutionSystem {
    public static void main(String[] args) {
        try {
            new ConstitutionPrinter().printWhatUserWant(args);
            //} catch (IOException err) {
            //  System.out.println(err + " bufferedReader throws a kind off IOException");
        } catch (PatternSyntaxException err) {
            System.out.println(err + " pattern has a kind of syntax error");
        } catch (IllegalArgumentException err) {
            System.out.println(err.getMessage());
        }
    }
}
