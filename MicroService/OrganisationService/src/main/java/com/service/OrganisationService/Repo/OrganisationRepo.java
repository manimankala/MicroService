package com.service.OrganisationService.Repo;

import com.service.OrganisationService.Model.Organisation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface OrganisationRepo extends MongoRepository<Organisation, UUID> {

    @Query("{'organisationName':?0}")
    List<Organisation> findByOrgName(String organisationName);
}
