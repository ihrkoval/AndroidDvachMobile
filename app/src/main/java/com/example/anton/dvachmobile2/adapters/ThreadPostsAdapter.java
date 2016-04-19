package com.example.anton.dvachmobile2.adapters;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.anton.dvachmobile2.Json.Thread.Post;
import com.example.anton.dvachmobile2.R;
import com.example.anton.dvachmobile2.fragments.postFormFragment;
import com.squareup.picasso.Picasso;

/**
 * Created by Anton on 25.03.2016.
 */
public class ThreadPostsAdapter extends BaseAdapter {
private LayoutInflater layoutInflater;
Post[] posts;
    String id;

    public ThreadPostsAdapter(Context context, Post[] threadPosts, String board_id) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        posts = threadPosts;
        id = board_id;
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
        return posts.length;
    }

    @Override
    public Object getItem(int position) {
        return posts[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        View view = convertView;
        Post post = posts[position];

        if (view == null) {
            view = layoutInflater.inflate(R.layout.activity_thread_adapter, parent, false);
        }
        TextView postName = (TextView) view.findViewById(R.id.postName);
        TextView email = (TextView) view.findViewById(R.id.emailPostTextView);
        TextView op = (TextView) view.findViewById(R.id.opPostTV);
        TextView uniqueP = (TextView) view.findViewById(R.id.unique);
        TextView date = (TextView) view.findViewById(R.id.dateTV);
        TextView num = (TextView) view.findViewById(R.id.numTV);
        TextView subj = (TextView) view.findViewById(R.id.subjectTV);
        TextView comment = (TextView) view.findViewById(R.id.comentTV);

        comment.setMovementMethod(LinkMovementMethod.getInstance());


        ImageView postFile1 = (ImageView) view.findViewById(R.id.postFile1);
        ImageView postFile2 = (ImageView) view.findViewById(R.id.postFile2);
        ImageView postFile3 = (ImageView) view.findViewById(R.id.postFile3);
        ImageView postFile4 = (ImageView) view.findViewById(R.id.postFile4);

       // final Button button = (Button) view.findViewById(R.id.button);
        final View finalView = view;
       /* button.setOnClickListener(new View.OnClickListener() {


            public void onClick(View v) {

                button.setBackgroundResource(android.R.drawable.ic_menu_revert);


                FragmentTransaction ft = ((FragmentActivity) finalView.getContext()).getSupportFragmentManager().beginTransaction();
                ;
                postFormFragment pf = new postFormFragment();
                if (button.getTextSize() == 28 || button.getTextSize() == 56) {
                    ft.add(R.id.postFormContainer, pf);
                    ft.commit();
                    button.setTextSize(1);
                    button.setBackgroundResource(android.R.drawable.ic_menu_revert);

                } else {
                    ft.remove(pf);
                    ft.commit();
                    button.setTextSize(28);
                    button.setBackgroundResource(android.R.drawable.ic_menu_more);
                }

                notifyDataSetChanged();
            }

        });
*/

        postName.setText(post.getName());
        if (post.getTrip().length() > 1) {
            postName.setText(post.getTrip());
        }
        email.setText(post.getEmail());
        if (post.getOp().equals(1))
        op.setText("OP");
        if (post.getUnique_posters() != null)
        uniqueP.setText("poseters:"+post.getUnique_posters());
        date.setText(post.getDate());
        num.setText("№" + post.getNum());
        subj.setText(Html.fromHtml(post.getSubject()));
        comment.setText(Html.fromHtml(post.getComment()));

if (post.getFiles() != null){
    try {
        Picasso.with(parent.getContext())
                .load("https://2ch.hk/" + id + "/" +post.getFiles()[0].getThumbnail())
                .resize(100, 100)
                .into(postFile1);
        Picasso.with(parent.getContext())
                .load("https://2ch.hk/" + id + "/" + post.getFiles()[1].getThumbnail())
                .resize(100, 100)
                .into(postFile2);
        Picasso.with(parent.getContext())
                .load("https://2ch.hk/" + id + "/" + post.getFiles()[2].getThumbnail())
                .resize(100, 100)
                .into(postFile3);
        Picasso.with(parent.getContext())
                .load("https://2ch.hk/" + id + "/" + post.getFiles()[3].getThumbnail())
                .resize(100, 100)
                .into(postFile4);
    } catch (ArrayIndexOutOfBoundsException e) {
        e.printStackTrace();
    }

}

        return view;
    }


}
