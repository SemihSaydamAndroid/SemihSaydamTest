package org.example;

import org.junit.Test;
import sun.rmi.runtime.Log;

public class LoginPageTest extends PageTest{

    @Test
    public void login(){
        new LoginPage(driver).login("testiniumtest@gmail.com","testinium123");
    }

}
