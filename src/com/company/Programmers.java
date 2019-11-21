package com.company;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Programmers implements ProgrammersInterface{
    private String firstName;
    private String lastName;
    private int id;
    private double salary;
    private Date startDate;
    private boolean isActive;
    private int daysWorked;
    SimpleDateFormat dateFormat = new SimpleDateFormat("d/M/yyyy");

    public Programmers() {
    }

    public Programmers(String firstName, String lastName, int id, double salary, Date startDate, boolean isActive, int daysWorked) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        this.salary = salary;
        this.startDate = startDate;
        this.isActive = isActive;
        this.daysWorked = daysWorked;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public int getDaysWorked() {
        return daysWorked;
    }

    public void setDaysWorked(int daysWorked) {
        this.daysWorked = daysWorked;
    }

    public void addProgrammer(ArrayList<Programmers> programmers, ArrayList<ProjectTeam> projectTeams) throws ParseException {
        int id = programmers.get(programmers.size() - 1).getId() + 1;
        System.out.println("- Add a New Programmer -");
        System.out.println("(You can type 0 to get back to the Menu)");
        System.out.println();
        Scanner scan = new Scanner(System.in);
        System.out.print("First Name: ");
        String firstName = scan.nextLine();
        System.out.println();
        if (firstName.equals("0")) {
            System.out.println("Canceled... Back to the menu");
            return;
        }
        System.out.print("Last Name: ");
        String lastName = scan.nextLine();
        System.out.println();
        if (lastName.equals("0")) {
            System.out.println("Canceled... Back to the menu");
            return;
        }
        System.out.print("Salary per day: ");
        double salary = scan.nextDouble();
        if (salary == 0.0) {
            System.out.println("Canceled... Back to the menu");
            return;
        }
        System.out.println();
        boolean correctlyAnswered = false;
        while (!correctlyAnswered) {
            System.out.print("Do you want to assign this programmer to a project? (y/n) ");
            String assigned = scan.next();
            System.out.println();
            switch (assigned) {
                case "y": {
                    System.out.println("Projects list:");
                    System.out.println();
                    for (ProjectTeam projectTeam : projectTeams) {
                        System.out.println(projectTeam.getId() + " - " + projectTeam.getProjectName());
                    }
                    System.out.println("0 - Cancel");
                    System.out.println();
                    Date startDate = new Date();
                    System.out.print("Which project you want to assign the new programmer for? ");
                    int projectID = scan.nextInt();
                    System.out.println();
                    for (ProjectTeam projectTeam : projectTeams) {
                        if (projectID == projectTeam.getId()) {
                            System.out.print("Please, indicate the function of the new programmer: ");
                            String function = scan.nextLine();
                            System.out.println();
                            if (function.equals("0")) {
                                System.out.println("Canceled... Back to the menu");
                                return;
                            }
                            Programmers newMember = new Programmers(firstName, lastName, id, salary, startDate, true, 0);
                            programmers.add(newMember);
                            projectTeam.getProgrammers().add(id);
                            projectTeam.getProgrammerFunctions().add(function);
                            System.out.println(firstName + " " + lastName + " was added to project " + projectTeam.getProjectName());
                            System.out.println();
                            return;
                        }
                    }
                    if (projectID == 0) {
                        System.out.println("Canceled... Back to the menu");
                        return;
                    } else {
                        System.out.println("Sorry, but there's no project with that ID");
                        System.out.println("Canceled... Back to the menu");
                        return;
                    }
                }
                case "n": {
                    Date startDate = dateFormat.parse("0/0/0000");
                    Programmers newMember = new Programmers(firstName, lastName, id, salary, startDate, false, 0);
                    programmers.add(newMember);
                    System.out.println(firstName + " " + lastName + " was added to the programmers list");
                    System.out.println();
                    correctlyAnswered = true;
                    break;
                }
                case "0":
                    System.out.println("Canceled... Back to the menu");
                    return;
                default:
                    System.out.println("Please answer y or n, Master...");
                    System.out.println();
                    break;
            }
        }
    }

    public void removeProgrammer(ArrayList<Programmers> programmers, ArrayList<ProjectTeam> projectTeams) {
        System.out.println("- Remove a Programmer -");
        System.out.println();
        System.out.println("Programmers list:");
        System.out.println();
        for (Programmers programmer : programmers) {
            System.out.println(programmer.getId() + " - " + programmer.getFirstName() + " " + programmer.getLastName());
        }
        System.out.println("0 - Cancel");
        System.out.println();
        Scanner scan = new Scanner(System.in);
        System.out.print("Which programmer do you want to remove? ");
        int idToRemove = scan.nextInt();
        System.out.println();
        boolean wasActive = true;
        for (int i = 0; i < programmers.size(); i++) {
            if (idToRemove == programmers.get(i).getId()) {
                if (!programmers.get(i).isActive) {
                    wasActive = false;
                }
                System.out.println(programmers.get(i).getFirstName() + " " + programmers.get(i).getLastName() + " was removed from the list and from the projects");
                programmers.remove(i);
                if (!wasActive) {
                    return;
                }
            }
        }
        for (ProjectTeam projectTeam : projectTeams) {
            for (int j = 0; j < projectTeam.getProgrammers().size(); j++) {
                if (idToRemove == projectTeam.getProgrammers().get(j)) {
                    projectTeam.getProgrammers().remove(j);
                    projectTeam.getProgrammerFunctions().remove(j);
                    return;
                }
            }
        }
        if (idToRemove == 0) {
            System.out.println("Canceled... Back to the menu");
        } else {
            System.out.println("The ID entered was not valid. You can create a new one in the Projects Menu");
            System.out.println("Back to the menu...");
        }
    }
}
