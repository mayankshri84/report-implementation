package com.example.utils;

import org.example.App;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CommonUtils {

    public Properties readObectRepository(){
        Properties properties = new Properties();
        try{
            InputStream input = new FileInputStream("object-repo.properties");
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    public String getObjectProperty(String str){
        return readObectRepository().getProperty(str);
    }

    public By getBy(String str){
        if(str.contains("xpath"))
            return By.xpath(str.substring(str.indexOf("=")+1));
        else
            return null;
    }

    public static void main(String[] args) {
        //WebDriver driver=new ChromeDriver();
        /*CommonUtils commonUtils = new CommonUtils();
        By prop = commonUtils.getBy(commonUtils.getObjectProperty("username"));
        System.out.println(prop);
*/
    }
}
