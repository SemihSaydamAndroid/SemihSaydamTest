package org.example;

    //TODO Semih Saydam

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Random;

public class LoginPage {
    WebDriver webDriver;
    WebDriverWait webDriverWait;

    public LoginPage(WebDriver webDriver) {         //Constructor
        this.webDriver = webDriver;
        this.webDriverWait = new WebDriverWait(webDriver,30,150);
    }

    public void login(String username, String password) {
        ////_____________________________________________________________________________LOGIN__________________________________________________________________________

        webDriver.get("https://www.n11.com/");
        Assert.assertEquals("n11.com - Alışverişin Uğurlu Adresi",webDriver.getTitle());  // Control .get("https://www.n11.com")
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.className("btnSignIn"))).click();
        Assert.assertEquals("Giriş Yap - n11.com",webDriver.getTitle()); // Control Login page
        webDriver.findElement(By.id("email")).clear();                     //clear e-mail text area
        webDriver.findElement(By.id("email")).sendKeys(username);         // write e-mail
        webDriver.findElement(By.id("password")).clear();                //clear password text area
        webDriver.findElement(By.id("password")).sendKeys(password);     // write password
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("loginButton"))).click();
        Assert.assertEquals("n11.com - Alışverişin Uğurlu Adresi",webDriver.getTitle());  // Control to Come back to Home page

        ////________________________________________________________________________________SEARCH______________________________________________________________________

        WebElement searchData = webDriver.findElement(By.id("searchData"));
        searchData.sendKeys("Bilgisayar");
        searchData.sendKeys(Keys.ENTER);

        ////________________________________________________________________________________2ND PAGE______________________________________________________________________

        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"contentListing\"]/div/div/div[2]/div[4]/a[2]"))).click();
        //webDriver.findElement(By.xpath("//*[@id=\"contentListing\"]/div/div/div[2]/div[4]/a[2]")).click();
        ////control 2nd page
        WebElement currentPage = webDriver.findElement(By.xpath("//*[@id=\"currentPage\"]"));
        String page = currentPage.getAttribute("value").toString();
        Assert.assertTrue("2.sayfaya ulaşılamadı", page.equals("2"));
        ////

        ////________________________________________________________________________________RANDOM SELECT_________________________________________________________________

        // TODO Random select product(not working. I select first item because only this clickable)
        List<WebElement> products = webDriver.findElements(By.cssSelector("div[class='pro']"));
        //Random rnd = new Random();
        //int index = rnd.nextInt(27)+4;   5-32 list items
        // index += 4;
        //products.get(index).click();
        products.get(5).click();

        ////________________________________________________________________________________BASKET _________________________________________________________________________

        // get the Search page price(for compare with basket prize)
        WebElement productPrice = webDriver.findElement(By.cssSelector("input[class='productDisplayPrice']"));
        String value = productPrice.getAttribute("value").toString();   // Search page price

        //Click "Sepete ekle" button
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[class='btn btnGrey btnAddBasket']"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[class='btn btnBlack btnGoBasket']"))).click();  // go to basket

        // TODO Clear the basket (not working)____(i want to clear basket because if u have items in there you cant pass Control prize)
        //webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("allItemSelected"))).click();
       //webDriverWait.until(ExpectedConditions.elementToBeClickable(By.className("removeProd svgIcon svgIcon_trash"))).click();
        //

        //________________________________________________________________________CONTROL PRİZE__________________________________________________________________________

        WebElement productBasketPrice = webDriver.findElement(By.className("productPrice"));
        String basketValue = productBasketPrice.getAttribute("value").toString();
        //basketValue = "6423.55"; //Test my assertTure command line
        Assert.assertTrue("Sayfadaki fiyat ile Sepetteki fiyat aynı değil!",value.equals(basketValue));  // compare value of search page prize with basket prize

        //________________________________________________________________________INCREASE PRODUCT__________________________________________________________________________
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("span[class='spinnerUp spinnerArrow']"))).click(); // increase products to 2
        //Control products pieces
        WebElement productCount = webDriver.findElement(By.className("quantity"));
        String productValue = productCount.getAttribute("value").toString();
        Assert.assertTrue("Lütfen adeti 2 yapınız.",productValue.equals("2"));

        ////___________________________________________________________________ERASE AND CONTROL "Sepetiniz boş"__________________________________________________________

        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("span[title='Sil']"))).click();   ///Erase basket item

        //Control "Sepetiniz Boş"
        WebElement emptyBasket = webDriver.findElement(By.cssSelector("h2[class='title']"));
        String controlEmpty = emptyBasket.getText().toString();
        Assert.assertTrue("Sepetiniz boş değil",controlEmpty.equals("Sepetiniz Boş")); /// I try "Sepetinizzzz boş" for assertTrue. And it will work correctly


        webDriver.quit();
    }

}



