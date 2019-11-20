package com.company;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Main {

    public static void readFile(ArrayList<Programmers> programmers, ArrayList<ProjectTeam> projectTeams) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("d/M/yyyy");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse("src/ITCompanyData.xml");
            NodeList savedProgrammersList = doc.getElementsByTagName("programmer");
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
//                    System.out.println(member.getFirstName() + " " + member.getLastName());
                }
            }
            NodeList savedProjectsList = doc.getElementsByTagName("project");
            for (int i = 0; i < savedProjectsList.getLength(); i++) {
                Node p = savedProjectsList.item(i);
                if (p.getNodeType() == Node.ELEMENT_NODE) {
                    Element project = (Element) p;
                    int id = Integer.parseInt(project.getElementsByTagName("id").item(0).getTextContent());
                    String projectName = project.getElementsByTagName("projectName").item(0).getTextContent();
                    Date startDate = dateFormat.parse(project.getElementsByTagName("startDate").item(0).getTextContent());
                    Date endDate = dateFormat.parse(project.getElementsByTagName("endDate").item(0).getTextContent());
                    int countProgrammers = project.getElementsByTagName("memberID").getLength();
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
                    ProjectTeam team = new ProjectTeam(id, projectName, startDate, endDate, members, functions);
                    projectTeams.add(team);
//                    System.out.println(team.getProjectName());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ArrayList<Programmers> programmersList = new ArrayList<>();
        ArrayList<ProjectTeam> projectTeamsList = new ArrayList<>();

        readFile(programmersList, projectTeamsList);

        Menu menu = new Menu();

        menu.execute(programmersList, projectTeamsList);
    }
}
