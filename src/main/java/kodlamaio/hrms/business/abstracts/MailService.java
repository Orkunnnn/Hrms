package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.entities.concretes.User;

public interface MailService {
    boolean verifyMail(User user);
}
