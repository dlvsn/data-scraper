package denys.mazurenko.dto.external.jobs;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record FiltersDto(@JsonProperty("searchable_locations")
                         List<String> searchableLocations,
                         @JsonProperty("job_functions")
                         List<String> jobFunctions) {
}
