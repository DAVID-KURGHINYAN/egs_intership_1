package com.egs.employee;

import com.egs.employee.model.EmployeeModel;
import com.egs.enums.Action;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class NetworkManager implements Comparator<EmployeeModel> {
  DataBase base = new DataBase();
  Scanner scan = new Scanner(System.in);
  private final File f = new File("C:\\employee\\employee.txt");

  public static void createEmployeesFile(List<EmployeeModel> emp, File f) {
    try (ObjectOutputStream out = new ObjectOutputStream(
        new BufferedOutputStream(new FileOutputStream(f)))) {
      for (EmployeeModel emp1 : emp) {
        out.writeObject(emp1);
      }
    } catch (IOException io) {
      System.err.println(io.getMessage());
    }
  }

  //Starting our program
  public void start() {
    createEmployeesFile(base.getList(), f);
    if (f.exists()) {
      System.out.println("Directory and file was created");
    }

    System.out.println("Welcome to our company!!!" + "\n");

    System.out.println("Please choose the language: hy, en or fr");
    String language = scan.nextLine();
    if (language.equals("en")) {
      System.out.println("Choose the action: ");
      System.out.println("""
          Press a to add a new employee
          Press d to delete an employee
          Press f to find an employee
          Press s to sort the employees
          Press p to print the employees
          Press q to quit
          Press h to help
          """);
    }
    Action action = Action.valueOf(scan.nextLine());
    switch (action) {
      case a -> addEmployee();
      case d -> deleteEmployee();
      case f -> findEmployee();
      case s -> sortEmployees();
      case p -> printEmployees();
      case q -> quit();
      case h -> help();
      default -> System.out.println("Do something else:");
    }
  }

  public void deleteEmployee() {
    System.out.println("Enter first name and last name:");
    String firstName = scan.nextLine();
    String lastName = scan.nextLine();
    for (int i = 0; i < base.getList().size(); i++) {
      if (firstName.equals(base.getList().get(i).getFirstName()) &&
          lastName.equals(base.getList().get(i).getLastName())) {
        base.getList().remove(base.getList().get(i));
      }
    }
  }

  public void addEmployee() {
    EmployeeModel employee = new EmployeeModel();
    System.out.println("Enter first name:");
    String firstName = scan.nextLine();
    validate(firstName);
    System.out.println("Enter last name։");
    String lastName = scan.nextLine();
    validate(lastName);
    employee.setLastName(lastName);
    System.out.println("Enter the birthday (YYYY-MM-DD): ");
    LocalDate birthday = LocalDate.parse(scan.nextLine());
    if (validateDate(birthday)){
      employee.setBirthday(birthday);
    }
  }

  public void findEmployee() {
    System.out.println("Enter firstname or lastname");
    String search = scan.nextLine();
    for (int i = 0; i < base.getList().size(); i++) {
      if (search.equals(base.getList().get(i).getFirstName()) ||
          search.equals(base.getList().get(i).getLastName())) {
        System.out.println(base.getList().get(i));
      }
    }
  }

  @Override
  public int compare(EmployeeModel a, EmployeeModel b) {

    return a.getFirstName().compareTo(b.getFirstName());
  }

  public void sortEmployees() {
    base.getList().sort(EmployeeModel::compare);
    saveObject();
    start();
  }

  private void saveObject() {

    try (ObjectOutputStream oos = new ObjectOutputStream(
        new FileOutputStream("C:\\temp\\employee.txt"))) {
      EmployeeModel p = new EmployeeModel("Sam", "Sam", LocalDate.of(2000, 10, 10));
      EmployeeModel e = new EmployeeModel("Gor", "Gor", LocalDate.of(2000, 10, 10));
      oos.writeObject(p);
      oos.writeObject(e);

      FileInputStream fi = new FileInputStream("C:\\temp\\employee.txt");
      ObjectInputStream oi = new ObjectInputStream(fi);


      EmployeeModel emp = (EmployeeModel) oi.readObject();
      EmployeeModel emp1 = (EmployeeModel) oi.readObject();

      System.out.println(emp.toString());
      System.out.println(emp1.toString());

      oi.close();
      fi.close();

    } catch (Exception ex) {
      System.out.println(ex.getMessage());
    }

  }

  public void printEmployees() {
    for (EmployeeModel employee : base.getList()) {
      System.out.println(employee);
    }
  }

  public void validate(String s) {
    boolean flag = true;
    for (int i = 0; i < s.length(); i++) {
      char ch = s.charAt(i);
      if (!(ch >= 'a' && ch <= 'z' || ch >= 'A' && ch <= 'Z')) {
        flag = false;
        break;
      }}
    if(flag)
      System.out.println("Given string is a proper name.");
    else
      System.err.println("Given string is a proper string is not a proper name.");
  }

  public boolean validateDate(LocalDate birthday){
    boolean bool = false;
    LocalDate today = LocalDate.now();
    if (today.getYear()-birthday.getYear()>=18){
      System.out.println("Employee added");
      bool = true;
    }else {
      System.err.println("You are a minor");
    }
    return bool;
  }
  //Program is over

  public void quit() {
    saveObject();
    System.exit(0);
  }

  //
  public void help() {
  }
}
