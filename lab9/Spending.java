package agh.cs.lab9;

import java.util.HashMap;

/**
 * Created by Arek on 2017-01-05.
 * <p>
 * This class stores all information about deputy spending.
 */
class Spending {
    private HashMap<Integer, Double> spendingMap = new HashMap<>();
    private int year;
    private Double spendingSum = 0.0;
    private SpendingTitles titles = new SpendingTitles();

    Spending(int year) {
        this.year = year;
        this.spendingSum = 0.0;
    }

    void addNewSpending(String title, String value) {
        int key = this.titles.addNewTitle(title);
        this.spendingMap.put(key, Double.valueOf(value));
        this.spendingSum += Double.valueOf(value);
    }

    int getYear() {
        return this.year;
    }

    Double getSpending(int id) {
        return this.spendingMap.get(id);
    }

    Double getSpendingSum() {
        return this.spendingSum;
    }

    SpendingTitles getTitles() {
        return this.titles;
    }
}
