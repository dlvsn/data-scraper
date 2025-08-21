package denys.mazurenko.dto.external.jobfunctions;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class JobFunctionsResult {
    @JsonProperty("job_functions")
    private List<String> jobFunctions;
}
