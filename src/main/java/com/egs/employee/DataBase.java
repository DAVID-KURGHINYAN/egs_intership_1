package com.egs.employee;

import com.egs.employee.model.EmployeeModel;

import java.util.ArrayList;
import java.util.List;

public class DataBase {
    private final List<EmployeeModel> list = new ArrayList<>();
    public DataBase() {
    }

    public List<EmployeeModel> getList() {
        return list;
    }
}

