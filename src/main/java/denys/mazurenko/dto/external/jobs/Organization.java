package denys.mazurenko.dto.external.jobs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Organization {
    @JsonProperty("logo_url")
    private String logoUrl;
    private String name;
    private String slug;
    @JsonProperty("industry_tags")
    private List<String> industryTags;
}
