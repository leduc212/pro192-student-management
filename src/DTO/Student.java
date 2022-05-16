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
public class Student {
    int id;
    String name;
    String address;
    String gender;
    Campus campus;
    
    //cóntructors

    public Student(int id, String name, String address, String gender, Campus campus) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.gender = gender;
        this.campus = campus;
    }

    public Student() {
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Campus getCampus() {
        return campus;
    }

    public void setCampus(Campus campus) {
        this.campus = campus;
    }
    
    //methods
    public void inputStudent(){
        id = MyToys.getAnInteger("Input student's ID: (integer only)", "Invalid ID!", 0);
        name = MyToys.getString("Input student's name: ", "Invalid name!");
        address = MyToys.getString("Input student's address: ", "Invalid address!");
        gender = MyToys.getId("Input student's gender: (M/F)", "Invalid gender!", "^[M|F|m|f]$");
    }
    
    public void outputStudent(){
        String show = String.format("|%-8s|%-5d|%-20s|%-40s|%-7s|%-10d|",
                "Student", getId(), getName(), getAddress(),getGender(),getCampus().getId());
        System.out.println(show);
    }
}
