package denys.mazurenko.controller;

import denys.mazurenko.dto.JobFunctionResponseDto;
import denys.mazurenko.service.JobFunctionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/job_functions")
public class JobFunctionsController {
    private final JobFunctionsService jobFunctionsService;

    @GetMapping
    public List<JobFunctionResponseDto> getJobFunctions() {
        return jobFunctionsService.findJobFunctions();
    }
}
