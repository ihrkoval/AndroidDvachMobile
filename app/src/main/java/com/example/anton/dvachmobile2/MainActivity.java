package com.example.anton.dvachmobile2;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;


import com.example.anton.dvachmobile2.Json.Board;
import com.example.anton.dvachmobile2.Json.ThreadsList;
import com.example.anton.dvachmobile2.adapters.ExpandMenu;
import com.example.anton.dvachmobile2.fragments.ListOfThreads;
import com.example.anton.dvachmobile2.fragments.postFormFragment;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    String result;
    ArrayList<ArrayList<Map<String, String>>> groups;
    Bundle bundle;
    int count;
    FragmentTransaction ft;
    postFormFragment pf;
    String board_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ft = getSupportFragmentManager().beginTransaction();
                pf = new postFormFragment();
                if (count == 1){
                    ft.remove(pf);
                    ft.commit();
                    findViewById(R.id.postFormContainer).setVisibility(View.GONE);

                    count = 0;

                } else {
                    Bundle b = new Bundle();
                    b.putString("board_id", board_id );
                    pf.setArguments(b);
                    ft.replace(R.id.postFormContainer, pf);

                    findViewById(R.id.postFormContainer).setVisibility(View.VISIBLE);

                    ft.commit();
                    count = 1;
                }


            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(this);



        GetBoards getBoards = new GetBoards();
        try {
            getBoards.execute("https://2ch.hk/makaba/mobile.fcgi?task=get_boards");
            result = getBoards.get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        groups = new ArrayList<ArrayList<Map<String, String>>>();
        getBoardsByCat("Разное");
        getBoardsByCat("Тематика");
        getBoardsByCat("Политика");
        getBoardsByCat("Взрослым");
        getBoardsByCat("Техника и софт");
        getBoardsByCat("Творчество");
        getBoardsByCat("Игры");
        getBoardsByCat("Японская культура");
        getBoardsByCat("Пользовательские");

        ExpandableListView exList = (ExpandableListView) findViewById(R.id.expandableListView);

        ViewGroup header = (ViewGroup) getLayoutInflater().inflate(R.layout.nav_header_main, exList, false);
        exList.addHeaderView(header, null, false);
        final ExpandMenu adapter = new ExpandMenu(getApplicationContext(), groups);
        exList.setAdapter(adapter);
        exList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                board_id = adapter.getChildText(groupPosition, childPosition);

                GetBoards getBoards = new GetBoards();
                try {

                    getBoards.execute("https://2ch.hk/"+board_id+"/index.json");
                    result = getBoards.get();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                JsonParser parser = new JsonParser();
                Gson gson = new GsonBuilder().create();
                ThreadsList threadsList = gson.fromJson(result, ThreadsList.class);
                Bundle b = new Bundle();

                b.putSerializable("threads", threadsList.threads);
                b.putString("board_id", board_id);

                ListOfThreads LotFragment = new ListOfThreads();
                LotFragment.setArguments(b);

                FragmentManager manager = getSupportFragmentManager();
               FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.threadsLayout, LotFragment, ListOfThreads.TAG);
                transaction.addToBackStack(null).commit();




                Toast t = Toast.makeText(getApplicationContext(), threadsList.BoardName, Toast.LENGTH_SHORT);
                t.show();

                return false;
            }
        });



    }

    /*mAdapter = new PagerAdapter(getActivity().getSupportFragmentManager(), this);
    mPager = (ViewPager)getActivity().findViewById(R.id.viewpager);
    mPager.setAdapter(mAdapter);
    mSlidingTabLayout = (SlidingTabLayout) view.findViewById(R.id.sliding_tabs);
    mSlidingTabLayout.setViewPager(mViewPager);*/


    private void getBoardsByCat(String cat){
        JsonParser parser = new JsonParser();
        JsonObject obj = parser.parse(result).getAsJsonObject();
        JsonArray NotForKids = obj.getAsJsonArray(cat);
        Gson gson = new GsonBuilder().create();
        Board[] boards = gson.fromJson(NotForKids, Board[].class);
        ArrayList<Map<String, String>> themes = new ArrayList<Map<String, String>>();

        for (Board b : boards) {
            Map<String, String> board = new HashMap<>();
            board.put("name", b.name);
            board.put("category", b.category);
            board.put("id", b.id);
            themes.add(board);
        }
        groups.add(themes);
    }

    @Override
    public void onBackPressed() {

        if(getFragmentManager().getBackStackEntryCount() > 0)
            getFragmentManager().popBackStack();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        //menu.add(0, 0, 1, R.string.refresh);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
      /*  // Handle navigation view item clicks here.
        int id = item.getItemId();

       *//* if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else*//* if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}


class GetBoards extends AsyncTask<String, String, String> {

    @Override
    protected String doInBackground(String... params) {

        StringBuilder sb = new StringBuilder();
        try {
            URL pageURL = new URL(params[0]);
            String inputLine;
            URLConnection uc = pageURL.openConnection();
            BufferedReader buff = new BufferedReader(new InputStreamReader(uc.getInputStream()));
            while ((inputLine = buff.readLine()) != null) {
                sb.append(inputLine);
            }
            buff.close();
        } catch (Exception e) {
        }
        return sb.toString();
    }
}
