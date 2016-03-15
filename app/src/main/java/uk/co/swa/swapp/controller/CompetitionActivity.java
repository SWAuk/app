package uk.co.swa.swapp.controller;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import uk.co.swa.swapp.R;

public class CompetitionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_competition);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = (Intent) getIntent();
        Spinner typeSpinner = (Spinner) findViewById(R.id.typeSpinner);
        EditText lengthEditText = (EditText) findViewById(R.id.lengthEditText);
        Spinner heatSpinner = (Spinner) findViewById(R.id.heatSpinner);

        int requestCode = intent.getIntExtra("requestCode", -1);
        if (requestCode == 0) {
            setTitle("Add Competition");
        } else if (requestCode == 1) {
            setTitle("Edit Competition");
            int competitionType = intent.getIntExtra("competitionType", -1);
            int competitionLength = intent.getIntExtra("competitionLength", -1);
            int competitionHeat = intent.getIntExtra("competitionHeat", -1);

            typeSpinner.setSelection(competitionType);
            lengthEditText.setText(competitionLength);
            heatSpinner.setSelection(competitionHeat);

        } else {
            Log.e(getLocalClassName(), "Unknown 'requestCode' received from " +
                    getCallingActivity().getShortClassName());
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
