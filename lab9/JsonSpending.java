package agh.cs.lab9;

import javax.json.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Arek on 2017-01-05.
 */
public class JsonSpending implements Runnable{
    private List<Spending> spending = new LinkedList<>();
    private Deputy deputy;
    private static String url = "https://api-v3.mojepanstwo.pl/dane/poslowie/";
    private static String layer = ".json?layers[]=wydatki";

    JsonSpending(Deputy deputy){
        this.deputy = deputy;
    }

    @Override
    public void run() {
        getDeputySpending();
    }

    //public List<Spending> getDeputySpending(){
      //  return getAllSpending("https://api-v3.mojepanstwo.pl/dane/poslowie/" + deputyID + ".json?layers[]=wydatki");
    //}

    public void getDeputySpending() {
         String urlAddress = url + this.deputy.getId() + layer;
        try{
            URL url = new URL(urlAddress);
            InputStream inputStream = url.openStream();
            JsonReader jsonReader = Json.createReader(inputStream);
            JsonObject jsonSpendingObject = jsonReader.readObject();
            JsonObject spendingObject = jsonSpendingObject.getJsonObject("layers");
            JsonObject spendingObject2 = spendingObject.getJsonObject("wydatki");
            JsonArray titlesArray = spendingObject2.getJsonArray("punkty");
            JsonArray yearsArray = spendingObject2.getJsonArray("roczniki");
            for(int i=0; i<yearsArray.size(); i++) {
                this.spending.add(getYearSpending(titlesArray, yearsArray.getJsonObject(i)));
            }
            inputStream.close();
            this.deputy.setSpending(this.spending);
        } catch (MalformedURLException err){
             System.out.println("There is a problem with this URL address: " + urlAddress);

        } catch (IOException err){
             System.out.println(err.getMessage());
             new JsonSpending(this.deputy).getDeputySpending();
        }
    }

    private Spending getYearSpending(JsonArray titlesArray, JsonObject yearSpending) {
        JsonArray spendingArray = yearSpending.getJsonArray("pola");
        Spending result = new Spending(Integer.valueOf(yearSpending.getString("rok")));
        int tArraySize = titlesArray.size();
        for(int i=0; i<spendingArray.size(); i++) {
            if (i < tArraySize)
                result.addNewSpending(titlesArray.getJsonObject(i).getString("tytul"), spendingArray.getString(i));
            else result.addNewSpending("no title", spendingArray.getString(i));
        }
            return result;

    }
}
