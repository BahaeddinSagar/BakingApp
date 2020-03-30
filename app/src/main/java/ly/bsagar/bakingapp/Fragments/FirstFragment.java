package ly.bsagar.bakingapp.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import ly.bsagar.bakingapp.Adapters.IngredientAdapter;
import ly.bsagar.bakingapp.POJO.Ingredient;
import ly.bsagar.bakingapp.R;
import ly.bsagar.bakingapp.databinding.FragmentFirstBinding;
import ly.bsagar.bakingapp.BakingViewModel;
import ly.bsagar.bakingapp.utilities.JsonUtili;

public class FirstFragment extends Fragment {
    BakingViewModel model;
    ArrayList<Ingredient> ingredients;
    FragmentFirstBinding binding;
    String recipieID;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentFirstBinding.inflate(inflater);
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });



        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();
        model = new ViewModelProvider(getActivity()).get(BakingViewModel.class);
        int recipieID = Integer.parseInt(model.getRecipieID());
        try {

            ingredients = JsonUtili.ParseIngredient(new JSONArray(JsonUtili.JSONDATA).getJSONObject(recipieID).getJSONArray("ingredients"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        IngredientAdapter adapter = new IngredientAdapter();
        adapter.setContent(ingredients);
        binding.ingredientsView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.ingredientsView.setAdapter(adapter);

    }
}
