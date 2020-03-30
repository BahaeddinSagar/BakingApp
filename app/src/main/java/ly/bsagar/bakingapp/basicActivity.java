package ly.bsagar.bakingapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import org.parceler.Parcels;

import ly.bsagar.bakingapp.POJO.Recipie;

public class basicActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Recipie recipie = Parcels.unwrap(getIntent().getParcelableExtra("recipie"));
        long position =  getIntent().getIntExtra("position", -1);
        BakingViewModel model = new ViewModelProvider(this).get(BakingViewModel.class);
        model.setRecipieID(String.valueOf(position));
        model.setRecipieName(recipie.getTitle());
        setTitle(recipie.getTitle());


    }

}
