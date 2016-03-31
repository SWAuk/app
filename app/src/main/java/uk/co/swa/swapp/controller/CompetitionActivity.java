package uk.co.swa.swapp.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Spinner;

import java.util.List;

import uk.co.swa.swapp.God;
import uk.co.swa.swapp.R;
import uk.co.swa.swapp.model.Competition;
import uk.co.swa.swapp.model.CompetitionType;

public class CompetitionActivity extends AppCompatActivity {

    God god;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_competition);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.god = God.getInstance();

        List<CompetitionType> competitionTypeList = this.god.getAppStore().getCompetitionTypes();

        Intent intent = getIntent();

        Spinner typeSpinner = (Spinner) findViewById(R.id.typeSpinner);
        SwaObjectAdapter competitionTypeAdapter = new SwaObjectAdapter(this,
                android.R.layout.simple_list_item_1, competitionTypeList);

        typeSpinner.setAdapter(competitionTypeAdapter);

        long competitionID = intent.getLongExtra("competitionID", -1);
        if (competitionID == -1) {
            setTitle("Add Competition");
        } else {
            Competition competition = this.god.getAppStore().getCompetition(competitionID);
            if (competition != null) {
                setTitle("Edit Competition");

                typeSpinner.setSelection(competitionTypeAdapter.getPosition(
                        competition.getCompetitionType()));

            } else {
                Log.e(getLocalClassName(), "competitionID " + competitionID + " not found in store.");
            }
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
