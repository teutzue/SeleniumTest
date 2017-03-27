/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 *
 * @author CosticaTeodor
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SeleniumTest {

    WebDriver driver;

    public SeleniumTest() {
        System.setProperty("webdriver.chrome.driver", "/Users/CosticaTeodor/Downloads/drivers/chromedriver");
        driver = new ChromeDriver();
    }

    @Test
    public void test1() {
        //reset data
        com.jayway.restassured.RestAssured.given().get("http://localhost:3000/reset");
        //driver.get("http://localhost:3000/");
        driver.navigate().to("http://localhost:3000/");

        //verify the page is loaded, wait 10 seconds if it doesn't
        try {
            WebDriver.Timeouts t = driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
            assertEquals("Document", driver.getTitle());
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.err.println(e.getStackTrace());
        }

        int rowCount = driver.findElements(By.xpath("//table[@class='table']/tbody/tr")).size();
        assertEquals(rowCount, 5);

        driver.quit();
    }

    @Test
    public void test2() {
        //reset data
        com.jayway.restassured.RestAssured.given().get("http://localhost:3000/reset");
        //driver.get("http://localhost:3000/");
        driver.navigate().to("http://localhost:3000/");

        // Find the filter input element by its name
        WebElement searchFilter = driver.findElement(By.id("filter"));

        // Enter 2002 to search for
        searchFilter.sendKeys("2002");

        //check if there are two rows left
        int rowCount = driver.findElements(By.xpath("//table[@class='table']/tbody/tr")).size();
        System.out.println("Row count after filter is: " + rowCount);
        assertEquals(rowCount, 2);

        driver.quit();
    }

    @Test
    public void test3() {
        //reset data
        com.jayway.restassured.RestAssured.given().get("http://localhost:3000/reset");
        //driver.get("http://localhost:3000/");
        driver.navigate().to("http://localhost:3000/");

        // Find the filter input element by its name
        WebElement searchFilter = driver.findElement(By.id("filter"));

        // Enter 2002 to search for
        searchFilter.sendKeys("2002");

        //check if there are two rows left
        int rowCount = driver.findElements(By.xpath("//table[@class='table']/tbody/tr")).size();
        System.out.println("Row count after filter is: " + rowCount);
        assertEquals(2, rowCount);

        searchFilter.clear();

        int rows = driver.findElements(By.xpath("//table[@class='table']/tbody/tr")).size();
        System.out.println("Number of rows after clear: " + rows);
        assertEquals(5, rows);

        driver.quit();
    }

    @Test
    public void test4() {
        //reset data
        com.jayway.restassured.RestAssured.given().get("http://localhost:3000/reset");
        //driver.get("http://localhost:3000/");
        driver.navigate().to("http://localhost:3000/");

        WebElement yearFilter = driver.findElement(By.id("h_year"));
        yearFilter.click();

        WebElement row = driver.findElement(By.xpath("//tbody/tr[td='938']"));
        WebElement row940 = driver.findElement(By.xpath("//tbody/tr[td='940']"));

        List<WebElement> TDCollection = row.findElements(By.tagName("td"));
        for (WebElement td : TDCollection) {
            if (td.getText().equalsIgnoreCase("938")) {
                System.out.println("Id 938 found!");
                assertEquals(td.getText(), "938");
                break;
            }
        }

        List<WebElement> TDCollection2 = row940.findElements(By.tagName("td"));
        for (WebElement td : TDCollection2) {
            if (td.getText().equalsIgnoreCase("940")) {
                System.out.println("Id 940 found!");
                assertEquals(td.getText(), "940");
                break;
            }

        }
        driver.quit();
    }

    @Test
    public void test5() {
        //reset data
        com.jayway.restassured.RestAssured.given().get("http://localhost:3000/reset");
        //driver.get("http://localhost:3000/");
        driver.navigate().to("http://localhost:3000/");

        WebElement row = driver.findElement(By.xpath("//tbody/tr[td='938']"));
        WebElement a = row.findElements(By.tagName("a")).get(0);
        a.click();

        WebElement desc = driver.findElement(By.id("description"));
        desc.clear();
        desc.sendKeys("Cool car");

        WebElement saveButton = driver.findElement(By.id("save"));
        saveButton.click();

        WebElement r = driver.findElement(By.xpath("//tbody/tr[td='938']"));
        List<WebElement> TDColl = r.findElements(By.tagName("td"));
        for (WebElement td : TDColl) {
            if (td.getText().equalsIgnoreCase("Cool car")) {
                System.out.println("Cool car description found in row with the id 938!");
                assertEquals(td.getText(), "Cool car");
                break;
            }
        }

        driver.quit();
    }

    @Test
    public void test6() {
        //reset data
        com.jayway.restassured.RestAssured.given().get("http://localhost:3000/reset");
        //driver.get("http://localhost:3000/");
        driver.navigate().to("http://localhost:3000/");

        WebElement newButtonCar = driver.findElement(By.id("new"));
        newButtonCar.click();
        WebElement saveCarButton = driver.findElement(By.id("save"));
        saveCarButton.click();
        WebElement errMessage = driver.findElement(By.id("submiterr"));
        if (errMessage.getText().equalsIgnoreCase("All fields are required")) {
            System.out.println("Error message displayed!");
            assertEquals(errMessage.getText(), "All fields are required");
        }

        int rowCount = driver.findElements(By.xpath("//table[@class='table']/tbody/tr")).size();
        assertEquals(rowCount, 5);

        driver.quit();
    }

    @Test
    public void test7() {
        //reset data
        com.jayway.restassured.RestAssured.given().get("http://localhost:3000/reset");
        //driver.get("http://localhost:3000/");
        driver.navigate().to("http://localhost:3000/");

        WebElement newButtonCar2 = driver.findElement(By.id("new"));
        WebElement saveCarButton2 = driver.findElement(By.id("save"));
        newButtonCar2.click();

        WebElement year2 = driver.findElement(By.id("year"));
        WebElement registered2 = driver.findElement(By.id("registered"));
        WebElement make2 = driver.findElement(By.id("make"));
        WebElement model2 = driver.findElement(By.id("model"));
        WebElement description2 = driver.findElement(By.id("description"));
        WebElement price2 = driver.findElement(By.id("price"));

        year2.sendKeys("2008");
        registered2.sendKeys("2002-5-5");
        make2.sendKeys("Kia");
        model2.sendKeys("Rio");
        description2.sendKeys("As new");
        price2.sendKeys("31000");

        saveCarButton2.click();

        WebElement r = driver.findElement(By.xpath("//tbody/tr/td"));
        List<WebElement> tds = r.findElements(By.tagName("td"));
        tds.forEach((td) -> {
            if (td.getText().equalsIgnoreCase("2008")) {
                System.out.println("Year 2008 found!");
                assertEquals(td.getText(), "2008");
            } else if (td.getText().equalsIgnoreCase("2002-5-5")) {
                System.out.println("Registered on 2002-5-5 found!");
                assertEquals(td.getText(), "2002-5-5");
            } else if (td.getText().equalsIgnoreCase("Kia")) {
                System.out.println("Make Kia found!");
                assertEquals(td.getText(), "Kia");
            } else if (td.getText().equalsIgnoreCase("Rio")) {
                System.out.println("Model Rio found!");
                assertEquals(td.getText(), "Rio");
            } else if (td.getText().equalsIgnoreCase("As new")) {
                System.out.println("Description As new found!");
                assertEquals(td.getText(), "As new");
            } else if (td.getText().equalsIgnoreCase("31000")) {
                System.out.println("Price 31000 found!");
                assertEquals(td.getText(), "31000");
            }
        });

        driver.quit();
    }

}
