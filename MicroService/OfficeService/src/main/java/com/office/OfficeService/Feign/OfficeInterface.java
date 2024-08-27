package com.office.OfficeService.Feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@FeignClient(value = "MANAGEMENT")
public interface OfficeInterface {
    @GetMapping(value = "employee/empCode")
    public List<String> getAllEmpCodes(@RequestParam String officeName);
}
