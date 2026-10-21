package com.example.practicalfive;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MUNICIPALITY =
            "com.example.practicalfive.extra.MUNICIPALITY";

    private EditText editTextMunicipality;
    private final TextProcessor textProcessor = new TextProcessor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
}
