package com.example.jamieayer.project1list;

import android.graphics.Color;

import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by JamieAyer on 3/2/16.
 */
public class Item implements Serializable {

    private static final long serialVersionUID = 7526472295622776147L;

    private String mItemTitle;
    private Date mItemDate;
    private int mItemPosition;
    private String mDescription;
    private String mLocation;
    private ArrayList<String> mItemsArray;
    private Color mColor;

    public void Item(String itemTitle, String location, String description, ArrayList items)
    {
        this.mDescription = description;
        this.mLocation = location;
        this.mItemTitle = itemTitle;
        this.mItemsArray = items;
    }

    public String getmItemTitle() {
        return mItemTitle;
    }

    public void setmItemTitle(String mItemTitle) {
        this.mItemTitle = mItemTitle;
    }

    public Color getmColor() {
        return mColor;
    }

    public void setmColor(Color mColor) {
        this.mColor = mColor;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getmLocation() {
        return mLocation;
    }

    public void setmLocation(String mLocation) {
        this.mLocation = mLocation;
    }

    public Date getmItemDate() {
        return mItemDate;
    }

    public void setmItemDate(Date mItemDate) {
        this.mItemDate = mItemDate;
    }

    public int getmItemPosition() {
        return mItemPosition;
    }

    public void setmItemPosition(int mItemPosition) {
        this.mItemPosition = mItemPosition;
    }

    public ArrayList getmItemsArray() {
        return mItemsArray;
    }

    public void setmItemsArray(ArrayList mItemsArray) {
        this.mItemsArray = mItemsArray;
    }


    @Override
    public String toString() {
        return getmItemTitle();
    }

}
