/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DTO.Campus;
import DTO.CampusList;
import DTO.Course;
import DTO.CourseList;
import DTO.Registration;
import DTO.Student;
import DTO.StudentList;
import java.util.ArrayList;
import java.util.Scanner;
import util.MyToys;

/**
 *
 * @author HP
 */
public class Program {

    public static void main(String[] args) {
        CampusList camp = new CampusList();
        StudentList s = new StudentList();
        CourseList c = new CourseList();
        ArrayList<Registration> registrationLog = new ArrayList<>();

        camp.campusList = initCampus();
        s.studentList = initStudent();
        c.courseList = initCourse();
        registrationLog = initRegistration();

        int choice;
        Scanner sc = new Scanner(System.in);

        do {
            menu();
            choice = MyToys.getAnInteger("", "Input a valid option, please!", 1, 5);

            switch (choice) {
                case 1:
                    boolean flag = true;
                    do {
                        menu1();
                        int choice1 = MyToys.getAnInteger("", "Input a valid option, please!", 1, 5);
                        switch (choice1) {
                            case 1:
                                System.out.println("===============================================================");
                                System.out.println("You have chosen to: Add a new Campus!");
                                camp.addNewCampus();
                                break;
                            case 2:
                                System.out.println("===============================================================");
                                System.out.println("You have chosen to: Remove a Campus");
                                camp.removeCampus();
                                break;
                            case 3:
                                System.out.println("===============================================================");
                                System.out.println("You have chosen to: Show the list of Campus");
                                camp.printAscendingByID();
                                break;
                            case 4:
                                System.out.println("===============================================================");
                                System.out.println("You have chosen to: Search a Campus with ID and show its students");
                                int id;
                                id = MyToys.getAnInteger("Input Campus's ID: (integer only)", "Invalid ID!", 0);
                                if (camp.searchCampusObjectByID(id) != null) {
                                    String show = String.format("|%-8s|%-5s|%-20s|%-40s|",
                                            "Campus", "ID", "Name", "Address");
                                    System.out.println(show);
                                    System.out.println("------------------------------------------------------------------------------");
                                    camp.searchCampusObjectByID(id).outputCampus();
                                    System.out.println("");
                                } else {
                                    System.out.println("Campus not found");
                                }
                                for (Student student : s.studentList) {
                                    if (student.getCampus().getId() == id) {
                                        String show = String.format("|%-8s|%-5s|%-20s|%-40s|%-7s|%-10s|",
                                                "Student", "ID", "Name", "Address", "Gender", "Campus ID");
                                        System.out.println(show);
                                        System.out.println("-------------------------------------------------------------------------------------------------");
                                        break;
                                    }
                                }
                                for (Student student : s.studentList) {
                                    if (student.getCampus().getId() == id) {
                                        student.outputStudent();
                                    }
                                }
                                break;
                            case 5:
                                System.out.println("Return to main menu!");
                                flag = false;
                                break;
                        }

                    } while (flag);
                    break;
                case 2:
                    flag = true;
                    do {
                        menu2();
                        int choice2 = MyToys.getAnInteger("", "Input a valid option, please!", 1, 6);
                        switch (choice2) {
                            case 1:
                                System.out.println("===============================================================");
                                System.out.println("You have chosen to: Add a new Student!");
                                s.addNewStudent(camp);
                                break;
                            case 2:
                                System.out.println("===============================================================");
                                System.out.println("You have chosen to: Change the campus of a Student!");
                                s.updateCampus(camp);
                                break;
                            case 3:
                                System.out.println("===============================================================");
                                System.out.println("You have chosen to: Remove a Student");
                                s.removeStudent();
                                break;
                            case 4:
                                System.out.println("===============================================================");
                                System.out.println("You have chosen to: Show the list of Student");
                                s.printAscendingByID();
                                break;
                            case 5:
                                System.out.println("===============================================================");
                                System.out.println("You have chosen to: Search a Student with ID and show their courses");
                                int id;
                                id = MyToys.getAnInteger("Input Student's ID: (integer only)", "Invalid ID!", 0);
                                if (s.searchStudentObjectByID(id) != null) {
                                    String show = String.format("|%-8s|%-5s|%-20s|%-40s|%-7s|%-10s|",
                                            "Student", "ID", "Name", "Address", "Gender", "Campus ID");
                                    System.out.println(show);
                                    System.out.println("-------------------------------------------------------------------------------------------------");
                                    s.searchStudentObjectByID(id).outputStudent();
                                    System.out.println("");
                                    for (Registration log : registrationLog) {
                                        int studentID = log.getStudent().getId();
                                        if (studentID == id) {
                                            String show1 = String.format("|%-8s|%-5s|%-25s|%-6s|",
                                                    "Course", "ID", "Name", "Credit");
                                            System.out.println(show1);
                                            System.out.println("-------------------------------------------------");
                                            break;
                                        }
                                    }
                                    for (Registration log : registrationLog) {
                                        int studentID = log.getStudent().getId();
                                        Course course = log.getCourse();

                                        if (studentID == id) {
                                            course.outputCourse();
                                        }
                                    }
                                } else {
                                    System.out.println("Student not found");
                                }
                                break;
                            case 6:
                                System.out.println("Return to main menu!");
                                flag = false;
                                break;
                        }

                    } while (flag);
                    break;
                case 3:
                    flag = true;
                    do {
                        menu3();
                        int choice3 = MyToys.getAnInteger("", "Input a valid option, please!", 1, 6);
                        switch (choice3) {
                            case 1:
                                System.out.println("===============================================================");
                                System.out.println("You have chosen to: Add a new Course!");
                                c.addNewCourse();
                                break;
                            case 2:
                                System.out.println("===============================================================");
                                System.out.println("You have chosen to: Update the credit of a Course!");
                                c.updateCredit();
                                break;
                            case 3:
                                System.out.println("===============================================================");
                                System.out.println("You have chosen to: Remove a Course");
                                c.removeCourse();
                                break;
                            case 4:
                                System.out.println("===============================================================");
                                System.out.println("You have chosen to: Show the list of Courses");
                                c.printAscendingByID();
                                break;
                            case 5:
                                System.out.println("===============================================================");
                                System.out.println("You have chosen to: Search a Course with ID and show which Student is enrolled in that course");
                                int id;
                                id = MyToys.getAnInteger("Input Course's ID: (integer only)", "Invalid ID!", 0);
                                if (c.searchCourseObjectByID(id) != null) {
                                    String show = String.format("|%-8s|%-5s|%-25s|%-6s|",
                                            "Course", "ID", "Name", "Credit");
                                    System.out.println(show);
                                    System.out.println("-------------------------------------------------");
                                    c.searchCourseObjectByID(id).outputCourse();
                                    System.out.println("");
                                    for (Registration log : registrationLog) {
                                        int courseID = log.getCourse().getId();
                                        if (courseID == id) {
                                            String show1 = String.format("|%-8s|%-5s|%-20s|%-40s|%-7s|%-10s|",
                                                    "Student", "ID", "Name", "Address", "Gender", "Campus ID");
                                            System.out.println(show1);
                                            System.out.println("-------------------------------------------------------------------------------------------------");
                                            break;
                                        }
                                    }
                                    for (Registration log : registrationLog) {
                                        int courseID = log.getCourse().getId();
                                        Student student = log.getStudent();

                                        if (courseID == id) {
                                            student.outputStudent();
                                        }
                                    }
                                } else {
                                    System.out.println("Course not found");
                                }
                                break;
                            case 6:
                                System.out.println("Return to main menu!");
                                flag = false;
                                break;
                        }

                    } while (flag);
                    break;
                case 4:
                    flag = true;
                    do {
                        menu4();
                        int choice3 = MyToys.getAnInteger("", "Input a valid option, please!", 1, 4);
                        switch (choice3) {
                            case 1:
                                System.out.println("===============================================================");
                                System.out.println("You have chosen to: Add a new course registration!");
                                System.out.println("Input a student and a course that is enrolled by the student!");
                                //Tim Student
                                int studentID,
                                 courseID;
                                studentID = MyToys.getAnInteger("Input Student's ID: (integer only)", "Invalid ID!", 0);
                                if (s.searchStudentObjectByID(studentID) != null) {
                                    String show = String.format("|%-8s|%-5s|%-20s|%-40s|%-7s|%-10s|",
                                            "Student", "ID", "Name", "Address", "Gender", "Campus ID");
                                    System.out.println(show);
                                    System.out.println("-------------------------------------------------------------------------------------------------");
                                    s.searchStudentObjectByID(studentID).outputStudent();
                                } else {
                                    System.out.println("Student not found");
                                    break;
                                }
                                //Tim course
                                courseID = MyToys.getAnInteger("Input Course's ID: (integer only)", "Invalid ID!", 0);
                                if (c.searchCourseObjectByID(courseID) != null) {
                                    String show = String.format("|%-8s|%-5s|%-25s|%-6s|",
                                            "Course", "ID", "Name", "Credit");
                                    System.out.println(show);
                                    System.out.println("-------------------------------------------------");
                                    c.searchCourseObjectByID(courseID).outputCourse();
                                } else {
                                    System.out.println("Course not found");
                                    break;
                                }
                                //Add vao 1 list
                                registrationLog.add(new Registration(s.searchStudentObjectByID(studentID), c.searchCourseObjectByID(courseID)));
                                System.out.println("Added a new log successfully!");
                                break;
                            case 2:
                                System.out.println("===============================================================");
                                System.out.println("You have chosen to: Show all course registration log!");
                                int n = 0;
                                if (registrationLog.isEmpty()) {
                                    System.out.println("The registration log is empty. Nothing to print");
                                    break;
                                }
                                String show = String.format("|%-8s|%-10s|%-20s|%-10s|%-25s|%-6s|",
                                        "Log ", "Student ID", "Student Name", "Course ID", "Course Name", "Credit");
                                System.out.println(show);
                                System.out.println("--------------------------------------------------------------------------------------");
                                for (Registration log : registrationLog) {
                                    n += 1;
                                    int tmpStudentID = log.getStudent().getId();
                                    String tmpStudentName = log.getStudent().getName();
                                    int tmpCourseID = log.getCourse().getId();
                                    String tmpCourseName = log.getCourse().getName();
                                    int tmpCredit = log.getCourse().getCredit();

                                    show = String.format("|%-8d|%-10d|%-20s|%-10d|%-25s|%-6d|",
                                            n, tmpStudentID, tmpStudentName, tmpCourseID, tmpCourseName, tmpCredit);
                                    System.out.println(show);
                                }
                                break;
                            case 3:
                                System.out.println("===============================================================");
                                System.out.println("You have chosen to: Remove a course registration log!");
                                if (registrationLog.isEmpty()) {
                                    System.out.println("The registration log list is empty. Nothing to remove!");
                                    break;
                                }
                                System.out.println("Input a student's ID and choose which course you want to remove!");
                                int sID;
                                Student studentObj = null;
                                sID = MyToys.getAnInteger("Input Student's ID: (integer only)", "Invalid ID!", 0);

                                for (int i = 0; i < registrationLog.size(); i++) {
                                    if (registrationLog.get(i).getStudent().getId() == sID) {
                                        studentObj = registrationLog.get(i).getStudent();
                                        break;
                                    }
                                }
                                if (studentObj != null) {
                                    String show1 = String.format("|%-8s|%-5s|%-20s|%-40s|%-7s|%-10s|",
                                            "Student", "ID", "Name", "Address", "Gender", "Campus ID");
                                    System.out.println(show1);
                                    System.out.println("-------------------------------------------------------------------------------------------------");
                                    s.searchStudentObjectByID(sID).outputStudent();
                                    System.out.println("This student is attending these courses:");
                                    for (Registration log : registrationLog) {
                                        studentID = log.getStudent().getId();
                                        if (studentID == sID) {
                                            String show2 = String.format("|%-8s|%-5s|%-25s|%-6s|",
                                                    "Course", "ID", "Name", "Credit");
                                            System.out.println(show2);
                                            System.out.println("-------------------------------------------------");
                                            break;
                                        }
                                    }
                                    for (Registration log : registrationLog) {
                                        studentID = log.getStudent().getId();
                                        Course course = log.getCourse();

                                        if (studentID == sID) {
                                            course.outputCourse();
                                        }
                                    }

                                    int cID;
                                    Registration rl = null;
                                    cID = MyToys.getAnInteger("Input ID of the course that you want to remove from this student: (integer only)", "Invalid ID!", 0);
                                    for (Registration regLog1 : registrationLog) {
                                        if ((regLog1.getStudent().getId() == sID) && (regLog1.getCourse().getId() == cID)) {
                                            rl = regLog1;
                                        }
                                    }
                                    if (rl != null) {
                                        registrationLog.remove(rl);
                                        System.out.println("Remove successfully!");
                                    } else {
                                        System.out.println("There is no course with this ID that the student is attending!");
                                    }

                                } else {
                                    System.out.println("There is no student with this ID in the registration log!");
                                }

                                break;
                            case 4:
                                System.out.println("Return to main menu!");
                                flag = false;
                                break;
                        }
                    } while (flag);
                    break;

                case 5:
                    System.out.println("Exiting...");
                    return;
            }

        } while (true);
    }

