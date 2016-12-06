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

        //System.out.println(constitution.getChapter(2).getArticles().get(8).getChapterSubtitle());

        if (oP.getPrintType() == 'a')
            this.printArticles(oP.getContentToPrint(), constitution);
        else
            this.printChapter(oP.getContentToPrint().get(0), constitution);
    }

    private void printChapter(Integer num, Constitution constitution) {
        Chapter chapter = constitution.getChapter(num);
        List<Article> articles = chapter.getArticles();
        String prevTitle = articles.get(0).getChapterSubtitle();

        System.out.print(chapter.getChapterTitle());
        if (!(prevTitle.equals("")))
            System.out.print("\n" + prevTitle);

        for (Article art : articles) {
            System.out.print(art.getArticlePoints());
            if (!(prevTitle.equals(art.getChapterSubtitle()) && prevTitle != "")) {
                prevTitle = art.getChapterSubtitle();
                System.out.print("\n" + prevTitle);
            }
        }
    }

    private void printArticles(List<Integer> content, Constitution constitution) {

    }
}
