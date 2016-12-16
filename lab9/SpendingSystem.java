package agh.cs.lab9;

import java.io.IOException;

/**
 * Created by Arek on 2016-12-16.
 */
public class SpendingSystem {
    public static void main(String[] args) {
    try {
        new JSONExecutor().getJson();
        //new OptionParser().getUserOptions(args);
    } catch(IOException err){
    } catch(IllegalArgumentException err){
        System.out.println(err.getMessage());
    }
    }
}
