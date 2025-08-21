package denys.mazurenko.service;

import denys.mazurenko.dto.JobFunctionResponseDto;
import denys.mazurenko.dto.external.jobfunctions.JobFunctionsExternalResponseDto;
import denys.mazurenko.entity.JobFunction;
import denys.mazurenko.mapper.JobFunctionMapper;
import denys.mazurenko.repository.JobFunctionRepository;
import denys.mazurenko.service.api.JobFunctionsApiDataFetcher;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JobFunctionsServiceImpl implements JobFunctionsService {
    private final JobFunctionsApiDataFetcher jobFunctionsApiDataFetcher;
    private final JobFunctionMapper jobFunctionMapper;
    private final JobFunctionRepository jobFunctionRepository;

    @EventListener(ContextRefreshedEvent.class)
    void init() {
        JobFunctionsExternalResponseDto jobFunctionsExternalResponseDto = jobFunctionsApiDataFetcher.fetchData();
        List<JobFunction> jobFunctions = jobFunctionMapper.fromExternalDtoToEntities(jobFunctionsExternalResponseDto);
        jobFunctionRepository.saveAll(jobFunctions);
    }

    @Override
    public List<JobFunctionResponseDto> findJobFunctions() {
        return jobFunctionRepository.findAll()
                .stream()
                .map(jobFunctionMapper::toDto)
                .toList();
    }
}
