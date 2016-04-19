package com.example.anton.dvachmobile2.Json.Thread;

/**
 * Created by Anton on 25.03.2016.
 */
public class Post {
    String banned;
    String closed;
    String comment;
    String date;
    String email;
    Files[] files;
    String lasthit;
    String name;
    String num;
    String op;
    String parent;
    String sticky;
    String subject;
    String tags;
    String timestamp;
    String trip;
    String unique_posters;

    public String getUnique_posters() {
        return unique_posters;
    }

    public void setUnique_posters(String unique_posters) {
        this.unique_posters = unique_posters;
    }

    public String getTrip() {
        return trip;
    }

    public void setTrip(String trip) {
        this.trip = trip;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSticky() {
        return sticky;
    }

    public void setSticky(String sticky) {
        this.sticky = sticky;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLasthit() {
        return lasthit;
    }

    public void setLasthit(String lasthit) {
        this.lasthit = lasthit;
    }

    public Files[] getFiles() {
        return files;
    }

    public void setFiles(Files[] files) {
        this.files = files;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getClosed() {
        return closed;
    }

    public void setClosed(String closed) {
        this.closed = closed;
    }

    public String getBanned() {
        return banned;
    }

    public void setBanned(String banned) {
        this.banned = banned;
    }
}
