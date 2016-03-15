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
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import uk.co.swa.swapp.R;
import uk.co.swa.swapp.GodScrappyDog;
import uk.co.swa.swapp.model.Season;
import uk.co.swa.swapp.model.SeasonList;

public class SeasonListActivity extends AppCompatActivity {

    private GodScrappyDog god = GodScrappyDog.letMeAtEm();
    private SeasonList seasons = god.getSeasonList();
    private ArrayAdapter<Season> seasonsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_season_list);
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

        ListView seasonListView = (ListView) findViewById(R.id.seasonsListView);

        seasonsAdapter = new ArrayAdapter<Season>(seasonListView.getContext(),
                android.R.layout.simple_list_item_1, seasons.getAllSeasons());
        seasonListView.setAdapter(seasonsAdapter);

        seasonListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(getLocalClassName(), "Seasons ListView Clicked, id: " + id);

                Intent intent = new Intent(SeasonListActivity.this, EventListActivity.class);
                intent.putExtra("seasonID", (int) id);
                startActivity(intent);
            }
        });

        SeasonListEditDelete(this, seasonListView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // TODO: if no database/file/data then direct to add event activity
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_seasons, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void SeasonListEditDelete(final Activity activity, final ListView seasonListView) {

        seasonListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, final View view,
                                           final int position, final long id) {
                Log.d(getLocalClassName(), String.format("seasonListView long clicked. " +
                        "position: %d, id: %d", position, id));

                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(activity);
                dialogBuilder.setTitle(seasons.getSeason((int) id).getSeasonName());
                dialogBuilder.setItems(R.array.edit_delete, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d(getLocalClassName(), "EditDeleteDialog clicked: " + which);
                        Season season = seasons.getSeason((int) id);
                        switch (which) {
                            case 0:
                                Snackbar.make(view, "Edit not implemented yet...",
                                        Snackbar.LENGTH_LONG).setAction(null, null).show();

                                Intent addEventIntent = new Intent(activity,
                                        EventListActivity.class);

//                                addCompetitionIntent.putExtra("requestCode", 0);
//                                startActivityForResult(addCompetitionIntent, 0);
                                break;
                            case 1:
                                boolean success = seasons.removeSeason(season);
                                if (success) {
                                    seasonsAdapter.notifyDataSetChanged();
                                    Snackbar.make(view, season + " removed.",
                                            Snackbar.LENGTH_LONG).setAction(null, null).show();
                                } else {
                                    Snackbar.make(view, "Unable to remove " + season + ".",
                                            Snackbar.LENGTH_LONG).setAction(null, null).show();
                                }
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
