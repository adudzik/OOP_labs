package agh.cs.lab9;

import java.util.List;

/**
 * Created by Arek on 2016-12-16.
 */
public class Deputy {
    private String name;
    private int id;
    private List<Spending> spending;

    Deputy(int id, String name){
        this.id = id;
        this.name = name;
    }

    String getName(){
        return this.name;
    }

    int getId(){
        return this.id;
    }

    void addSpending(Spending s){
        this.spending.add(s);
    }

    long getAllSpending(){
        long result = 0;

        for(Spending s : this.spending)
            result += s.getSpendingSum();

        return result;
    }
}
