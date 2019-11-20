package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    public void execute(ArrayList<Programmers> programmers, ArrayList<ProjectTeam> projectTeamsList) throws InterruptedException {
        welcomeMessage();
        mainMenu();
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

    private void mainMenu() throws InterruptedException {
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
                    programmersMenu();
                    break;
                case "2":
                    projectsMenu();
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

    private void programmersMenu() throws InterruptedException {
        boolean executing = true;
        Thread.sleep(1500);
        while (executing) {
            System.out.println();
            System.out.println("********************");
            System.out.println();
            System.out.println("==== PROGRAMMERS MENU COMMANDS ====");
            System.out.println("1 - Add New Programmer");
            System.out.println("2 - Outras Cenas");
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
//                    Executar função com switch para ler as hipóteses de editar lista de programadores
                    break;
                case "2":
//                    Executar função com switch para ler as hipóteses de editar lista de projectos
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

    private void projectsMenu() throws InterruptedException {
        boolean executing = true;
        Thread.sleep(1500);
        while (executing) {
            System.out.println();
            System.out.println("********************");
            System.out.println();
            System.out.println("===== PROJECTS MENU COMMANDS =====");
            System.out.println("1 - Coisas");
            System.out.println("2 - Outras Coisas");
            System.out.println("3 - Mais Coisas");
            System.out.println("4 - Ainda Mais Coisas");
            System.out.println("5 - Montes de Coisas");
            System.out.println("0 - Return to Main Menu");
            System.out.println();
            Scanner scan = new Scanner(System.in);
            System.out.print("Your command: ");
            String s = scan.next();
            System.out.println();
            switch (s) {
                case "1":
//                    Executar função com switch para ler as hipóteses de editar lista de programadores
                    break;
                case "2":
//                    Executar função com switch para ler as hipóteses de editar lista de projectos
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
}
