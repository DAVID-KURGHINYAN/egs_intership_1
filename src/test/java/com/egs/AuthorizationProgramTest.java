package com.egs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AuthorizationProgramTest {
    private final AuthorizationProgram program;

    public AuthorizationProgramTest(){
        program =new AuthorizationProgram();
    }
    @Test
    public void user(){
        String[] loginList1 = {"Gor", "David", "Maria"};
        String[] passwordList = {"000", "111", "222"};
        Assertions.assertEquals(loginList1.length,program.loginList.length);
        Assertions.assertEquals(passwordList[1],program.passwordList[1]);
        Assertions.assertEquals(program.ALLOWABLE_NUMBER_OF_AUTHORIZATION_ATTEMPT,3);
        System.out.println("It`s ok");
    }
}