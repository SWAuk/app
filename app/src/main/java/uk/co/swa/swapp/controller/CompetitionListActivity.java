package uk.co.swa.swapp.controller;

import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.TintResources;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import uk.co.swa.swapp.God;
import uk.co.swa.swapp.R;
import uk.co.swa.swapp.model.Competition;
import uk.co.swa.swapp.model.CompetitionType;
import uk.co.swa.swapp.model.Event;
import uk.co.swa.swapp.store.AppStore;

public class CompetitionListActivity extends AppCompatActivity {

    private God god;
    private AppStore appStore;
    private List<Competition> competitionList;
    SwaObjectAdapter competitionsAdapter;

    private Event event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_competition_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.createFloatingActionButton();

        this.god = God.getInstance();
        this.appStore = god.getAppStore();

        // get the eventID passed from the activity calling us
        long eventID = getIntent().getLongExtra("eventID", -1);
        Log.d(getLocalClassName(), "eventID: " + eventID);

        this.event = this.appStore.getEvent(eventID);
        this.competitionList = this.appStore.getCompetitions(this.event);

        setTitle(event.getEventName());

        ListView competitionListView = (ListView) findViewById(R.id.competitionsListView);

        this.competitionsAdapter = new SwaObjectAdapter(competitionListView.getContext(),
                android.R.layout.simple_list_item_1, this.competitionList);

        competitionListView.setAdapter(competitionsAdapter);

        competitionListView.setOnItemClickListener(this.onCompetitionListViewItemClick());
        competitionListView.setOnItemLongClickListener(this.onCompetitionListViewItemLongClick());
    }

    @Override
    protected void onResume() {
        Log.d(getLocalClassName(), "onResume called.");
        super.onResume();
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
        fab.setOnClickListener(this.onFloatingActionButtonClick());
    }

    private View.OnClickListener onFloatingActionButtonClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get all the competition types from the AppStore.
                List<CompetitionType> competitionTypes = appStore.getCompetitionTypes();
                // Remove any CompetitionTypes that already exist for this event.
                for (Competition competition : competitionList) {
                    competitionTypes.remove(competition.getCompetitionType());
                }

                // Create an adapter
                SwaObjectAdapter adapter = new SwaObjectAdapter(view.getContext(),
                        android.R.layout.simple_list_item_1, competitionTypes);

                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(view.getContext());
                dialogBuilder.setTitle("Add Competition");
                dialogBuilder.setAdapter(adapter, onAddCompetitionClick());

                dialogBuilder.create().show();
            }
        };
    }

    private DialogInterface.OnClickListener onAddCompetitionClick() {
        return new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ListView listView = ((AlertDialog) dialog).getListView();
                SwaObjectAdapter adapter = (SwaObjectAdapter) listView.getAdapter();
                CompetitionType competitionType = (CompetitionType) adapter.getItem(which);
                Competition competition = new Competition(competitionType, event);
                competitionsAdapter.add(competition);
                appStore.addCompetition(competition);

                Snackbar.make(getCurrentFocus(), competition + " added to " + event + ".",
                        Snackbar.LENGTH_LONG).setAction(null, null).show();
            }
        };
    }

    private DialogInterface.OnClickListener onEditCompetitionClick(final Competition competition) {
        return new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                CompetitionType oldCompetitionType = competition.getCompetitionType();

                ListView listView = ((AlertDialog) dialog).getListView();
                SwaObjectAdapter adapter = (SwaObjectAdapter) listView.getAdapter();
                CompetitionType competitionType = (CompetitionType) adapter.getItem(which);

                int position = competitionsAdapter.getPosition(competition);
                competitionsAdapter.remove(competition);
                competition.setCompetitionType(competitionType);
                competitionsAdapter.insert(competition, position);

                Snackbar.make(getCurrentFocus(), oldCompetitionType + " changed to " +
                        competition + ".", Snackbar.LENGTH_LONG).setAction(null, null).show();
            }
        };
    }

    private AdapterView.OnItemClickListener onCompetitionListViewItemClick() {

        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long appID) {

                Log.d(getLocalClassName(), "Competition ListView Clicked, appID: " + appID);

                Intent intent = new Intent(CompetitionListActivity.this,
                        CompetitionActivity.class);

                intent.putExtra("competitionID", appID);
                startActivity(intent);

            }
        };

    }

    private AdapterView.OnItemLongClickListener onCompetitionListViewItemLongClick() {
        return new EditDeleteDialogOnItemLongClick(this.onEditDeleteDialogClick());
    }

    private OnEditDeleteDialogClick onEditDeleteDialogClick() {

        return new OnEditDeleteDialogClick() {
            @Override
            public void onEditClicked(AdapterView<?> parent, View view,
                                      int position, long appID) {
                // Get all the competition types from the AppStore.
                List<CompetitionType> competitionTypes = appStore.getCompetitionTypes();
                // Remove any CompetitionTypes that already exist for this event.
                for (Competition competition : competitionList) {
                    competitionTypes.remove(competition.getCompetitionType());
                }

                // Get which Competition was LongClicked
                Competition competition = (Competition) parent.getItemAtPosition(position);

                // Create an adapter
                SwaObjectAdapter adapter = new SwaObjectAdapter(view.getContext(),
                        android.R.layout.simple_list_item_1, competitionTypes);

                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(view.getContext());
                dialogBuilder.setTitle("Change Competition Type");
                dialogBuilder.setAdapter(adapter, onEditCompetitionClick(competition));

                dialogBuilder.create().show();
            }

            @Override
            public void onDeleteClicked(AdapterView<?> parent, View view,
                                        int position, long appID) {

                SwaObjectAdapter adapter = (SwaObjectAdapter) parent.getAdapter();
                Competition competition = (Competition) adapter.getItem(position);

                adapter.remove(competition);
                appStore.removeCompetition(competition);

                Snackbar.make(view, competition + " removed from " + event + ".",
                        Snackbar.LENGTH_LONG).setAction(null, null).show();

            }
        };

    }

}
