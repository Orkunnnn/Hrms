package kodlamaio.hrms.business.fakes.mailService;


public class FakeMailService {
    public boolean verifyMail(String email) {
        System.out.println("Mail Verified");
        return true;
    }
}
