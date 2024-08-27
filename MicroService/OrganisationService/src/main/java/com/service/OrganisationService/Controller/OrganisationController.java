package com.service.OrganisationService.Controller;

import com.office.OfficeService.Model.Office;
import com.service.OrganisationService.Model.Organisation;
import com.service.OrganisationService.Service.OrganisationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/org", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrganisationController  {

    @Autowired
    private OrganisationService organisationService;

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Organisation> addOrganisation(@RequestBody Organisation organisation){
        Organisation createdOrganisation = organisationService.addOrganisation(organisation);
        return new ResponseEntity<>(createdOrganisation, HttpStatus.CREATED);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<Organisation>> getAllOrganisation() {
        List<Organisation> organisations = organisationService.getAllOrganisation();
        return new ResponseEntity<>(organisations, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<Organisation>> getOrganisationById(@PathVariable UUID id) {
        Optional<Organisation> organisation = organisationService.getOrganisationById(id);
        return new ResponseEntity<>(organisation, HttpStatus.OK);
    }

    @GetMapping(value = "/find")
    public ResponseEntity<List<Organisation>> getOrganisationById(@RequestParam String name) {
        List<Organisation> organisations = organisationService.getOrganisationByName(name);
        return new ResponseEntity<>(organisations, HttpStatus.OK);
    }

    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Organisation> updateOrganisation(@RequestBody Organisation organisation) {
        Organisation updatedOrganisation = organisationService.updateOrganisation(organisation);
        return new ResponseEntity<>(updatedOrganisation, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteOrganisation(@PathVariable UUID id) {
        String response = organisationService.deleteOrganisation(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/office")
    public ResponseEntity<List<Office>> getAllOffices() {
        List<Office> offices = organisationService.getAllOffices();
        return ResponseEntity.ok(offices);
    }
    @GetMapping(value = "/officecodes")
    public List<String> getAllOfficeCodesUsingOrgName(@RequestParam String orgName){
        return organisationService.getOfficeCodesUsingOrgName(orgName);
    }
}