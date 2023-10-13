package com.more.more50.repos;

import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.more.more50.models.office.OfficeModel;

@Repository
public interface OfficeRepo extends MongoRepository<OfficeModel, UUID>{
    
}
