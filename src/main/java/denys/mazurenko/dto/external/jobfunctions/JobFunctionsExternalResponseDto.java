package denys.mazurenko.dto.external.jobfunctions;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class JobFunctionsExternalResponseDto {
    private JobFunctionsResult results;
}
