package controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;


import service.PdfGenerationService;

@RestController
@RequestMapping("/api/pdf")

//http://localhost:8081/api/pdf/work-order-report/0
public class PdfController {

    @Autowired
    private PdfGenerationService pdfGenerationService;

//    @GetMapping("/address-slip/{workOrderId}")
//    public ResponseEntity<Resource> getAddressSlip(@PathVariable Long workOrderId) {
//        TmWorkOrder tmWorkOrder = getWorkOrderById(workOrderId); // Implement this method to fetch the work order
//        File pdf = pdfGenerationService.generateAddressSlip(tmWorkOrder);
//        return ResponseEntity.ok()
//            .contentType(MediaType.APPLICATION_PDF)
//            .body(new FileSystemResource(pdf));
//    }

    @GetMapping("/work-order-report/{workOrderId}")
   
    public ResponseEntity<Resource> getWorkOrderReport(@PathVariable Long workOrderId) {
        File pdf = pdfGenerationService.generateWorkOrderReport(workOrderId);
        return ResponseEntity.ok()
            .contentType(MediaType.APPLICATION_PDF)
            .body(new FileSystemResource(pdf));
    }

}
