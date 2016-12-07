package agh.cs.lab8;

import java.util.ArrayList;
import java.util.List;

/**
 * This class stores a chapter
 * with chapter number, chapter title and list of articles
 * Created by Arek on 2016-12-01.
 */
class Chapter {
    private int chapterNumber;
    private String chapterTitle;
    private List<Article> articles = new ArrayList<>();

    Chapter(int n, String t, List<Article> l) {
        this.chapterNumber = n;
        this.articles = l;
        this.chapterTitle = t;
    }

    List<Article> getArticles() {
        return this.articles;
    }

    int getChapterNumber() {
        return this.chapterNumber;
    }

    int getFirstArticleNumber() {
        return this.articles.get(0).getArticleNumber();
    }

    String getChapterTitle() {
        return this.chapterTitle;
    }
}
