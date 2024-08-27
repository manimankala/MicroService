package com.office.OfficeService.Service;


import com.office.OfficeService.Feign.OfficeInterface;
import com.office.OfficeService.Model.Office;
import com.office.OfficeService.Repo.OfficeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Component
public class OfficeService {
    @Autowired
    private OfficeRepo officeRepo;
    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private OfficeInterface officeInterface;
    public List<Office> getOffice() {
        return officeRepo.findAll();
    }
    public Optional<Office> getOfficeById(UUID id){
        return officeRepo.findById(id);
    }
    public Optional<Office> getOfficeByName(String officeName)
    {
        return officeRepo.findByOfficeName(officeName);
    }


    public Office addOffice(Office office) {
        office.setOfficeId(UUID.randomUUID());
        return officeRepo.save(office);
    }
    public Office updateOffice(Office updateOffice){
        Office existingOffice=officeRepo.findByOfficeId(updateOffice.getOfficeId());
        if (existingOffice!=null){
            existingOffice.setOfficeName(updateOffice.getOfficeName());
            existingOffice.setOfficeEmail(updateOffice.getOfficeEmail());
            existingOffice.setOfficeCode(updateOffice.getOfficeCode());
            existingOffice.setOfficeNumber(updateOffice.getOfficeNumber());
            existingOffice.setOfficeLocation(updateOffice.getOfficeLocation());
            return officeRepo.save(existingOffice);
        }
        else
            return null;
    }
    public String deleteOffice(UUID id){
        officeRepo.deleteById(id);
        return "deleted successfully";
    }

    public List<Office> filterOfficeByLoc(String officeLoc){
        Query query=new Query();
        query.addCriteria(Criteria.where("officeLocation").regex(officeLoc,"i"));
        return mongoTemplate.find(query, Office.class);
    }
    public List<Office> sortOffices(String value){
        return officeRepo.findAll(Sort.by(Sort.Direction.ASC,value));
    }
    public Page<Office> paginateOffices(int offset,int pageSize){
        return officeRepo.findAll(PageRequest.of(offset,pageSize));
    }

    public List<String> getAllOfficeCodes(String organisationName)
    {   String orgName=organisationName.length()>3?organisationName.substring(0,3):organisationName;
        Query query=new Query();
        query.addCriteria(Criteria.where("officeCode").regex("^"+orgName,"i"));
        query.fields().include("officeCode").exclude("officeId");
        return mongoTemplate.find(query,Office.class).stream().map(Office::getOfficeCode).toList();
    }
    public List<String> getEmpCodeUsingOffName(String OfficeName){
        return officeInterface.getAllEmpCodes(OfficeName);

    }

}