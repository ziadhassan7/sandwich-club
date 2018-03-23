package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    private  TextView is_knownAs;
    private static TextView origin_is;
    private static TextView description_is;
    private static TextView ingredients_is;

    List<String> knownAs_list;
    List<String> ingredients_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView imageIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(imageIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {
        //TODO 2: Populate the UI
        is_knownAs = findViewById(R.id.is_knownAs);
        origin_is = findViewById(R.id.origin_is);
        description_is = findViewById(R.id.description_is);
        ingredients_is = findViewById(R.id.ingredients_is);

        knownAs_list = sandwich.getAlsoKnownAs();
        if(knownAs_list != null){
            for(int i=0; i<knownAs_list.size(); i++){
                is_knownAs.append(knownAs_list.get(i) +"      ");
            }
        }if(sandwich.getAlsoKnownAs().isEmpty()){
            is_knownAs.setText("-------");
        }

        if(sandwich.getPlaceOfOrigin()!=null) {
            origin_is.setText(sandwich.getPlaceOfOrigin());
        }if(sandwich.getPlaceOfOrigin().isEmpty()){
            origin_is.setText("-------");
        }

        if(sandwich.getDescription()!=null){
            description_is.setText(sandwich.getDescription());
        }

        ingredients_list = sandwich.getIngredients();
        if(ingredients_list != null){
            for(int i=0; i<ingredients_list.size(); i++){
                ingredients_is.append(ingredients_list.get(i) +"      ");
            }
        }
    }
}
