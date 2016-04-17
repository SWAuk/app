package uk.co.swa.swapp.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import uk.co.swa.swapp.God;
import uk.co.swa.swapp.R;
import uk.co.swa.swapp.model.Competition;
import uk.co.swa.swapp.store.AppStore;

public class CompetitionActivity extends AppCompatActivity {

    AppStore appStore;
    String[] competitionMenuItems;
    Competition competition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_competition);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        this.appStore = God.getInstance().getAppStore();

        Intent intent = getIntent();

        ListView competitionMenuListView = (ListView) findViewById(R.id.competitionMenuListView);
        this.competitionMenuItems = getResources().getStringArray(R.array.competition_menu);
        ArrayAdapter<String> competitionMenuAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, competitionMenuItems);

        competitionMenuListView.setAdapter(competitionMenuAdapter);

        long competitionID = intent.getLongExtra("competitionID", -1);
        Log.i(getLocalClassName(), "competitionID passed from calling activity: " + competitionID);
        if (competitionID == -1) {
            setTitle("Unknown Competition");
        } else {
            competition = this.appStore.getCompetition(competitionID);
            if (competition != null) {
                setTitle(competition.getEvent().getEventName());
                actionBar.setSubtitle(competition.toString() + " Competition");
            } else {
                Log.e(getLocalClassName(), "competitionID " + competitionID + " not found in store.");
                setTitle("Unknown Competition");
            }
        }

        competitionMenuListView.setOnItemClickListener(onCompetitionMenuItemClick());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return true;
    }

    public AdapterView.OnItemClickListener onCompetitionMenuItemClick() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i(getLocalClassName(), "competitionMenu clicked at position: " + position);

                String menuItem = competitionMenuItems[position];
                Log.d(getLocalClassName(), "competitionMenu click: " + menuItem);

                switch (menuItem) {
                    case "Competitors":
                        Intent intent = new Intent(CompetitionActivity.this,
                                CompetitorListActivity.class);
                        intent.putExtra("competitionID", competition.getAppID());
                        startActivity(intent);
                        break;
                    case "Rounds":
                        intent = new Intent(CompetitionActivity.this,
                                RoundListActivity.class);
                        intent.putExtra("competitionID", competition.getAppID());
                        startActivity(intent);
                        break;
                }
            }
        };
    }



    private void saveCompetition() {
        Log.i(this.getLocalClassName(), "saveCompetition(): ");
    }

}
