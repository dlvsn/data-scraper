package denys.mazurenko.controller;

import denys.mazurenko.dto.JobsResponseDto;
import denys.mazurenko.dto.external.JobSearchRequestDto;
import denys.mazurenko.service.JobsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/jobs")
@RequiredArgsConstructor
public class JobsController {
    private final JobsService jobsService;

    @PostMapping
    public List<JobsResponseDto> getJobs(@RequestBody JobSearchRequestDto request) throws IOException {
        return jobsService.saveFetchedData(request);
    }

}
