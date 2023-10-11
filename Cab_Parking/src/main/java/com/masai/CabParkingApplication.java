package com.masai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
@PropertySource("classpath:application.properties")
public class CabParkingApplication {

	public static void main(String[] args) {
		
        Dotenv dotenv = Dotenv.configure().directory("D:/projects/Company_Assignments/Cab_Parking").load();
        
        System.setProperty("spring.datasource.url", dotenv.get("DATABASE_URL"));
        System.setProperty("spring.datasource.username", dotenv.get("DATABASE_USERNAME"));
        System.setProperty("spring.datasource.password", dotenv.get("DATABASE_PASSWORD"));
        
        int numberOfSlots = Integer.parseInt(dotenv.get("PARKING_LOT_SIZE"));
        System.out.println(numberOfSlots);
		
		SpringApplication.run(CabParkingApplication.class, args);
	}

}
