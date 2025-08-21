package denys.mazurenko.service;

import denys.mazurenko.dto.CompanyDto;
import denys.mazurenko.entity.Company;

public interface CompanyService {
    Company getOrSave(CompanyDto dto);
}
