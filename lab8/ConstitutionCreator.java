package agh.cs.lab8;

import java.util.List;

/**
 * Created by Arek on 2016-12-02.
 */
public class ConstitutionCreator {
    public void joinChapterToList(int chNumber, List<Article> articles, List<Chapter> chapters){
        chapters.add(new Chapter(chNumber, articles));
    }

    public void joinArticleToList(int artNumber, List<Article> articles, StringBuilder text){
        articles.add(new Article(artNumber, text.toString()));
    }

}
