package agh.cs.lab9;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Arek on 2016-12-16.
 */
public class Parliament {
    private int cadenceNumber;
    private List<Deputy> cadenceDeputiesList = new ArrayList<>();


    Parliament(int num, List<Deputy> deputiesList){
        this.cadenceNumber = num;
        this.cadenceDeputiesList = deputiesList;
    }

    int getCadenceNumber(){
        return this.cadenceNumber;
    }

    Deputy getDeputy(String name){
        for(Deputy d : this.cadenceDeputiesList){
            if(d.getName().equals(name))
                return d;
        }
        return null;
    }

    List<Deputy> getCadenceDeputiesList(){
        return this.cadenceDeputiesList;
    }
}
