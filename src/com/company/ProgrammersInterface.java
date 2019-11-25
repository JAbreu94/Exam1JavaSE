package com.company;

import java.util.ArrayList;
import java.util.Date;

public interface ProgrammersInterface {
    void addProgrammer(ArrayList<Date> systemDate, ArrayList<Programmers> programmers, ArrayList<ProjectTeam> projectTeams);
    void removeProgrammer(ArrayList<Programmers> programmers, ArrayList<ProjectTeam> projectTeams);
}
