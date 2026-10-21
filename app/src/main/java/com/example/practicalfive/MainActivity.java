package com.example.practicalfive;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MUNICIPALITY =
            "com.example.practicalfive.extra.MUNICIPALITY";

    private EditText editTextMunicipality;
    private final TextProcessor textProcessor = new TextProcessor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        applySystemBarInsets();

        editTextMunicipality = findViewById(R.id.editTextMunicipality);
        editTextMunicipality.setOnEditorActionListener((textView, actionId, event) -> {
            if (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    openMunicipality();
                }
                return true;
            }

            if (actionId == EditorInfo.IME_ACTION_GO
                    || actionId == EditorInfo.IME_ACTION_DONE) {
                openMunicipality();
                return true;
            }

            return false;
        });
    }

    private void openMunicipality() {
        String municipality = textProcessor.processMunicipality(
                editTextMunicipality.getText().toString());

        if (municipality == null) {
            editTextMunicipality.setError(getString(R.string.invalid_municipality));
            editTextMunicipality.requestFocus();
            return;
        }

        editTextMunicipality.setError(null);
        Intent intent = new Intent(this, Municipality.class);
        intent.putExtra(EXTRA_MUNICIPALITY, municipality);
        startActivity(intent);
    }

    private void applySystemBarInsets() {
        View rootView = findViewById(R.id.rootMain);
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
