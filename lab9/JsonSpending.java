package agh.cs.lab9;

import javax.json.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Arek on 2017-01-05.
 */
public class JsonSpending {
    private Spending spending;
    private SpendingTitles titles;

    public JsonSpending getDeputySpendings(int deputyID) throws IOException{
        return new JsonSpending().getAllSpending("https://api-v3.mojepanstwo.pl/dane/poslowie/" + deputyID + ".json?layers[]=wyjazdy");
    }

    private JsonSpending getAllSpending(String urlAddress) throws IOException{
        try{
            URL url = new URL(urlAddress);
            InputStream inputStream = url.openStream();
            JsonReader jsonReader = Json.createReader(inputStream);
            JsonObject spending = jsonReader.readObject();
            JsonArray titlesArray = spending.getJsonArray("punkty");
            JsonArray yearsArray = spending.getJsonArray("roczniki");

            for(int i=0; i<yearsArray.size(); i++)
                getYearSpending(titlesArray, yearsArray.getJsonObject(i));
            return this;
        } catch (MalformedURLException err){
            throw new MalformedURLException("There is a problem with this URL address: " + urlAddress);
        } catch (IOException err){
            throw new IOException("Can't find this URL address" + urlAddress);
        }
    }

    private void getYearSpending(JsonArray titlesArray, JsonObject yearsSpendingArray){
        JsonArray spending = yearsSpendingArray.getJsonArray("pola");

        if(spending.size() == titlesArray.size()){

        }
    }
}
