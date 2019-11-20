package com.company;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Programmers {
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

    public void addProgrammer(ArrayList<Programmers> programmers) throws ParseException {
        System.out.println("- Add a New Programmer -");
        System.out.println();
        Scanner scan = new Scanner(System.in);
        System.out.print("First Name: ");
        String firstName = scan.next();
        System.out.println();
        System.out.print("Last Name: ");
        String lastName = scan.next();
        System.out.println();
        System.out.print("Salary per day: ");
        double salary = scan.nextDouble();
        System.out.println();
        String assigned = "";
        while (assigned != "y" || assigned != "n") {
            System.out.print("Do you want to assign this programmer to a project? (y/n) ");
            assigned = scan.next();
            if (assigned == "y") {
                boolean isActive = true;
                Date startDate = new Date(System.currentTimeMillis());
                startDate = dateFormat.format(startDate);
//                Falta adicionar ao projecto
            } else if (assigned == "n") {
                boolean isActive = false;
                Date startDate = dateFormat.parse("0/0/0000");
            } else {
                System.out.println();
                System.out.println("Please answer y or n, Master...");
                System.out.println();
            }
        }
    }
}
