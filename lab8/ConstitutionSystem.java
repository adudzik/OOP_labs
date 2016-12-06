package agh.cs.lab8;

import java.io.IOException;
import java.util.regex.PatternSyntaxException;

/**
 * This is a main class
 * Created by Arek on 2016-12-01.
 */
public class ConstitutionSystem {
    public static void main(String[] args) {
        try {
            new ConstitutionPrinter().print(args);
        } catch (IOException err) {
            System.out.println(err);
        } catch (PatternSyntaxException err) {
            System.out.println(err + " pattern has a kind of syntax error");
        } catch (IllegalArgumentException err) {
            System.out.println(err.getMessage());
        }
    }
}
