package uk.co.swa.swapp.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import res.layout.SelectUniversityActivity;
import uk.co.swa.swapp.God;
import uk.co.swa.swapp.R;
import uk.co.swa.swapp.model.Competition;
import uk.co.swa.swapp.model.CompetitionEntrant;
import uk.co.swa.swapp.model.Member;
import uk.co.swa.swapp.model.Team;
import uk.co.swa.swapp.model.University;
import uk.co.swa.swapp.store.AppStore;

public class CompetitorListActivity extends AppCompatActivity {

    private static final int SELECT_MEMBER_REQUEST = 1;
    private static final int SELECT_UNIVERSITY_REQUEST = 2;

    private AppStore appStore;
    private boolean isTeam;
    private Competition competition;
    List<? extends CompetitionEntrant> competitionEntrantList;
    private SwaObjectAdapter competitorListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_competitor_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        appStore = God.getInstance().getAppStore();

        // get the competitionID passed from the activity calling us
        long competitionID = getIntent().getLongExtra("competitionID", -1);
        Log.d(getLocalClassName(), "competitionID: " + competitionID);

        competition = appStore.getCompetition(competitionID);

        setTitle(competition.getEvent().getEventName());
        actionBar.setSubtitle(competition.toString() + " Competitors");

        // TODO: this seems a bit of a hack checking it contains a String?
        // check whether it is a team competition type or not.
        isTeam = competition.getCompetitionType().toString().contains("Team");

        competitionEntrantList = this.appStore.getCompetitionEntrants(competition);

        ListView competitorListView = (ListView) findViewById(R.id.competitorListView);

        competitorListAdapter = new SwaObjectAdapter(competitorListView.getContext(),
                android.R.layout.simple_list_item_1, competitionEntrantList);

        competitorListView.setAdapter(competitorListAdapter);

        setupFloatingActionButton();
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

    private void setupFloatingActionButton() {

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        if (this.isTeam) {

            // set the Floating Action Button icon to group to indicate adding teams
            fab.setImageDrawable(ContextCompat.getDrawable(this,
                    R.drawable.ic_group_add_white_24dp));

            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(CompetitorListActivity.this,
                            SelectUniversityActivity.class);
                    intent.putExtra("competitionID", competition.getAppID());
                    startActivityForResult(intent, SELECT_UNIVERSITY_REQUEST);
                }
            });

        } else {

            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(CompetitorListActivity.this,
                            SelectMemberActivity.class);
                    intent.putExtra("competitionID", competition.getAppID());
                    startActivityForResult(intent, SELECT_MEMBER_REQUEST);
                }
            });

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case SELECT_MEMBER_REQUEST:
                if (resultCode == Activity.RESULT_OK) {
                    long memberID = data.getLongExtra("memberID", -1);
                    Member member = appStore.getMember(memberID);

                    if (!competitionEntrantList.contains(member)) {
                        appStore.addCompetitionEntrant(competition, member);
                        competitorListAdapter.add(member);

                        Snackbar.make(getCurrentFocus(), member.getName() +
                                        " registered for the " + competition.toString() +
                                " competition.", Snackbar.LENGTH_LONG).setAction(null, null).show();
                    } else {
                        Snackbar.make(getCurrentFocus(), member.getName() +
                                " is already registered for the " + competition.toString() +
                                " competition.", Snackbar.LENGTH_LONG).setAction(null, null).show();
                    }
                }

                break;

            case SELECT_UNIVERSITY_REQUEST:
                if (resultCode == Activity.RESULT_OK) {
                    long universityID = data.getLongExtra("universityID", -1);
                    University university = appStore.getUniversity(universityID);

                    int teamNumber = 1;
                    for (Team t : (List<Team>) competitionEntrantList) {
                        if (t.getUniversity() == university) teamNumber++;
                    }

                    Team team = new Team(university, teamNumber);

                    appStore.addCompetitionEntrant(competition, team);
                    competitorListAdapter.add(team);

                    Snackbar.make(getCurrentFocus(), team.getName() + " registered.",
                            Snackbar.LENGTH_LONG).setAction(null, null).show();
                }
                
                break;
        }
    }

    public AdapterView.OnItemClickListener onCompetitorListItemClick() {
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
            public void onDeleteClicked(AdapterView<?> parent, View view, int position, long id) {
                CompetitionEntrant competitionEntrant =
                        appStore.getCompetitionEntrant(competition, id);
                competitorListAdapter.remove(competitionEntrant);
                appStore.removeCompetitionEntrant(competition, competitionEntrant);

                Snackbar.make(view, competitionEntrant.getName() + " unregistered from " +
                        competition + " competition.",
                        Snackbar.LENGTH_LONG).setAction(null, null).show();
            }
        };
    }

}
