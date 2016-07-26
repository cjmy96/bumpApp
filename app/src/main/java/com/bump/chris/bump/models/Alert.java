package com.bump.chris.bump.models;

import java.sql.Date;

/**
 * Created by chris on 18/07/16.
 */
public class Alert {
    private String mBumpID;
    private boolean mIsHIVRisk;
    private boolean mIsSyphilisRisk;
    private boolean mIsChlamydiaRisk;
    private boolean mIsGonorrheaRisk;
    private Date mHIVTestDate;
    private Date mSyphilisTestDate;
    private Date mChlamydiaTestDate;
    private Date mGonorrheaTestDate;
    private Date mSuggestedTestDate;

    public Alert(String bumpID, Date suggestedTestDate) {
        mBumpID = bumpID;
        mSuggestedTestDate = suggestedTestDate;
        mIsHIVRisk = false;
        mIsSyphilisRisk = false;
        mIsChlamydiaRisk = false;
        mIsGonorrheaRisk = false;
    }

    public void setHIVTestDate(Date date) {
        mHIVTestDate = date;
        mIsHIVRisk = true;
    }

    public void setSyphilisTestDate(Date date) {
        mSyphilisTestDate = date;
        mIsSyphilisRisk = true;
    }

    public void setChlamydiaTestDate(Date date) {
        mChlamydiaTestDate = date;
        mIsChlamydiaRisk = true;
    }

    public void setGonorrheaTestDate(Date date) {
        mGonorrheaTestDate = date;
        mIsGonorrheaRisk = true;
    }
}