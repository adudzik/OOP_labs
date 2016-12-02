package agh.cs.lab8;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arek on 2016-12-01.
 */
public class Chapter {
    private int chapterNumber;
    private String chapterTitle;
    List<Article> articles = new ArrayList<>();

    public Chapter(int n, String t, List<Article> l){
        this.chapterNumber = n;
        this.articles = l;
        this.chapterTitle = t;
    }

    @Override
    public String toString(){
        String result = new String();

        for(Article article : this.articles){
           result = result + " " + article.getArticlePoints();
        }
        return result;
    }
    public int getChapterNumber() {
        return this.chapterNumber;
    }

    public int getFirstArticleNumber(){
        return this.articles.get(0).getArticleNumber();
    }

    public String getChapterTitle(){
        return this.chapterTitle;
    }
}
