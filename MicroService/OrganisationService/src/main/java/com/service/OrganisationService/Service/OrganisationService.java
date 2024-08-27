package com.service.OrganisationService.Service;

import com.office.OfficeService.Model.Office;
import com.service.OrganisationService.Feign.OrganisationInterface;
import com.service.OrganisationService.Model.Organisation;
import com.service.OrganisationService.Repo.OrganisationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

@Component
@Service
public class OrganisationService {

    @Autowired
    private OrganisationRepo organisationRepo;

    @Autowired
    private OrganisationInterface organisationInterface;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private RestTemplate restTemplate;


    public Organisation addOrganisation(Organisation organisation){
        organisation.setOrganisationId(UUID.randomUUID());
        organisation.setOrganisationCode(organisation.getOrganisationName().substring(0,3)+UUID.randomUUID().toString().substring(0,4));
        return organisationRepo.save(organisation);

    }
    public List<Organisation> getAllOrganisation(){
        return organisationRepo.findAll();
    }
    public Optional<Organisation> getOrganisationById(UUID id)
    {
        return organisationRepo.findById(id);
    }
    public List<Organisation> getOrganisationByName(String name)
    {
        return organisationRepo.findByOrgName(name);
    }
    public Organisation updateOrganisation(Organisation updOrganisation)
    {
        AtomicReference<Organisation> orgAtomicReference=new AtomicReference<>();
        Optional<Organisation> existingOrg=organisationRepo.findById(updOrganisation.getOrganisationId());
        existingOrg.ifPresentOrElse(checker-> {
            checker.setOrganisationName(updOrganisation.getOrganisationName()!=null ?
                    updOrganisation.getOrganisationName() : checker.getOrganisationName());

            checker.setOrganisationLoc(updOrganisation.getOrganisationLoc()!=null ?
                    updOrganisation.getOrganisationLoc() : checker.getOrganisationLoc());

            checker.setOrganisationEmail(updOrganisation.getOrganisationEmail()!=null ?
                    updOrganisation.getOrganisationEmail() : checker.getOrganisationEmail());

            checker.setOrganisationNumber(updOrganisation.getOrganisationNumber()!=null ?
                    updOrganisation.getOrganisationNumber() : checker.getOrganisationNumber());
            orgAtomicReference.set(organisationRepo.save(checker));

        },()->{

                try {
                    throw new Exception("Org Not Found");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
        });
        return orgAtomicReference.get();

    }
    public String deleteOrganisation(UUID id)
    {
        organisationRepo.deleteById(id);
        return "Deleted Successfully";
    }

    public List<Office> getAllOffices() {

        return organisationInterface.getOfficeAll();
    }

    public List<String> getOfficeCodesUsingOrgName(String orgName){

        return organisationInterface.getAllEmpCodes(orgName);


    }

}
