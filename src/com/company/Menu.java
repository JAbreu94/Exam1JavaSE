package com.company;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    Programmers programmer = new Programmers();
    ProjectTeam projectTeam = new ProjectTeam();

    public void execute(ArrayList<Programmers> programmers, ArrayList<ProjectTeam> projectTeamsList) throws InterruptedException, ParseException {
        welcomeMessage();
        mainMenu(programmers, projectTeamsList);
    }

    private void welcomeMessage() throws InterruptedException {
        System.out.println("$ SITH PROGRAMMING SPECIALIZED IT COMPANY $");
        System.out.println("---Join the dark size of the program---");
        System.out.println();
        Thread.sleep(1500);
        System.out.println("********************");
        System.out.println();
        System.out.println("Welcome, Master! We've been expecting your return...");
    }

    private void mainMenu(ArrayList<Programmers> programmers, ArrayList<ProjectTeam> projectTeams) throws InterruptedException, ParseException {
        boolean executing = true;
        Thread.sleep(1500);
        while (executing) {
            System.out.println();
            System.out.println("********************");
            System.out.println();
            System.out.println("How may I be of your service?");
            System.out.println();
            System.out.println("======= MAIN MENU COMMANDS =======");
            System.out.println("1 - Manage Programmers");
            System.out.println("2 - Manage Teams");
            System.out.println("3 - Print Report");
            System.out.println("4 - Save System State");
            System.out.println("5 - Update System");
            System.out.println("0 - Quit");
            System.out.println();
            Scanner scan = new Scanner(System.in);
            System.out.print("Your command: ");
            String s = scan.next();
            System.out.println();
            switch (s) {
                case "1":
                    programmersMenu(programmers, projectTeams);
                    break;
                case "2":
                    projectsMenu(programmers, projectTeams);
                    break;
                case "3":
//                    Executar função que imprime o relatório
                    break;
                case "4":
//                    Executar função que guarda informação das listas no ficheiros
                    break;
                case "5":
//                    Executar função que passa um dia para a frente
                    break;
                case "0":
                    System.out.println("Thank you for your visit, Master.");
                    System.out.println("Switching off!!");
                    executing = false;
                    break;
                default:
                    System.out.println("Please insert a valid command, master...");
                    break;
            }
        }
    }

    private void programmersMenu(ArrayList<Programmers> programmers, ArrayList<ProjectTeam> projectTeams) throws ParseException {
        boolean executing = true;
        while (executing) {
            System.out.println();
            System.out.println("********************");
            System.out.println();
            System.out.println("==== PROGRAMMERS MENU COMMANDS ====");
            System.out.println("1 - Add New Programmer");
            System.out.println("2 - Remove Programmer");
            System.out.println("3 - Mais Cenas");
            System.out.println("4 - Ainda Mais Cenas");
            System.out.println("5 - Montes de Cenas");
            System.out.println("0 - Return to Main Menu");
            System.out.println();
            Scanner scan = new Scanner(System.in);
            System.out.print("Your command: ");
            String s = scan.next();
            System.out.println();
            switch (s) {
                case "1":
                    programmer.addProgrammer(programmers, projectTeams);
                    break;
                case "2":
                    programmer.removeProgrammer(programmers, projectTeams);
                    break;
                case "3":
//                    Executar função que imprime o relatório
                    break;
                case "4":
//                    Executar função que guarda informação das listas no ficheiros
                    break;
                case "5":
//                    Executar função que passa um dia para a frente
                    break;
                case "0":
                    System.out.println("Returning to the Main Menu");
                    executing = false;
                    break;
                default:
                    System.out.println("Please insert a valid command, master...");
                    break;
            }
        }
    }

    private void projectsMenu(ArrayList<Programmers> programmers, ArrayList<ProjectTeam> projectTeams) throws ParseException {
        boolean executing = true;
        while (executing) {
            System.out.println();
            System.out.println("********************");
            System.out.println();
            System.out.println("===== PROJECTS MENU COMMANDS =====");
            System.out.println("1 - Create New Project");
            System.out.println("2 - Remove Project");
            System.out.println("3 - Add Programmer to Project");
            System.out.println("4 - Remove Programmer from Project");
            System.out.println("5 - Edit End Date from Project");
            System.out.println("0 - Return to Main Menu");
            System.out.println();
            Scanner scan = new Scanner(System.in);
            System.out.print("Your command: ");
            String s = scan.next();
            System.out.println();
            switch (s) {
                case "1":
                    projectTeam.newProject(programmers, projectTeams);
                    break;
                case "2":
                    projectTeam.removeProject(programmers, projectTeams);
                    break;
                case "3":
                    projectTeam.addProgrammerToProject(programmers, projectTeams);
                    break;
                case "4":
                    projectTeam.removeProgrammerFromProject(programmers, projectTeams);
                    break;
                case "5":
//                    Executar função que passa um dia para a frente
                    break;
                case "0":
                    System.out.println("Returning to the Main Menu");
                    executing = false;
                    break;
                default:
                    System.out.println("Please insert a valid command, master...");
                    break;
            }
        }
    }
}
