package aru.kotlinbasic.utils;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Converter {

    public static Object convertResponseData(JsonObject response, Class destinationClass) {
        return jsonToObject(response, destinationClass, "data");
    }

    public static Object jsonToObject(JsonObject response, Class destinationClass, String name) {
        if (destinationClass.equals(String.class)) {

            Log.e("Error Msh",""+name);
            // It's an object
            return response.getAsJsonObject("message").get("success").getAsString();
        } else {
            Object intervention = response.get(name);
            Log.e("Error Msh  sddsd",""+response.get(name));

            if (intervention instanceof JsonArray) {
                // It's an array
                return jsonArrayToObject(destinationClass, (JsonArray) intervention);
            } else if (intervention instanceof JsonObject) {
                // It's an object
                return jsonObjectToClass(((JsonObject) intervention).getAsJsonObject(), destinationClass);
            } else {
                return null;
            }
        }
    }

    private static Object jsonArrayToObject(Class destinationClass, JsonArray intervention) {
        List<Object> resObjects = new ArrayList<>();
        JsonArray jsonElements = intervention.getAsJsonArray();
        for (int i = 0; i < jsonElements.size(); i++) {
            resObjects.add(jsonObjectToClass(jsonElements.get(i).getAsJsonObject(), destinationClass));
        }
        return resObjects;
    }

    public static Object jsonObjectToClass(JSONObject data, Class destinationClass) {
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonElement mJson = parser.parse(String.valueOf(data));
        return gson.fromJson(mJson, destinationClass);
    }

    public static Object jsonObjectToClass(JsonObject data, Class destinationClass) {
        Gson gson = new Gson();
        return gson.fromJson(data, destinationClass);
    }
}
