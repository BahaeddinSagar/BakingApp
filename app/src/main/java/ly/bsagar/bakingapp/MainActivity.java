package ly.bsagar.bakingapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.parceler.Parcels;

import java.util.ArrayList;

import ly.bsagar.bakingapp.Adapters.RecipeAdapter;
import ly.bsagar.bakingapp.POJO.Recipie;
import ly.bsagar.bakingapp.databinding.ActivityMainBinding;
import ly.bsagar.bakingapp.utilities.JsonUtili;

public class MainActivity extends AppCompatActivity implements RecipeAdapter.RecipeAdapterClickHandler {
    ActivityMainBinding binding;
    ArrayList<Recipie> recipies = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        try {

            recipies = JsonUtili.ParseRecipie(new JSONArray(JsonUtili.JSONDATA));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        RecipeAdapter adapter = new RecipeAdapter(this);
        adapter.setContent(recipies);
        binding.recipeList.setLayoutManager(new LinearLayoutManager(this));
        binding.recipeList.setAdapter(adapter);

    }

    @Override
    public void onClick(int position) {
        Intent intent = new Intent(this, basicActivity.class);
        intent.putExtra("recipie", Parcels.wrap(recipies.get(position)));
        intent.putExtra("position",position);
        startActivity(intent);
    }
}
