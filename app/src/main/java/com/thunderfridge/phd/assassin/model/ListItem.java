package com.thunderfridge.phd.assassin.model;

/**
 * Java reprsentation of our data to be displayed in recycler view
 * Created by Crist on 4/12/2017.
 */

public class ListItem {
    private String title;
    private String decscription;

    public String getDecscription() {
        return decscription;
    }

    public void setDecscription(String decscription) {
        this.decscription = decscription;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
