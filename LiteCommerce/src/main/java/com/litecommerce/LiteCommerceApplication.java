package com.litecommerce;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;

@SpringBootApplication
public class LiteCommerceApplication {
	public static String uploadDir = "";
	public static void main(String[] args) throws Exception{
		SpringApplication.run(LiteCommerceApplication.class, args);
		var staticFolder = new File(new ClassPathResource("/static").getURI()).getAbsoluteFile();
		var uploadFolder = new File(staticFolder + "/upload");
		if(!uploadFolder.exists()) {
			uploadFolder.mkdir();
		}
		uploadDir = uploadFolder.getAbsolutePath();
	}
	

}
