package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.EmployeeVerificationService;
import kodlamaio.hrms.business.abstracts.EmployerService;
import kodlamaio.hrms.business.adapters.MailServiceAdapter;
import kodlamaio.hrms.core.utilities.helpers.StringHelper;
import kodlamaio.hrms.core.utilities.results.*;
import kodlamaio.hrms.dataAccess.abstracts.EmployerDao;
import kodlamaio.hrms.entities.concretes.Employer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployerManager implements EmployerService {

    private EmployerDao employerDao;
    private MailServiceAdapter mailServiceAdapter;
    private EmployeeVerificationService employeeVerificationService;

    @Autowired
    public EmployerManager(EmployerDao employerDao, MailServiceAdapter mailServiceAdapter, EmployeeVerificationService employeeVerificationService) {
        this.employerDao = employerDao;
        this.mailServiceAdapter = mailServiceAdapter;
        this.employeeVerificationService = employeeVerificationService;
    }

    @Override
    public DataResult<List<Employer>> getAll() {
        return new SuccessDataResult<>(employerDao.findAll(), "All employers got successfully.");
    }

    @Override
    public Result register(Employer employer) {
        if (isNullOrEmpty(employer.getCompanyName()) || isNullOrEmpty(employer.getPhoneNumber()) || isNullOrEmpty(employer.getWebSite())
                || isNullOrEmpty(employer.getEmail()) || isNullOrEmpty(employer.getPassword()))
            return new ErrorResult("Fields can not be null");
        if (checkIfEmailExists(employer.getEmail())) return new ErrorResult("Email already exists.");
        if (!checkIfEmailValid(employer)) return new ErrorResult("Email must contain web site domain.");
        if (!mailServiceAdapter.verifyMail(employer)) return new ErrorResult("You need to verify your email.");
        if (!employeeVerificationService.verify(employer)) return new ErrorResult("Employee must verify you.");
        employerDao.save(employer);
        return new SuccessResult("Registered successfully.");
    }

    private boolean checkIfEmailExists(String email) {
        return employerDao.findByEmail(email) != null;
    }

    private boolean checkIfEmailValid(Employer employer) {
        String email = employer.getEmail();
        if (!email.contains("@")) return false;
        String[] webSiteDomain = email.split("@");
        return webSiteDomain[1].equals(employer.getWebSite());
    }

    private boolean isNullOrEmpty(String str) {
        if (str == null) {
            return true;
        } else return str.trim().isEmpty();
    }
}
