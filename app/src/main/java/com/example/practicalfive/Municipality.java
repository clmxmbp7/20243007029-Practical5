package com.example.practicalfive;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

public class Municipality extends AppCompatActivity {

    private int websiteStringResource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_municipality);
        applySystemBarInsets();

        TextProcessor textProcessor = new TextProcessor();
        String municipality = textProcessor.processMunicipality(
                getIntent().getStringExtra(MainActivity.EXTRA_MUNICIPALITY));

        if (municipality == null) {
            Toast.makeText(this, R.string.missing_municipality, Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        displayMunicipality(municipality);
    }

    private void displayMunicipality(String municipality) {
        TextView textViewName = findViewById(R.id.textViewMunicipalityName);
        TextView textViewInformation = findViewById(R.id.textViewInformation);
        ImageView imageViewMunicipality = findViewById(R.id.imageViewMunicipality);
        MaterialButton buttonVisitSite = findViewById(R.id.buttonVisitSite);

        int informationIndex;
        int imageResource;
        int colourResource;

        switch (municipality) {
            case "Beijing":
                informationIndex = 0;
                imageResource = R.drawable.beijing;
                colourResource = R.color.municipality_beijing;
                websiteStringResource = R.string.website_beijing;
                break;
            case "Shanghai":
                informationIndex = 1;
                imageResource = R.drawable.shanghai;
                colourResource = R.color.municipality_shanghai;
                websiteStringResource = R.string.website_shanghai;
                break;
            case "Chongqing":
                informationIndex = 2;
                imageResource = R.drawable.chongqing;
                colourResource = R.color.municipality_chongqing;
                websiteStringResource = R.string.website_chongqing;
                break;
            case "Tianjin":
                informationIndex = 3;
                imageResource = R.drawable.tianjin;
                colourResource = R.color.municipality_tianjin;
                websiteStringResource = R.string.website_tianjin;
                break;
            default:
                Toast.makeText(this, R.string.missing_municipality, Toast.LENGTH_LONG).show();
                finish();
                return;
        }

        String[] municipalityInformation =
                getResources().getStringArray(R.array.municipalities_array);
        int municipalityColour = ContextCompat.getColor(this, colourResource);
        int contrastingTextColour = ContextCompat.getColor(
                this,
                municipality.equals("Chongqing")
                        ? R.color.color_text_dark
                        : R.color.color_text_light);

        textViewName.setText(municipality);
        textViewName.setBackgroundColor(municipalityColour);
        textViewName.setTextColor(contrastingTextColour);

        textViewInformation.setText(municipalityInformation[informationIndex]);
        textViewInformation.setTextColor(municipalityColour);

        imageViewMunicipality.setImageResource(imageResource);
        imageViewMunicipality.setContentDescription(
                getString(R.string.municipality_image_description, municipality));

        buttonVisitSite.setBackgroundTintList(ColorStateList.valueOf(municipalityColour));
        buttonVisitSite.setTextColor(contrastingTextColour);
        buttonVisitSite.setContentDescription(
                getString(R.string.official_website_description, municipality));
        buttonVisitSite.setOnClickListener(view -> openOfficialWebsite());
    }

    private void openOfficialWebsite() {
        Uri website = Uri.parse(getString(websiteStringResource));
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, website);

        try {
            startActivity(browserIntent);
        } catch (ActivityNotFoundException exception) {
            Toast.makeText(this, R.string.no_browser_available, Toast.LENGTH_LONG).show();
        }
    }

    private void applySystemBarInsets() {
        View rootView = findViewById(R.id.rootMunicipality);
        int initialLeft = rootView.getPaddingLeft();
        int initialTop = rootView.getPaddingTop();
        int initialRight = rootView.getPaddingRight();
        int initialBottom = rootView.getPaddingBottom();

        ViewCompat.setOnApplyWindowInsetsListener(rootView, (view, windowInsets) -> {
            Insets systemBars = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars());
            view.setPadding(
                    initialLeft + systemBars.left,
                    initialTop + systemBars.top,
                    initialRight + systemBars.right,
                    initialBottom + systemBars.bottom);
            return windowInsets;
        });

        WindowCompat.getInsetsController(getWindow(), rootView)
                .setAppearanceLightStatusBars(true);
        WindowCompat.getInsetsController(getWindow(), rootView)
                .setAppearanceLightNavigationBars(true);
        ViewCompat.requestApplyInsets(rootView);
    }
}
