package com.egs.employee;

import com.egs.employee.model.EmployeeModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

class NetworkManagerTest {
    NetworkManager manager = new NetworkManager();
    EmployeeModel model = new EmployeeModel();
    @Test
    void start() {
        try {
            Assertions.assertEquals(manager.scan.next(), "hy");
            throw new NoSuchMethodException();
        }catch (NoSuchMethodException e){
            System.err.println(e.getMessage());
        }
    }

    @Test
    void deleteEmployeeFromListSuccess(int index) {
        try {
            Assertions.assertNull(manager.base.getList().get(index));
            throw new NoSuchElementException();
        }catch (NoSuchElementException e){
            System.err.println(e.getMessage());
        }
    }

    @Test
    void addEmployeeToListSuccess() {
        model.setFirstName("Ara");
        model.setLastName("yan");
        model.setBirthday(LocalDate.parse("1999-05-25"));
        manager.base.getList().add(model);
        Assertions.assertEquals(model.getFirstName(),"Ara");

    }

    @Test
    void findEmployeeFromListSuccess() {
        model.setLastName("Ara");
        model.setLastName("yan");
        List<EmployeeModel> list = manager.base.getList();
        list.add(model);
        try {
            for (EmployeeModel employeeModel : list) {
                Assertions.assertEquals(employeeModel.getFirstName(), "Ara");
                Assertions.assertEquals(employeeModel.getLastName(), "Ara");
            }
            throw new NoSuchElementException();
        }catch (NoSuchElementException e){
            System.err.println(e.getMessage());
        }
    }

    @Test
    void printEmployeesFromListSuccess() {
        model.setLastName("Ara");
        model.setLastName("yan");
        for (EmployeeModel employeeModel : manager.base.getList()) {
            Assertions.assertNotNull(employeeModel);
        }
    }

    @Test
    void quitFromProgramSuccess() {
    }
}