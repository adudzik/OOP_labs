package agh.cs.lab8;

import java.util.ArrayList;
import java.util.List;

/**
 * This class contains all chapters
 * Created by Arek on 2016-12-01.
 */
public class Constitution {

     private String title = "KONSTYTUCJA \nRZECZYPOSPOLITEJ POLSKIEJ";
     private List<Chapter> chapters = new ArrayList<>();

     Chapter getChapter(int i){
          return this.chapters.get(i);
     }

     void setChapters(List<Chapter> l){
          this.chapters = l;
     }

}
