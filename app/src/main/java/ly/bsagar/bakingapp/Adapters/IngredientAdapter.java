package ly.bsagar.bakingapp.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ly.bsagar.bakingapp.POJO.Ingredient;
import ly.bsagar.bakingapp.R;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.IngredientAdapterViewHolder> {

    ArrayList<Ingredient> ingredients ;


    public IngredientAdapter(){

    }

    public void setContent(ArrayList<Ingredient> ingredients ) {
        this.ingredients = ingredients;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public IngredientAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new IngredientAdapterViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.ingredient_item,parent,false)) ;
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientAdapterViewHolder holder, int position) {
        holder.titleTV.setText(ingredients.get(position).getIngredient());
        holder.servingTV.setText(ingredients.get(position).getQuantity()+" "+ingredients.get(position).getMeasure());
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }


    public class IngredientAdapterViewHolder extends RecyclerView.ViewHolder  {
        TextView titleTV;
        TextView servingTV;
        public IngredientAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTV = itemView.findViewById(R.id.ingredient);
            servingTV = itemView.findViewById(R.id.measure);

        }


    }
}
