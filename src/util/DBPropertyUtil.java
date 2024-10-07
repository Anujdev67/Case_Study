package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DBPropertyUtil {

    public static Properties loadProperties() throws IOException {
        Properties properties = new Properties();
        FileInputStream input = null;
        try {
            // Load the properties file from the src/util directory
            input = new FileInputStream("src/util/db.properties");
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException("Could not load database properties file.");
        } finally {
            if (input != null) {
                input.close();
            }
        }
        return properties;
    }
}
