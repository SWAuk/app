package uk.co.swa.swapp.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Spinner;

import java.util.List;

import uk.co.swa.swapp.God;
import uk.co.swa.swapp.R;
import uk.co.swa.swapp.model.Competition;
import uk.co.swa.swapp.model.CompetitionType;
import uk.co.swa.swapp.store.AppStore;

public class CompetitionActivity extends AppCompatActivity {

    AppStore appStore;
    Spinner typeSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_competition);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.appStore = God.getInstance().getAppStore();

        List<CompetitionType> competitionTypeList = this.appStore.getCompetitionTypes();

        Intent intent = getIntent();

        typeSpinner = (Spinner) findViewById(R.id.typeSpinner);
        SwaObjectAdapter competitionTypeAdapter = new SwaObjectAdapter(this,
                android.R.layout.simple_list_item_1, competitionTypeList);

        typeSpinner.setAdapter(competitionTypeAdapter);

        long competitionID = intent.getLongExtra("competitionID", -1);
        if (competitionID == -1) {
            setTitle("Add Competition");
        } else {
            setTitle("Edit Competition");

            Competition competition = this.appStore.getCompetition(competitionID);
            if (competition != null) {
                typeSpinner.setSelection(competitionTypeAdapter.getPosition(
                        competition.getCompetitionType()));
            } else {
                Log.e(getLocalClassName(), "competitionID " + competitionID + " not found in store.");
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_done, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.action_done:
                this.saveCompetition();
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void saveCompetition() {
        Log.i(this.getLocalClassName(), "saveCompetition(): ");
    }

}
