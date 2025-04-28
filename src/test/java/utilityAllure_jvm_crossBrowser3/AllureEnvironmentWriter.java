package utilityAllure_jvm_crossBrowser3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;

public class AllureEnvironmentWriter {
	/*
	 public static void writeEnvironmentProperties() {	 
		
		 /*
	        Properties props = new Properties();
	        props.setProperty("OS", System.getProperty("os.name"));
	        props.setProperty("Browser", "Chrome");
	        props.setProperty("Environment", "QA");
	        props.setProperty("BaseURL", "https://example.com");

	        try {
	            File resultsDir1 = new File("target/allure-results");
	            if (!resultsDir1.exists()) resultsDir1.mkdirs();

	            File file = new File(resultsDir1, "environment.properties");
	            try (FileOutputStream fos = new FileOutputStream(file)) {
	                props.store(fos, "Environment Properties for Allure Report");
	                System.out.println("✅ Allure environment.properties file generated!");
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }*/
		 
		 
		/* 
		  // Get the allure-results directory (from system property or use default "allure-results")
	      //  Path resultsDir = Paths.get(System.getProperty("allure.results.directory", "allure-results"));
		 
		 Path resultsDir = Paths.get("target", "allure-results");
		// Files.createDirectories(resultsDir);

	        // Ensure the directory exists
	        File directory = resultsDir.toFile();
	        if (!directory.exists()) {
	            directory.mkdirs();
	        }

	        // Prepare environment info
	        Map<String, String> environmentInfo = new LinkedHashMap<>();

	        WebDriver driver = BaseClassAllure_jvm_crossBrowser3.driver;

	        if (driver != null) {
	            try {
	                Capabilities caps = ((RemoteWebDriver) driver).getCapabilities();
	                String browserName = caps.getBrowserName();           // dynamic browser name
	                String browserVersion = caps.getBrowserVersion();     // dynamic browser version

	                environmentInfo.put("browser", browserName);
	                environmentInfo.put("browser Version", browserVersion);

	            } catch (Exception e) {
	                System.err.println("⚠ Error fetching browser capabilities: " + e.getMessage());
	            }
	        } else {
	            System.err.println("⚠ WebDriver is not initialized. Browser information will not be available.");
	        }

	        environmentInfo.put("Environment", "QA");
	        environmentInfo.put("Base URL", BaseClassAllure_jvm_crossBrowser3.allure_jvm_crossBrowser3_prop.getProperty("base.url"));
	        environmentInfo.put("OS", System.getProperty("os.name"));
	        environmentInfo.put("Java Version", System.getProperty("java.version"));
	       // environmentInfo.put("Browser", System.getProperty("browser"));

	        // Write to environment.properties inside allure-results
	        File envFile = new File(resultsDir.toFile(), "environment.properties");
	        try (BufferedWriter writer = new BufferedWriter(new FileWriter(envFile))) {
	            for (Map.Entry<String, String> entry : environmentInfo.entrySet()) {
	                writer.write(entry.getKey() + "=" + entry.getValue());
	                writer.newLine();
	            }
	            System.out.println("✔ environment.properties written to: " + envFile.getAbsolutePath());
	        } catch (IOException e) {
	            System.err.println("❌ Failed to write environment.properties: " + e.getMessage());
	        }
		 */
		 
	/*	 
		public static void writeEnvironmentProperties(String browserName) { 
		 
		 try {
	            Properties props = new Properties();
	            props.setProperty("Browser", browserName);
	            props.setProperty("OS", System.getProperty("os.name"));
	            
	            File dir = new File("target/allure-results");
	            if (!dir.exists()) {
	                dir.mkdirs();
	            }
	            File file = new File(dir, "environment.properties");

	            FileWriter writer = new FileWriter(file);
	            props.store(writer, "Environment Info");
	            writer.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		 
	    }*/




