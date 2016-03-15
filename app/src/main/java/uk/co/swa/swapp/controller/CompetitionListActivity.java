package uk.co.swa.swapp.controller;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import uk.co.swa.swapp.GodScrappyDog;
import uk.co.swa.swapp.R;
import uk.co.swa.swapp.model.Competition;
import uk.co.swa.swapp.model.Event;

public class CompetitionListActivity extends AppCompatActivity {

    private static final int AddCompetitionRequestValue = 0;
    private static final int EditCompetitionRequestValue = 1;

    private static ArrayList<List<String>> event_competitions = new ArrayList<List<String>>() {{
        add(Arrays.asList("Wave"));
        add(Arrays.asList("Advanced", "Intermediate", "Beginner", "Freestyle", "Team"));
    }};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_competition_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ListView competitionListView = (ListView) findViewById(R.id.competitionsListView);

        // get the eventID passed from the activity calling us
        int eventID = getIntent().getIntExtra("eventID", -1);
        Log.d(getLocalClassName(), "eventID: " + eventID);

        if (eventID > -1 && eventID < event_competitions.size()) {
            List<String> competitions = event_competitions.get(eventID);
            ArrayAdapter<String> competitionsAdapter = new ArrayAdapter<String>(competitionListView.getContext(),
                    android.R.layout.simple_list_item_1, competitions);

            competitionListView.setAdapter(competitionsAdapter);
        } else {
            // TODO: Show empty message
        }

        competitionListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(getLocalClassName(), "Competition ListView Clicked, id: " + id);

                Intent intent = new Intent(CompetitionListActivity.this,
                        CompetitorListActivity.class);
                intent.putExtra("competitionID", (int) id);
                startActivity(intent);
            }
        });

        //
        CompetitionListEditDeleteDialog(this, competitionListView);
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

    private void CompetitionListEditDeleteDialog(final Activity activity,
                                                 final ListView competitionList){

        competitionList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, final View view, final int position, long id) {
                Log.d(getLocalClassName(), String.format("competitionListView long clicked. " +
                        "position: %d, id: %d", position, id));

                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(activity);
                dialogBuilder.setTitle((String) competitionList.getItemAtPosition(position));
                dialogBuilder.setItems(R.array.edit_delete, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d(getLocalClassName(), "EditDeleteDialog clicked: " + which);
                        switch (which) {
                            case 0:
                                Intent editCompetitionIntent = new Intent(activity,
                                        CompetitionActivity.class);

                                editCompetitionIntent.putExtra("requestCode", 0);
                                startActivityForResult(editCompetitionIntent, 0);
                                break;
                            case 1:
                                Snackbar.make(view, "Delete not implemented yet...",
                                        Snackbar.LENGTH_LONG).setAction(null, null).show();
                                break;
                        }
                    }
                });

                dialogBuilder.create().show();

                return true;
            }
        });
    }
}
