package com.example.anton.dvachmobile2.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.anton.dvachmobile2.ImageViewActivity;
import com.example.anton.dvachmobile2.Json.Threads;
import com.example.anton.dvachmobile2.R;
import com.example.anton.dvachmobile2.adapters.ThreadsListAdapter;

/**
 * Created by Anton on 22.03.2016.
 */
public class ListOfThreads extends Fragment {

    public static final String TAG = "Threads";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list_of_threads_fragment, container, false);

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        final Threads[] threads = (Threads[]) getArguments().getSerializable("threads");
        final String board_id = getArguments().getString("board_id");
        ThreadsListAdapter adapterTL = new ThreadsListAdapter(getActivity().getApplicationContext(), threads, board_id);
        ListView lv = (ListView) getActivity().findViewById(R.id.list_of_threads);
        lv.setAdapter(adapterTL);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Threads thread = threads[position];
                String str ="https://2ch.hk/makaba/mobile.fcgi?task=get_thread&board="+board_id+"&thread="+thread.getThread_num()+"&post=0";

                Intent intent = new Intent(getContext(), ThreadActivity.class);
                intent.putExtra("thread", str);
                intent.putExtra("board_id", board_id);
                getContext().startActivity(intent);
                Toast t = Toast.makeText(getContext(), str, Toast.LENGTH_LONG);
                t.show();
            }
        });
    }
}
