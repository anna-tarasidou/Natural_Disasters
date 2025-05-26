package com.app;

import java.awt.Desktop;
import java.net.URI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NaturalDisastersApplication {

	public static void main(String[] args) {
	    SpringApplication.run(NaturalDisastersApplication.class, args);

	    try {
	        if (Desktop.isDesktopSupported()) {
	            Desktop.getDesktop().browse(new URI("http://localhost:8080/disasters/"));
	        }
	    } catch (Exception e) {
	        System.err.println("Failed to open browser: " + e.getMessage());
	    }
	}
}
