package com.robert.hospitalappointmentbookingsystem.doctor.models;

import android.graphics.drawable.Drawable;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class DoctorInfo {
    private String IDNumber;
    private Drawable profilePhoto;
    private String firstName;
    private String lastName;
    private String doctorName;
    private String profName;
    private String profStatement;
    private Date practisingFrom;

    private boolean openSlot;
    private boolean viewDoctorDetails;


    public DoctorInfo(String IDNumber, Drawable profilePhoto, String firstName, String lastName, String doctorName, String profName, String profStatement, Date practisingFrom, boolean openSlot, boolean viewDoctorDetails) {
        this.IDNumber = IDNumber;
        this.profilePhoto = profilePhoto;
        this.firstName = firstName;
        this.lastName = lastName;
        this.doctorName = doctorName;
        this.profName = profName;
        this.profStatement = profStatement;
        this.practisingFrom = practisingFrom;
        this.openSlot = openSlot;
        this.viewDoctorDetails = viewDoctorDetails;
    }
}
