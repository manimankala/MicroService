package com.Job.Management.Services;

import com.Job.Management.Models.Employee;
import com.Job.Management.Repos.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.BooleanOperators;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private MongoTemplate mongoTemplate;



    public Employee addEmployee(Employee employee) {
        employee.setEmployeeId(UUID.randomUUID());
        employee.setEmployeeCode(employee.getEmployeeOfficeName().substring(0,3)+UUID.randomUUID().toString().substring(0,4));
        return employeeRepo.save(employee);
    }

    public Optional<Employee> getEmployeeById(UUID id) {
        return employeeRepo.findById(id);
    }

    public Optional<Employee> getEmployeeByName(String employeeName) {
        return employeeRepo.findByEmpName(employeeName);
    }
    public List<Employee> getAllEmployee() {
        return employeeRepo.findAll();
    }
    public List<Employee> sortEmployee(String field) {
        return employeeRepo.findAll(Sort.by(Sort.Direction.ASC,field));
    }
    public Page<Employee> paginateEmployee(int offset, int pageSize) {
        return employeeRepo.findAll(PageRequest.of(offset,pageSize));
    }

    public Employee updateEmployee(Employee updatedemployee) {
        Employee existingEmp = employeeRepo.findByEmployeeId(updatedemployee.getEmployeeId());
        if ( existingEmp != null) {
            existingEmp.setEmployeeName(updatedemployee.getEmployeeName());
            existingEmp.setEmployeeOfficeName(updatedemployee.getEmployeeOfficeName());
            existingEmp.setEmployeeNumber(updatedemployee.getEmployeeNumber());
            existingEmp.setEmployeeEmail(updatedemployee.getEmployeeEmail());
            existingEmp.setEmployeeLocation(updatedemployee.getEmployeeLocation());
            existingEmp.setEmployeeSkills(updatedemployee.getEmployeeSkills());
            existingEmp.setEmployeeRole(updatedemployee.getEmployeeRole());
            existingEmp.setEmployeeJoiningDate(updatedemployee.getEmployeeJoiningDate());
            existingEmp.setEmployeeBloodGroup(updatedemployee.getEmployeeBloodGroup());
            return employeeRepo.save(existingEmp);
        } else {
            return null;
        }
    }

    public String deleteEmployee(UUID id) {
        employeeRepo.deleteById(id);
        return "Employee deleted Successfully";

    }
    public List<Employee> filterEmployee(String field,String role) {
        Query query = new Query();
        Criteria criteria=new Criteria();

        query.addCriteria(criteria.andOperator(Criteria.where("employeeSkills").regex(field,"i"),Criteria.where(
                "employeeSkills").regex(field,"i")));
        //query.addCriteria(Criteria.where("employeeSkills").regex(field,"i").and("employeeRole").regex(role,"i"));
        return mongoTemplate.find(query,Employee.class);
    }

    public List<String> getEmployeeCodesUsingOfficeName(String OfficeName){
        String office=OfficeName.length()>3?OfficeName.substring(0,3):OfficeName;
        Query query=new Query();

        query.addCriteria(Criteria.where("employeeCode").regex(office,"i"));
        query.fields().include("employeeCode").exclude("employeeId");
        return mongoTemplate.find(query,Employee.class).stream().map(Employee::getEmployeeCode).toList();
    }

}