    static void menu() {
        System.out.println("===========================MainMenu============================");
        System.out.println("**          1. Campus management                             **");
        System.out.println("**          2. Student management                            **");
        System.out.println("**          3. Course management                             **");
        System.out.println("**          4. Course Registration Management                **");
        System.out.println("**          5. Exit                                          **");
        System.out.println("===============================================================");
        System.out.println(">Input your choice: ");
    }

    static void menu1() {
        System.out.println("=======================CampusManagement========================");
        System.out.println("**      1. Add a new Campus                                  **");
        System.out.println("**      2. Remove a Campus                                   **");
        System.out.println("**      3. Show the list of Campus                           **");
        System.out.println("**      4. Search a Campus with ID and show its students     **");
        System.out.println("**      5. Return to main menu                               **");
        System.out.println("===============================================================");
        System.out.println(">Input your choice: ");

    }

    static void menu2() {
        System.out.println("=======================StudentManagement=======================");
        System.out.println("**  1. Add a new Student                                     **");
        System.out.println("**  2. Change the Campus of a Student                        **");
        System.out.println("**  3. Remove a Student                                      **");
        System.out.println("**  4. Show the list of Student                              **");
        System.out.println("**  5. Search a Student with ID and show their courses       **");
        System.out.println("**  6. Return to main menu                                   **");
        System.out.println("===============================================================");
        System.out.println(">Input your choice: ");
    }

