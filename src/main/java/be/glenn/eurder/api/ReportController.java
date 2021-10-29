package be.glenn.eurder.api;

import be.glenn.eurder.domain.dtos.ReportDto;
import be.glenn.eurder.services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reports")
public class ReportController {

    private final ReportService service;

    @Autowired
    public ReportController(ReportService service) {
        this.service = service;
    }

    @GetMapping(produces = "application/json", params = "customerId")
    @ResponseStatus(HttpStatus.OK)
    public ReportDto getReportsByCustomerId(@RequestParam(name = "customerId") String customerId) {
        return service.getReportsByCustomerId(customerId);
    }
}
