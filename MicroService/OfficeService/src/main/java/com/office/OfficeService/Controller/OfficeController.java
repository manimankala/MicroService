package com.office.OfficeService.Controller;


import com.office.OfficeService.Model.Office;
import com.office.OfficeService.Service.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/office")
public class OfficeController {


    @Autowired
    private OfficeService officeService;

    @GetMapping(value = "/all")
    public List<Office> getOfficeAll() {
        return officeService.getOffice();
    }
    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Office> getOfficeById(@PathVariable UUID id)
    {
        return officeService.getOfficeById(id);
    }
    @GetMapping(value = "/officeName")
    public Optional<Office> getOfficeByName(@RequestParam String officeName)
    {
        return officeService.getOfficeByName(officeName);
    }

    @PostMapping(value = "/add")
    public Office createOffice(@RequestBody Office office) {
        return officeService.addOffice(office);
    }
    @PutMapping(value = "/update")
    public Office updateOffice(@RequestBody Office office)
    {
        return officeService.updateOffice(office);
    }
    @DeleteMapping(value = "/{id}")
    public String deleteOffice(@PathVariable UUID id)
    {
        return officeService.deleteOffice(id);
    }
    @GetMapping(value = "/filter")
    public List<Office> filterByLoc(@RequestParam String officeLoc){
        return officeService.filterOfficeByLoc(officeLoc);
    }

    @GetMapping(value = "/sort")
    public List<Office> sortByValue(@RequestParam String value){
        return officeService.sortOffices(value);
    }
    @GetMapping(value = "/pagination/{offset}/{pageSize}")
    public Page<Office> paginateOffices(@PathVariable int offset,@PathVariable int pageSize){
        return officeService.paginateOffices(offset,pageSize);
    }
    @GetMapping(value="/codes")
    public List<String> getAllOfficeCodes(@RequestParam String organisationName){
        return officeService.getAllOfficeCodes(organisationName);
    }

    @GetMapping(value = "/empcodes")
    public List<String> getAllEmpCodesUsingOffName(@RequestParam String office){
        return officeService.getEmpCodeUsingOffName(office);
    }

}