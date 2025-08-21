package denys.mazurenko.dto.external;

import denys.mazurenko.dto.external.jobs.FiltersDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class JobSearchRequestDto {
    private int hitsPerPage = 20;
    private int page = 0;
    private FiltersDto filters;
    private String query = "";
}
