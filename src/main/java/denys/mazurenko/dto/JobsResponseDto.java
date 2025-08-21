package denys.mazurenko.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.List;

@Getter
@Setter
@ToString
public class JobsResponseDto {
    private Long externalId;
    private String jobPageUrl;
    private String position;
    private String linkToApply;
    private String logoUrl;
    private String organizationTittle;
    private List<String> laborFunctions;
    private List<String> locations;
    private Integer createdAt;
    private String description;
    private List<String> tags;
}
