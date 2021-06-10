package kodlamaio.hrms.business.adapters;

import kodlamaio.hrms.business.abstracts.MailService;
import kodlamaio.hrms.dataAccess.abstracts.UserDao;
import kodlamaio.hrms.entities.concretes.User;
import kodlamaio.hrms.business.fakes.mailService.FakeMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MailServiceAdapter implements MailService {

    private UserDao userDao;

    @Autowired
    public MailServiceAdapter(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public boolean verifyMail(User user) {
        FakeMailService fakeMailService = new FakeMailService();
        boolean result = fakeMailService.verifyMail(user.getEmail());
        if (result) {
            user.setVerified(true);
            return true;
        } else {
            user.setVerified(false);
            return false;
        }
    }
}
