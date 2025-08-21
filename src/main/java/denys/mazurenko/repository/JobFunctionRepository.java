package denys.mazurenko.repository;

import denys.mazurenko.entity.JobFunction;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface JobFunctionRepository extends JpaRepository<JobFunction, Long> {
    List<JobFunction> findByNameIn(List<String> names);
}
