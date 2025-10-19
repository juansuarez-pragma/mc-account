package com.stech.mcc_account_service.repository;

import com.stech.mcc_account_service.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAccountRepository extends JpaRepository<AccountEntity, String> {

    Optional<AccountEntity> findByAccountNumberAndCustomerCu(String accountNumber, String customerCu);
}
