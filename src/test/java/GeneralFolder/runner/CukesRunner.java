package GeneralFolder.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"json:target/cucumber.json",
                "html:target/default-html-reports",
                "rerun:target/rerun1.txt"
        },
        features = "src/test/resources/features",
        glue = "GeneralFolder/stepDefs",
        dryRun = false,
        tags = "@purchase"
)
public class CukesRunner {
}
