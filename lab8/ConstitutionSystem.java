package agh.cs.lab8;

import java.io.IOException;

/**
 * Created by Arek on 2016-12-01.
 */
public class ConstitutionSystem {
    public static void main(String[] args) {
        try {
            Constitution constPl = new ConstitutionCreator().create("F:/konstytucja.txt");
            int l = constPl.chapters.get(0).getFirstArticleNumber();
            System.out.println(constPl.chapters.get(0).articles.get(0).getArticlePoints());
        } catch (IOException err) {
            System.out.println(err);
        }/*

        String text = new String("A");
        String regexp = "[A-Z][A-Z]";

        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(text);

        System.out.println(matcher.find());
        System.out.println(matcher.matches());
     */
    }
}
