package agh.cs.lab9;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arek on 2016-12-16.
 */
public class Parliament {
    private int cadenceNumber;
    private List<Deputy> cadenceDeputies = new ArrayList<>();

    Parliament(int num, List<Deputy> deputies){
        this.cadenceNumber = num;
        this.cadenceDeputies = deputies;
    }

    int getCadenceNumber(){
        return this.cadenceNumber;
    }

    List<Deputy> getCadenceDeputies(){
        return this.cadenceDeputies;
    }
}
