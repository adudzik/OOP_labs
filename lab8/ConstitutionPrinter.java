package agh.cs.lab8;


import java.util.List;

/**
 * Created by Arek on 2016-12-03.
 */
public class ConstitutionPrinter {
    public void printWhatUserWant (String[] args){
        OptionParser oP = new OptionParser().getUserOptions(args);

        List<Integer> list = oP.getArticlesToPrint();
        char type = oP.getPrintType();
        String path = oP.getPath();

        System.out.println(list.get(0));
        System.out.println(type);
        System.out.println(path);
    }
}
