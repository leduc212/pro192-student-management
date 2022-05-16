/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import util.MyToys;

/**
 *
 * @author HP
 */
public class StudentList {

    public ArrayList<Student> studentList = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    //Tim student theo ID
    public int searchStudentByID(int ID) {
        if (studentList.isEmpty()) {
            return -1;
        }
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getId() == ID) {
                return i;
            }
        }
        return -1;
    }

    //Tim Student theo ID tra ve object
    public Student searchStudentObjectByID(int ID) {
        if (studentList.isEmpty()) {
            return null;
        }
        for (Student o : studentList) {
            if (o.getId() == ID) {
                return o;
            }
        }
        return null;
    }

    //Input Student
    public void addNewStudent(CampusList camp) {
        int id;
        String name, address, gender;
        int campusID;
        int pos;

        do {
            id = MyToys.getAnInteger("Input student's ID: (integer only)", "Invalid ID!", 0);
            pos = searchStudentByID(id);
            if (pos >= 0) {
                System.out.println("The student ID is already existed! "
                        + "Input another one!");
            }
        } while (pos != -1);

        campusID = MyToys.getAnInteger("Input the ID of this student's campus: (integer only)", "Invalid ID!", -1);
        pos = camp.searchCampusByID(campusID);
        if (pos >= 0) {
            System.out.println("Added the Campus of this Student!");
        } else {
            System.out.println("Can't find the Campus with this ID! Please add a new campus in the 'Campus Management' menu first");
            return;
        }

        name = MyToys.getString("Input student's name: ", "Invalid name!");
        address = MyToys.getString("Input student's address: ", "Invalid name!");
        gender = MyToys.getId("Input student's gender: (M/F)", "Invalid gender!", "^[M|F|m|f]$");

        Campus campus = camp.searchCampusObjectByID(campusID);
        studentList.add(new Student(id, name, address, gender, campus));
        System.out.println("A Student profile is added successfully.");
    }

    //Remove a student
    public void removeStudent() {
        int id;
        id = MyToys.getAnInteger("Input student's ID: (integer only)", "Invalid ID!", 0);
        if (searchStudentObjectByID(id) != null) {
            studentList.remove(searchStudentObjectByID(id));
            System.out.println("Student removed successfully!");
        } else {
            System.out.println("Student not found");
        }
    }

    //Xuat list
    public void printAscendingByID() {
        if (studentList.isEmpty()) {
            System.out.println("The Student list is empty. Nothing to print");
            return;
        }

        Comparator ascID = new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.getId() - o2.getId();
            }
        };
        Collections.sort(studentList, ascID);

        String show = String.format("|%-8s|%-5s|%-20s|%-40s|%-7s|%-10s|",
                "Student", "ID", "Name", "Address", "Gender", "Campus ID");
        System.out.println(show);
        System.out.println("-------------------------------------------------------------------------------------------------");
        for (Student o : studentList) {
            o.outputStudent();
        }
    }

    //Change campus
    public void updateCampus(CampusList camp) {
        int id;
        int campusID;
        Student x;
        id = MyToys.getAnInteger("Input student's ID: (integer only)", "Invalid ID!", 0);
        x = searchStudentObjectByID(id);
        System.out.println("===========================================");
        if (x == null) {
            System.out.println("Not Found!!!!");
        } else {
            System.out.println("Here is the Student before updating");
            String show1 = String.format("|%-8s|%-5s|%-20s|%-40s|%-7s|%-10s|",
                    "Student", "ID", "Name", "Address", "Gender", "Campus ID");
            System.out.println(show1);
            System.out.println("-------------------------------------------------------------------------------------------------");
            x.outputStudent();
            System.out.println("You are required to input a new campus' ID for this student: ");
            campusID = MyToys.getAnInteger("Input the ID of this student's campus: (integer only)", "Invalid ID!", -1);
            int pos = camp.searchCampusByID(campusID);
            if (pos >= 0) {
                System.out.println("Added the Campus of this Student!");
            } else {
                System.out.println("Can't find the Campus with this ID! Please add a new campus first!");
                return;
            }
            x.setCampus(camp.searchCampusObjectByID(campusID));
            System.out.println("The campus info of this student is updated successfully!");
        }
    }
}
