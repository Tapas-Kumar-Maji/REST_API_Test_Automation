package practice_7Oct2025.section14_cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = { "src/test/resources/feature_files" }, glue = { "practice_7Oct2025/section14_cucumber" }, 
	plugin = { "pretty", "html:reports/cucumberReports.html" }, monochrome = false)
public class GoogleMapsApiRunner extends AbstractTestNGCucumberTests {

}
