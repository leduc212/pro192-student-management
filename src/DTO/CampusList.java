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
public class CampusList {
    public ArrayList<Campus> campusList = new ArrayList<>();
    Scanner sc = new Scanner(System.in);
    
    //Tim campus theo ID
    public int searchCampusByID(int ID) {
        if (campusList.isEmpty()) {
            return -1;
        }
        for (int i = 0; i < campusList.size(); i++) {
            if (campusList.get(i).getId() == ID) {
                return i;
            }
        }
        return -1;
    }
    
    //Tim campus theo ID tra ve object
    public Campus searchCampusObjectByID(int ID) {
        if (campusList.isEmpty()) {
            return null;
        }
        for (Campus o : campusList) {
            if (o.getId() == ID) {
                return o;
            }
        }
        return null;
    }
    
    //Input Campus
    public void addNewCampus() {
        int id;
        String name, address;
        int pos;

        do {
            id = MyToys.getAnInteger("Input Campus' ID: (integer only)", "Invalid ID!", 0);
            pos = searchCampusByID(id);
            if (pos >= 0) {
                System.out.println("The Campus ID is already existed! "
                        + "Input another one!");
            }
        } while (pos != -1);

        name = MyToys.getString("Input Campus' name: ", "Invalid name!");
        address = MyToys.getString("Input Campus' address: ", "Invalid address!");
        campusList.add(new Campus(id, name, address));
        System.out.println("A Campus profile is added successfully.");
    }
    
    //Remove a Campus
    public void removeCampus() {
        int id;
        id = MyToys.getAnInteger("Input Campus' ID: (integer only)", "Invalid ID!", 0);
        if (searchCampusObjectByID(id) != null) {
            campusList.remove(searchCampusObjectByID(id));
            System.out.println("Campus removed successfully!");
        } else {
            System.out.println("Campus not found");
        }
    }
    
    //Xuat list
    public void printAscendingByID() {
        if (campusList.isEmpty()) {
            System.out.println("The Campus list is empty. Nothing to print");
            return;
        }
        
        Comparator ascID = new Comparator<Campus>() {
            @Override
            public int compare(Campus o1, Campus o2) {
                return o1.getId() - o2.getId();
            }
        };
        Collections.sort(campusList, ascID);

        String show = String.format("|%-8s|%-5s|%-20s|%-40s|",
                "Campus", "ID", "Name", "Address");
        System.out.println(show);
        System.out.println("------------------------------------------------------------------------------");
        for (Campus o : campusList) {
            o.outputCampus();
        }
    }
}
