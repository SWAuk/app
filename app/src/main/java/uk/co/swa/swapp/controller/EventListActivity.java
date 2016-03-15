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

import java.util.List;

import uk.co.swa.swapp.GodScrappyDog;
import uk.co.swa.swapp.R;
import uk.co.swa.swapp.model.Event;
import uk.co.swa.swapp.model.Season;

public class EventListActivity extends AppCompatActivity {

    private GodScrappyDog god;
    private List<Event> eventList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Add not implemented yet...", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        god = GodScrappyDog.letMeAtEm();

        ListView eventListView = (ListView) findViewById(R.id.eventsListView);

        int seasonID = getIntent().getIntExtra("seasonID", -1);
        Log.d(getLocalClassName(), "seasonID: " + seasonID);

        if (seasonID > -1) {
            Season season = god.getSeasonList().getSeason(seasonID);
            Log.d(getLocalClassName(), "Season: " + season);

            this.eventList = season.getAllEvents();
            
            ArrayAdapter<Event> eventListAdapter = new ArrayAdapter<Event>(eventListView.getContext(),
                    android.R.layout.simple_list_item_1, eventList);

            eventListView.setAdapter(eventListAdapter);
        } else {
            // TODO
        }

        eventListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(getLocalClassName(), "Events ListView Clicked, id: " + id);

                Intent intent = new Intent(EventListActivity.this, CompetitionListActivity.class);
                intent.putExtra("eventID", (int) id);
                startActivity(intent);
            }
        });

        EventListEditDeleteDialog(this, eventListView);
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

    private void EventListEditDeleteDialog(final Activity activity, final ListView eventList){

        eventList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, final View view,
                                           final int position, long id) {

                Log.d(getLocalClassName(), String.format("eventListView long clicked. " +
                        "position: %d, id: %d", position, id));

                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(activity);
                dialogBuilder.setTitle((String) eventList.getItemAtPosition(position));
                dialogBuilder.setItems(R.array.edit_delete, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d(getLocalClassName(), "EditDeleteDialog clicked: " + which);
                        switch (which) {
                            case 0:
                                Snackbar.make(view, "Edit not implemented yet...",
                                        Snackbar.LENGTH_LONG).setAction(null, null).show();

                                Intent editCompetitionIntent = new Intent(activity,
                                        CompetitionActivity.class);

//                                editCompetitionIntent.putExtra("requestCode", 0);
//                                startActivityForResult(editCompetitionIntent, 0);
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
