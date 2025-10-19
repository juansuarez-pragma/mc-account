package com.stech.mcc_account_service.service.impl;

import com.stech.mcc_account_service.client.ICustomerRestClient;
import com.stech.mcc_account_service.dto.AccountDTO;
import com.stech.mcc_account_service.dto.CustomerDTO;
import com.stech.mcc_account_service.entity.AccountEntity;
import com.stech.mcc_account_service.repository.IAccountRepository;
import com.stech.mcc_account_service.service.interfaces.IAccountService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountService {

    private IAccountRepository accountRepository;
    private ICustomerRestClient customerRestClient;

    @Override
    public List<AccountDTO> getAll() {
        return this.accountRepository.findAll().stream().map(AccountEntity::getDto).toList();
    }

    @Override
    public AccountDTO add(AccountDTO accountDTO) {
        ResponseEntity<CustomerDTO> responseEntityNewCustomerDto = this.customerRestClient.add(accountDTO.getCustomer());
        if (responseEntityNewCustomerDto.getStatusCode().is2xxSuccessful()) {
            log.info("Customer added successfully");
            accountDTO.setCustomer(responseEntityNewCustomerDto.getBody());
            log.info("Acc account to account repository, {}", accountDTO);
            AccountEntity accountEntity = new AccountEntity();
            accountEntity.setData(accountDTO);
            return this.accountRepository.save(accountEntity).getDto();
        } else {
            log.error("Customer add failed");
            return AccountDTO.builder().build();
        }
    }

    @Override
    public AccountDTO update(AccountDTO accountDTO) {
        return null;
    }

    @Override
    public AccountDTO delete(AccountDTO accountDTO) {
        return null;
    }

    @Override
    public AccountDTO getById(String id) {
        return null;
    }
}
