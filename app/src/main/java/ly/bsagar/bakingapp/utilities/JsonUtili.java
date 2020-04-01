package ly.bsagar.bakingapp.utilities;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import ly.bsagar.bakingapp.POJO.Ingredient;
import ly.bsagar.bakingapp.POJO.Recipie;
import ly.bsagar.bakingapp.POJO.Step;

public class JsonUtili {

    public static JSONArray jsonArray;

    public static ArrayList<Recipie> ParseRecipie(JSONArray jsonArray) throws JSONException {
        int len = jsonArray.length();
        ArrayList<Recipie> recipies = new ArrayList<>();
        for (int i=0 ; i<len;i++){
            recipies.add(makeRecipie(jsonArray.getJSONObject(i)));
        }
        return recipies;
    }

    private static Recipie makeRecipie(JSONObject jsonObject) throws JSONException {
        return new Recipie(jsonObject.getString("id"),jsonObject.getString("name"),jsonObject.getString("servings"),jsonObject.getString("image"));
    }

    public static ArrayList<Ingredient> ParseIngredient(JSONArray jsonArray) throws JSONException {
        int len = jsonArray.length();
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        for (int i=0 ; i<len;i++){
            ingredients.add(makeIngredient(jsonArray.getJSONObject(i)));
        }
        return ingredients;
    }
    private static Ingredient makeIngredient(JSONObject jsonObject) throws JSONException {
        return new Ingredient(jsonObject.getString("ingredient"),jsonObject.getString("measure"),jsonObject.getDouble("quantity"));
    }
    public static ArrayList<Step> ParseSteps(JSONArray jsonArray) throws JSONException {
        int len = jsonArray.length();
        ArrayList<Step> steps = new ArrayList<>();
        for (int i=0 ; i<len;i++){
            steps.add(makeStep(jsonArray.getJSONObject(i)));
        }
        return steps;
    }
    private static Step makeStep(JSONObject jsonObject) throws JSONException {
        return new Step(jsonObject.getString("shortDescription"),jsonObject.getString("id"),jsonObject.getString("description"),
                jsonObject.getString("thumbnailURL"),jsonObject.getString("videoURL"));
    }



}
