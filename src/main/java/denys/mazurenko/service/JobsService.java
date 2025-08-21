package denys.mazurenko.service;

import denys.mazurenko.dto.JobsResponseDto;
import denys.mazurenko.dto.external.JobSearchRequestDto;
import java.io.IOException;
import java.util.List;

public interface JobsService {
    List<JobsResponseDto> saveFetchedData(JobSearchRequestDto requestDto) throws IOException;
}
