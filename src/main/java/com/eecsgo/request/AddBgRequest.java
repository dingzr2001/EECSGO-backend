package com.eecsgo.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddBgRequest {
    private String undergradUniversity;
    private String undergradMajor;
    private String undergradDual;
    private String undergradMinor;
    private double avg;
    private double gpa;
    private double gpaScale;
    private int languageType;
    private double languageOverall;
    private int listening;
    private int reading;
    private int speaking;
    private int writing;
    private int greV;
    private int greQ;
    private double greAw;
    private String exchange;
    private String pub;
    private String research;
    private String internship;
    private String project;
    private String rl;
}
