package be.glenn.eurder.api;

import be.glenn.eurder.domain.dtos.ReportDto;
import be.glenn.eurder.repos.OrderRepo;
import be.glenn.eurder.services.ReportService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reports")
public class ReportController {

    private final ReportService service = new ReportService(new OrderRepo());

    @GetMapping(produces = "application/json", path = "/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    public ReportDto getReportsByCustomerId(@PathVariable String customerId) {
        return service.getReportsByCustomerId(customerId);
    }
}
