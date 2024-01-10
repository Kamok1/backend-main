package com.ecobank.api.services;

import com.ecobank.api.database.entities.Account;
import com.ecobank.api.database.entities.AccountType;
import com.ecobank.api.database.entities.User;
import com.ecobank.api.database.repositories.IAccountRepository;
import com.ecobank.api.database.repositories.IAccountTypeRepository;
import com.ecobank.api.database.repositories.IUserRepository;
import com.ecobank.api.services.abstractions.IAccountService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class AccountService implements IAccountService {

    private final IUserRepository userRepository;
    private final IAccountRepository accountRepository;
    private final IAccountTypeRepository accountTypeRepository;

    public AccountService(IUserRepository userRepository, IAccountRepository accountRepository, IAccountTypeRepository accountTypeRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.accountTypeRepository = accountTypeRepository;
    }
    @Override
    public Optional<Account> getAccountsByUserEmail(String email) {
        var user = userRepository.findUserByEmail(email);
        if(user == null)
            return Optional.empty();
        return accountRepository.findAccountByUser(user);
    }

    @Override
    public Optional<Account> createAccountForUser(String email, String accountTypeName) {
        var user = userRepository.findUserByEmail(email);
        if(user.isEmpty())
            return Optional.empty();
        var accountType = accountTypeRepository.findAccountTypeByType("Normal");
        if(accountType.isEmpty())
            return Optional.empty();
        var account = Account.builder()
                .balance(new BigDecimal(0))
                .currency("PLN")
                .freeFunds(new BigDecimal(0))
                .user(user.get())
                .accountType(accountType.get())
                .build();

        accountRepository.save(account);
        return Optional.of(account);

    }
}
