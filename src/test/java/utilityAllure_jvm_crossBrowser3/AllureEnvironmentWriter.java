package utilityAllure_jvm_crossBrowser3;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class AllureEnvironmentWriter {
	
	 public static void writeEnvironmentProperties() {
	        Properties props = new Properties();
	        props.setProperty("OS", System.getProperty("os.name"));
	        props.setProperty("Browser", "Chrome");
	        props.setProperty("Environment", "QA");
	        props.setProperty("BaseURL", "https://example.com");

	        try {
	            File resultsDir = new File("target/allure-results");
	            if (!resultsDir.exists()) resultsDir.mkdirs();

	            File file = new File(resultsDir, "environment.properties");
	            try (FileOutputStream fos = new FileOutputStream(file)) {
	                props.store(fos, "Environment Properties for Allure Report");
	                System.out.println("✅ Allure environment.properties file generated!");
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

}
