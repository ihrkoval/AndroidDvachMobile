package com.example.anton.dvachmobile2.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.anton.dvachmobile2.R;
import com.squareup.picasso.Picasso;

import org.apache.http.entity.mime.content.StringBody;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Anton on 25.03.2016.
 */
public class postFormFragment extends Fragment {

    private FragmentActivity context;
    View v;
    private final int SELECT_PHOTO = 1;
    Bitmap selectedImage;
    Button fileChouse;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup postFormContainer, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.post_form_fragment, postFormContainer, false);
        return v;



    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        switch(requestCode) {
            case SELECT_PHOTO:
                System.out.println(resultCode  + " "+ imageReturnedIntent + " RESULT");
               if(resultCode == -1){
                    try {
                        final Uri imageUri = imageReturnedIntent.getData();
                        final InputStream imageStream = v.getContext().getContentResolver().openInputStream(imageUri);
                        selectedImage = BitmapFactory.decodeStream(imageStream);
                        fileChouse.setText("ok");
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                }
        }
    }

    @Override
    public void onStart() {
        super.onStart();



        fileChouse = (Button) v.findViewById(R.id.buttonFileChuoser);
        fileChouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, SELECT_PHOTO);
            }
        });

        final EditText comment = (EditText) v.findViewById(R.id.editText);
        Button j = (Button)v.findViewById(R.id.buttonJir);
        j.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                comment.setText(comment.getText()+"[b][/b]");
            }
        });

        Button kursive = (Button)v.findViewById(R.id.button);
        kursive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                comment.setText(comment.getText()+"[i][/i]");
            }
        });

        Button podc = (Button)v.findViewById(R.id.buttonP);
        podc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                comment.setText(comment.getText()+"[p][/p]");
            }
        });

        Button zach = (Button)v.findViewById(R.id.buttonZ);
        zach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                comment.setText(comment.getText()+"[s][/s]");
            }
        });

        Button spoiler = (Button)v.findViewById(R.id.buttonSpoiler);

        String result = "";
        GetCaptcha getCaptcha = new GetCaptcha();
        final String url = "https://2ch.hk/makaba/captcha.fcgi?type=2chaptcha";

        spoiler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                comment.setText(comment.getText()+"[spoiler][/spoiler]");
            }
        });

        final ImageView captcha = (ImageView)v.findViewById(R.id.imageViewCaptcha);
        captcha.setOnClickListener(new View.OnClickListener() {
            String res = "";
            @Override
            public void onClick(View view) {

                try {
                    res = new GetCaptcha().execute(url).get();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                res = res.replace("CHECK", "");

                Picasso.with(v.getContext())
                        .load("https://2ch.hk/makaba/captcha.fcgi?type=2chaptcha&action=image&id=" + res)
                        .into(captcha);
            }
        });


        try {

            getCaptcha.execute(url);
            result = getCaptcha.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        result = result.replace("CHECK", "");

        Picasso.with(v.getContext())
                .load("https://2ch.hk/makaba/captcha.fcgi?type=2chaptcha&action=image&id=" + result)
                .into(captcha);


        Button otvet = (Button)v.findViewById(R.id.OtvetBTN);
        otvet.setOnClickListener(new View.OnClickListener() {

            String res = "";
            String[] createthread = new String[7];
            String board_id=getArguments().getString("board_id");

            @Override
            public void onClick(View view) {
                EditText emailEditText = (EditText) v.findViewById(R.id.emailEditTextPost);
                EditText nameEditText = (EditText) v.findViewById(R.id.namePostform);
                EditText subj = (EditText) v.findViewById(R.id.editText2);
                EditText comment = (EditText) v.findViewById(R.id.editText);


                String mail = "";
                if (String.valueOf(emailEditText.getText()).equals("email"))
                mail = "";
                else mail=String.valueOf(emailEditText.getText());

                String name = "";
                if (String.valueOf(nameEditText.getText()).equals("Имя"))
                    mail = "";
                else mail=String.valueOf(nameEditText.getText());

                String subject = subj.getText().toString();


                createthread[0]=board_id;
                createthread[1]="0";
                createthread[2] = mail;
                createthread[3] =name ;
                createthread[4] =subject ;
                createthread[5] = comment.getText().toString();
                createthread[6] = ;

                try {
                    res = new Createthread().execute(createthread).get();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }




}

class Createthread extends AsyncTask<String, String[], String> {
    String url = "https://2ch.hk/makaba/posting.fcgi?json=1";

    @Override
    protected String doInBackground(String... params) {
        String postData = "&task=post&board="+params[0]+"&thread="+params[1]+"&email="+params[2]+"&name="
                +params[3]+"&subject="+params[4]+"&comment="+params[5]+"&image="+params[6];

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


class GetCaptcha extends AsyncTask<String, String, String> {

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



