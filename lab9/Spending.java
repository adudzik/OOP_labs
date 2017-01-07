package agh.cs.lab9;

import java.util.HashMap;

/**
 * Created by Arek on 2017-01-05.
 */
public class Spending {
    private HashMap<Integer, Double> spendingMap = new HashMap<>();
    private int year;
    private Double spendingSum;
    private SpendingTitles titles = new SpendingTitles();

    Spending(int year){
        this.year = year;
        this.spendingSum = 0.0;
    }

    void addNewSpending(String title, String value){
        int key = this.titles.addNewTitle(title);
        this.spendingMap.put(key, Double.valueOf(value));
        System.out.println("tu");
        this.spendingSum += Double.valueOf(value);
        System.out.println("tu");
    }

    int getYear(){
        return this.year;
    }

    Double getSpendingSum(){
        return this.spendingSum;
    }

    SpendingTitles getTitles(){
        return this.titles;
    }
}
