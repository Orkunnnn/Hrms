package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.CandidateService;
import kodlamaio.hrms.business.abstracts.UserCheckService;
import kodlamaio.hrms.business.adapters.MailServiceAdapter;
import kodlamaio.hrms.core.utilities.results.*;
import kodlamaio.hrms.dataAccess.abstracts.CandidateDao;
import kodlamaio.hrms.entities.concretes.Candidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CandidateManager implements CandidateService {

    private CandidateDao candidateDao;
    private UserCheckService userCheckService;
    private MailServiceAdapter mailServiceAdapter;

    @Autowired
    public CandidateManager(CandidateDao candidateDao, UserCheckService userCheckService, MailServiceAdapter mailServiceAdapter) {
        this.userCheckService = userCheckService;
        this.candidateDao = candidateDao;
        this.mailServiceAdapter = mailServiceAdapter;
    }

    @Override
    public DataResult<List<Candidate>> getAll() {
        return new SuccessDataResult<>(candidateDao.findAll(), "All candidates got successfully.");
    }

    @Override
    public Result register(Candidate candidate) {
        if (!checkIfFieldsNull(candidate)) return new ErrorResult("Fields can not be null");
        if (!userCheckService.verify(candidate)) return new ErrorResult("Not a valid user");
        if (checkIfEmailExists(candidate.getEmail())) return new ErrorResult("Email is already exists.");
        if (checkIfNationalityIdExists(candidate.getNationalityId()))
            return new ErrorResult("Nationality Id is already exists.");
        if (!mailServiceAdapter.verifyMail(candidate))
            return new ErrorResult("You need to verify your email");
        if (candidate.isVerified())
            candidateDao.save(candidate);
        return new SuccessResult("Successfully registered.");
    }

    private boolean checkIfFieldsNull(Candidate candidate) {
        return !(isNullOrEmpty(candidate.getFirstName()) || isNullOrEmpty(candidate.getLastName()) || isNullOrEmpty(candidate.getEmail())
                || isNullOrEmpty(candidate.getNationalityId()) || Objects.isNull(candidate.getYearOfBirth()));
    }

    private boolean checkIfEmailExists(String email) {
        return candidateDao.findByEmail(email) != null;
    }

    private boolean checkIfNationalityIdExists(String nationalityId) {
        return candidateDao.findByNationalityId(nationalityId) != null;
    }

    private boolean isNullOrEmpty(String str) {
        if (str == null) {
            return true;
        } else return str.trim().isEmpty();
    }
}
