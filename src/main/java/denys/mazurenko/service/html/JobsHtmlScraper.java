package denys.mazurenko.service.html;

import denys.mazurenko.dto.JobsResponseDto;
import denys.mazurenko.dto.external.JobSearchRequestDto;
import java.io.IOException;
import java.util.List;

public interface JobsHtmlScraper {
    List<JobsResponseDto> fetchDataFromHtml(JobSearchRequestDto requestDto) throws IOException;
}
