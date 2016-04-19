package com.example.anton.dvachmobile2.Json;

import java.io.Serializable;

/**
 * Created by Anton on 22.03.2016.
 */
public class Threads implements Serializable {
    String files_count;
    Posts[] posts;
    String posts_count;
    String thread_num;

    public String getFile_count() {
        return files_count;
    }

    public Posts[] getPosts() {
        return posts;
    }

    public String getPosts_count() {
        return posts_count;
    }

    public String getThread_num() {
        return thread_num;
    }
}
