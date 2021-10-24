package com.egs.employee.controller;

import com.egs.employee.NetworkManager;

public class EmployeeController {
    private final NetworkManager network;

    public EmployeeController() {
        network = new NetworkManager();
    }

    public void start() {
        network.start();
    }
}
