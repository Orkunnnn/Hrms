package kodlamaio.hrms.dataAccess.abstracts;

import kodlamaio.hrms.entities.concretes.JobAdvertisement;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JobAdvertisementDao extends JpaRepository<JobAdvertisement, Integer> {
    @Query("from JobAdvertisement where isActive=true")
    List<JobAdvertisement> findAllByActive();

    @Query("from JobAdvertisement where isActive=true")
    List<JobAdvertisement> findAllByActive(Sort sort);

    @Query("from JobAdvertisement where isActive=true and employer.id=:employerId")
    List<JobAdvertisement> findByActiveAndEmployer_Id(int employerId);
}
