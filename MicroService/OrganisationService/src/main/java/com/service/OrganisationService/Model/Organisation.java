package com.service.OrganisationService.Model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Organisation {

    @Id
    private UUID organisationId;
    private String organisationName;
    private String organisationLoc;
    private String organisationCode;
    private String organisationEmail;
    private String organisationNumber;
    private List<String> officeCodes;

}
