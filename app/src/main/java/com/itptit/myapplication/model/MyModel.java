package com.itptit.myapplication.model;

import java.io.Serializable;

public class MyModel implements Serializable {
    int id;
    String jobName;
    String jobDetail;
    String finishDate;
    String jobStatus;
    int collaboration;

    public MyModel(int id, String jobName, String jobDetail, String finishDate, String jobStatus, int collaboration) {
        this.id = id;
        this.jobName = jobName;
        this.jobDetail = jobDetail;
        this.finishDate = finishDate;
        this.jobStatus = jobStatus;
        this.collaboration = collaboration;
    }

    public MyModel(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobDetail() {
        return jobDetail;
    }

    public void setJobDetail(String jobDetail) {
        this.jobDetail = jobDetail;
    }

    public String getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(String finishDate) {
        this.finishDate = finishDate;
    }

    public String getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(String jobStatus) {
        this.jobStatus = jobStatus;
    }

    public int getCollaboration() {
        return collaboration;
    }

    public void setCollaboration(int collaboration) {
        this.collaboration = collaboration;
    }
}
