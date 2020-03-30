package ly.bsagar.bakingapp.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import ly.bsagar.bakingapp.POJO.Ingredient;
import ly.bsagar.bakingapp.POJO.Recipie;
import ly.bsagar.bakingapp.R;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeAdapterViewHolder> {

    ArrayList<Recipie> recipies ;
    private RecipeAdapterClickHandler recipeAdapterClickHandler;

    public interface RecipeAdapterClickHandler {
        void onClick(int position);
    }

    public RecipeAdapter(RecipeAdapterClickHandler recipeAdapterClickHandler){
        this.recipeAdapterClickHandler = recipeAdapterClickHandler;
    }

    public void setContent(ArrayList<Recipie> recipies) {

        this.recipies = recipies;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public RecipeAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new RecipeAdapterViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recipie_item,parent,false)) ;
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeAdapterViewHolder holder, int position) {
        holder.titleTV.setText(recipies.get(position).getTitle());
        holder.servingTV.append(recipies.get(position).getServing());
        if (!recipies.get(position).getImageURL().isEmpty()){
            Picasso.get().load(recipies.get(position).getImageURL()).into(holder.imageView);
        } else {
            holder.imageView.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return recipies.size();
    }




    public class RecipeAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView titleTV;
        TextView servingTV;
        ImageView imageView;
        public RecipeAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTV = itemView.findViewById(R.id.title);
            servingTV = itemView.findViewById(R.id.serving);
            imageView = itemView.findViewById(R.id.recipie_image);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            recipeAdapterClickHandler.onClick(this.getLayoutPosition());

        }
    }


}
