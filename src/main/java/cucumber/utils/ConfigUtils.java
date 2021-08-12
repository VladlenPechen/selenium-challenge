package cucumber.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigUtils {
    public static void setProperties(Properties props, String propertiesFileRelativePath) {
        InputStream ios = null;
        try {
            ios = ConfigUtils.class.getClassLoader().getResourceAsStream(propertiesFileRelativePath);
            props.load(ios);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            if (ios != null) {
                try {
                    ios.close();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }
    }
}
