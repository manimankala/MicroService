package com.office.OfficeService.Model;

import com.Job.Management.Models.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
@Component
public class Office {

    @Id
    private UUID officeId;
    private String officeName;
    private String officeLocation;
    private String officeNumber;
    private String officeEmail;
    private String officeCode;
    private List<Employee> employeeList;

}