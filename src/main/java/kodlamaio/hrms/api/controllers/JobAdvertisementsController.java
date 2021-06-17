package kodlamaio.hrms.api.controllers;

import kodlamaio.hrms.business.abstracts.JobAdvertisementService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.JobAdvertisement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobadvertisements")
public class JobAdvertisementsController {

    private JobAdvertisementService jobAdvertisementService;

    @Autowired
    public JobAdvertisementsController(JobAdvertisementService jobAdvertisementService) {
        this.jobAdvertisementService = jobAdvertisementService;
    }

    @GetMapping("/getall")
    public DataResult<List<JobAdvertisement>> getAll() {
        return jobAdvertisementService.getAll();
    }

    @GetMapping("/getallbyactiveandorderbypublishdateasc")
    public DataResult<List<JobAdvertisement>> getAllByActiveAndOrderByPublishDateAsc() {
        return jobAdvertisementService.getAllByActiveAndOrderByPublishDateAsc();
    }

    @GetMapping("/getallbyactiveandorderbypublishdatedesc")
    public DataResult<List<JobAdvertisement>> getAllByActiveAndOrderByPublishDateDesc() {
        return jobAdvertisementService.getAllByActiveAndOrderByPublishDateDesc();
    }

    @GetMapping("/getallbyactive")
    public DataResult<List<JobAdvertisement>> getAllByActive() {
        return jobAdvertisementService.getAllByActive();
    }

    @GetMapping("/getallbyactiveandemployerid")
    public DataResult<List<JobAdvertisement>> getAllByActiveAndEmployerId(@RequestParam int employerId) {
        return jobAdvertisementService.getAllByActiveAndEmployerId(employerId);
    }

    @PostMapping("/add")
    public Result add(@RequestBody JobAdvertisement jobAdvertisement) {
        return jobAdvertisementService.add(jobAdvertisement);
    }

    @PostMapping("/close")
    public Result close(@RequestParam int jobAdvertisementId) {
        return jobAdvertisementService.close(jobAdvertisementId);
    }

}
