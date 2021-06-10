package kodlamaio.hrms.business.fakes.mernisService;

public class MernisService {
    public boolean verify(Long nationalityId, String firstName, String lastName, int yearOfBirth) {
        System.out.println("Verified");
        return true;
    }
}
