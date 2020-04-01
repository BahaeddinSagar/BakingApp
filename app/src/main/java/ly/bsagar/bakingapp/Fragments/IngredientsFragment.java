package ly.bsagar.bakingapp.Fragments;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
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
import ly.bsagar.bakingapp.BakeWidget;
import ly.bsagar.bakingapp.BakingViewModel;
import ly.bsagar.bakingapp.POJO.Ingredient;
import ly.bsagar.bakingapp.R;
import ly.bsagar.bakingapp.databinding.FragmentIngredientsBinding;
import ly.bsagar.bakingapp.databinding.FragmentStepsBinding;
import ly.bsagar.bakingapp.utilities.JsonUtili;

public class IngredientsFragment extends Fragment {
    BakingViewModel model;
    ArrayList<Ingredient> ingredients;
    FragmentIngredientsBinding binding;
    String recipieID;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentIngredientsBinding.inflate(inflater);
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(IngredientsFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });

        // in case of tablet, hide the showSteps button since steps are already displayed
        if(getResources().getBoolean(R.bool.isTablet)){
            binding.fab.setVisibility(View.GONE);
        }
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();
        model = new ViewModelProvider(getActivity()).get(BakingViewModel.class);
        binding.ingredientContains.setText(model.getRecipieName()+" "+getString(R.string.consists_of));
        int recipieID = Integer.parseInt(model.getRecipieID());
        try {
            ingredients = JsonUtili.ParseIngredient(JsonUtili.jsonArray.getJSONObject(recipieID).getJSONArray("ingredients"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        IngredientAdapter adapter = new IngredientAdapter();
        adapter.setContent(ingredients);
        binding.ingredientsView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.ingredientsView.setAdapter(adapter);
        updateWidger(getActivity().getApplicationContext(),model.getRecipieName(),ingredients);
    }
    // this shows the title of the selected recipie in the home screen widget
    private void updateWidger(Context context, String title, ArrayList<Ingredient> ingredients) {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(context, BakeWidget.class));
        ArrayList<String> strings = new ArrayList<>();
        BakeWidget.updateAllWidgers(context,appWidgetManager,appWidgetIds,title);
    }
}
