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
    private Map<String, Integer> cadenceDeputiesMap = new HashMap<>();

    Parliament(int num, List<Deputy> deputiesList){
        this.cadenceNumber = num;
        this.cadenceDeputiesList = deputiesList;

        for(Deputy dep : deputiesList)
            this.cadenceDeputiesMap.put(dep.getName(), dep.getId());
    }

    int getCadenceNumber(){
        return this.cadenceNumber;
    }

    Map<String, Integer> getCadenceDeputiesMap(){
        return this.cadenceDeputiesMap;
    }

    List<Deputy> getCadenceDeputiesList(){
        return this.cadenceDeputiesList;
    }
}
