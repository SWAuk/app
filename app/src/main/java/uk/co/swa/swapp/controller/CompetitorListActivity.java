package uk.co.swa.swapp.controller;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import uk.co.swa.swapp.R;

public class CompetitorListActivity extends AppCompatActivity {

    String[][] event_competitors = {
            {}, {}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_competitor_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Not implemented yet...", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ListView compListView = (ListView) findViewById(R.id.competitorsListView);

        // get the competitionID passed from the activity calling us
        int competitionID = (int) getIntent().getLongExtra("competitionID", -1);
        Log.d(getLocalClassName(), "competitionID: " + competitionID);

        if (competitionID > -1 && competitionID < event_competitors.length) {
            String[] events = event_competitors[competitionID];
            ArrayAdapter<String> eventsAdapter = new ArrayAdapter<String>(compListView.getContext(),
                    android.R.layout.simple_list_item_1, events);

            compListView.setAdapter(eventsAdapter);
        } else {
            // TODO: Show empty message
        }
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

}
