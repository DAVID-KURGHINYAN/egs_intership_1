package com.egs;

import lombok.Data;

import java.util.Scanner;

public class AuthorizationProgram {
    Scanner scan = new Scanner(System.in);
    boolean userAuthorized = false;
    String[] loginList = {"Gor", "David", "Maria"};
    String[] passwordList = {"000", "111", "222"};
    int authorizationAttemptCounter = 0;
    final int ALLOWABLE_NUMBER_OF_AUTHORIZATION_ATTEMPT = 3;
    boolean authorizationAttemptAvailable = authorizationAttemptCounter < ALLOWABLE_NUMBER_OF_AUTHORIZATION_ATTEMPT;

    public void user() {
        while (authorizationAttemptAvailable) {
            String login, password;
            {
                System.out.println("Enter your login: ");
                login = scan.nextLine();
                System.out.println("Enter your password: ");
                password = scan.nextLine();
            }
            {
                int index = 0;
                while (index < loginList.length && index < passwordList.length) {
                    boolean loginMatch, passwordMatch;
                    {
                        String loginByCurrentIndex = loginList[index];
                        loginMatch = loginByCurrentIndex.equals(login);
                        String passwordByCurrentIndex = passwordList[index];
                        passwordMatch = passwordByCurrentIndex.equals(password);

                    }
                    if (loginMatch && passwordMatch) {
                        userAuthorized = true;
                        break;
                    } else {
                        index++;
                    }

                }
            }
            if (userAuthorized) {
                System.out.println("You authorized successfully");
                break;
            } else {
                System.out.println("Your login or password are incorrect.");
                authorizationAttemptAvailable = ++authorizationAttemptCounter < ALLOWABLE_NUMBER_OF_AUTHORIZATION_ATTEMPT;

                if (authorizationAttemptAvailable) {
                    continue;
                } else {
                    System.out.println("You are exhausted number of authorization attempts. " +
                            "Please contact your administrator.");
                    break;
                }
            }
        }
    }

//    public static void main(String[] args) {
//        AuthorizationProgram program = new AuthorizationProgram();
//        program.user();
//    }
}
