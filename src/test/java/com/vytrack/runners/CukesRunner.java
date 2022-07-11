package com.vytrack.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions (
        plugin = {
                "pretty",
                "html:target/cucumber-reports.html",
                "junit:target/cucumber-reports.xml",
                "rerun:target/rerun.txt",
                "me.jvt.cucumber.report.PrettyReports:target/cucumber"
        },
        features = "src/test/resources/features",
        glue = "com/vytrack/stepdefinitions",
        dryRun = false,
        publish = true
)

public class CukesRunner {
}
