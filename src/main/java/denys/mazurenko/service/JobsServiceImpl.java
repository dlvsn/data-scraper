package denys.mazurenko.service;

import denys.mazurenko.dto.CompanyDto;
import denys.mazurenko.dto.JobsResponseDto;
import denys.mazurenko.dto.external.JobSearchRequestDto;
import denys.mazurenko.entity.Job;
import denys.mazurenko.repository.JobFunctionRepository;
import denys.mazurenko.repository.JobRepository;
import denys.mazurenko.service.html.JobsHtmlScraper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JobsServiceImpl implements JobsService {
    private final CompanyService companyService;
    private final JobRepository jobRepository;
    private final JobFunctionRepository jobFunctionRepository;
    private final JobsHtmlScraper jobsHtmlScraper;

    public List<JobsResponseDto> saveFetchedData(JobSearchRequestDto requestDto) throws IOException {
        List<JobsResponseDto> fetchedDataFromHtml = jobsHtmlScraper.fetchDataFromHtml(requestDto);
        List<Job> list = fetchedDataFromHtml.stream().map(this::mapFromDtoToEntity).toList();
        jobRepository.saveAll(list);
        return fetchedDataFromHtml;
    }

    private Job mapFromDtoToEntity(JobsResponseDto dto) {
        Job job = new Job();
        job.setLinkToApply(dto.getLinkToApply());
        job.setExternalId(dto.getExternalId());
        job.setJobFunctions(jobFunctionRepository.findByNameIn(dto.getLaborFunctions()));
        job.setName(dto.getPosition());
        job.setDescription(dto.getDescription());
        job.setCreatedAt(dto.getCreatedAt());
        job.setTags(Arrays.toString(dto.getTags().toArray()));
        job.setCompany(companyService.getOrSave(new CompanyDto(dto.getOrganizationTittle(), dto.getLogoUrl())));
        return job;
    }
}

