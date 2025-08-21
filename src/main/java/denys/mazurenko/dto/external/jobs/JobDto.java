package denys.mazurenko.dto.external.jobs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class JobDto {
    @JsonProperty("created_at")
    private Integer createdAt;
    private String title;
    @JsonProperty("url")
    private String companyUrl;
    private String slug;
    private Organization organization;
    private List<String> locations;
    @JsonProperty("has_description")
    private boolean hasDescription;
    @JsonProperty("id")
    private Long idExternal;
}
