package agh.cs.lab8;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arek on 2016-12-01.
 */
public class Constitution {

     private String title = "KONSTYTUCJA \nRZECZYPOSPOLITEJ POLSKIEJ";
     List<Chapter> chapters = new ArrayList<>();

     public String getTitle(){
          return this.title;
     }
}
