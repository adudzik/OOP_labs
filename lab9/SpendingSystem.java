package agh.cs.lab9;

/**
 * Created by Arek on 2016-12-16.
 */
public class SpendingSystem {
    public static void main(String[] args) {
    try{
        new OptionParser().getUserOptions(args);
    } catch(IllegalArgumentException err){
        System.out.println(err.getMessage());
    }
    }
}
