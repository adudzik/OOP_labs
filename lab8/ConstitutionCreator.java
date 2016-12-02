package agh.cs.lab8;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

/**
 * Created by Arek on 2016-12-02.
 */
public class ConstitutionCreator {

    public Constitution create(String pathFile) throws IOException{
        Constitution result = new Constitution();
        result.chapters = new FileParser().parse(pathFile);
        return result;
    }

    public void joinChapterToList(int chNumber, String chTitle, List<Article> articles, List<Chapter> chapters){
        chapters.add(new Chapter(chNumber, chTitle, articles));
    }

    public void joinArticleToList(int artNumber, StringBuilder text, String title, List<Article> articles){
        articles.add(new Article(artNumber, text.toString(), title));
    }
}
