package com.service.OrganisationService.Feign;

import com.office.OfficeService.Model.Office;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "OFFICE-SERVICE")
public interface OrganisationInterface {

    @GetMapping(value = "office/all")
    public List<Office> getOfficeAll();

    @GetMapping("office/codes")
    public List<String> getAllEmpCodes(@RequestParam String organisationName);

}
