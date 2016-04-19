package com.example.anton.dvachmobile2.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.anton.dvachmobile2.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Anton on 29.02.2016.
 */
public class ExpandMenu extends BaseExpandableListAdapter {

    private  ArrayList<ArrayList<Map<String, String>>> mGroups;
    private Context mContext;



    public ExpandMenu(Context context, ArrayList<ArrayList<Map<String, String>>> groups){
        mContext = context;
        mGroups = groups;
    }

    @Override
    public int getGroupCount() {
        return mGroups.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mGroups.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mGroups.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mGroups.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.group_view, null);
        }

        if (isExpanded){
            //Изменяем что-нибудь, если текущая Group раскрыта
        }
        else{
            //Изменяем что-нибудь, если текущая Group скрыта
        }
        HashMap<String, String> board = (HashMap<String, String>) mGroups.get(groupPosition).get(0);
        String k="";

        for ( Map.Entry<String, String> entry : board.entrySet()) {
            k = entry.getValue();
            System.out.println(k);
        }

        TextView textGroup = (TextView) convertView.findViewById(R.id.textGroup);
        textGroup.setText(k);

        return convertView;

    }
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.child_view, null);
        }

        TextView textChild = (TextView) convertView.findViewById(R.id.textChild);
        Map board = mGroups.get(groupPosition).get(childPosition);
        textChild.setText("/"+board.get("id")+"/"+"  "+board.get("name").toString());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

   public String getGroupChildText(int groupPos, int childPos) {
        return getGroupText(groupPos) + " " +  getChildText(groupPos, childPos);
    }

    public String getChildText(int gpos, int chpos){
        Map board = mGroups.get(gpos).get(chpos);
        return board.get("id").toString();
    }

    public String getGroupText(int groupPos){
        HashMap<String, String> board = (HashMap<String, String>) mGroups.get(groupPos).get(0);
        String k=board.get("category");
        return k;
    }
}
