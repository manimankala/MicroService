package com.Job.Management.Models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    @Id
    private UUID employeeId;
    private String employeeOfficeName;
    private String employeeName;
    private String employeeNumber;
    private String employeeEmail;
    private String employeeLocation;
    private List<String> employeeSkills;
    private String employeeRole;
    private String employeeJoiningDate;
    private String employeeBloodGroup;
    private String employeeCode;

}
