package com.eecsgo.request;

import com.eecsgo.entity.Background;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddDpRequest {
    private int programId;
    private String programName;
    private String track;
    private int result;
    private int bgId;
    private Date submitDate;
    private Date informDate;
}
