package ly.bsagar.bakingapp.Fragments;

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

import ly.bsagar.bakingapp.Adapters.StepsAdapter;
import ly.bsagar.bakingapp.BakingViewModel;
import ly.bsagar.bakingapp.POJO.Step;
import ly.bsagar.bakingapp.R;
import ly.bsagar.bakingapp.databinding.FragmentStepsBinding;
import ly.bsagar.bakingapp.utilities.JsonUtili;

public class StepsFragment extends Fragment implements StepsAdapter.StepAdapterClickHandler {
    FragmentStepsBinding binding;
    ArrayList<Step> steps;
    BakingViewModel model;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentStepsBinding.inflate(inflater);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        steps = new ArrayList<>();
        model = new ViewModelProvider(getActivity()).get(BakingViewModel.class);
        int recipieID = Integer.parseInt(model.getRecipieID());
        try {
            steps = JsonUtili.ParseSteps(new JSONArray(JsonUtili.JSONDATA).getJSONObject(recipieID).getJSONArray("steps"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        StepsAdapter adapter = new StepsAdapter(this);
        adapter.setContent(steps);
        binding.stepRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.stepRecycler.setAdapter(adapter);
    }

    @Override
    public void onClick(int position) {
        model.setStep(steps.get(position));
        // behavior is different in case of Tablet or phone due to master/detail view.
        if (getResources().getBoolean(R.bool.isTablet)){
            // show fragment in spare space in large display
            DetailStepFragment fragment = new DetailStepFragment();
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frameLayout, fragment)
                    .commit();
            return;
        }
        // replace existing fragment with the navigation controller
        NavHostFragment.findNavController(this)
                .navigate(R.id.action_SecondFragment_to_ThridFragment);
    }
}
