package uk.co.swa.swapp.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import uk.co.swa.swapp.God;
import uk.co.swa.swapp.R;
import uk.co.swa.swapp.model.Competition;
import uk.co.swa.swapp.model.Round;
import uk.co.swa.swapp.store.AppStore;

public class RoundListActivity extends AppCompatActivity {

    AppStore appStore;
    Competition competition;
    ListView roundsListView;
    List<Round> roundList;
    SwaObjectAdapter roundsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        appStore = God.getInstance().getAppStore();

        Intent intent = getIntent();
        long competitionID = intent.getLongExtra("competitionID", -1);
        Log.i(getLocalClassName(), "competitionID passed from calling activity: " + competitionID);

        competition = appStore.getCompetition(competitionID);
        roundList = appStore.getCompetitionRounds(competition);

        roundsListView = (ListView) findViewById(R.id.roundsListView);

        roundsAdapter = new SwaObjectAdapter(roundsListView.getContext(),
                android.R.layout.simple_list_item_1, roundList);

        roundsListView.setAdapter(roundsAdapter);

        setTitle(competition.getEvent().getEventName());
        actionBar.setSubtitle(competition.toString() + " Rounds");

        createFloatingActionButton();

    }

    @Override
    protected void onResume() {
        super.onResume();
        roundList = appStore.getCompetitionRounds(competition);
        roundsAdapter.notifyDataSetChanged();
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

    private void createFloatingActionButton() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RoundListActivity.this, AddEditRoundsActivity.class);
                intent.putExtra("competitionID", competition.getAppID());
                intent.putExtra("roundNumber", roundList.size() + 1);
                startActivity(intent);
            }
        });
    }


    private AdapterView.OnItemLongClickListener onCompetitionListViewItemLongClick() {
        return new EditDeleteDialogOnItemLongClick(this.onEditDeleteDialogClick());
    }

    private OnEditDeleteDialogClick onEditDeleteDialogClick() {

        return new OnEditDeleteDialogClick() {
            @Override
            public void onEditClicked(AdapterView<?> parent, View view,
                                      int position, long appID) {

                // Get which Round was LongClicked
                Round round = (Round) parent.getItemAtPosition(position);

            }

            @Override
            public void onDeleteClicked(AdapterView<?> parent, View view,
                                        int position, long appID) {

                SwaObjectAdapter adapter = (SwaObjectAdapter) parent.getAdapter();
                Round round = (Round) adapter.getItem(position);

                adapter.remove(round);
                appStore.removeRound(round);

                Snackbar.make(view, round + " removed from " + "" + ".",
                        Snackbar.LENGTH_LONG).setAction(null, null).show();

            }
        };

    }
}
