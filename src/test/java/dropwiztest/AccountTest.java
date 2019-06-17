package dropwiztest;

import drop.wiz.money.api.AccountRequest;
import drop.wiz.money.core.Account;
import drop.wiz.money.core.AccountType;
import drop.wiz.money.core.Currency;
import drop.wiz.money.db.AccountRepository;
import drop.wiz.money.exception.AccountNotFoundException;
import drop.wiz.money.exception.InvalidAccountTypeException;
import drop.wiz.money.exception.InvalidCurrencyCodeException;
import drop.wiz.money.service.AccountService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

/**
 * Author: arastogi
 */

@RunWith(MockitoJUnitRunner.class)
public class AccountTest {

    @Mock
    AccountRepository accountRepositoryMock;

    @InjectMocks
    AccountService accountService;

    Account account;

    Long accountId;

    String userId;

    List<Account> accountList;

    AccountRequest accountRequest;


    @Before
    public void setUp() {
        accountId = 1l;
        userId = "1234";
        account = Account.builder()
                .id(accountId)
                .userId(userId)
                .balance(BigDecimal.ZERO)
                .currency(Currency.EURO)
                .isActive(true)
                .accountType(AccountType.CURRENT)
                .build();
        accountList = new ArrayList<>();
        accountList.add(account);
        accountRequest = AccountRequest.builder()
                .accountType("CURRENT")
                .balance(0d)
                .currency("EURO")
                .isActive(true)
                .userId(userId)
                .build();

    }

    @After
    public void tearDown() {

    }

    /**
     * This test case tests the getAccountById method
     *
     * @throws AccountNotFoundException
     */
    @Test
    public void testGetAccountById() throws AccountNotFoundException {

        when(accountRepositoryMock.findById(accountId)).thenReturn(Optional.ofNullable(account));
        Assert.assertEquals(account, accountService.getAccountById(accountId));
    }

    /**
     * This test case test the getAccountsForUser method
     */
    @Test
    public void testGetAccountsForUser() {

        StringBuilder userBuilder = new StringBuilder("%");
        userBuilder.append(userId).append("%");
        when(accountRepositoryMock.findByUserId(userBuilder.toString())).thenReturn(accountList);
        Assert.assertEquals(accountList, accountService.getAccountsForUser(userId));
    }

    /**
     * This test case tests the saveAccounts method
     */
    @Test
    public void testSaveAccount() throws InvalidCurrencyCodeException, InvalidAccountTypeException {

        Account accountExpected = Account.builder()
                .accountType(AccountType.valueOf(accountRequest.getAccountType()))
                .currency(Currency.valueOf(accountRequest.getCurrency()))
                .isActive(accountRequest.getIsActive() == null ? true : accountRequest.getIsActive())
                .userId(accountRequest.getUserId())
                .balance(BigDecimal.valueOf(accountRequest.getBalance() == null ? 0 : accountRequest.getBalance()))
                .build();
        when(accountRepositoryMock.saveOrUpdate(accountExpected)).thenReturn(accountExpected);
        Assert.assertEquals(accountExpected, accountService.save(accountRequest));
    }
}
