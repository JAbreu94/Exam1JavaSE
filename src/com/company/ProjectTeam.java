package com.company;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class ProjectTeam {
    private int id;
    private String projectName;
    private Date startDate;
    private Date endDate;
    private Boolean active;
    private ArrayList<Integer> programmers = new ArrayList<>();
    private ArrayList<String> programmerFunctions = new ArrayList<>();
    SimpleDateFormat dateFormat = new SimpleDateFormat("d/M/yyyy");

    public ProjectTeam() {
    }

    public ProjectTeam(int id, String projectName, Date startDate, Date endDate, Boolean active, ArrayList<Integer> programmers, ArrayList<String> programmerFunctions) {
        this.id = id;
        this.projectName = projectName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.active = active;
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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
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

    public void newProject(ArrayList<Date> systemDate, ArrayList<Programmers> programmers, ArrayList<ProjectTeam> projectTeams) throws ParseException {
        int count = 0;
        for (Programmers programmer: programmers) {
            if (!programmer.isActive()) {
                count += 1;
            }
        }
        if (count < 2) {
            System.out.println("ERROR - You need to have at least 2 programmers without a project to create a new one");
            System.out.println("Canceled... Back to the menu");
            System.out.println();
        } else {
            int id = projectTeams.get(projectTeams.size() - 1).getId() + 1;
            System.out.println("- Create a New Project -");
            System.out.println("(You can type 0 to get back to the Menu)");
            System.out.println();
            Scanner scan = new Scanner(System.in);
            System.out.print("Project Name: ");
            String projectName = scan.nextLine();
            System.out.println();
            if (projectName.equals("0")) {
                System.out.println("Canceled... Back to the menu");
                return;
            }
            System.out.print("Project End Date (day/month/year): ");
            String date = scan.nextLine();
//            Check if it's a valid date before converting to date?
            Date endDate = dateFormat.parse(date);
            if (!endDate.after(systemDate.get(0))) {
                System.out.println("That's not a valid date");
                System.out.println("Remember that the system date is " + dateFormat.format(systemDate.get(0)));
                System.out.println("Canceled... Back to the menu");
                return;
            }
            Date startDate = systemDate.get(0);
            System.out.println();
            if (date.equals("0")) {
                System.out.println("Canceled... Back to the menu");
                return;
            }
            ArrayList<Integer> programmerIDs = new ArrayList<>();
            ArrayList<String> programmerFunctions = new ArrayList<>();
            boolean ended = false;
            while (!ended) {
                System.out.println("List of available programmers:");
                System.out.println();
                for (Programmers programmer: programmers) {
                    if (!programmer.isActive()) {
                        System.out.println(programmer.getId() + " - " + programmer.getFirstName() + " " + programmer.getLastName());
                    }
                }
                System.out.println("0 - Cancel");
                System.out.println();
                System.out.println("You have to assign at least two programmers to this project.");
                System.out.print("Which programmer do you want to assign to this project? ");
                int programmerID = scan.nextInt();
                scan.nextLine();
                System.out.println();
                if (programmerID == 0) {
                    System.out.println("Canceled... Back to the menu");
                    return;
                }
                for (Programmers programmer: programmers) {
                    if (programmerID == programmer.getId() && programmer.isActive()) {
                        System.out.println("That ID was not on the list...");
                        System.out.println("Canceled... Back to the menu");
                        System.out.println();
                        return;
                    }
                }
                programmerIDs.add(programmerID);
                System.out.print("Which is the function of this programmer in the project? ");
                String programmerFunction = scan.nextLine();
                if (programmerFunction.equals("0")) {
                    System.out.println("Canceled... Back to the menu");
                    return;
                }
                programmerFunctions.add(programmerFunction);
                System.out.println();
                System.out.println("This programmer was added as " + programmerFunction);
                System.out.println();
                for (Programmers programmer: programmers) {
                    if (programmer.getId() == programmerID) {
                        programmer.setActive(true);
                        programmer.setStartDate(systemDate.get(0));
                    }
                }
                int inactive = 0;
                for (Programmers programmer: programmers) {
                    if (!programmer.isActive()) {
                        inactive += 1;
                    }
                }
                if (inactive == 0) {
                    ProjectTeam project = new ProjectTeam(id, projectName, startDate, endDate, active, programmerIDs, programmerFunctions);
                    projectTeams.add(project);
                    System.out.println("Project " + projectName + " was added!");
                    System.out.println();
                    return;
                }
                if (programmerIDs.size() >= 2) {
                    boolean correctlyAnswered = false;
                    while (!correctlyAnswered) {
                        System.out.println("You already selected " + programmerIDs.size() + " programmers.");
                        System.out.print("Do you want to select more? (y/n) ");
                        String continuing = scan.nextLine();
                        System.out.println();
                        switch (continuing) {
                            case "y":
                                correctlyAnswered = true;
                                break;
                            case "n":
                                ProjectTeam project = new ProjectTeam(id, projectName, startDate, endDate, active, programmerIDs, programmerFunctions);
                                projectTeams.add(project);
                                System.out.println("Project " + projectName + " was added!");
                                System.out.println();
                                correctlyAnswered = true;
                                ended = true;
                                break;
                            case "0":
                                for (Programmers programmer: programmers) {
                                    for (int progID: programmerIDs) {
                                        if (progID == programmer.getId()) {
                                            programmer.setActive(false);
                                        }
                                    }
                                }
                                System.out.println("Canceled... Back to the menu");
                                return;
                            default:
                                System.out.println("Please answer y or n, Master...");
                                System.out.println();
                                break;
                        }
                    }
                }
            }
        }
    }

    public void removeProject(ArrayList<Programmers> programmers, ArrayList<ProjectTeam> projectTeams) {
        if (projectTeams.size() > 2) {
            System.out.println("- Remove a Project -");
            System.out.println("(You can type 0 to get back to the Menu)");
            System.out.println();
            System.out.println("List of projects:");
            System.out.println();
            for (ProjectTeam projectTeam: projectTeams) {
                System.out.println(projectTeam.getId() + " - " + projectTeam.getProjectName());
            }
            System.out.println("0 - Cancel");
            System.out.println();
            Scanner scan = new Scanner(System.in);
            System.out.print("Which project do you want to remove? ");
            int projectID = scan.nextInt();
            scan.nextLine();
            System.out.println();
            if (projectID == 0) {
                System.out.println("Canceled... Back to the menu");
                return;
            }
            for (int i = 0; i < projectTeams.size(); i++) {
                if (projectID == projectTeams.get(i).getId()) {
                    for (int programmerID: projectTeams.get(i).getProgrammers()) {
                        for (Programmers programmer: programmers) {
                            if (programmerID == programmer.getId()) {
                                programmer.setActive(false);
                            }
                        }
                    }
                    System.out.println("Project " + projectTeams.get(i).getProjectName() + " was removed");
                    System.out.println();
                    projectTeams.remove(i);
                }
            }
        } else {
            System.out.println("Master, you can't have less than 2 projects in this company...");
            System.out.println("Back to the Menu...");
            System.out.println();
        }
    }

    public void addProgrammerToProject(ArrayList<Date> systemDate, ArrayList<Programmers> programmers, ArrayList<ProjectTeam> projectTeams) {
        int count = 0;
        for (Programmers programmer: programmers) {
            if (!programmer.isActive()) {
                count += 1;
            }
        }
        if (count < 1) {
            System.out.println("You don't have any available programmers...");
            System.out.println("Back to the Menu...");
        } else {
            System.out.println("- Add a Programmer to a Project -");
            System.out.println("(You can type 0 to get back to the Menu)");
            System.out.println();
            System.out.println("List of projects:");
            System.out.println();
            for (ProjectTeam projectTeam: projectTeams) {
                System.out.println(projectTeam.getId() + " - " + projectTeam.getProjectName());
            }
            System.out.println("0 - Cancel");
            System.out.println();
            Scanner scan = new Scanner(System.in);
            System.out.print("Which project do you want to add a programmer to? ");
            int projectID = scan.nextInt();
            scan.nextLine();
            System.out.println();
            if (projectID == 0) {
                System.out.println("Canceled... Back to the menu");
                return;
            }
            for (ProjectTeam projectTeam: projectTeams) {
                if (projectID == projectTeam.getId()) {
                    System.out.println("List of available programmers:");
                    System.out.println();
                    for (Programmers programmer: programmers) {
                        if (!programmer.isActive()) {
                            System.out.println(programmer.getId() + " - " + programmer.getFirstName() + " " + programmer.getLastName());
                        }
                    }
                    System.out.println("0 - Cancel");
                    System.out.println();
                    System.out.print("Which programmer do you want to add to " + projectTeam.getProjectName() + "? ");
                    int programmerID = scan.nextInt();
                    scan.nextLine();
                    System.out.println();
                    if (programmerID == 0) {
                        System.out.println("Canceled... Back to the menu");
                        return;
                    }
                    System.out.print("Please, indicate the function of the new programmer: ");
                    String function = scan.nextLine();
                    System.out.println();
                    if (function.equals("0")) {
                        System.out.println("Canceled... Back to the menu");
                        return;
                    }
                    for (Programmers programmer: programmers) {
                        if (programmerID == programmer.getId() && !programmer.isActive()) {
                            programmer.setActive(true);
                            programmer.setStartDate(systemDate.get(0));
                            projectTeam.getProgrammers().add(programmerID);
                            projectTeam.getProgrammerFunctions().add(function);
                            System.out.println(programmer.getFirstName() + " " + programmer.getLastName() + " was added to the project " + projectTeam.getProjectName());
                            System.out.println();
                            return;
                        }
                    }
                    System.out.println("Master, you didn't indicate an available programmer...");
                    System.out.println("Back to the Menu...");
                    System.out.println();
                    return;
                }
            }
            System.out.println("Master, you didn't indicate the id of a project...");
            System.out.println("Back to the Menu...");
            System.out.println();
        }
    }

    public void removeProgrammerFromProject(ArrayList<Programmers> programmers, ArrayList<ProjectTeam> projectTeams) {
        int count = 0;
        for (ProjectTeam projectTeam: projectTeams) {
            if (projectTeam.getProgrammers().size() > 2) {
                count += 1;
            }
        }
        if (count < 1) {
            System.out.println("All our projects have the minimum number of programmers (2)...");
            System.out.println("Back to the Menu...");
        } else {
            System.out.println("- Remove a Programmer from a Project -");
            System.out.println("(You can type 0 to get back to the Menu)");
            System.out.println();
            System.out.println("List of Projects with at least 3 programmers:");
            System.out.println("(Remember you need to have at least 2 programmers in each project)");
            System.out.println();
            for (ProjectTeam projectTeam: projectTeams) {
                if (projectTeam.getProgrammers().size() > 2) {
                    System.out.println(projectTeam.getId() + " - " + projectTeam.getProjectName() + ": " + projectTeam.getProgrammers().size() + " members");
                }
            }
            System.out.println("0 - Cancel");
            System.out.println();
            Scanner scan = new Scanner(System.in);
            System.out.print("Which project do you want to remove a programmer from? ");
            int projectID = scan.nextInt();
            scan.nextLine();
            System.out.println();
            if (projectID == 0) {
                System.out.println("Canceled... Back to the menu");
                return;
            }
            for (ProjectTeam projectTeam: projectTeams) {
                if (projectID == projectTeam.getId()) {
                    System.out.println("List of programmers from " + projectTeam.getProjectName() + ":");
                    System.out.println();
                    for (Programmers programmer: programmers) {
                        for (int i = 0; i < projectTeam.getProgrammers().size(); i++) {
                            if (programmer.getId() == projectTeam.getProgrammers().get(i)) {
                                System.out.println(programmer.getId() + " - " + programmer.getFirstName() + " " + programmer.getLastName());
                            }
                        }
                    }
                    System.out.println("0 - Cancel");
                    System.out.println();
                    System.out.print("Which programmer do you want to remove from " + projectTeam.getProjectName() + "? ");
                    int programmerID = scan.nextInt();
                    scan.nextLine();
                    System.out.println();
                    if (programmerID == 0) {
                        System.out.println("Canceled... Back to the menu");
                        return;
                    }
                    for (Programmers programmer: programmers) {
                        for (int i = 0; i < projectTeam.getProgrammers().size(); i++) {
                            if (programmerID == programmer.getId() && programmerID == projectTeam.getProgrammers().get(i)) {
                                System.out.println(programmer.getFirstName() + " " + programmer.getLastName() + " was removed from " + projectTeam.getProjectName());
                                System.out.println();
                                programmer.setActive(false);
                                projectTeam.getProgrammers().remove(i);
                                projectTeam.getProgrammerFunctions().remove(i);
                                return;
                            }
                        }
                    }
                    System.out.println("Master, that id was not on the list...");
                    System.out.println("Back to the Menu...");
                    return;
                }
            }
            System.out.println("Master, that id was not on the list...");
            System.out.println("Back to the Menu...");
        }
    }

    public void changeEndDate(ArrayList<Date> systemDate, ArrayList<ProjectTeam> projectTeams) throws ParseException {
        System.out.println("- Change End Date of a Project -");
        System.out.println("(You can type 0 to get back to the Menu)");
        System.out.println();
        System.out.println("List of projects:");
        System.out.println();
        for (ProjectTeam projectTeam: projectTeams) {
            System.out.println(projectTeam.getId() + " - " + projectTeam.getProjectName() + " - end date: " + dateFormat.format(projectTeam.getEndDate()));
        }
        System.out.println("0 - Cancel");
        System.out.println();
        Scanner scan = new Scanner(System.in);
        System.out.print("Which project do you want to change the end date from? ");
        int projectID = scan.nextInt();
        scan.nextLine();
        System.out.println();
        if (projectID == 0) {
            System.out.println("Canceled... Back to the menu");
            return;
        }
        for (ProjectTeam projectTeam: projectTeams) {
            if (projectID == projectTeam.getId()) {
                System.out.print("New end date of " + projectTeam.getProjectName() + " (day/month/year): ");
                String date = scan.nextLine();
//            Check if it's a valid date before converting to date?
                Date endDate = dateFormat.parse(date);
                System.out.println();
                if (date.equals("0")) {
                    System.out.println("Canceled... Back to the menu");
                    return;
                }
                if (!endDate.after(systemDate.get(0))) {
                    System.out.println("That's not a valid date");
                    System.out.println("Remember that the system date is " + dateFormat.format(systemDate.get(0)));
                    System.out.println("Canceled... Back to the menu");
                    return;
                }
                projectTeam.setEndDate(endDate);
                System.out.println("End date from " + projectTeam.getProjectName() + " changed to " + dateFormat.format(projectTeam.getEndDate()));
                return;
            }
        }
        System.out.println("Master, that id was not on the list...");
        System.out.println("Back to the Menu...");
    }
}
