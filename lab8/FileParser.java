package agh.cs.lab8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arek on 2016-12-01.
 */
public class FileParser {

    public Constitution parse(String filePath) throws IOException {
        Constitution result = new Constitution();
        List<Chapter> pChapters = new ArrayList<>();
        List<Article> pArticles = new ArrayList<>();


        FileReader fileReader = new FileReader(filePath);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String textLine = new String();

        StringBuilder articlePoints = new StringBuilder(textLine);

        int artCount = 0;
        int chapCount = 0;

        do {
            if(textLine.length() < 2){
                textLine = bufferedReader.readLine();
                continue;
            }

            if (textLine.contains("Kancelaria") || textLine.contains("2009-")) {
                textLine = bufferedReader.readLine();
                continue;
            }
            if (textLine.contains("Rozdzia")) {
                Article a = new Article(artCount, articlePoints.toString());
                pArticles.add(a);
                Chapter c = new Chapter(chapCount, pArticles);
                pChapters.add(c);

                chapCount++;

                pArticles = new ArrayList<Article>();

                articlePoints = new StringBuilder();
                articlePoints.append("\n" + textLine);
                textLine = bufferedReader.readLine();

                continue;
            } else {
                if (textLine.contains("Art. ")) {
                    artCount++;
                    Article a = new Article(artCount, articlePoints.toString());
                    pArticles.add(a);

                    articlePoints = new StringBuilder();
                    articlePoints.append("\n" + textLine);
                    textLine = bufferedReader.readLine();
                    continue;
                } else {
                }
                articlePoints.append("\n" + textLine);
                textLine = bufferedReader.readLine();
            }

        } while (textLine != null);
        result.chapters = pChapters;
        bufferedReader.close();
        return result;
    }
}