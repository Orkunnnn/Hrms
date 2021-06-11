package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.JobPositionService;
import kodlamaio.hrms.core.utilities.results.*;
import kodlamaio.hrms.dataAccess.abstracts.JobPositionDao;
import kodlamaio.hrms.entities.concretes.JobPosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobPositionManager implements JobPositionService {

    private JobPositionDao jobPositionDao;

    @Autowired
    public JobPositionManager(JobPositionDao jobPositionDao) {
        this.jobPositionDao = jobPositionDao;
    }

    @Override
    public DataResult<List<JobPosition>> getAll() {
        return new SuccessDataResult<>(jobPositionDao.findAll(), "All jobs got successfully.");
    }

    @Override
    public Result add(JobPosition jobPosition) {
        if (checkIfJobPositionExists(jobPosition.getPositionName())) {
            return new ErrorResult("Job position already exists.");
        }
        jobPositionDao.save(jobPosition);
        return new SuccessResult("Job position added successfully");
    }

    private boolean checkIfJobPositionExists(String positionName) {
        return jobPositionDao.findByPositionName(positionName) != null;
    }

}
