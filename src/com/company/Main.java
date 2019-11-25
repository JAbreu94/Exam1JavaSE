package com.company;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Main {

    private static void readFile(ArrayList<Date> systemDate, ArrayList<Programmers> programmers, ArrayList<ProjectTeam> projectTeams) throws ParseException {
//        Function to read the file
        SimpleDateFormat dateFormat = new SimpleDateFormat("d/M/yyyy");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse("xml/ITCompanyData.xml");
            NodeList savedSystemDateList = doc.getElementsByTagName("savedDate");
//            Reading the system date;
            for (int i = 0; i < savedSystemDateList.getLength(); i++) {
                Node savedSystemDate = savedSystemDateList.item(i);
                if (savedSystemDate.getNodeType() == Node.ELEMENT_NODE) {
                    Element date = (Element) savedSystemDate;
                    Date savedDate = dateFormat.parse(date.getElementsByTagName("systemDate").item(0).getTextContent());
                    systemDate.add(savedDate);
                    System.out.println("Today is " + dateFormat.format(systemDate.get(0)));
                }
            }
            NodeList savedProgrammersList = doc.getElementsByTagName("programmer");
//            Reading each programmer
            for (int i = 0; i < savedProgrammersList.getLength(); i++) {
                Node p = savedProgrammersList.item(i);
                if (p.getNodeType() == Node.ELEMENT_NODE) {
                    Element programmer = (Element) p;
                    int id = Integer.parseInt(programmer.getElementsByTagName("id").item(0).getTextContent());
                    String firstName = programmer.getElementsByTagName("firstName").item(0).getTextContent();
                    String lastName = programmer.getElementsByTagName("lastName").item(0).getTextContent();
                    double salary = Double.parseDouble(programmer.getElementsByTagName("salary").item(0).getTextContent());
                    Date date = dateFormat.parse(programmer.getElementsByTagName("startDate").item(0).getTextContent());
                    boolean isActive = Boolean.parseBoolean(programmer.getElementsByTagName("isActive").item(0).getTextContent());
                    int daysWorked = Integer.parseInt(programmer.getElementsByTagName("daysWorked").item(0).getTextContent());
                    Programmers member = new Programmers(firstName, lastName, id, salary, date, isActive, daysWorked);
                    programmers.add(member);
                }
            }
            NodeList savedProjectsList = doc.getElementsByTagName("project");
//            Reading each project
            for (int i = 0; i < savedProjectsList.getLength(); i++) {
                Node p = savedProjectsList.item(i);
                if (p.getNodeType() == Node.ELEMENT_NODE) {
                    Element project = (Element) p;
                    int id = Integer.parseInt(project.getElementsByTagName("id").item(0).getTextContent());
                    String projectName = project.getElementsByTagName("projectName").item(0).getTextContent();
                    Date startDate = dateFormat.parse(project.getElementsByTagName("startDate").item(0).getTextContent());
                    Date endDate = dateFormat.parse(project.getElementsByTagName("endDate").item(0).getTextContent());
                    int countProgrammers = project.getElementsByTagName("memberID").getLength();
                    boolean active = Boolean.parseBoolean(project.getElementsByTagName("active").item(0).getTextContent());
                    ArrayList<Integer> members = new ArrayList<>();
                    for (int j = 0; j < countProgrammers; j++) {
                        int member = Integer.parseInt(project.getElementsByTagName("memberID").item(j).getTextContent());
                        members.add(member);
                    }
                    ArrayList<String> functions = new ArrayList<>();
                    for (int j = 0; j < countProgrammers; j++) {
                        String function = project.getElementsByTagName("memberFunction").item(j).getTextContent();
                        functions.add(function);
                    }
                    ProjectTeam team = new ProjectTeam(id, projectName, startDate, endDate, active, members, functions);
                    projectTeams.add(team);
                }
            }
        } catch (Exception e) {
//            If there's no file, I need to create one
            if (e.getMessage() != null && e.getMessage().contains("The system cannot find the file specified")) {
                createFile(systemDate, programmers, projectTeams);
            } else {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void createFile(ArrayList<Date> systemDate, ArrayList<Programmers> programmers, ArrayList<ProjectTeam> projectTeams) throws ParseException {
//        Function to create a new file when there's not one
        SimpleDateFormat dateFormat = new SimpleDateFormat("d/M/yyyy");
//        Setting the default system date
        Date date = dateFormat.parse("20/11/2019");
//        Setting the default programmers
        Programmers programmer1 = new Programmers("Asdrúbal", "Fonseca", 1, 33, dateFormat.parse("1/11/2019"), true, 20);
        Programmers programmer2 = new Programmers("Tozé", "Manecas", 2, 42, dateFormat.parse("1/11/2019"), true, 20);
        Programmers programmer3 = new Programmers("Gerubilda", "Gerubildes", 3, 40, dateFormat.parse("1/11/2019"), true, 20);
        Programmers programmer4 = new Programmers("Gilberto", "D'Apolónia", 4, 55, dateFormat.parse("1/11/2019"), true, 20);
//        Setting the default projects
        ArrayList<Integer> ids1 = new ArrayList<>();
        ids1.add(1);
        ids1.add(2);
        ArrayList<Integer> ids2 = new ArrayList<>();
        ids2.add(3);
        ids2.add(4);
        ArrayList<String> functions1 = new ArrayList<>();
        functions1.add("Infiltrated elf");
        functions1.add("Geolocation");
        ArrayList<String> functions2 = new ArrayList<>();
        functions2.add("Sandwich making and vodka production");
        functions2.add("Luso-Russian hacker");
        ProjectTeam projectTeam1 = new ProjectTeam(1,"Find Santa's Home Address", dateFormat.parse("1/11/2019"), dateFormat.parse("31/12/2019"), true, ids1, functions1);
        ProjectTeam projectTeam2 = new ProjectTeam(2,"Interfere in USA 2020 elections", dateFormat.parse("1/11/2019"), dateFormat.parse("31/10/2020"), true, ids2, functions2);
        programmers.add(programmer1);
        programmers.add(programmer2);
        programmers.add(programmer3);
        programmers.add(programmer4);
        projectTeams.add(projectTeam1);
        projectTeams.add(projectTeam2);
        systemDate.add(date);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();
            Element root = doc.createElement("itCompany");
            doc.appendChild(root);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("xml/ITCompanyData.xml"));
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, result);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Menu menu = new Menu();
//        Saving the default parameters
        menu.saveSystemState(systemDate, programmers, projectTeams);
        System.out.println("New File created");
    }

    public static void main(String[] args) throws InterruptedException, ParseException {
        ArrayList<Programmers> programmersList = new ArrayList<>();
        ArrayList<ProjectTeam> projectTeamsList = new ArrayList<>();
        ArrayList<Date> systemDate = new ArrayList<>();
//        Reading or creating a new file
        readFile(systemDate, programmersList, projectTeamsList);
//        Executing the menu
        Menu menu = new Menu();
        menu.execute(systemDate, programmersList, projectTeamsList);
    }
}
