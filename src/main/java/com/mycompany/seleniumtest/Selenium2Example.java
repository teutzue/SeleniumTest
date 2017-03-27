/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.seleniumtest;

/**
 *
 * @author CosticaTeodor
 */
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Selenium2Example {

    public static void main(String[] args) {
        // Create a new instance of the Firefox driver
        // Notice that the remainder of the code relies on the interface, 
        // not the implementation.

        System.setProperty("webdriver.chrome.driver", "/Users/CosticaTeodor/Downloads/drivers/chromedriver");
        System.setProperty("webdriver.safari.driver", "/usr/bin/safaridriver");

        //WebDriver driver = new ChromeDriver();
        WebDriver driver = new ChromeDriver();

        // And now use this to visit the cars program
        driver.get("http://localhost:3000/");
        // Alternatively the same thing can be done like this
        // driver.navigate().to("http://www.google.com");

        //verify the page is loaded, wait 10 seconds if it doesn't
        try {
            driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
            System.out.println("Page loaded!");
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.err.println(e.getStackTrace());
        }

        int rowCount = driver.findElements(By.xpath("//table[@class='table']/tbody/tr")).size();
        if (rowCount == 5) {
            System.out.println(rowCount + " is the row count!");
        } else {
            System.out.println("rowcount should be different!");
        }

        // Find the filter input element by its name
        WebElement searchFilter = driver.findElement(By.id("filter"));

        // Enter 2002 to search for
        searchFilter.sendKeys("2002");

        //check if there are two rows left
        rowCount = driver.findElements(By.xpath("//table[@class='table']/tbody/tr")).size();
        System.out.println("Row count after filter is: " + rowCount);

        searchFilter.clear();
        searchFilter.sendKeys("");

        rowCount = driver.findElements(By.xpath("//table[@class='table']/tbody/tr")).size();
        System.out.println("Row count after filter is: " + rowCount);
        //Close the browser
        driver.quit();
    }
}
