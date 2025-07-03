package org.example;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import net.serenitybdd.cucumber.CucumberWithSerenity;


@CucumberOptions(features = "src/test/resources/features",
        glue = "com.example.stepdefinitions",

        plugin = {"pretty"})
@RunWith(CucumberWithSerenity.class)
/*@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("/features")*/
public class TestRunner {
}
