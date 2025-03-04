/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pp3.st.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author Mati
 */
public class ConfigReader {
    private static final String CONFIG_FILE = "src/pp3/st/config.properties";

    public static Properties getProperties() {
        
        
        Properties properties = new Properties();
        try {
            
            FileInputStream inputStream = new FileInputStream(CONFIG_FILE);
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            e.getCause();
        }
        return properties;
    }
}
