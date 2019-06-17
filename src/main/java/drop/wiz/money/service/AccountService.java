package drop.wiz.money.service;

import drop.wiz.money.api.AccountRequest;
import drop.wiz.money.core.Account;
import drop.wiz.money.core.AccountType;
import drop.wiz.money.core.Currency;
import drop.wiz.money.db.AccountRepository;
import drop.wiz.money.exception.AccountNotFoundException;
import drop.wiz.money.exception.InvalidAccountTypeException;
import drop.wiz.money.exception.InvalidCurrencyCodeException;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.List;

/**
 * Author: arastogi
 */

@Slf4j
public class AccountService {

    private AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account getAccountById(Long accountId) throws AccountNotFoundException {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> {
                    log.error("Account Not Found");
                    return new AccountNotFoundException(String.format("Account %s not found", accountId));
                });
    }

    public List<Account> getAccountsForUser(String userId) {
        StringBuilder userIdBuilder = new StringBuilder("%");
        userIdBuilder.append(userId).append("%");
        return accountRepository.findByUserId(userIdBuilder.toString());
    }

    public Account save(AccountRequest accountRequest) throws InvalidAccountTypeException, InvalidCurrencyCodeException {

        AccountType accountType;
        Currency currency;
        try {
            accountType = AccountType.valueOf(accountRequest.getAccountType());
        } catch (Exception e) {
            throw new InvalidAccountTypeException("Account Type provided is invalid");
        }
        try {
            currency = Currency.valueOf(accountRequest.getCurrency());
        } catch (Exception e) {
            throw new InvalidCurrencyCodeException("Currency Code is invalid");
        }
        Account account = Account.builder()
                .accountType(accountType)
                .currency(currency)
                .isActive(accountRequest.getIsActive() == null ? true : accountRequest.getIsActive())
                .userId(accountRequest.getUserId())
                .balance(BigDecimal.valueOf(accountRequest.getBalance() == null ? 0 : accountRequest.getBalance()))
                .build();

        return accountRepository.saveOrUpdate(account);
    }

    public void deleteAccount(Long accountNumber) {
        accountRepository.delete(accountNumber);
    }

}
