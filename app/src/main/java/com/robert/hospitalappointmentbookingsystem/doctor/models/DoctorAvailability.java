package com.robert.hospitalappointmentbookingsystem.doctor.models;

import androidx.room.PrimaryKey;

import java.util.List;
import java.util.Map;

public class DoctorAvailability {

    @PrimaryKey
    private  String doctorIDNumber;
    private double firstConsultationFee;
    private double followUpConsultationFee;
    private List<String> modeOfPayment;

    private List<String> patientComments;
}
