package com.udacity.sandwichclub.utils;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private static String mainName;
    private static List<String> alsoKnownAsArray;
    private static String placeOfOrigin;
    private static String description;
    private static String image;
    private static List<String> ingredientsArray;


    public static Sandwich parseSandwichJson(String json) {
        //TODO 1: implement the JSON parsing
        //Parsing the JSON

        //checking if the json object isn't null
        if (json != null) {

            try {
                JSONObject sandwichesDetails = new JSONObject(json);

                //Name
                JSONObject name = sandwichesDetails.getJSONObject("name");

                //main name
                mainName = name.getString("mainName");


                //alsoKnownAs
                alsoKnownAsArray = new ArrayList<>();

                JSONArray alsoKnownAs = name.getJSONArray("alsoKnownAs");
                for(int i=0; i< alsoKnownAs.length(); i++){
                    alsoKnownAsArray.add(alsoKnownAs.getString(i));
                }
                //

                //Place Of Origin
                placeOfOrigin = sandwichesDetails.getString("placeOfOrigin");
                //

                //Description
                description = sandwichesDetails.getString("description");
                //

                //Image
                image = sandwichesDetails.getString("image");
                //

                //Ingredients
                ingredientsArray = new ArrayList<>();

                JSONArray ingredients = sandwichesDetails.getJSONArray("ingredients");
                for(int i=0; i<ingredients.length(); i++){
                    ingredientsArray.add(ingredients.getString(i));
                }
                //

            //Handling ERRORS
            } catch (JSONException e) {
                //parsing [FAILED]
                Log.e("JsonUtils", "YOU HAD ONE JOB! ONE JOB!");
                e.printStackTrace();
            }

            catch (Exception e) {
                //Catch all errors
                e.printStackTrace();
            }

        }

        Sandwich sandwich = new Sandwich(mainName,alsoKnownAsArray,placeOfOrigin,description,image,ingredientsArray);
        return sandwich;
    }
}
