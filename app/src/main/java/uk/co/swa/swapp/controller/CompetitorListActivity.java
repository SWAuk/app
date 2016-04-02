package uk.co.swa.swapp.controller;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import uk.co.swa.swapp.God;
import uk.co.swa.swapp.R;
import uk.co.swa.swapp.model.Competition;
import uk.co.swa.swapp.model.CompetitionEntrant;

public class CompetitorListActivity extends AppCompatActivity {

    private God god;
    private boolean isTeam;

    private ArrayAdapter competitorListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        this.god = God.getInstance();

        // get the competitionID passed from the activity calling us
        long competitionID = getIntent().getLongExtra("competitionID", -1);
        Log.d(getLocalClassName(), "competitionID: " + competitionID);

        setContentView(R.layout.activity_competitor_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Competition competition = this.god.getAppStore().getCompetition(competitionID);

        // TODO: this seems a bit of a hack checking it contains a String?
        // check whether it is a team competition type or not.
        this.isTeam = competition.getCompetitionType().toString().contains("Team");

        List<? extends CompetitionEntrant> competitionEntrantList =
                this.god.getAppStore().getCompetitionEntrants(competition);

        ListView competitorListView = (ListView) findViewById(R.id.competitorListView);

        this.competitorListAdapter = new SwaObjectAdapter(this,
                android.R.layout.simple_list_item_1, competitionEntrantList);

        competitorListView.setAdapter(competitorListAdapter);

        this.createFloatingActionButton();
        competitorListView.setOnItemClickListener(this.onCompetitorListItemClick());
        competitorListView.setOnItemLongClickListener(this.onCompetitorListItemLongClick());

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
        fab.setOnClickListener(this.onFabClick());

        if (this.isTeam) {
            // set the Floating Action Button icon to group to indicate adding teams
            fab.setImageDrawable(ContextCompat.getDrawable(this,
                    R.drawable.ic_group_add_white_24dp));
        }
    }

    private View.OnClickListener onFabClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Not implemented yet...", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        };
    }

    private AdapterView.OnItemClickListener onCompetitorListItemClick() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long appID) {
                Snackbar.make(view, "Click not implemented yet...",
                        Snackbar.LENGTH_LONG).setAction(null, null).show();
            }
        };
    }

    private AdapterView.OnItemLongClickListener onCompetitorListItemLongClick() {
        return new EditDeleteDialogOnItemLongClick(this.onEditDeleteDialogClick());
    }

    private OnEditDeleteDialogClick onEditDeleteDialogClick() {
        return new OnEditDeleteDialogClick() {
            @Override
            public void onEditClicked(AdapterView<?> parent, View view, int position, long appID) {
                Snackbar.make(view, "Edit not implemented yet...",
                        Snackbar.LENGTH_LONG).setAction(null, null).show();
            }

            @Override
            public void onDeleteClicked(AdapterView<?> parent, View view, int position, long appID) {
                Snackbar.make(view, "Delete not implemented yet...",
                        Snackbar.LENGTH_LONG).setAction(null, null).show();
            }
        };
    }

}
