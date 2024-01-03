package com.rizvankarimov.appointment_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class AppointmentAppApplication {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	} // this is for encoding passwords

	public static void main(String[] args) {
		SpringApplication.run(AppointmentAppApplication.class, args);
	}

}
