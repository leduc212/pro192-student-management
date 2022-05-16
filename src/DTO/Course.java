/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import util.MyToys;

/**
 *
 * @author HP
 */
public class Course {
    int id;
    String name;
    int credit;
    
    //constructors

    public Course() {
    }

    public Course(int id, String name, int credit) {
        this.id = id;
        this.name = name;
        this.credit = credit;
    }
    
    //getters&setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }
    
    //methods
    public void inputCourse(){
        id = MyToys.getAnInteger("Input course's ID: (integer only)", "Invalid ID!", 0);
        name = MyToys.getString("Input course's name: ", "Invalid name!");
        credit = MyToys.getAnInteger("Input course's credit: (integer only)", "Invalid credit!", 0);
    }
    
    public void outputCourse() {
        String show = String.format("|%-8s|%-5d|%-25s|%-6d|",
                "Course", getId(), getName(), getCredit());
        System.out.println(show);
    }
}
