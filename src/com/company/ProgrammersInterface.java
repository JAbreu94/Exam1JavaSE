package com.company;

import java.text.ParseException;
import java.util.ArrayList;

public interface ProgrammersInterface {
    public void addProgrammer(ArrayList<Programmers> programmers, ArrayList<ProjectTeam> projectTeams) throws ParseException;
    public void removeProgrammer(ArrayList<Programmers> programmers, ArrayList<ProjectTeam> projectTeams);
}
