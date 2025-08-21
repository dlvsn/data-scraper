package denys.mazurenko.service;

import denys.mazurenko.dto.CompanyDto;
import denys.mazurenko.entity.Company;
import denys.mazurenko.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;

    @Override
    public Company getOrSave(CompanyDto dto) {
        return companyRepository.findByName(dto.name())
                .orElseGet(() -> companyRepository.save(new Company(dto.name(), dto.logoUrl())));
    }
}
