package denys.mazurenko.dto.external.jobs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class JobsExternalResponseDto {
    private JobsResult results;
}
