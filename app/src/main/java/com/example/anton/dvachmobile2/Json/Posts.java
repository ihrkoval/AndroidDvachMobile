package com.example.anton.dvachmobile2.Json;

import java.io.Serializable;

/**
 * Created by Anton on 22.03.2016.
 */
public class Posts implements Serializable {
    String banned;
    String closed;
    String comment;
    String date;
    String email;
    Files[] files;
    String files_cont;
    String hidden_num;
    String lasthit;
    String name;
    String num;
    String op;
    String parent;
    String posts_count;
    String sticky;
    String subject;
    String tags;
    String timestamp;
    String trip;

    public String getBanned() {
        return banned;
    }

    public String getClosed() {
        return closed;
    }

    public String getComment() {
        return comment;
    }

    public String getDate() {
        return date;
    }

    public String getEmail() {
        return email;
    }

    public Files[] getFiles() {
        return files;
    }

    public String getFiles_cont() {
        return files_cont;
    }

    public String getHidden_num() {
        return hidden_num;
    }

    public String getLasthit() {
        return lasthit;
    }

    public String getName() {
        return name;
    }

    public String getNum() {
        return num;
    }

    public String getOp() {
        return op;
    }

    public String getParent() {
        return parent;
    }

    public String getPosts_count() {
        return posts_count;
    }

    public String getSticky() {
        return sticky;
    }

    public String getSubject() {
        return subject;
    }

    public String getTags() {
        return tags;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getTrip() {
        return trip;
    }
}
