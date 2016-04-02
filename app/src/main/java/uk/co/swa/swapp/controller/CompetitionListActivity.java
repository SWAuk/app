package uk.co.swa.swapp.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
import uk.co.swa.swapp.model.Event;

public class CompetitionListActivity extends AppCompatActivity {

    private static final int AddCompetitionRequestValue = 0;
    private static final int EditCompetitionRequestValue = 1;

    private God god;
    private List<Competition> competitionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_competition_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.createFloatingActionButton();

        this.god = God.getInstance();

        ListView competitionListView = (ListView) findViewById(R.id.competitionsListView);

        // get the eventID passed from the activity calling us
        long eventID = getIntent().getLongExtra("eventID", -1);
        Log.d(getLocalClassName(), "eventID: " + eventID);

        Event event = this.god.getAppStore().getEvent(eventID);

        this.competitionList = this.god.getAppStore().getCompetitions(event);
        SwaObjectAdapter competitionsAdapter =
                new SwaObjectAdapter(competitionListView.getContext(),
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
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addCompetitionIntent = new Intent(CompetitionListActivity.this,
                        CompetitionActivity.class);
                addCompetitionIntent.putExtra("requestCode", AddCompetitionRequestValue);
                startActivityForResult(addCompetitionIntent, AddCompetitionRequestValue);
            }
        });
    }

    private AdapterView.OnItemClickListener onCompetitionListViewItemClick() {

        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long appID) {

                Log.d(getLocalClassName(), "Competition ListView Clicked, appID: " + appID);

                Intent intent = new Intent(CompetitionListActivity.this,
                        CompetitorListActivity.class);

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

                Intent editCompetitionIntent = new Intent(parent.getContext(),
                        CompetitionActivity.class);

                editCompetitionIntent.putExtra("competitionID",
                        competitionList.get(position).getAppID());
                startActivityForResult(editCompetitionIntent, 0);

            }

            @Override
            public void onDeleteClicked(AdapterView<?> parent, View view,
                                           int position, long appID) {

                Snackbar.make(view, "Delete not implemented yet...",
                        Snackbar.LENGTH_LONG).setAction(null, null).show();

            }
        };

    }

}
