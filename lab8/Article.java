package agh.cs.lab8;

/**
 * This class stores a whole article
 * with article number and chapter subtitle
 * Created by Arek on 2016-12-01.
 */
class Article {
    private int articleNumber;
    private String chapterSubtitle;
    private String articlePoints;

    Article(int n, String s, String t) {
        this.articleNumber = n;
        this.articlePoints = s;
        this.chapterSubtitle = t;
    }


    int getArticleNumber() {
        return this.articleNumber;
    }

    String getArticlePoints() {
        return this.articlePoints;
    }

    String getChapterSubtitle() {
        return this.chapterSubtitle;
    }
}
