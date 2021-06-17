package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.JobAdvertisement;

import java.util.List;

public interface JobAdvertisementService {
    DataResult<List<JobAdvertisement>> getAll();

    Result add(JobAdvertisement jobAdvertisement);

    DataResult<List<JobAdvertisement>> getAllByActive();

    DataResult<List<JobAdvertisement>> getAllByActiveAndOrderByPublishDateAsc();

    DataResult<List<JobAdvertisement>> getAllByActiveAndOrderByPublishDateDesc();

    DataResult<List<JobAdvertisement>> getAllByActiveAndEmployerId(int employerId);

    Result close(int jobAdvertisementId);
}
