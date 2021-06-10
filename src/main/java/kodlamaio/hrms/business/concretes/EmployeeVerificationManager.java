package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.EmployeeVerificationService;
import kodlamaio.hrms.entities.concretes.User;
import org.springframework.stereotype.Service;

@Service
public class EmployeeVerificationManager implements EmployeeVerificationService {

    @Override
    public boolean verify(User user) {
        user.setVerified(true);
        return true;
    }
}
