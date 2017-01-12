package agh.cs.lab9;


/**
 * Created by Arek on 2017-01-07.
 * <p>
 * This class stores all information about deputy's trips.
 */
class Trips {
    private Double mostExpensiveTripCost = 0.0;
    private int tripsCount = 0;
    private int longestTripDaysCount = 0;
    private boolean beenInItaly = false;

    Trips(Double tripsCost, int tripsCount, int days) {
        this.mostExpensiveTripCost = tripsCost;
        this.tripsCount = tripsCount;
        this.longestTripDaysCount = days;
    }

    Trips(Double tripsCost, int tripsCount, int days, boolean beenInItaly) {
        this.mostExpensiveTripCost = tripsCost;
        this.tripsCount = tripsCount;
        this.longestTripDaysCount = days;
        this.beenInItaly = beenInItaly;
    }

    boolean hasBeenInItaly() {
        return this.beenInItaly;
    }

    Double getMostExpensiveTripsCost() {
        return this.mostExpensiveTripCost;
    }

    int getTripsCount() {
        return this.tripsCount;
    }

    int getLongestTripDaysCount() {
        return longestTripDaysCount;
    }
}

