package agh.cs.lab9;

import java.util.HashMap;

/**
 * Created by Arek on 2017-01-05.
 */
public class Spending {
    private HashMap<Integer, Long> spendingMap;
    private int year;
    private long spendingSum;
    private SpendingTitles titles;

    Spending(int year, Long sum, SpendingTitles titles){
        this.year = year;
        this.spendingSum = sum;
        this.titles = titles;
    }

    void addNewSpending(String title, String value){
        int key = this.titles.addNewTitle(title);

        
    }

    int getYear(){
        return this.year;
    }

    long getSpendingSum(){
        return this.spendingSum;
    }

    SpendingTitles getTitles(){
        return this.titles;
    }
}
