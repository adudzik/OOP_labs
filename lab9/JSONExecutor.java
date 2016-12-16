package agh.cs.lab9;

import javax.json.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by Arek on 2016-12-16.
 */
public class JSONExecutor {

    void printAll

    void getJson(String next) throws IOException{
        URL url = new URL("next");

        try(InputStream inputStream = url.openStream(); JsonReader jsonReader = Json.createReader(inputStream)){
            JsonObject obj = jsonReader.readObject();
            JsonArray array = obj.getJsonArray("Dataobject");

            for(JsonObject arr : array.getValuesAs(JsonObject.class)){
                System.out.print(arr.getJsonObject("data").getString("poslowie.id") + " ");
                System.out.println(arr.getJsonObject("data").getString("poslowie.nazwa"));
            }
        }
    }
}
