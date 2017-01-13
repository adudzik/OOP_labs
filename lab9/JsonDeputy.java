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
 * <p>
 * This class implement Runnable interface.
 * It's also responsible for download one page with deputies
 */
class JsonDeputy implements Runnable {
    private static String address = "https://api-v3.mojepanstwo.pl/dane/poslowie.json";
    private static String cadenceCondition = "?conditions%5Bposlowie.kadencja%5D=";
    private static String pageCondition = "&_type=objects&page=";
    private int cadenceNumber;
    private int currentPageNumber;

    JsonDeputy(int cadenceNumber, int pageNumber) {
        this.cadenceNumber = cadenceNumber;
        this.currentPageNumber = pageNumber;
    }

    @Override
    public void run() {
        getPageJson();
    }

    List<Deputy> getPageJson() {
        List<Deputy> results = new ArrayList<>();
        String urlAddress = address + cadenceCondition + cadenceNumber + pageCondition + currentPageNumber;
        try {
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
        } catch (MalformedURLException err) {
            System.out.println("There is a problem with this URL address: " + urlAddress);
        } catch (IOException err) {
            System.out.println("It's something wrong with this JSON: " + urlAddress);
        }
        return results;
    }

    int getLastPageNumber() {
        int result = 0;
        try {
            URL url = new URL(address + cadenceCondition + cadenceNumber + pageCondition + 1);
            InputStream inputStream = url.openStream();
            JsonReader jsonReader = Json.createReader(inputStream);

            JsonObject firstPage = jsonReader.readObject();
            JsonObject links = firstPage.getJsonObject("Links");
            String lastPage = links.getString("last");

            if((int) lastPage.charAt(lastPage.length() - 2) == 1)
                result = (int) lastPage.charAt(lastPage.length() - 1) + ((int) lastPage.charAt(lastPage.length() - 2)) * 10;
            else
                result = (int) lastPage.charAt(lastPage.length() - 1);

        } catch (MalformedURLException err) {
            System.out.println("This is incorrect URL address " + address + cadenceCondition + cadenceNumber + pageCondition + 1);
            System.exit(0);
        } catch (IOException err) {
            System.out.println("It's something wrong with this JSON " + address + cadenceCondition + cadenceNumber + pageCondition + 1);
            System.out.println("Check your Internet connection.");
            System.exit(0);
        }
        return result;
    }
}
