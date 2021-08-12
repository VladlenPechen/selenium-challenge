package cucumber.steps;

import org.slf4j.Logger;

import java.net.HttpURLConnection;
import java.util.Properties;

abstract class WeatherAPTestStepdefsBase {
    Properties stepsProps = new Properties();
    String endpointURL;
    String userToken;
    HttpURLConnection httpConnection;
    Logger log;
}