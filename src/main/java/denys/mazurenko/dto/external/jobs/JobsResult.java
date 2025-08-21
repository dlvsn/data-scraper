package denys.mazurenko.dto.external.jobs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class JobsResult {
    private List<JobDto> jobs;
    private Integer count;
}
