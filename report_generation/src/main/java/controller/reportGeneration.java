package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import service.reportGenerationService;

@RestController
@RequestMapping("/trakme_report")
//localhost:8081/trakme_report/generate_report
public class reportGeneration {
	@Autowired
	reportGenerationService reportgenerationservice;
		@GetMapping("/generate_report")
		public void generateReport() {
			try {
				reportgenerationservice.generateWoReport();
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		
}
