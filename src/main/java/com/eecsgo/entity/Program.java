package com.eecsgo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Program {
    private Integer id;
    private String nameAbbr;
    private String university;
    private String name;
    private String url;
    private String major;
    private String region;
    private String location;
    private Integer toefl;
    private Integer toeflL;
    private Integer toeflR;
    private Integer toeflS;
    private Integer toeflW;
    private Double ielts;
    private Double ieltsL;
    private Integer ieltsR;
    private Integer ieltsS;
    private Integer ieltsW;
    private Double minDuration;
    private Double maxDuration;
}
