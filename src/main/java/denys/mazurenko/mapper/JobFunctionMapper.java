package denys.mazurenko.mapper;

import denys.mazurenko.config.MapperConfig;
import denys.mazurenko.dto.JobFunctionResponseDto;
import denys.mazurenko.dto.external.jobfunctions.JobFunctionsExternalResponseDto;
import denys.mazurenko.entity.JobFunction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.Collections;
import java.util.List;

@Mapper(config = MapperConfig.class)
public interface JobFunctionMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "jobFunctionName")
    JobFunction toEntity(String jobFunctionName);

    JobFunctionResponseDto toDto(JobFunction jobFunction);

    default List<JobFunction> fromExternalDtoToEntities(JobFunctionsExternalResponseDto dto) {
        if (dto == null
                || dto.getResults() == null
                || dto.getResults().getJobFunctions() == null) {
            return Collections.emptyList();
        }
        return dto.getResults().getJobFunctions()
                .stream()
                .map(this::toEntity)
                .toList();
    }
}
