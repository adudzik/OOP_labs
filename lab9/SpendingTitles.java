package agh.cs.lab9;

import java.util.HashMap;

/**
 * Created by Arek on 2017-01-05.
 */
public class SpendingTitles {
    HashMap<String, Integer> titlesMap = new HashMap<>();

    int addNewTitle(String title){
        if(titlesMap.containsKey(title))
            return this.titlesMap.get(title);

        else{
            this.titlesMap.put(title, this.titlesMap.size());
            return this.titlesMap.size();
        }
    }

    int getTitleID(String title){
        if(titlesMap.containsKey(title))
            return this.titlesMap.get(title);
        else
            return -1;
    }


}
