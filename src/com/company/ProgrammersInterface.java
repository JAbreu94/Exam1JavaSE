package com.company;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public interface ProgrammersInterface {
    public void addProgrammer(ArrayList<Date> systemDate, ArrayList<Programmers> programmers, ArrayList<ProjectTeam> projectTeams) throws ParseException;
    public void removeProgrammer(ArrayList<Programmers> programmers, ArrayList<ProjectTeam> projectTeams);
}
