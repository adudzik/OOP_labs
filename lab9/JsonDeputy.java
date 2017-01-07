package agh.cs.lab9;

import javax.json.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Arek on 2016-12-16.
 */
public class JsonDeputy {

    public Parliament getAllDeputies(int cadenceNumber) throws IOException{
        String address = "https://api-v3.mojepanstwo.pl/dane/poslowie.json";
        String cadenceCondition = "?conditions%5Bposlowie.kadencja%5D=";
        String pageCondition = "&_type=objects&page=";

        List<Deputy> deputies = this.getDeputyJson(address + cadenceCondition + cadenceNumber + pageCondition + 1);

        int i = 2;
        do {
            deputies.addAll(deputies.size()-1, this.getDeputyJson(address + cadenceCondition + 8 + pageCondition + i));
            i++;
        } while (i <=15);

        return new Parliament(7, deputies);
    }

    private List<Deputy> getDeputyJson(String urlAddress) throws IOException {
        List<Deputy> results = new ArrayList<>();
        try{
            URL url = new URL(urlAddress);
            InputStream inputStream = url.openStream();
            JsonReader jsonReader = Json.createReader(inputStream);
            JsonObject deputies = jsonReader.readObject();
            JsonArray array = deputies.getJsonArray("Dataobject");
            results.addAll(array.getValuesAs(JsonObject.class)
                    .stream()
                    .map(arr -> new Deputy(Integer.valueOf(arr.getJsonObject("data").getString("poslowie.id")), arr.getJsonObject("data").getString("poslowie.nazwa")))
                    .collect(Collectors.toList()));

            inputStream.close();
        } catch (MalformedURLException err){
            throw new MalformedURLException("There is a problem with this URL address: " + urlAddress);
        } catch (IOException err){
            throw new IOException("Can't find this URL address" + urlAddress);
        }
        return results;
    }
}
