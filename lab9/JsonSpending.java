package agh.cs.lab9;

import javax.json.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Arek on 2017-01-05.
 */
public class JsonSpending {
    private List<Spending> spending = new LinkedList<>();

    public List<Spending> getDeputySpending(int deputyID) throws IOException{
        return getAllSpending("https://api-v3.mojepanstwo.pl/dane/poslowie/" + deputyID + ".json?layers[]=wydatki");
    }

    private List<Spending> getAllSpending(String urlAddress) throws IOException{
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
            return this.spending;
        } catch (MalformedURLException err){
            throw new MalformedURLException("There is a problem with this URL address: " + urlAddress);
        } catch (IOException err){
            throw new IOException("Can't find this URL address" + urlAddress);
        }
    }

    private Spending getYearSpending(JsonArray titlesArray, JsonObject yearSpending) throws IOException{
        JsonArray spendingArray = yearSpending.getJsonArray("pola");
        Spending result = new Spending(Integer.valueOf(yearSpending.getString("rok")));
        if(spendingArray.size() == titlesArray.size()){
            for(int i=0; i<spendingArray.size(); i++)
                result.addNewSpending(titlesArray.getJsonObject(i).getString("tytul"), spendingArray.getString(i));

            return result;
        } else throw new IOException("There is a problem with JSON. It more titles than costs");
    }
}