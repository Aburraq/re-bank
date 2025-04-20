package com.royalreserve.accountservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.royalreserve.accountservice.model.Account;

public interface AccountRepository extends MongoRepository<Account, String> {
}
