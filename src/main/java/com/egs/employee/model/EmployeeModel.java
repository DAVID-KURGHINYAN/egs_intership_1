package com.egs.employee.model;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class EmployeeModel implements Serializable {
    private static final long serialVersionVID = 1L;
    private String firstName;
    private String lastName;
    private LocalDate birthday;

    public EmployeeModel() {
    }

    public EmployeeModel(String firstName, String lastName, LocalDate birthday) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
    }

    public static int compare(EmployeeModel model, EmployeeModel model1) {
        return model.getFirstName().compareTo(model1.getFirstName());
    }
}
