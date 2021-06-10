package kodlamaio.hrms.business.adapters;

import kodlamaio.hrms.business.abstracts.UserCheckService;
import kodlamaio.hrms.entities.concretes.Candidate;
import kodlamaio.hrms.business.fakes.mernisService.MernisService;
import org.springframework.stereotype.Service;

@Service
public class MernisServiceAdapter implements UserCheckService {

    MernisService mernisService = new MernisService();

    @Override
    public boolean verify(Candidate candidate) {
        return mernisService.verify(Long.parseLong(candidate.getNationalityId()), candidate.getFirstName(),
                candidate.getLastName(), candidate.getYearOfBirth());
    }
}
