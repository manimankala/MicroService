package com.office.OfficeService.Repo;

import com.office.OfficeService.Model.Office;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface OfficeRepo extends MongoRepository<Office, UUID> {
    @Query("{'officeId': ?0}")
    Office findByOfficeId(UUID officeId);

    @Query("{'officeName': ?0}")
    Optional<Office> findByOfficeName(String officeName);
}