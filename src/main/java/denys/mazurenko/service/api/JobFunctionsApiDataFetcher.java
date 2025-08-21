package denys.mazurenko.service.api;

import denys.mazurenko.dto.external.jobfunctions.JobFunctionsExternalResponseDto;

public interface JobFunctionsApiDataFetcher {
    JobFunctionsExternalResponseDto fetchData();
}
