package uk.co.swa.swapp.controller;

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
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

import uk.co.swa.swapp.God;
import uk.co.swa.swapp.R;
import uk.co.swa.swapp.model.Competition;
import uk.co.swa.swapp.model.Member;
import uk.co.swa.swapp.store.AppStore;

public class SelectMemberActivity extends AppCompatActivity {

    AppStore appStore;
    List<Member> memberList;
    FilterableMemberListAdapter memberListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_member);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        appStore = God.getInstance().getAppStore();

        long competitionID = getIntent().getLongExtra("competitionID", -1);
        Competition competition = appStore.getCompetition(competitionID);

        memberList = appStore.getEventAttendees(competition);

        ListView entrantListView = (ListView) findViewById(R.id.entrantListView);

        memberListAdapter = new FilterableMemberListAdapter(entrantListView.getContext(),
                android.R.layout.simple_list_item_activated_2, memberList);

        entrantListView.setAdapter(memberListAdapter);

        entrantListView.setOnItemClickListener(onMemberClicked());

        EditText filterEditText = (EditText) findViewById(R.id.filterEditText);

        filterEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                memberListAdapter.getFilter().filter(s);
            }
        });

    }

    private AdapterView.OnItemClickListener onMemberClicked() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.putExtra("memberID", id);
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
