package com.example.githubrepositories;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RepoModel {
    long id;
    String node_id;
    String name;
    String full_name;
    @SerializedName("private")
    String pr;
    OwnerData owner;
    String html_url;

    public RepoModel(long id, String node_id, String name, String full_name, String pr, OwnerData owner,String html_url) {
        this.id = id;
        this.node_id = node_id;
        this.name = name;
        this.full_name = full_name;
        this.pr = pr;
        this.owner = owner;
        this.html_url=html_url;
    }

    public RepoModel(long id, String name, String full_name,String html_url) {
        this.id = id;
        this.name = name;
        this.full_name = full_name;
        this.html_url = html_url;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNode_id() {
        return node_id;
    }

    public void setNode_id(String node_id) {
        this.node_id = node_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getPr() {
        return pr;
    }

    public void setPr(String pr) {
        this.pr = pr;
    }

    public OwnerData getOwner() {
        return owner;
    }

    public void setOwner(OwnerData owner) {
        this.owner = owner;
    }
}
