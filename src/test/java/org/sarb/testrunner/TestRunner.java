package org.sarb.testrunner;

import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import net.serenitybdd.cucumber.CucumberWithSerenity;


@CucumberOptions(features = "src/test/resources/features",
        glue = "org.sarb.stepdefinitions",
        tags = "@Sanity",
        plugin = {"pretty"})
@RunWith(CucumberWithSerenity.class)
/*@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("/features")*/
public class TestRunner {

}
