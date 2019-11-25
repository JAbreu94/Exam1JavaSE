package com.company;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

import static java.time.temporal.ChronoUnit.DAYS;

public class Menu {
    private Programmers programmer = new Programmers();
    private ProjectTeam projectTeam = new ProjectTeam();
    private SimpleDateFormat otherDateFormat = new SimpleDateFormat("d MMMM yyyy", Locale.UK);
    private SimpleDateFormat dateFormat = new SimpleDateFormat("d/M/yyyy");

    public void execute(ArrayList<Date> systemDate, ArrayList<Programmers> programmers, ArrayList<ProjectTeam> projectTeamsList) throws InterruptedException {
        welcomeMessage(systemDate);
        mainMenu(systemDate, programmers, projectTeamsList);
    }

    private void welcomeMessage(ArrayList<Date> systemDate) throws InterruptedException {
//        A welcome message to the user
        System.out.println("$ SITH PROGRAMMING SPECIALIZED IT COMPANY $");
        System.out.println("---Join the dark size of the program---");
        System.out.println();
        Thread.sleep(1500);
        System.out.println("********************");
        System.out.println();
        System.out.println("Welcome, Master! We've been expecting your return...");
        System.out.println("Today is the glorious day " + otherDateFormat.format(systemDate.get(0)));
        System.out.println();
    }

    private void mainMenu(ArrayList<Date> systemDate, ArrayList<Programmers> programmers, ArrayList<ProjectTeam> projectTeams) throws InterruptedException {
//        Function for the main menu
        boolean executing = true;
        Thread.sleep(1500);
        while (executing) {
            System.out.println("********************");
            System.out.println();
            System.out.println("How may I be of your service?");
            System.out.println();
            System.out.println("======= MAIN MENU COMMANDS =======");
            System.out.println("(" + dateFormat.format(systemDate.get(0)) + ")");
            System.out.println("1 - Add New Programmer");
            System.out.println("2 - Remove Programmer");
            System.out.println("3 - Manage Teams");
            System.out.println("4 - Print Report");
            System.out.println("5 - Save System State");
            System.out.println("6 - Update System");
            System.out.println("0 - Quit");
            System.out.println();
            Scanner scan = new Scanner(System.in);
            System.out.print("Your command: ");
            String s = scan.nextLine();
            System.out.println();
            switch (s) {
                case "1":
                    programmer.addProgrammer(systemDate, programmers, projectTeams);
                    break;
                case "2":
                    programmer.removeProgrammer(programmers, projectTeams);
                    break;
                case "3":
                    projectsMenu(systemDate, programmers, projectTeams);
                    break;
                case "4":
                    report(systemDate, programmers, projectTeams);
                    break;
                case "5":
                    saveSystemState(systemDate, programmers, projectTeams);
                    break;
                case "6":
                    updateDate(systemDate, programmers, projectTeams);
                    break;
                case "0":
                    System.out.println("Any unsaved alterations will be lost.");
                    System.out.print("Are you sure you want to quit the program? (y/n) ");
                    String answer = scan.nextLine();
                    System.out.println();
                    if (answer.equals("y")) {
                        System.out.println("Thank you for your visit, Master.");
                        System.out.println("Switching off!!");
                        executing = false;
                    }
                    break;
                default:
                    System.out.println("Please insert a valid command, master...");
                    break;
            }
        }
    }

