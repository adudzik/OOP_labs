package agh.cs.lab9;

import java.io.IOException;

/**
 * Created by Arek on 2016-12-16.
 */
public class SpendingSystem {
    public static void main(String[] args) {
    try {
        new OptionParser().getUserOptions(args);
        Parliament p = new JsonDeputy().getAllDeputies();
        System.out.println(p.getCadenceDeputiesList().size());

        for(Deputy dep : p.getCadenceDeputiesList()){
            System.out.println(dep.getId() + " " + dep.getName());
        }
    } catch(IllegalArgumentException err){
        System.out.println(err.getMessage());
    } catch (IOException err){
        System.out.println(err.getMessage());
    }
    }
}
