package com.eecsgo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataPoint {
    private int id;
    private int programId;
//    private String programName;
    private String track;
    private int result;
    private Integer bgId;
    private Date submitDate;
    private Date informDate;
    private int creatorId;
    private String creatorUsername;
    private Date createDate;
    private Background bg;
}
