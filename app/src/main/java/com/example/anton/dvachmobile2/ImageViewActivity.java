package com.example.anton.dvachmobile2;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ImageViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        System.out.println(getIntent().getStringExtra("pic") + " PPPPPPPPPPPPPPPPPPPPPPIIIIIICC");

        ImageView imageView = (ImageView) findViewById(R.id.imageView6);
        Picasso.with(getApplicationContext())
                .load(getIntent().getStringExtra("pic"))
                .into(imageView);


    }

}
