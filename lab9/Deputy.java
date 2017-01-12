package agh.cs.lab9;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Arek on 2016-12-16.
 * <p>
 * This class contains all information of single deputy.
 */
class Deputy {
    private String name;
    private int id;
    private List<Spending> spending = new LinkedList<>();
    private Trips trips;

    Deputy(int id, String name) {
        this.id = id;
        this.name = name;
    }

    String getName() {
        return this.name;
    }

    int getId() {
        return this.id;
    }

    void setSpending(List<Spending> s) {
        this.spending = s;
    }

    List<Spending> getSpending() {
        return this.spending;
    }

    Trips getTrips() {
        return this.trips;
    }

    void setTrips(Trips trips) {
        this.trips = trips;
    }
}
