package dropwiztest;

import drop.wiz.money.api.TransactionRequest;
import drop.wiz.money.core.Account;
import drop.wiz.money.core.AccountType;
import drop.wiz.money.core.Currency;
import drop.wiz.money.core.Transaction;
import drop.wiz.money.db.TransactionRepository;
import drop.wiz.money.exception.AccountNotFoundException;
import drop.wiz.money.exception.TransactionFailedException;
import drop.wiz.money.exception.TransactionNotFoundException;
import drop.wiz.money.service.AccountService;
import drop.wiz.money.service.ConversionRateService;
import drop.wiz.money.service.TransactionService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

/**
 * Author: arastogi
 */

@RunWith(MockitoJUnitRunner.class)
public class TransactionTest {

    @Mock
    AccountService accountService;

    @Mock
    TransactionRepository transactionRepository;

    @Mock
    ConversionRateService conversionRateService;

    @InjectMocks
    TransactionService transactionService;

    Transaction transaction;

    Transaction transactionExpected;

    TransactionRequest transactionRequest;

    Account srcAccount;

    Account srcAccountExpected;

    Account destAccount;

    Account destAccountExpected;

    Long transactionId;

    Long srcAccountId;

    Long destAccountId;

    List<Transaction> transactionList;

    @Before
    public void setUp() {

        transactionId = 12l;

        srcAccountId = 1l;

        destAccountId = 2l;

        srcAccount = Account.builder()
                .id(srcAccountId)
                .userId("1234")
                .balance(new BigDecimal(100))
                .currency(Currency.EURO)
                .isActive(true)
                .accountType(AccountType.CURRENT)
                .build();

        srcAccountExpected = Account.builder()
                .id(srcAccountId)
                .userId("1234")
                .balance(new BigDecimal(80))
                .currency(Currency.EURO)
                .isActive(true)
                .accountType(AccountType.CURRENT)
                .build();

        destAccount = Account.builder()
                .id(destAccountId)
                .userId("1122")
                .balance(new BigDecimal(100))
                .currency(Currency.EURO)
                .isActive(true)
                .accountType(AccountType.CURRENT)
                .build();

        destAccountExpected = Account.builder()
                .id(destAccountId)
                .userId("1122")
                .balance(new BigDecimal(120))
                .currency(Currency.EURO)
                .isActive(true)
                .accountType(AccountType.CURRENT)
                .build();

        transaction = Transaction.builder()
                .amount(new BigDecimal(20))
                .conversionFactor(1.0)
                .sourceAccount(srcAccount)
                .destinationAccount(destAccount)
                .id(transactionId)
                .build();

        transactionExpected = Transaction.builder()
                .amount(new BigDecimal(20))
                .sourceAccount(srcAccount)
                .destinationAccount(destAccount)
                .build();

        transactionRequest = TransactionRequest.builder()
                .sourceAccount(srcAccountId)
                .destinationAccount(destAccountId)
                .amount(new BigDecimal(20))
                .build();

        transactionList = Collections.singletonList(transaction);
    }

    @After
    public void tearDown() {

    }

    /**
     * This test case tests createTransaction method
     *
     * @throws AccountNotFoundException
     * @throws TransactionFailedException
     */
    @Test
    public void testCreateTransaction() throws AccountNotFoundException, TransactionFailedException {

        when(accountService.getAccountById(srcAccountId)).thenReturn(srcAccount);
        when(accountService.getAccountById(destAccountId)).thenReturn(destAccount);

        when(transactionRepository.create(transactionExpected)).thenReturn(transactionExpected);

        Transaction transactionActual = transactionService.createTransaction(transactionRequest);
        Assert.assertEquals(transactionExpected, transactionActual);
    }

    /**
     * This test case tests getTransactionsById method
     *
     * @throws TransactionNotFoundException
     */
    @Test
    public void testGetTransactionForId() throws TransactionNotFoundException {

        when(transactionRepository.findById(transactionId)).thenReturn(Optional.ofNullable(transaction));

        Assert.assertEquals(transaction, transactionService.getTransactionForId(transactionId));

    }

    /**
     * This test case tests getTransactionsForAccount method
     */
    @Test
    public void testGetTransactionsForAccount() {

        when(transactionRepository.findByAccountIdSource(srcAccountId)).thenReturn(transactionList);

        Assert.assertEquals(transactionList, transactionService.getTransactionsForAccount(srcAccountId, true, false));
    }

}
