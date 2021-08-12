package cucumber.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.utils.ConfigUtils;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class WeatherAPTestStepdefs extends WeatherAPTestStepdefsBase {

    private int responseCode;
    private String responseBody;

    @Given("^Read Weather API endpoint URL$")
    public void connectToWeatherAPI() throws Throwable {
        ConfigUtils.setProperties(stepsProps, "test.properties");
        endpointURL = stepsProps.getProperty("api.base.url");
        userToken = stepsProps.getProperty("user.token");
        log = LoggerFactory.getLogger(this.getClass());
    }

    @And("^Compose query by adding \"([^\"]*)\" in \"([^\"]*)\" value$")
    public void composeQueryByAddingInValue(String param, String value) throws Throwable {
        List<String> params = Arrays.asList(param.split("\\s*;\\s*"));
        List<String> values = Arrays.asList(value.split("\\s*;\\s*"));
        for (String currParam : params) {
            endpointURL = String.format(endpointURL + "%s=%s", currParam, values.get(params.indexOf(currParam)));
            if (params.indexOf(currParam) + 1 < param.length()) {
                endpointURL += "&";
            }
        }
        endpointURL += String.format("&APPID=%s", userToken);

    }

    @And("^Request API$")
    public void requestAPI() {
        try {
            httpConnection = (HttpURLConnection) new URL(endpointURL).openConnection();
            httpConnection.setRequestMethod("GET");
            responseCode = httpConnection.getResponseCode();
            BufferedReader br = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));
            String output;
            while ((output = br.readLine()) != null) {
                responseBody += output + "\n";
            }
        } catch (IOException ioe) {
            log.error(ioe.getMessage());
        } finally {
            httpConnection.disconnect();
        }
    }

    @Then("^Check that response status is \"([^\"]*)\"$")
    public void checkThatResponseStatusIs(String status) throws Throwable {
        Assert.assertEquals(Integer.parseInt(status), responseCode, String.format(
                "Expected response code %s but was: ", status, responseCode));
    }

    @And("^Check fields in response \"([^\"]*)\"$")
    public void checkFieldsInResponse(String expectedInBody) throws Throwable {
        Assert.assertTrue(responseBody.contains(expectedInBody), String.format(
                "Expected %s contains in %s but was absent", expectedInBody, responseBody));
    }


}
