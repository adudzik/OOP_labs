package agh.cs.lab9;

/**
 * Created by Arek on 2016-12-16.
 */
public class Deputy {
    private String name;
    private int id;

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
}
