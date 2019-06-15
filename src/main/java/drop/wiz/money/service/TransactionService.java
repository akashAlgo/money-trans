package drop.wiz.money.service;

import drop.wiz.money.api.TransactionRequest;
import drop.wiz.money.core.Account;
import drop.wiz.money.core.Transaction;
import drop.wiz.money.db.TransactionRepository;
import drop.wiz.money.exception.AccountNotFoundException;
import drop.wiz.money.exception.TransactionFailedException;
import drop.wiz.money.exception.TransactionNotFoundException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: arastogi
 * Date: 2019-06-15
 */
public class TransactionService {

    TransactionRepository transactionRepository;

    AccountService accountService;

    ConversionRateService conversionRateService;

    public TransactionService(TransactionRepository transactionRepository, AccountService accountService) {
        this.transactionRepository = transactionRepository;
        this.accountService = accountService;
    }

    public Transaction createTransaction(TransactionRequest transactionRequest) throws TransactionFailedException {
        Account sourceAccount;
        Account destinationAccount;
        try {
            sourceAccount = accountService.getAccountById(transactionRequest.getSourceAccount());
            destinationAccount = accountService.getAccountById(transactionRequest.getDestinationAccount());
        } catch (AccountNotFoundException e) {
            throw new TransactionFailedException("Transaction failed because either source or destination account not found");
        }

        Double conversionRate = conversionRateService.getConversionDate(sourceAccount.getCurrency(),
                destinationAccount.getCurrency());

        // Considering the amount to transfer is in source currency, destination amount is calculated based on a conversion rate
        BigDecimal destinationAmount = transactionRequest.getAmount().multiply(BigDecimal.valueOf(conversionRate));
        destinationAmount.setScale(2, RoundingMode.HALF_EVEN);

        // This logic will always avoid deadlock as the lock is never acquired in a cyclic manner
        Account first = sourceAccount.getId() > destinationAccount.getId() ? sourceAccount : destinationAccount;
        Account second = first != sourceAccount ? sourceAccount : destinationAccount;
        synchronized (first) {
            synchronized (second) {
                sourceAccount.setBalance(sourceAccount.getBalance().subtract(transactionRequest.getAmount()));
                destinationAccount.setBalance(destinationAccount.getBalance().add(destinationAmount));
            }
        }

        Transaction transaction = Transaction.builder()
                .amount(transactionRequest.getAmount())
                .sourceAccount(sourceAccount)
                .destinationAccount(destinationAccount)
                .conversionFactor(conversionRate)
                .build();


        return transactionRepository.create(transaction);
    }

    public List<Transaction> getTransactionsForAccount(Long accountNumber, Boolean onlySource,
                                                       Boolean onlyDestination) {

        List<Transaction> transactions = new ArrayList<>();

        // This should handle all the cases of Source or Destination being true or none
        if (onlySource || !onlyDestination) {
            transactions.addAll(transactionRepository.findByAccountIdSource(accountNumber));
        }
        if (onlyDestination || !onlySource) {
            transactions.addAll(transactionRepository.findByAccountIdDestination(accountNumber));
        }
        return transactions;
    }

    public Transaction getTransactionForId(Long transactionId) throws TransactionNotFoundException {
        return transactionRepository.findById(transactionId).orElseThrow(() ->
                new TransactionNotFoundException(String.format("The request transaction Id %s doesn't exist",
                        transactionId.toString())));
    }

}