    static void menu3() {
        System.out.println("==============================CourseManagement===============================");
        System.out.println("**1. Add a new Course                                                      **");
        System.out.println("**2. Update the credit of a Course                                         **");
        System.out.println("**3. Remove a Course                                                       **");
        System.out.println("**4. Show the list of Course                                               **");
        System.out.println("**5. Search a Course with ID and show which Student enrolled in this course**");
        System.out.println("**6. Return to main menu                                                   **");
        System.out.println("=============================================================================");
        System.out.println(">Input your choice: ");
    }

    static void menu4() {
        System.out.println("================CourseRegistrationManagement===================");
        System.out.println("**      1. Add a new course registration log                 **");
        System.out.println("**      2. Show all course registration log                  **");
        System.out.println("**      3. Remove a log                                      **");
        System.out.println("**      4. Return to main menu                               **");
        System.out.println("===============================================================");
        System.out.println(">Input your choice: ");
    }

    static ArrayList<Campus> initCampus() {
        ArrayList<Campus> ds = new ArrayList<>();
        ds.add(new Campus(1, "Campus HoChiMinh", "D1, Khu cong nghe cao, Q9, TPHCM"));
        ds.add(new Campus(2, "Campus Ha Noi", "Khu cong nghe cao Hoa Lac, Ha Noi"));
        ds.add(new Campus(3, "Campus Can Tho", "Nguyen Van cu, Ninh Kieu, Can Tho"));
        ds.add(new Campus(4, "Campus Da Nang", "Khu do thi FPT City, Da Nang"));
        return ds;
    }

