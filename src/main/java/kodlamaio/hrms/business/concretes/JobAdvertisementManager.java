package kodlamaio.hrms.business.concretes;

import ch.qos.logback.core.joran.action.IADataForComplexProperty;
import kodlamaio.hrms.business.abstracts.JobAdvertisementService;
import kodlamaio.hrms.core.utilities.results.*;
import kodlamaio.hrms.dataAccess.abstracts.JobAdvertisementDao;
import kodlamaio.hrms.entities.concretes.JobAdvertisement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobAdvertisementManager implements JobAdvertisementService {

    private JobAdvertisementDao jobAdvertisementDao;

    @Autowired
    public JobAdvertisementManager(JobAdvertisementDao jobAdvertisementDao) {
        this.jobAdvertisementDao = jobAdvertisementDao;
    }

    @Override
    public DataResult<List<JobAdvertisement>> getAll() {
        return new SuccessDataResult<>(jobAdvertisementDao.findAll(), "All jobs advertisements got successfully.");
    }

    @Override
    public Result add(JobAdvertisement jobAdvertisement) {
        jobAdvertisementDao.save(jobAdvertisement);
        return new SuccessResult("Job advertisement added successfully.");
    }

    @Override
    public DataResult<List<JobAdvertisement>> getAllByActive() {
        return new SuccessDataResult<>(jobAdvertisementDao.findAllByActive(), "All jobs got by is active parameter successfully.");
    }

    @Override
    public DataResult<List<JobAdvertisement>> getAllByActiveAndOrderByPublishDateAsc() {
        Sort sort = Sort.by(Sort.Direction.ASC, "publishDate");
        return new SuccessDataResult<>(jobAdvertisementDao.findAllByActive(sort), "All jobs got by is active parameter and orderedAsc by publish date successfully.");
    }

    @Override
    public DataResult<List<JobAdvertisement>> getAllByActiveAndOrderByPublishDateDesc() {
        Sort sort = Sort.by(Sort.Direction.DESC, "publishDate");
        return new SuccessDataResult<>(jobAdvertisementDao.findAllByActive(sort), "All jobs got by is active parameter and orderedDesc by publish date successfully.");
    }

    @Override
    public DataResult<List<JobAdvertisement>> getAllByActiveAndEmployerId(int employerId) {
        return new SuccessDataResult<>(jobAdvertisementDao.findByActiveAndEmployer_Id(employerId), "All jobs got by is active parameter and employer id successfully.");
    }

    @Override
    public Result close(int jobAdvertisementId) {
        var jobAdvertisement = jobAdvertisementDao.findById(jobAdvertisementId).get();
        if (jobAdvertisement == null) {
            return new ErrorResult("Job advertisement not found.");
        }
        jobAdvertisement.setActive(false);
        jobAdvertisementDao.save(jobAdvertisement);
        return new SuccessResult("Job closed successfully.");
    }
}
