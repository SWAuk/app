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
import uk.co.swa.swapp.model.Event;
import uk.co.swa.swapp.model.Season;

public class EventListActivity extends AppCompatActivity {

    private God god;
    private List<Event> eventList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.createFloatingActionButton();

        this.god = God.getInstance();

        ListView eventListView = (ListView) findViewById(R.id.eventsListView);

        this.eventList = this.god.getAppStore().getEvents(5);

        SwaObjectAdapter eventListAdapter = new SwaObjectAdapter(eventListView.getContext(),
                android.R.layout.simple_list_item_1, this.eventList);

        eventListView.setAdapter(eventListAdapter);

        eventListView.setOnItemClickListener(this.onEventListViewItemClick());
        eventListView.setOnItemLongClickListener(this.onEventListViewItemLongClick());
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
                Snackbar.make(view, "Add not implemented yet...", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private AdapterView.OnItemClickListener onEventListViewItemClick() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long appID) {
                Log.d(getLocalClassName(), "Events ListView Clicked, position: " + position +
                        ", appID: " + appID + ".");

                Intent intent = new Intent(EventListActivity.this, CompetitionListActivity.class);
                intent.putExtra("eventID", appID);
                startActivity(intent);
            }
        };
    }

    private AdapterView.OnItemLongClickListener onEventListViewItemLongClick() {
        return new EditDeleteDialogOnItemLongClick(this.onEditDeleteDialogClick());
    }

    private OnEditDeleteDialogClick onEditDeleteDialogClick() {

        return new OnEditDeleteDialogClick() {

            @Override
            public void onEditClicked(AdapterView<?> parent, View view, int position, long appID) {
                Snackbar.make(view, "Edit not implemented...",
                        Snackbar.LENGTH_LONG).setAction(null, null).show();
            }

            @Override
            public void onDeleteClicked(AdapterView<?> parent, View view, int position, long appID) {

                Snackbar.make(view, "Delete not implemented...",
                        Snackbar.LENGTH_LONG).setAction(null, null).show();
            }
        };

    }

}
