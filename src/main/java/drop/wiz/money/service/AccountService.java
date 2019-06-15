package drop.wiz.money.service;

import drop.wiz.money.api.AccountRequest;
import drop.wiz.money.core.Account;
import drop.wiz.money.db.AccountRepository;
import drop.wiz.money.exception.AccountNotFoundException;

import java.util.List;

/**
 * Author: arastogi
 * Date: 2019-06-15
 */
public class AccountService {

    private AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account getAccountById(Long accountId) throws AccountNotFoundException {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException(String.format("Account {} not found", accountId)));
    }

    public List<Account> getAccountsForUser(String userId) {
        StringBuilder userIdBuilder = new StringBuilder("%");
        userIdBuilder.append(userId).append("%");
        return accountRepository.findByUserId(userIdBuilder.toString());
    }

    public Account saveOrUpdate(AccountRequest accountRequest) {
        Account account = Account.builder()
                .accountType(accountRequest.getAccountType())
                .currency(accountRequest.getCurrency())
                .isActive(accountRequest.getIsActive())
                .userId(accountRequest.getUserId())
                .build();
        return accountRepository.saveOrUpdate(account);
    }

    public void deleteAccount(Long accountNumber) {
        accountRepository.delete(accountNumber);
    }

}