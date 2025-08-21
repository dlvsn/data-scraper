package denys.mazurenko.service.api;

import denys.mazurenko.dto.external.JobSearchRequestDto;
import denys.mazurenko.dto.external.jobs.JobsExternalResponseDto;

public interface JobsApiDataFetcher {
    JobsExternalResponseDto fetchData(JobSearchRequestDto requestDto);
}
