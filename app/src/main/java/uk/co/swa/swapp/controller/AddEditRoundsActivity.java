package uk.co.swa.swapp.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import uk.co.swa.swapp.God;
import uk.co.swa.swapp.R;
import uk.co.swa.swapp.model.Competition;
import uk.co.swa.swapp.model.Round;
import uk.co.swa.swapp.store.AppStore;

public class AddEditRoundsActivity extends AppCompatActivity {

    AppStore appStore;
    Competition competition;
    int roundNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_heats);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        appStore = God.getInstance().getAppStore();

        Intent intent = getIntent();

        long competitionID = intent.getLongExtra("competitionID", -1);
        if (competitionID != -1) {

            competition = appStore.getCompetition(competitionID);
            roundNumber = intent.getIntExtra("roundNumber", -1);

            if (roundNumber == -1) {
                // Error
            }

            setTitle(competition.getEvent().getEventName());
            actionBar.setSubtitle("Add Round");

        } else {
            int roundID = intent.getIntExtra("roundID", -1);
            if (roundID != -1) {
                Round round = appStore.getRound(roundID);
            } else {
                // Error
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_done, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.action_done:
                addRound();
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void addRound() {
        String durationStr = ((TextView)findViewById(R.id.durationEditText)).getText().toString();
        int duration = Integer.parseInt(durationStr);
        Round round = new Round(competition, roundNumber, duration, false);
        appStore.addRound(round);
    }
}
