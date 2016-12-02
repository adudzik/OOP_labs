package agh.cs.lab8;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by Arek on 2016-12-01.
 */
public class ConstitutionSystem {
    public static void main(String[] args) {
        try {
            Constitution constPl = new FileParser().parse("F:/konstytucja.txt");
            //System.out.println(constPl.chapters.size());
            int l = constPl.chapters.get(13).getFirstArticleNumber();
            System.out.println(constPl.chapters.get(13).articles.get(243-l+1).getArticlePoints());
            for(int i=0; i < constPl.chapters.size(); i++) {
                int n = constPl.chapters.get(i).getFirstArticleNumber();
                System.out.println(n);
            }
        } catch (IOException err) {
            System.out.println(err);
        }
        /*
        String text = new String("ASSS");
        String regexp = "[A-Z][A-Z] | [a-z][a-z] | [0-7]";

        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(text);

        System.out.println(matcher.find());
        System.out.println(matcher.matches());
        */
    }
}
