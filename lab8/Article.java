package agh.cs.lab8;

/**
 * Created by Arek on 2016-12-01.
 */
public class Article {
    private int articleNumber;
    private String articlePoints;

    public Article(int n, String s){
        this.articleNumber = n;
        this.articlePoints = s;
    }

    public int getArticleNumber(){
        return this.articleNumber;
    }

    public String getArticlePoints(){
        return this.articlePoints;
    }
}
