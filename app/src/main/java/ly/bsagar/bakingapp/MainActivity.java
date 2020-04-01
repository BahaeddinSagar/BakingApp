package ly.bsagar.bakingapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

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
    String REQUESTURL = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @Override
    protected void onResume() {
        super.onResume();

        RequestQueue queue = Volley.newRequestQueue(this);

        JsonArrayRequest request =  new JsonArrayRequest(Request.Method.GET, REQUESTURL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                populateUI(response);
                JsonUtili.jsonArray = response;
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(MainActivity.this, "Error Fetching Data", Toast.LENGTH_SHORT).show();
            }
        });

        queue.add(request);

    }

    private void populateUI(JSONArray response) {
        try {
            recipies = JsonUtili.ParseRecipie(response);
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