    private void projectsMenu(ArrayList<Date> systemDate, ArrayList<Programmers> programmers, ArrayList<ProjectTeam> projectTeams) {
//        Function with sub menu to manage the projects
        boolean executing = true;
        while (executing) {
            System.out.println();
            System.out.println("********************");
            System.out.println();
            System.out.println("===== PROJECTS MENU COMMANDS =====");
            System.out.println("(" + dateFormat.format(systemDate.get(0)) + ")");
            System.out.println("1 - Create New Project");
            System.out.println("2 - Remove Project");
            System.out.println("3 - Add Programmer to Project");
            System.out.println("4 - Remove Programmer from Project");
            System.out.println("5 - Change End Date from Project");
            System.out.println("0 - Return to Main Menu");
            System.out.println();
            Scanner scan = new Scanner(System.in);
            System.out.print("Your command: ");
            String s = scan.next();
            System.out.println();
            switch (s) {
                case "1":
                    projectTeam.newProject(systemDate, programmers, projectTeams);
                    break;
                case "2":
                    projectTeam.removeProject(programmers, projectTeams);
                    break;
                case "3":
                    projectTeam.addProgrammerToProject(systemDate, programmers, projectTeams);
                    break;
                case "4":
                    projectTeam.removeProgrammerFromProject(programmers, projectTeams);
                    break;
                case "5":
                    projectTeam.changeEndDate(systemDate, projectTeams);
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

    public void saveSystemState(ArrayList<Date> systemDate, ArrayList<Programmers> programmers, ArrayList<ProjectTeam> projectTeams) {
//        Function to save the data in the .xml file
        SimpleDateFormat dateFormat = new SimpleDateFormat("d/M/yyyy");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//        Erasing the existing info
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse("xml/ITCompanyData.xml");
            NodeList savedSystemDateList = doc.getElementsByTagName("savedDate");
            for (int i = 0; i < savedSystemDateList.getLength(); i++) {
                Node savedSystemDate = savedSystemDateList.item(i);
                savedSystemDate.getParentNode().removeChild(savedSystemDate);
                i--;
            }
            NodeList savedProgrammersList = doc.getElementsByTagName("programmer");
            for (int i =0; i < savedProgrammersList.getLength(); i++) {
                Node pg = savedProgrammersList.item(i);
                pg.getParentNode().removeChild(pg);
                i--;
            }
            NodeList savedProjectsList = doc.getElementsByTagName("project");
            for (int i =0; i < savedProjectsList.getLength(); i++) {
                Node pj = savedProjectsList.item(i);
                pj.getParentNode().removeChild(pj);
                i--;
            }
            NodeList company = doc.getElementsByTagName("itCompany");
//            Saving the date
            Element date = doc.createElement("savedDate");
            company.item(0).appendChild(date);
            Element saveDate = doc.createElement("systemDate");
            saveDate.appendChild(doc.createTextNode(dateFormat.format(systemDate.get(0))));
            date.appendChild(saveDate);
//            Saving the programmers list
            for (Programmers programmer: programmers) {
                Element pg = doc.createElement("programmer");
                company.item(0).appendChild(pg);
                Element id = doc.createElement("id");
                id.appendChild(doc.createTextNode(Integer.toString(programmer.getId())));
                pg.appendChild(id);
                Element firstName = doc.createElement("firstName");
                firstName.appendChild(doc.createTextNode(programmer.getFirstName()));
                pg.appendChild(firstName);
                Element lastName = doc.createElement("lastName");
                lastName.appendChild(doc.createTextNode(programmer.getLastName()));
                pg.appendChild(lastName);
                Element salary = doc.createElement("salary");
                salary.appendChild(doc.createTextNode(Double.toString(programmer.getSalary())));
                pg.appendChild(salary);
                Element startDate = doc.createElement("startDate");
                startDate.appendChild(doc.createTextNode(dateFormat.format(programmer.getStartDate())));
                pg.appendChild(startDate);
                Element isActive = doc.createElement("isActive");
                isActive.appendChild(doc.createTextNode(Boolean.toString(programmer.isActive())));
                pg.appendChild(isActive);
                Element daysWorked = doc.createElement("daysWorked");
                daysWorked.appendChild(doc.createTextNode(Integer.toString(programmer.getDaysWorked())));
                pg.appendChild(daysWorked);
            }
//            Save the projects list
            for (ProjectTeam projectTeam: projectTeams) {
                Element pj = doc.createElement("project");
                company.item(0).appendChild(pj);
                Element id = doc.createElement("id");
                id.appendChild(doc.createTextNode(Integer.toString(projectTeam.getId())));
                pj.appendChild(id);
                Element projectName = doc.createElement("projectName");
                projectName.appendChild(doc.createTextNode(projectTeam.getProjectName()));
                pj.appendChild(projectName);
                Element startDate = doc.createElement("startDate");
                startDate.appendChild(doc.createTextNode(dateFormat.format(projectTeam.getStartDate())));
                pj.appendChild(startDate);
                Element endDate = doc.createElement("endDate");
                endDate.appendChild(doc.createTextNode(dateFormat.format(projectTeam.getEndDate())));
                pj.appendChild(endDate);
                Element active = doc.createElement("active");
                active.appendChild(doc.createTextNode(Boolean.toString(projectTeam.getActive())));
                pj.appendChild(active);
                for (int member: projectTeam.getProgrammers()) {
                    Element memberID = doc.createElement("memberID");
                    memberID.appendChild(doc.createTextNode(Integer.toString(member)));
                    pj.appendChild(memberID);
                }
                for (String function: projectTeam.getProgrammerFunctions()) {
                    Element memberFunction = doc.createElement("memberFunction");
                    memberFunction.appendChild(doc.createTextNode(function));
                    pj.appendChild(memberFunction);
                }
            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("xml/ITCompanyData.xml"));
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, result);
            System.out.println("Your programmers and projects were saved!");
            System.out.println();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void updateDate(ArrayList<Date> systemDate, ArrayList<Programmers> programmers, ArrayList<ProjectTeam> projectTeams) {
//        Function to add 1 day to the system date
        int count = 0;
        for (ProjectTeam projectTeam: projectTeams) {
            if (systemDate.get(0).equals(projectTeam.getEndDate())) {
                count++;
            }
        }
//        Checking if after the function is executed we'll be left with at least 2 active projects
        if (projectTeams.size() - count < 2) {
            System.out.println("You have projects that end today");
            System.out.println("You always need to have a minimum of 2 active projects");
            System.out.println("If you want to update the system, you need to create new projects");
            System.out.println();
        } else {
            System.out.println("- Updating System Date -");
            System.out.println();
//            Finishing projects
            for (ProjectTeam projectTeam: projectTeams) {
                if (systemDate.get(0).equals(projectTeam.getEndDate())) {
                    System.out.println(projectTeam.getProjectName() + " ended today");
                    System.out.println("You'll have " + projectTeam.getProgrammers().size() + " programmers available tomorrow");
                    System.out.println();
                    projectTeam.setActive(false);
                    for (Programmers programmer: programmers) {
                        for (int pg: projectTeam.getProgrammers()) {
                            if (pg == programmer.getId()) {
                                programmer.setActive(false);
                            }
                        }
                    }
                }
            }
//            Adding one day to the count of days worked in the month
            for (Programmers programmer: programmers) {
                if (programmer.isActive()) {
                    programmer.setDaysWorked(programmer.getDaysWorked() + 1);
                }
            }
            Calendar c = Calendar.getInstance();
            c.setTime(systemDate.get(0));
            int month = c.get(Calendar.MONTH);
            c.add(Calendar.DAY_OF_MONTH, 1);
            systemDate.set(0, c.getTime());
            if (month != c.get(Calendar.MONTH)) {
                System.out.println("It's the end of the month. Your programmers have been paid!");
                for (Programmers programmer: programmers) {
                    if (programmer.isActive()) {
                        programmer.setDaysWorked(1);
                    } else {
                        programmer.setDaysWorked(0);
                    }
                }
            }
            System.out.println("New date is " + dateFormat.format(systemDate.get(0)));
            System.out.println();
        }



    }

    private void report(ArrayList<Date> systemDate, ArrayList<Programmers> programmers, ArrayList<ProjectTeam> projectTeams) {
//        Function to print the report
        int pWorked = 0;
        int daysWorked = 0;
        int daysLeftWorking = 0;
        Calendar current = Calendar.getInstance();
        current.setTime(systemDate.get(0));
        LocalDate currentLocalDate = systemDate.get(0).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        for (Programmers programmer: programmers) {
            if (programmer.getDaysWorked() > 0) {
                pWorked += 1;
                daysWorked += programmer.getDaysWorked();
            }
        }
        for (ProjectTeam projectTeam: projectTeams) {
            if (projectTeam.getActive()) {
                Calendar end = Calendar.getInstance();
                end.setTime(projectTeam.getEndDate());
                if (current.get(Calendar.MONTH) == end.get(Calendar.MONTH)) {
                    daysLeftWorking += end.get(Calendar.DAY_OF_MONTH) - current.get(Calendar.DAY_OF_MONTH);
                } else {
                    daysLeftWorking += (currentLocalDate.lengthOfMonth() - current.get(Calendar.DAY_OF_MONTH)) * projectTeam.getProgrammers().size();
                }
            }
        }
//        Printing report
        System.out.println("=== SITH COMPANY REPORT ===");
        System.out.println();
        System.out.println("Sith Company is currently composed of " + projectTeams.size() + " project teams and " + programmers.size() + " programmers.");
        System.out.println("This month, " + pWorked + " programmers have been working " + daysWorked + " days, and there are " + daysLeftWorking + " days left for working.");
        System.out.println();
        System.out.println("- Project teams details - ");
        System.out.println();
        for (ProjectTeam projectTeam: projectTeams) {
            Calendar end = Calendar.getInstance();
            end.setTime(projectTeam.getEndDate());
            LocalDate endLocalDate = projectTeam.getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate startLocalDate = projectTeam.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            if (systemDate.get(0).before(projectTeam.getEndDate()) || current.get(Calendar.MONTH) == end.get(Calendar.MONTH)) {
                System.out.println("- Project team: " + projectTeam.getProjectName());
                System.out.println();
                for (Programmers programmer: programmers) {
                    for (int i = 0; i < projectTeam.getProgrammers().size(); i++) {
                        if (projectTeam.getProgrammers().get(i) == programmer.getId()) {
                            long duration = DAYS.between(startLocalDate, endLocalDate);
                            int nDaysWorked;
                            if (DAYS.between(startLocalDate, currentLocalDate) < programmer.getDaysWorked() - 1) {
                                nDaysWorked = (int)DAYS.between(startLocalDate, currentLocalDate) + 1;
                            } else {
                                nDaysWorked = programmer.getDaysWorked();
                            }
                            double salary = nDaysWorked * programmer.getSalary();
                            System.out.println(programmer.getLastName() + ", " + programmer.getFirstName() + ", in charge of " + projectTeam.getProgrammerFunctions().get(i)
                                    + " from " + dateFormat.format(projectTeam.getStartDate()) + " to " + dateFormat.format(projectTeam.getEndDate())
                                    + "(duration " + duration + "), has worked " + nDaysWorked + " days this month (total salary $" + salary + ")");
                        }
                    }
                }
                System.out.println();
            }
        }
    }
}
