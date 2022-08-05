package runners;


import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;

import org.testng.annotations.DataProvider;

@CucumberOptions(

        plugin = {
                "pretty",
                "html:target/default-cucumber-reports.html",
                "json:target/json-reports/cucumber.json",
                "junit:target/xml-report/cucumber.xml",
                "rerun:target/failedRerun.txt" ,
             },
        features = "./src/test/resources/features",//path od features folder
        glue = {"hooks", "stepdefinitions"},//path of the step definitions folder
        tags = "",
        dryRun = false
)

public class CukesRunner extends AbstractTestNGCucumberTests{

    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }

}