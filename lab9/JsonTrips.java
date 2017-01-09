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
public class JsonTrips {
    private List<Deputy> deputiesInItaly = new LinkedList<>();

    public List<Deputy> getDeputiesInItaly(){
        return this.deputiesInItaly;
    }

    public JsonTrips getTripsInfo(Parliament parliament) throws IOException{

        for(Deputy d : parliament.getCadenceDeputiesList()){
            d.setTrips(getDeputyTrip(d));
        }
        return this;
    }

    private Trips getDeputyTrip(Deputy deputy) throws IOException {
        try {
            int id = deputy.getId();

            URL url = new URL("https://api-v3.mojepanstwo.pl/dane/poslowie/" + id + ".json?layers[]=wyjazdy");
            InputStream inputStream = url.openStream();
            JsonReader jsonReader = Json.createReader(inputStream);
            JsonObject jsonTripsObject = jsonReader.readObject();

            Double cost = jsonTripsObject.getJsonObject("data").getJsonNumber("poslowie.wartosc_wyjazdow").doubleValue();
            int tripsCount = jsonTripsObject.getJsonObject("data").getJsonNumber("poslowie.liczba_wyjazdow").intValue();


            if (tripsCount == 0 || cost == 0.0)
                return new Trips(0.0, tripsCount, 0);

            JsonObject layersObject = jsonTripsObject.getJsonObject("layers");
            JsonArray tripsArray = layersObject.getJsonArray("wyjazdy");
            Double expensiveTripCost = 0.0;
            int days = 0;
            int k = 0;
            for (int i = 0; i < tripsArray.size(); i++) {
                days += Integer.valueOf(tripsArray.getJsonObject(i).getString("liczba_dni"));
                if (Double.valueOf(tripsArray.getJsonObject(i).getString("koszt_suma")) > expensiveTripCost)
                    expensiveTripCost = Double.valueOf(tripsArray.getJsonObject(i).getString("koszt_suma"));

                if (k == 0 && tripsArray.getJsonObject(i).getString("kraj").equals("Włochy")) {
                    k++;
                    this.deputiesInItaly.add(deputy);
                }
            }

            inputStream.close();
            return new Trips(expensiveTripCost, tripsCount, days);
        } catch (ClassCastException e){
            System.out.println("This deputy " + deputy.getName() + " doesn't have 'wydatki' array!");
            return new Trips(0.0, 0, 0);
        } catch (IOException e) {
            throw new IOException("blabla");
        }
    }
}
