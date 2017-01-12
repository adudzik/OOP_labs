package agh.cs.lab9;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arek on 2016-12-16.
 * <p>
 * This class contains all information about one cadence:
 * -cadence number
 * -list of deputies
 */
class Parliament {
    private List<Deputy> cadenceDeputiesList = new ArrayList<>();

    Parliament(List<Deputy> deputiesList) {
        this.cadenceDeputiesList = deputiesList;
    }

    Deputy getDeputy(String name) {
        for (Deputy d : this.cadenceDeputiesList) {
            if (d.getName().equals(name))
                return d;
        }
        return null;
    }

    List<Deputy> getCadenceDeputiesList() {
        return this.cadenceDeputiesList;
    }
}
