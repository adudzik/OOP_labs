package agh.cs.lab8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;


/**
 * Created by Arek on 2016-12-01.
 */
public class FileParser {

    private String textLine;
    private ConstitutionCreator creator = new ConstitutionCreator();
    private Matcher matcher;

    public List<Chapter> parse(String filePath) throws IOException, PatternSyntaxException {
        List<Chapter> pChapters = new ArrayList<>();
        List<Article> pArticles = new ArrayList<>();

        FileReader fileReader = new FileReader(filePath);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        this.textLine = new String();

        StringBuilder articlePoints = new StringBuilder(this.textLine);
        String chtSubTitle = new String("brak");
        String chapTitle = new String("brak");

        int artCount = 0;
        int chapCount = 0;
        int isChapterTitle = 0;

        do {
            if (this.hasUselessLine()) {
                this.textLine = bufferedReader.readLine();
                continue;
            }

            if (this.findMatch("Rozdzia")) {
                this.creator.joinArticleToList(artCount, articlePoints, chtSubTitle, pArticles);
                this.creator.joinChapterToList(chapCount, chapTitle, pArticles, pChapters);
                chapCount++;

                chtSubTitle = "brak";
                isChapterTitle = 0;

                pArticles = new ArrayList<>();

                articlePoints = new StringBuilder();
            }

            if (this.findMatch("[^XI ][A-Z][A-Z]")) {
                if (isChapterTitle == 0) {
                    chapTitle = this.textLine;
                    isChapterTitle++;
                } else {
                    chtSubTitle = this.textLine;
                }
            }

            if (this.findMatch("Art. ")) {
                artCount++;
                this.creator.joinArticleToList(artCount, articlePoints, chtSubTitle, pArticles);

                articlePoints = new StringBuilder();
            }

            if (this.textLine.endsWith("-"))
                this.deleteAndConcat(bufferedReader);
            this.addAndSet(bufferedReader, articlePoints);

        } while (this.textLine != null);

        this.creator.joinArticleToList((artCount + 1), articlePoints, chtSubTitle, pArticles);
        this.creator.joinChapterToList(chapCount, chapTitle, pArticles, pChapters);

        bufferedReader.close();

        return pChapters;
    }

    private boolean hasUselessLine() {
        return (this.textLine.length() < 2 || this.findMatch("Kancelaria") || this.findMatch("2009-"));
    }

    private void addAndSet(BufferedReader bReader, StringBuilder article) throws IOException {
        article.append("\n" + this.textLine);
        this.textLine = bReader.readLine();
    }


    private boolean findMatch(String regexp) throws PatternSyntaxException {
        Pattern pattern = Pattern.compile(regexp);
        this.matcher = pattern.matcher(this.textLine);
        return this.matcher.find();
    }
    private void deleteAndConcat(BufferedReader bReader) throws IOException {
        StringBuilder sb = new StringBuilder(this.textLine);
        sb.deleteCharAt(this.textLine.length() - 1);
        this.textLine = sb.append(bReader.readLine()).toString();
    }
}
