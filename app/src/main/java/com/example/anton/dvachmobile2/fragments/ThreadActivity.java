package com.example.anton.dvachmobile2.fragments;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.anton.dvachmobile2.Json.Thread.Post;
import com.example.anton.dvachmobile2.Json.Thread.ThreadPosts;
import com.example.anton.dvachmobile2.Json.ThreadsList;
import com.example.anton.dvachmobile2.R;
import com.example.anton.dvachmobile2.adapters.ThreadPostsAdapter;
import com.example.anton.dvachmobile2.adapters.ThreadsListAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class ThreadActivity extends AppCompatActivity {
String result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);

        String s = getIntent().getStringExtra("thread");
        String board_id = getIntent().getStringExtra("board_id");
        GetThread getThread = new GetThread();
        try {
            getThread.execute(s);
            result = getThread.get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(result+" RESULTTTTT");
        JsonParser parser = new JsonParser();
        Gson gson = new GsonBuilder().create();
        Post[] thread = gson.fromJson(result, Post[].class);
        System.out.println();

        ThreadPostsAdapter adapterTL = new ThreadPostsAdapter(this, thread, board_id);
        ListView lv = (ListView)findViewById(R.id.listView);
        lv.setAdapter(adapterTL);
    }
}

class GetThread extends AsyncTask<String, String, String> {

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
