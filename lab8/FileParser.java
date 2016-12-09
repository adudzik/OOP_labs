package agh.cs.lab8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class transform file text to object form
 * and delete useless lines from file
 * Created by Arek on 2016-12-01.
 */
class FileParser {
    private String textLine;

    Constitution parse(String filePath) throws IOException {
        Constitution result = new Constitution();
        List<Chapter> pChapters = new ArrayList<>();
        List<Article> pArticles = new ArrayList<>();

        FileReader fileReader = new FileReader(filePath);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        this.textLine = "";
        StringBuilder articlePoints = new StringBuilder(this.textLine);
        StringBuilder chSubTitle = new StringBuilder();
        StringBuilder chapTitle = new StringBuilder();


        int artCount = 0;
        int chapCount = 0;
        boolean isChapterTitle = true;


        do {
            if (this.hasUselessLine()) {
                this.textLine = bufferedReader.readLine();
                continue;
            }

            if (this.findMatch("Rozdzia")) {
                pArticles.add(new Article(artCount, articlePoints.toString(), chSubTitle.toString()));
                pChapters.add(new Chapter(chapCount, chapTitle.toString(), pArticles));
                chapCount++;

                chSubTitle = new StringBuilder("");
                chapTitle = new StringBuilder(this.textLine);
                isChapterTitle = false;

                pArticles = new ArrayList<>();

                articlePoints = new StringBuilder();
                this.textLine = bufferedReader.readLine();
                continue;

            }

            if (this.findMatch("[^XI ][A-Z][A-Z]")) {
                if (!isChapterTitle) {
                    chapTitle.append("\n");
                    chapTitle.append(this.textLine);
                    isChapterTitle = true;
                } else {
                    chSubTitle = new StringBuilder(this.textLine);
                }
                this.textLine = bufferedReader.readLine();
                continue;
            }

            if (this.findMatch("Art. ")) {
                if (!(articlePoints.toString().isEmpty()))
                    pArticles.add(new Article(artCount, articlePoints.toString(), chSubTitle.toString()));
                artCount++;
                articlePoints = new StringBuilder();
            }

            if (this.textLine.endsWith("-")) {
                this.deleteAndConcat(bufferedReader, articlePoints);
                continue;
            }

            this.addAndSet(bufferedReader, articlePoints);

        } while (this.textLine != null);

        artCount++;
        pArticles.add(new Article(artCount, articlePoints.toString(), chSubTitle.toString()));
        pChapters.add(new Chapter(chapCount, chapTitle.toString(), pArticles));

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


    private boolean findMatch(String regexp) {
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(this.textLine);
        return matcher.find();
    }

    private void deleteAndConcat(BufferedReader bReader, StringBuilder article) throws IOException {
        StringBuilder that = new StringBuilder(this.textLine);
        that.deleteCharAt(this.textLine.length() - 1);

        String next = bReader.readLine();
        String[] partedLine = next.split(" ", 2);
        that.append(partedLine[0]);
        article.append("\n");
        article.append(that);
        if(partedLine.length ==2)
            this.textLine = partedLine[1];
        else
            this.textLine = bReader.readLine();
    }
}