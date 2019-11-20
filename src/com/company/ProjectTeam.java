package com.company;

import java.util.ArrayList;
import java.util.Date;

public class ProjectTeam {
    private int id;
    private String projectName;
    private Date startDate;
    private Date endDate;
    private ArrayList<Integer> programmers = new ArrayList<>();
    private ArrayList<String> programmerFunctions = new ArrayList<>();

    public ProjectTeam() {
    }

    public ProjectTeam(int id, String projectName, Date startDate, Date endDate, ArrayList<Integer> programmers, ArrayList<String> programmerFunctions) {
        this.id = id;
        this.projectName = projectName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.programmers = programmers;
        this.programmerFunctions = programmerFunctions;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public ArrayList<Integer> getProgrammers() {
        return programmers;
    }

    public void setProgrammers(ArrayList<Integer> programmers) {
        this.programmers = programmers;
    }

    public ArrayList<String> getProgrammerFunctions() {
        return programmerFunctions;
    }

    public void setProgrammerFunctions(ArrayList<String> programmerFunctions) {
        this.programmerFunctions = programmerFunctions;
    }
}
