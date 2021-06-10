package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.entities.concretes.User;
import org.springframework.stereotype.Service;


public interface EmployeeVerificationService {
    boolean verify(User user);
}
