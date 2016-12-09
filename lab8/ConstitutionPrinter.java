package agh.cs.lab8;

import java.io.IOException;
import java.util.List;

/**
 * This class prints chapters or articles
 * from object form.
 * Created by Arek on 2016-12-03.
 */
class ConstitutionPrinter {
    void print(String[] args) throws IOException {
        OptionParser oP = new OptionParser().getUserOptions(args);
        Constitution constitution = new FileParser().parse(oP.getPath());

        if (oP.getPrintType() == 'r')
            this.printChapter(oP.getContentToPrint().get(0), constitution);
        else if (oP.getContentToPrint().size() == 1)
            this.printSingleArticle(oP.getContentToPrint().get(0), constitution);
        else
            this.printArticles(oP.getContentToPrint(), constitution);
    }

    private void printChapter(Integer num, Constitution constitution) {
        Chapter chapter = constitution.getChapter(num);
        List<Article> articles = chapter.getArticles();
        String prevTitle = articles.get(0).getChapterSubtitle();

        System.out.print(chapter.getChapterTitle());

        if (!(prevTitle.equals(" ")))
            System.out.print("\n" + prevTitle);

        for (Article art : articles) {
            System.out.print(art.getArticlePoints());

            if (!(prevTitle.equals(art.getChapterSubtitle()) && !(prevTitle.equals(" ")))) {
                prevTitle = art.getChapterSubtitle();
                System.out.print("\n" + prevTitle);
            }
        }
    }

    private void printSingleArticle(Integer num, Constitution constitution) {
        if (num == 0) {
            printChapter(num, constitution);
            return;
        }

        int chaptNumber;

        for (Chapter chapter : constitution.getChaptersList()) {
            if (chapter.getFirstArticleNumber() > num) {
                chaptNumber = chapter.getChapterNumber() - 1;

                for (Article art : constitution.getChapter(chaptNumber).getArticles()) {
                    if (art.getArticleNumber() == num) {
                        System.out.println(art.getArticlePoints());
                        break;
                    }
                }
                break;
            }
        }
    }

    private void printArticles(List<Integer> content, Constitution constitution) {
        if(content.get(0) < content.get(1)) {
            for (int i = content.get(0); i <= content.get(1); i++)
                printSingleArticle(i, constitution);
        }
        else {
            for (int i = content.get(0); i >= content.get(1); i--)
                printSingleArticle(i, constitution);
        }
    }
}
