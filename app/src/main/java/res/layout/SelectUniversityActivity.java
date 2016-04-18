package res.layout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

import uk.co.swa.swapp.God;
import uk.co.swa.swapp.R;
import uk.co.swa.swapp.controller.SwaObjectAdapter;
import uk.co.swa.swapp.model.Competition;
import uk.co.swa.swapp.model.SwaObject;
import uk.co.swa.swapp.model.University;
import uk.co.swa.swapp.store.AppStore;

public class SelectUniversityActivity extends AppCompatActivity {

    AppStore appStore;
    List<University> universityList;
    SwaObjectAdapter universityListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_university);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        appStore = God.getInstance().getAppStore();

        long competitionID = getIntent().getLongExtra("competitionID", -1);
        Competition competition = appStore.getCompetition(competitionID);

        universityList = appStore.getUniversities();

        ListView universityListView = (ListView) findViewById(R.id.universityListView);

        universityListAdapter = new SwaObjectAdapter(universityListView.getContext(),
                android.R.layout.simple_list_item_1, universityList);

        universityListView.setAdapter(universityListAdapter);
        universityListView.setOnItemClickListener(onUniversityClicked());

        EditText filterEditText = (EditText) findViewById(R.id.filterEditText);

        filterEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                universityListAdapter.getFilter().filter(s);
            }
        });
    }

    private AdapterView.OnItemClickListener onUniversityClicked() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.putExtra("universityID", id);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        };
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
