package agh.cs.lab9;

import java.util.*;

/**
 * Created by Arek on 2017-01-07.
 */
public class Trips {
    private Double mostExpensiveTripCost = 0.0;
    private int tripsCount = 0;
    private int longestTripDaysCount = 0;

    Trips(Double tripsCost, int tripsCount, int days){
        this.mostExpensiveTripCost = tripsCost;
        this.tripsCount = tripsCount;
        this.longestTripDaysCount = days;
    }

    public Double getMostExpensiveTripsCost(){
        return this.mostExpensiveTripCost;
    }

    public int getTripsCount(){
        return this.tripsCount;
    }

    public int getLongestTripDaysCount() {
        return longestTripDaysCount;
    }
}

