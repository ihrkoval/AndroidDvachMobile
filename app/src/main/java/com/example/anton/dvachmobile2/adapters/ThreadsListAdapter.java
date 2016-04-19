package com.example.anton.dvachmobile2.adapters;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anton.dvachmobile2.ImageViewActivity;
import com.example.anton.dvachmobile2.Json.Files;
import com.example.anton.dvachmobile2.Json.Posts;
import com.example.anton.dvachmobile2.Json.Threads;
import com.example.anton.dvachmobile2.R;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

/**
 * Created by Anton on 22.03.2016.
 */
public class ThreadsListAdapter extends BaseAdapter {

    private Threads[] threads;
    private LayoutInflater layoutInflater;
    private String id;


    public ThreadsListAdapter(Context context, Threads[] threads, String id) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.threads = threads;
        this.id = id;
    }

    @Override
    public int getViewTypeCount() {
        return getCount();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getCount() {
        return threads.length;
    }

    @Override
    public Object getItem(int position) {
        return threads[position];
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, final View convertView, final ViewGroup parent) {

        View view = convertView;
        final Threads thread = (Threads) getItem(position);

        if (view == null) {
            view = layoutInflater.inflate(R.layout.list_of_threads_adapter, parent, false);

        }
        TextView name = (TextView)view.findViewById(R.id.Name);
        TextView op = (TextView)view.findViewById(R.id.OP);
        TextView email = (TextView)view.findViewById(R.id.email);
        TextView date = (TextView)view.findViewById(R.id.date);
        TextView postnum = (TextView)view.findViewById(R.id.postnum);
        TextView subject = (TextView)view.findViewById(R.id.subject);
        TextView coment = (TextView)view.findViewById(R.id.cooment);
        TextView posts = (TextView) view.findViewById(R.id.postCountTV);
        TextView filesC = (TextView) view.findViewById(R.id.filesCountTV);

        posts.setText("P: "+thread.getPosts_count());
        filesC.setText("F: "+ thread.getFile_count());

        name.setText(thread.getPosts()[0].getName());
        if (thread.getPosts()[0].getTrip().length() > 1){
            name.setText(thread.getPosts()[0].getTrip());

        }
        op.setText("");
        if (thread.getPosts()[0].getOp().equals(1))
        op.setText("OP");
        email.setText(thread.getPosts()[0].getEmail());
        date.setText(thread.getPosts()[0].getDate());
        postnum.setText("№"+thread.getPosts()[0].getHidden_num());
        subject.setText(thread.getPosts()[0].getSubject());
        coment.setText(Html.fromHtml(thread.getPosts()[0].getComment()));


        Files[] files = thread.getPosts()[0].getFiles();
        ImageView image1 = (ImageView)view.findViewById(R.id.imageView2);
        final ImageView image2 = (ImageView)view.findViewById(R.id.imageView3);
        ImageView image3 = (ImageView)view.findViewById(R.id.imageView4);
        ImageView image4 = (ImageView)view.findViewById(R.id.imageView5);
        try {
            Picasso.with(parent.getContext())
                    .load("https://2ch.hk/" + id + "/" + thread.getPosts()[0].getFiles()[0].getThumbnail())
                    .resize(100, 100)
                    .into(image1);
            Picasso.with(parent.getContext())
                    .load("https://2ch.hk/" + id + "/" + thread.getPosts()[0].getFiles()[1].getThumbnail())
                    .resize(100, 100)
                    .into(image2);
            Picasso.with(parent.getContext())
                    .load("https://2ch.hk/" + id + "/" + thread.getPosts()[0].getFiles()[2].getThumbnail() )
                    .resize(100, 100)
                    .into(image3);
            Picasso.with(parent.getContext())
                    .load("https://2ch.hk/" + id + "/" + thread.getPosts()[0].getFiles()[3].getThumbnail())
                    .resize(100, 100)
                    .into(image4);
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }


        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ImageViewActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("pic", "https://2ch.hk/" + id + "/" + thread.getPosts()[0].getFiles()[0].getPath());
                v.getContext().startActivity(intent);

            }
        });

        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ImageViewActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("pic","https://2ch.hk/" + id + "/"+ thread.getPosts()[0].getFiles()[1].getPath());
                v.getContext().startActivity(intent);

            }
        });

        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ImageViewActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("pic", "https://2ch.hk/" + id + "/" + thread.getPosts()[0].getFiles()[2].getPath());
                v.getContext().startActivity(intent);

            }
        });

        image4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ImageViewActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("pic", "https://2ch.hk/" + id + "/" + thread.getPosts()[0].getFiles()[3].getPath());
                v.getContext().startActivity(intent);

            }
        });




         /*   if (files.length == 1){
                Picasso.with(parent.getContext())
                        .load("https://2ch.hk/"+id+"/"+files[0].getThumbnail())
                        .resize(100, 100)
                        .into(image1);

            } else  if (files.length == 2){
                Picasso.with(parent.getContext())
                        .load("https://2ch.hk/"+id+"/"+files[0].getThumbnail())
                        .resize(100, 100)
                        .into(image1);
                Picasso.with(parent.getContext())
                        .load("https://2ch.hk/"+id+"/"+files[1].getThumbnail())
                        .resize(100, 100)
                        .into(image2);

            } else  if (files.length == 3){
                Picasso.with(parent.getContext())
                        .load("https://2ch.hk/"+id+"/"+files[0].getThumbnail())
                        .resize(100, 100)
                        .into(image1);
                Picasso.with(parent.getContext())
                        .load("https://2ch.hk/"+id+"/"+files[1].getThumbnail())
                        .resize(100, 100)
                        .into(image2);
                Picasso.with(parent.getContext())
                        .load("https://2ch.hk/"+id+"/"+files[2].getThumbnail())
                        .resize(100, 100)
                        .into(image3);

            } else  if (files.length == 4){
                Picasso.with(parent.getContext())
                        .load("https://2ch.hk/"+id+"/"+files[0].getThumbnail())
                        .resize(100, 100)
                        .into(image1);
                Picasso.with(parent.getContext())
                        .load("https://2ch.hk/"+id+"/"+files[1].getThumbnail())
                        .resize(100, 100)
                        .into(image2);
                Picasso.with(parent.getContext())
                        .load("https://2ch.hk/"+id+"/"+files[2].getThumbnail())
                        .resize(100, 100)
                        .into(image3);
                Picasso.with(parent.getContext())
                        .load("https://2ch.hk/"+id+"/"+files[3].getThumbnail())
                        .resize(100, 100)
                        .into(image4);
            }*/







        return view;
    }
}

