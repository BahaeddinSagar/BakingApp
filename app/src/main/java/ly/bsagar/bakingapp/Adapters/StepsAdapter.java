package ly.bsagar.bakingapp.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ly.bsagar.bakingapp.POJO.Step;
import ly.bsagar.bakingapp.R;

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.StepAdapterViewHolder> {

ArrayList<Step> steps;

    private StepAdapterClickHandler stepAdapterClickHandler;

    public interface StepAdapterClickHandler{
        void onClick(int position);
    }

    public StepsAdapter(StepAdapterClickHandler stepAdapterClickHandler) {
        this.stepAdapterClickHandler = stepAdapterClickHandler;
    }

    @NonNull
    @Override
    public StepsAdapter.StepAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new StepsAdapter.StepAdapterViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.step_item, parent, false)) ;
    }

    @Override
    public void onBindViewHolder(@NonNull StepsAdapter.StepAdapterViewHolder holder, int position) {
        holder.stepTitleTV.setText(steps.get(position).getTitle());
    }

    public void setContent(ArrayList<Step> steps) {
        this.steps = steps;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return steps.size();
    }

    public class StepAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView stepTitleTV;
        public StepAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            stepTitleTV = itemView.findViewById(R.id.step_title1);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            stepAdapterClickHandler.onClick(this.getLayoutPosition());

        }
    }
}
