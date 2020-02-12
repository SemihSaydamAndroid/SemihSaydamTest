package org.example;

import org.junit.Test;

public class LoginPageTest extends PageTest{

    @Test
    public void login() {
        new LoginPage(driver).login("testiniumtest@gmail.com","testinium123");  // n11 Account
    }
}