 @AfterSuite(alwaysRun = true)
public static void writeEnvironmentProperties(WebDriver driver, Properties configProps) {
    try {
        // 1. Set the allure-results directory path
    	
        Path resultsDir = Paths.get(System.getProperty("allure.results.directory", "allure-results"));
    	
        Files.createDirectories(resultsDir); // auto-create if not exists

        // 2. Prepare environment info
        Map<String, String> envInfo = new LinkedHashMap<>();

        // 3. Fetch browser details if driver is active
        if (driver != null) {
            try {
                Capabilities caps = ((RemoteWebDriver) driver).getCapabilities();
                envInfo.put("Browser", safe(caps.getBrowserName()));
                envInfo.put("Browser-Version", safe(caps.getBrowserVersion()));
            } catch (Exception e) {
                System.err.println("⚠ Could not fetch browser capabilities: " + e.getMessage());
            }
        } else {
            System.err.println("⚠ WebDriver not initialized yet. Skipping browser info.");
        }

        // 4. Other environment details
        envInfo.put("Environment", safe(configProps.getProperty("env.name", "QA"))); // fallback QA
        envInfo.put("Base URL", safe(configProps.getProperty("base.url")));
        envInfo.put("OS", safe(System.getProperty("os.name")));
        envInfo.put("OS-Version", safe(System.getProperty("os.version")));
        envInfo.put("Java Version", safe(System.getProperty("java.version")));

        // 5. Write into environment.properties file
        File envFile = resultsDir.resolve("environment.properties").toFile();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(envFile))) {
            for (Map.Entry<String, String> entry : envInfo.entrySet()) {
                if (!entry.getValue().isEmpty()) {
                    writer.write(entry.getKey() + "=" + entry.getValue());
                    writer.newLine();
                }
            }
            System.out.println("✔ environment.properties written successfully at: " + envFile.getAbsolutePath());
        }

    } catch (IOException e) {
        System.err.println("❌ Failed writing environment.properties: " + e.getMessage());
    }
}

// 6. Helper: handles null values safely
private static String safe(String value) {
    return value != null ? value.trim() : "";
}
	
/*	
	 @AfterSuite(alwaysRun = true)
	    public static void writeEnvironmentProperties() {

	        System.out.println("🛠 Writing environment.properties for Allure report...");

	        // Correct location inside target/allure-results
	        Path resultsDir = Paths.get("target", "allure-results");
	        try {
	            Files.createDirectories(resultsDir);
	        } catch (IOException e) {
	            System.err.println("❌ Failed to create allure-results directory: " + e.getMessage());
	            return;
	        }

	        // Prepare environment info
	        Map<String, String> environmentInfo = new LinkedHashMap<>();

	        // Optional: Capture dynamic browser info
	        WebDriver driver = BaseClassAllure_jvm_crossBrowser3.driver;
	        if (driver != null) {
	            try {
	                Capabilities caps = ((RemoteWebDriver) driver).getCapabilities();
	                environmentInfo.put("Browser", caps.getBrowserName());
	                environmentInfo.put("Browser Version", caps.getBrowserVersion());
	            } catch (Exception e) {
	                System.err.println("⚠ Failed to fetch browser capabilities: " + e.getMessage());
	            }
	        } else {
	            System.err.println("⚠ WebDriver not initialized during @AfterSuite. Skipping browser details.");
	        }

	        // Static properties
	        environmentInfo.put("Environment", "QA");
	        environmentInfo.put("Base URL", BaseClassAllure_jvm_crossBrowser3.allure_jvm_crossBrowser3_prop.getProperty("base.url"));
	        environmentInfo.put("OS", System.getProperty("os.name"));
	        environmentInfo.put("Java Version", System.getProperty("java.version"));

	        // Write to environment.properties
	        File envFile = resultsDir.resolve("environment.properties").toFile();
	        try (BufferedWriter writer = new BufferedWriter(new FileWriter(envFile))) {
	            for (Map.Entry<String, String> entry : environmentInfo.entrySet()) {
	                writer.write(entry.getKey() + "=" + entry.getValue());
	                writer.newLine();
	            }
	            System.out.println("✔ environment.properties successfully created at: " + envFile.getAbsolutePath());
	        } catch (IOException e) {
	            System.err.println("❌ Error writing environment.properties: " + e.getMessage());
	        }
	    }*/
		
}


