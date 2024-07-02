package serviceImpl;

import org.springframework.stereotype.Service;

import service.reportGenerationService;
@Service
public class reportGenerationServiceImpl implements reportGenerationService {
	@Override
	public void generateWoReport() {
		System.out.println("This is your generated report");
	}

}
