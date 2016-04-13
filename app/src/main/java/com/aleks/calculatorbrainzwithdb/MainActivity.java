package com.aleks.calculatorbrainzwithdb;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String STATE_CURRENTVIEW = "currentView";
    private UOW uow;
    private int currentView = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(savedInstanceState != null) {
            currentView = Integer.parseInt(savedInstanceState.getString(STATE_CURRENTVIEW));
        }
        uow = new UOW(getApplicationContext());

        initListView(currentView);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //        .setAction("Action", null).show();
                initListView(currentView);
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle savedIntanceState) {
        savedIntanceState.putString(STATE_CURRENTVIEW, "" + currentView);
        super.onSaveInstanceState(savedIntanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        initListView(currentView);
    }

    private void initListView(int i) {
        ListView listView = (ListView) findViewById(android.R.id.list);
        switch(i) {
            case(1):
                OperandsAdapter operandsAdapter = new OperandsAdapter(getApplicationContext(), uow.operandRepo.getCursorAll(), uow);
                listView.setAdapter(operandsAdapter);
                break;
            case(2):
                OperationsAdapter operationsAdapter = new OperationsAdapter(getApplicationContext(), uow.operationRepo.getCursorAll(), uow);
                listView.setAdapter(operationsAdapter);
                break;
            case(3):
                DayStatsAdapter dayStatsAdapter = new DayStatsAdapter(getApplicationContext(), uow.dayStatRepo.getCursorAll(), uow);
                listView.setAdapter(dayStatsAdapter);
                break;
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        if (id == R.id.action_operands) {
            currentView = 1;
            initListView(currentView);
            return true;
        }
        if (id == R.id.action_operations) {
            currentView = 2;
            initListView(currentView);
            return true;
        }
        if (id == R.id.action_daystats) {
            currentView = 3;
            initListView(currentView);
            return true;
        }
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            uow.DropCreateDatabase();
            initListView(currentView);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