    static ArrayList<Student> initStudent() {
        ArrayList<Student> ds = new ArrayList<>();

        CampusList o = new CampusList();
        o.campusList = initCampus();

        ds.add(new Student(1, "Le Minh Duc", "Ho Chi Minh", "M", o.searchCampusObjectByID(1)));
        ds.add(new Student(2, "Pham Nhat Vuong", "Ha Noi", "M", o.searchCampusObjectByID(2)));
        ds.add(new Student(3, "Son Tung MTP", "Ho Chi Minh", "M", o.searchCampusObjectByID(1)));
        ds.add(new Student(4, "Chipu", "Can Tho", "F", o.searchCampusObjectByID(3)));
        ds.add(new Student(5, "Erik", "Da Nang", "M", o.searchCampusObjectByID(4)));
        ds.add(new Student(6, "Justatee", "Ha Noi", "M", o.searchCampusObjectByID(2)));
        ds.add(new Student(10, "Ronaldo", "Bo Dao Nha", "M", o.searchCampusObjectByID(4)));
        ds.add(new Student(20, "Messi", "Argentina", "M", o.searchCampusObjectByID(3)));
        return ds;
    }

    static ArrayList<Course> initCourse() {
        ArrayList<Course> ds = new ArrayList<>();
        ds.add(new Course(1, "Discrete Math", 3));
        ds.add(new Course(2, "Engineering Math", 4));
        ds.add(new Course(3, "Programming fundamental", 5));
        ds.add(new Course(4, "OOP programming", 4));
        ds.add(new Course(5, "Computer Science 1", 3));
        ds.add(new Course(6, "Physics 1", 4));
        ds.add(new Course(10, "Physics 2", 3));
        ds.add(new Course(15, "Operating System", 3));
        return ds;
    }

    static ArrayList<Registration> initRegistration() {
        ArrayList<Registration> ds = new ArrayList<>();

        StudentList s = new StudentList();
        s.studentList = initStudent();

        CourseList c = new CourseList();
        c.courseList = initCourse();

        ds.add(new Registration(s.searchStudentObjectByID(1), c.searchCourseObjectByID(1)));
        ds.add(new Registration(s.searchStudentObjectByID(1), c.searchCourseObjectByID(2)));
        ds.add(new Registration(s.searchStudentObjectByID(2), c.searchCourseObjectByID(1)));
        ds.add(new Registration(s.searchStudentObjectByID(3), c.searchCourseObjectByID(3)));
        ds.add(new Registration(s.searchStudentObjectByID(4), c.searchCourseObjectByID(1)));
        ds.add(new Registration(s.searchStudentObjectByID(1), c.searchCourseObjectByID(3)));
        ds.add(new Registration(s.searchStudentObjectByID(2), c.searchCourseObjectByID(5)));
        ds.add(new Registration(s.searchStudentObjectByID(3), c.searchCourseObjectByID(2)));
        return ds;
    }
}
