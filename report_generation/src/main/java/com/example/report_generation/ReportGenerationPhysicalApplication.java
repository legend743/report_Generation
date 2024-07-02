package com.example.report_generation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages = {"controller", "service", "serviceImpl"})
@EntityScan(basePackages = {"Entity"})
public class ReportGenerationPhysicalApplication {
	

	public static void main(String[] args) {
		SpringApplication.run(ReportGenerationPhysicalApplication.class, args);
	}

}
