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
 * This class transform file text to object form
 * and delete useless lines from file
 * Created by Arek on 2016-12-01.
 */
class FileParser {

    private String textLine;

    Constitution parse(String filePath) throws IOException, PatternSyntaxException {
        Constitution result = new Constitution();
        List<Chapter> pChapters = new ArrayList<>();
        List<Article> pArticles = new ArrayList<>();

        FileReader fileReader = new FileReader(filePath);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        this.textLine = new String();
        StringBuilder articlePoints = new StringBuilder(this.textLine);
        StringBuilder chSubTitle = new StringBuilder();
        StringBuilder chapTitle = new StringBuilder();
        String prevSubTitle = new String();

        int artCount = 0;
        int chapCount = 0;
        int isChapterTitle = 0;

        do {
            if (this.hasUselessLine()) {
                this.textLine = bufferedReader.readLine();
                continue;
            }

            if (this.findMatch("Rozdzia")) {
                this.joinArticleToList(artCount, articlePoints, chSubTitle.toString(), pArticles);
                this.joinChapterToList(chapCount, chapTitle.toString(), pArticles, pChapters);
                chapCount++;

                chSubTitle = new StringBuilder("");
                chapTitle = new StringBuilder(this.textLine);
                isChapterTitle = 0;

                pArticles = new ArrayList<>();


                articlePoints = new StringBuilder();
                this.textLine = bufferedReader.readLine();
                continue;

            }

            if (this.findMatch("[^XI ][A-Z][A-Z]")) {
                if (isChapterTitle == 0) {
                    chapTitle.append("\n");
                    chapTitle.append(this.textLine);
                    isChapterTitle++;
                } else {

                    chSubTitle = new StringBuilder(this.textLine);

                }
                this.textLine = bufferedReader.readLine();
                continue;
            }

            if (this.findMatch("Art. ")) {
                artCount++;
                this.joinArticleToList(artCount, articlePoints, chSubTitle.toString(), pArticles);

                articlePoints = new StringBuilder();
            }

            if (this.textLine.endsWith("-"))
                this.deleteAndConcat(bufferedReader);

            this.addAndSet(bufferedReader, articlePoints);

        } while (this.textLine != null);

        this.joinArticleToList((artCount + 1), articlePoints, chSubTitle.toString(), pArticles);
        this.joinChapterToList(chapCount, chapTitle.toString(), pArticles, pChapters);

        bufferedReader.close();
        result.setChapters(pChapters);
        return result;
    }

    private boolean hasUselessLine() {
        return (this.textLine.length() < 2 || this.findMatch("Kancelaria") || this.findMatch("2009-"));
    }

    private void addAndSet(BufferedReader bReader, StringBuilder article) throws IOException {
        article.append("\n");
        article.append(this.textLine);
        this.textLine = bReader.readLine();
    }


    private boolean findMatch(String regexp) throws PatternSyntaxException {
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(this.textLine);
        return matcher.find();
    }

    private void deleteAndConcat(BufferedReader bReader) throws IOException {
        StringBuilder sb = new StringBuilder(this.textLine);
        sb.deleteCharAt(this.textLine.length() - 1);
        this.textLine = sb.append(bReader.readLine()).toString();
    }

    private void joinChapterToList(int chNumber, String chTitle, List<Article> articles, List<Chapter> chapters) {
        chapters.add(new Chapter(chNumber, chTitle, articles));
    }

    private void joinArticleToList(int artNumber, StringBuilder text, String title, List<Article> articles) {
        articles.add(new Article(artNumber, text.toString(), title));
    }
}