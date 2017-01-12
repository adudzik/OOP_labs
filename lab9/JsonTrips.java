package agh.cs.lab9;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Arek on 2017-01-07.
 */
public class JsonTrips implements Runnable {
    private Deputy deputy;
    private static String urlAdd = "https://api-v3.mojepanstwo.pl/dane/poslowie/";
    private static String urlAdd2 = ".json?layers[]=wyjazdy";

    JsonTrips(Deputy deputy){
        this.deputy = deputy;
    }


    /*public JsonTrips getTripsInfo(Parliament parliament) throws IOException{

        for(Deputy d : parliament.getCadenceDeputiesList()){
            d.setTrips(getDeputyTrip());
        }
        return this;
    }*/
    @Override
    public void run(){
        getDeputyTrip();
    }

    private void getDeputyTrip(){
        int id = this.deputy.getId();
        try {
            URL url = new URL(urlAdd + id + urlAdd2);
            InputStream inputStream = url.openStream();
            JsonReader jsonReader = Json.createReader(inputStream);
            JsonObject jsonTripsObject = jsonReader.readObject();

            Double cost = jsonTripsObject.getJsonObject("data").getJsonNumber("poslowie.wartosc_wyjazdow").doubleValue();
            int tripsCount = jsonTripsObject.getJsonObject("data").getJsonNumber("poslowie.liczba_wyjazdow").intValue();


            if (tripsCount == 0 || cost == 0.0) {
                this.deputy.setTrips(new Trips(0.0, tripsCount, 0));
                return;
            }

            JsonObject layersObject = jsonTripsObject.getJsonObject("layers");
            JsonArray tripsArray = layersObject.getJsonArray("wyjazdy");

            Double expensiveTripCost = 0.0;
            int days = 0;
            int k = 0;
            boolean beenInItaly = false;
            for (int i = 0; i < tripsArray.size(); i++) {
                days += Integer.valueOf(tripsArray.getJsonObject(i).getString("liczba_dni"));
                if (Double.valueOf(tripsArray.getJsonObject(i).getString("koszt_suma")) > expensiveTripCost)
                    expensiveTripCost = Double.valueOf(tripsArray.getJsonObject(i).getString("koszt_suma"));

                if (k == 0 && tripsArray.getJsonObject(i).getString("kraj").equals("Włochy")) {
                    k++;
                    beenInItaly = true;
                }
            }

            inputStream.close();
            this.deputy.setTrips(new Trips(expensiveTripCost, tripsCount, days, beenInItaly));
        } catch (ClassCastException e){
            System.out.println("This deputy " + deputy.getName() + " doesn't have 'wyjazdy' array!");
            this.deputy.setTrips(new Trips(0.0, 0, 0));
            //new JsonTrips(this.deputy).getDeputyTrip();

        } catch (IOException e) {
            System.out.println(e.getMessage());
            new JsonTrips(this.deputy).getDeputyTrip();
        }
    }
}
