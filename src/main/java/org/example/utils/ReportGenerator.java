package org.example.utils;


import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ReportGenerator {

	public static void main(String[] args) {
		generateReport();
	}

	static String allure = System.getProperty("allure");

	public static void generateReport() {
		try {
			// Define the command to run Newman
			String[] command = {
					"/var/home/linuxbrew/.linuxbrew/bin/allure",
					"generate",
					"./test-output/allure-results/",
					"-c",
					"-o",
					"./test-output/allure-report",
					"--single-file"
			};
			LogUtils.logInfo("running command: ", String.join(" ", command));
			// Start the process
			Process process = new ProcessBuilder(command).start();
			// Read the output

			try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
				String line;
				while ((line = reader.readLine()) != null) {
					System.out.println(line);
				}
			}

			// Check if the process completed successfully
			int exitCode = process.waitFor();
			if (exitCode != 0) {
				throw new RuntimeException("Report Generating failed with exit code: " + exitCode);
			}

		} catch (Exception e) {
			LogUtils.logError("Failed to execute Postman test case", e.getMessage());
		}
	}


}
