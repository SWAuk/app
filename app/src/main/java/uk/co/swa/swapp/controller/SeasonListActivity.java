package uk.co.swa.swapp.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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

import uk.co.swa.swapp.God;
import uk.co.swa.swapp.R;

public class SeasonListActivity extends AppCompatActivity {

    private God god;
    // private List<Season> seasonList;
    // private ArrayAdapter<Season> seasonsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        this.god = God.getInstance();
//        this.seasonList = this.god.getAppStore().getSeasons();

        setContentView(R.layout.activity_season_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.createFloatingActionButton();

        ListView seasonListView = (ListView) findViewById(R.id.seasonsListView);

        //this.seasonsAdapter = new SwaObjectAdapter(seasonListView.getContext(),
        //        android.R.layout.simple_list_item_1, this.seasonList);

        // seasonListView.setAdapter(this.seasonsAdapter);

        seasonListView.setOnItemClickListener(this.onSeasonListViewItemClick());
        seasonListView.setOnItemLongClickListener(this.onSeasonListViewItemLongClick());

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

    private AdapterView.OnItemClickListener onSeasonListViewItemClick() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long appID) {
                Log.d(getLocalClassName(), "Seasons ListView Clicked, appID: " + appID);

                Intent intent = new Intent(SeasonListActivity.this, EventListActivity.class);
                intent.putExtra("seasonID", appID);
                startActivity(intent);
            }
        };
    }

    private AdapterView.OnItemLongClickListener onSeasonListViewItemLongClick() {
        return new EditDeleteDialogOnItemLongClick(this.onEditDeleteDialogClick());
    }

    private OnEditDeleteDialogClick onEditDeleteDialogClick() {

        return new OnEditDeleteDialogClick() {

            @Override
            public void onEditClicked(AdapterView<?> parent, View view,
                                         int position, long appID) {
                Snackbar.make(view, "Edit not implemented...",
                        Snackbar.LENGTH_LONG).setAction(null, null).show();
            }

            @Override
            public void onDeleteClicked(AdapterView<?> parent, View view,
                                           int position, long appID) {

                // Season season = seasonList.get(position);
                // boolean success = seasonList.remove(season);

                // if (success) {
                //    seasonsAdapter.notifyDataSetChanged();
                //    Snackbar.make(view, season + " removed.",
                //            Snackbar.LENGTH_LONG).setAction(null, null).show();
                // } else {
                //    Snackbar.make(view, "Unable to remove " + season + ".",
                //            Snackbar.LENGTH_LONG).setAction(null, null).show();
                // }
            }
        };

    }

}
