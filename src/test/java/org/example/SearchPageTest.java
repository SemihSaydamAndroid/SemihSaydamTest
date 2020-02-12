package org.example;

import org.junit.Test;

public class SearchPageTest extends PageTest {

    @Test
    public void search(){
        new SearchPage(driver).search("a");
    }
}
