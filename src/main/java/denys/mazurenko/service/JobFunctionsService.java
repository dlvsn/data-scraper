package denys.mazurenko.service;

import denys.mazurenko.dto.JobFunctionResponseDto;
import java.util.List;

public interface JobFunctionsService {
    List<JobFunctionResponseDto> findJobFunctions();
}
