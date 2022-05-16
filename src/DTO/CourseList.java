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
public class CourseList {
    public ArrayList<Course> courseList = new ArrayList<>();
    Scanner sc = new Scanner(System.in);
    
    //Tim course theo ID
    public int searchCourseByID(int ID) {
        if (courseList.isEmpty()) {
            return -1;
        }
        for (int i = 0; i < courseList.size(); i++) {
            if (courseList.get(i).getId() == ID) {
                return i;
            }
        }
        return -1;
    }
    
    //Tim course theo ID tra ve object
    public Course searchCourseObjectByID(int ID) {
        if (courseList.isEmpty()) {
            return null;
        }
        for (Course o : courseList) {
            if (o.getId() == ID) {
                return o;
            }
        }
        return null;
    }
    
    //Input course
    public void addNewCourse() {
        int id, credit;
        String name;
        int pos;

        do {
            id = MyToys.getAnInteger("Input course's ID: (integer only)", "Invalid ID!", 0);
            pos = searchCourseByID(id);
            if (pos >= 0) {
                System.out.println("The course ID is already existed! "
                        + "Input another one!");
            }
        } while (pos != -1);

        name = MyToys.getString("Input course's name: ", "Invalid name!");
        credit = MyToys.getAnInteger("Input course's credit: (integer only)", "Invalid credit!", 0);
        courseList.add(new Course(id, name, credit));
        System.out.println("A course is added successfully.");
    }
    
    //Remove a course
    public void removeCourse() {
        int id;
        id = MyToys.getAnInteger("Input course's ID: (integer only)", "Invalid ID!", 0);
        if (searchCourseObjectByID(id) != null) {
            courseList.remove(searchCourseObjectByID(id));
            System.out.println("Course removed successfully!");
        } else {
            System.out.println("Course not found");
        }
    }
    
    //Xuat list
    public void printAscendingByID() {
        if (courseList.isEmpty()) {
            System.out.println("The Course list is empty. Nothing to print");
            return;
        }
        
        Comparator ascID = new Comparator<Course>() {
            @Override
            public int compare(Course o1, Course o2) {
                return o1.getId() - o2.getId();
            }
        };
        Collections.sort(courseList, ascID);

        String show = String.format("|%-8s|%-5s|%-25s|%-6s|",
                "Course", "ID", "Name", "Credit");
        System.out.println(show);
        System.out.println("-------------------------------------------------");
        for (Course o : courseList) {
            o.outputCourse();
        }
    }
    
    //Update credit
    public void updateCredit() {
        int id;
        int tmpCredit;
        Course x;
        id = MyToys.getAnInteger("Input Course's ID: (integer only)", "Invalid ID!", 0);
        x = searchCourseObjectByID(id);
        System.out.println("===========================================");
        if (x == null) {
            System.out.println("Not Found!!!!");
        } else {
            System.out.println("Here is the Course before updating");
            x.outputCourse();
            System.out.println("You are required to input a new credit: ");
            tmpCredit = MyToys.getAnInteger("Input this course's credit: (integer only)", "Invalid credit!", 0);
            x.setCredit(tmpCredit);
            System.out.println("The credit info is updated successfully!");
        }
    }
}
