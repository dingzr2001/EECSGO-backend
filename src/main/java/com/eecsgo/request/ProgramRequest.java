package com.eecsgo.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProgramRequest {
    private String nameAbbr;
    private String university;
    private String name;
    private String major;
    private String url;
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
