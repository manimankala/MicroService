package com.Job.Management.Controllers;

import com.Job.Management.Models.Employee;
import com.Job.Management.Services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {

        return employeeService.addEmployee(employee);
    }
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Employee> findEmployeeById(@PathVariable UUID id) {
        return employeeService.getEmployeeById(id);
    }
    @GetMapping(value = "/sort/{field}", produces = MediaType.APPLICATION_JSON_VALUE, consumes =
            MediaType.APPLICATION_JSON_VALUE)
    public List<Employee> getSortedEmployee(@PathVariable String field) {
        return employeeService.sortEmployee(field);
    }
    @GetMapping(value="/find", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Employee> findEmployeeByName(@RequestParam String employeeName) {
        return employeeService.getEmployeeByName(employeeName);
    }
    @GetMapping("/all")
    public List<Employee> findAllEmployee() {
        return employeeService.getAllEmployee();
    }

    @GetMapping("/filter")
    public List<Employee> filterEmployee(@RequestParam String skill,@RequestParam String role) {
        return employeeService.filterEmployee(skill,role);
    }
//    @GetMapping(value = "/filter/role")
//    public List<Employee> filterEmployeeByRole(@RequestParam String role) {
//        return employeeService.filterEmpByRole(role);
//    }
    
    @GetMapping("/page/{offset}/{pageSize}")
    public Page<Employee> getPaginateEmployee(@PathVariable int offset, @PathVariable int pageSize) {
        return employeeService.paginateEmployee(offset,pageSize);
    }
    @PutMapping
    public Employee updateEmployee(@RequestBody Employee employee) {
        return employeeService.updateEmployee(employee);
    }
    @DeleteMapping("/{employeeId}")
    public String deleteEmployee(@PathVariable UUID employeeId) {
        return employeeService.deleteEmployee(employeeId);
    }

    @GetMapping(value = "/empCode")
    public List<String> getEmployeeCodesUsingOfficeName(@RequestParam String officeName)
    {
        return employeeService.getEmployeeCodesUsingOfficeName(officeName);
    }
}

