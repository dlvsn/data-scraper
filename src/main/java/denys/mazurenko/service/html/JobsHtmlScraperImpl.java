package denys.mazurenko.service.html;

import static denys.mazurenko.config.Constants.USER_AGENT_VALUE;

import denys.mazurenko.dto.JobsResponseDto;
import denys.mazurenko.dto.external.JobSearchRequestDto;
import denys.mazurenko.dto.external.jobs.JobDto;
import denys.mazurenko.dto.external.jobs.JobsExternalResponseDto;
import denys.mazurenko.exception.JsoupConnectionException;
import denys.mazurenko.service.api.JobsApiDataFetcher;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobsHtmlScraperImpl implements JobsHtmlScraper {
    private static final String POSTED_ON = "Posted on";
    private static final String REGEX = ",";
    private static final String WITHOUT_DESCRIPTION = "WITHOUT DESCRIPTION";
    private static final String FIRST_JOB_FUNCTION_PAGE_ELEMENT = ".sc-beqWaB.sc-gueYoa.fhZPis.MYFxR";
    private static final String SECOND_JOB_FUNCTION_PAGE_ELEMENT =".sc-beqWaB.bpXRKw";
    private static final String CAREER_PAGE_ELEMENT = "div[data-testid=careerPage]";
    private static final String url = "https://jobs.techstars.com/companies/%s/jobs/%s";
    private final JobsApiDataFetcher jobsApiDataFetcher;

    @Override
    public List<JobsResponseDto> fetchDataFromHtml(JobSearchRequestDto requestDto) {
        JobsExternalResponseDto jobsExternalResponseDto = jobsApiDataFetcher.fetchData(requestDto);
        return jobsExternalResponseDto.getResults().getJobs().stream()
                .map(job -> {
                    String finalUrl = String.format(url, job.getOrganization().getSlug(), job.getSlug());
                    try {
                        Document html = Jsoup.connect(finalUrl)
                                .userAgent(USER_AGENT_VALUE)
                                .get();
                        String description = job.isHasDescription()
                                ? html.selectFirst(CAREER_PAGE_ELEMENT).text() : WITHOUT_DESCRIPTION;
                        if (job.isHasDescription()) {
                            description = html.selectFirst(CAREER_PAGE_ELEMENT).text();
                        }
                        List<String> jobFunctions = fetchJobFunction(html);
                        return initJobsResponseDto(job, finalUrl, description, jobFunctions);
                    } catch (IOException e) {
                        throw new JsoupConnectionException("Can't connect to " + finalUrl);
                    }
                }).toList();
    }

    private static JobsResponseDto initJobsResponseDto(JobDto jobDto, String finalUrl, String description, List<String> jobFunctions) {
        JobsResponseDto responseDto = new JobsResponseDto();
        responseDto.setExternalId(jobDto.getIdExternal());
        responseDto.setJobPageUrl(finalUrl);
        responseDto.setPosition(jobDto.getTitle());
        responseDto.setLinkToApply(jobDto.getCompanyUrl());
        responseDto.setLogoUrl(jobDto.getOrganization().getLogoUrl());
        responseDto.setOrganizationTittle(jobDto.getOrganization().getName());
        responseDto.setLaborFunctions(jobFunctions);
        responseDto.setCreatedAt(jobDto.getCreatedAt());
        responseDto.setLocations(jobDto.getLocations());
        responseDto.setDescription(description);
        responseDto.setTags(jobDto.getOrganization().getIndustryTags());
        return responseDto;
    }

    private List<String> fetchJobFunction(Document html) {
        try {
            Elements containers = html.select(FIRST_JOB_FUNCTION_PAGE_ELEMENT);
            for (Element container : containers) {
                Elements jobFunctionElements = container.select(SECOND_JOB_FUNCTION_PAGE_ELEMENT);
                if (!jobFunctionElements.isEmpty()) {
                    String text = jobFunctionElements.first().text();
                    if (text != null && !text.contains(POSTED_ON)) {
                        return Arrays.stream(text.split(REGEX))
                                .map(String::trim)
                                .filter(s -> !s.isEmpty())
                                .collect(Collectors.toList());
                    }
                }
            }
            return Collections.emptyList();
        } catch (Exception e) {
            throw new JsoupConnectionException("Something went wrong");
        }
    }
}
