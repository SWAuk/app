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
import uk.co.swa.swapp.model.Heat;
import uk.co.swa.swapp.model.Round;
import uk.co.swa.swapp.store.AppStore;

public class HeatListActivity extends AppCompatActivity {

    AppStore appStore;
    Round round;
    ListView heatsListView;
    SwaObjectAdapter heatsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heat_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        appStore = God.getInstance().getAppStore();

        Intent intent = getIntent();
        long roundID = intent.getLongExtra("roundID", -1);
        Log.i(getLocalClassName(), "roundID passed from calling activity: " + roundID);

        round = appStore.getRound(roundID);
        List<Heat> heatList = appStore.getRoundHeats(round);

        heatsListView = (ListView) findViewById(R.id.heatsListView);

        heatsAdapter = new SwaObjectAdapter(heatsListView.getContext(),
                android.R.layout.simple_list_item_1, heatList);

        heatsListView.setAdapter(heatsAdapter);

        Competition competition = round.getCompetition();
        setTitle(competition.getEvent().getEventName());
        actionBar.setSubtitle(competition.toString() + " - " + round.toString() + " Heats");

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

    private AdapterView.OnItemLongClickListener onCompetitionListViewItemLongClick() {
        return new EditDeleteDialogOnItemLongClick(this.onEditDeleteDialogClick());
    }

    private OnEditDeleteDialogClick onEditDeleteDialogClick() {

        return new OnEditDeleteDialogClick() {
            @Override
            public void onEditClicked(AdapterView<?> parent, View view,
                                      int position, long appID) {

                // Get which Competition was LongClicked
                Heat heat = (Heat) parent.getItemAtPosition(position);


            }

            @Override
            public void onDeleteClicked(AdapterView<?> parent, View view,
                                        int position, long appID) {

                SwaObjectAdapter adapter = (SwaObjectAdapter) parent.getAdapter();
                Competition competition = (Competition) adapter.getItem(position);

                adapter.remove(competition);
                appStore.removeCompetition(competition);

                Snackbar.make(view, competition + " removed from " + "" + ".",
                        Snackbar.LENGTH_LONG).setAction(null, null).show();

            }
        };

    }
}
