package drop.wiz.money.resources;

import drop.wiz.money.api.TransactionRequest;
import drop.wiz.money.core.Transaction;
import drop.wiz.money.exception.TransactionFailedException;
import drop.wiz.money.exception.TransactionNotFoundException;
import drop.wiz.money.service.TransactionService;
import io.dropwizard.hibernate.UnitOfWork;
import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Author: arastogi
 */

@Path("")
@Slf4j
public class TransactionEndpoint {

    private TransactionService transactionService;

    public TransactionEndpoint(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    /**
     * This API creates a new {@link Transaction} based on the provided {@link TransactionRequest}
     *
     * @param transactionRequest Transaction Request of the Transaction to be created
     * @return Transaction created with the provided Transaction Request
     * @throws TransactionFailedException Exception Thrown if transaction could not be created because of source or destination account
     */
    @POST
    @Path("/transactions")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    public Transaction createTransaction(TransactionRequest transactionRequest) throws TransactionFailedException {

        log.info("Request: {}", transactionRequest);
        return transactionService.createTransaction(transactionRequest);
    }

    /**
     * This API gets the List of {@link Transaction} with the given Account Number and filter them by Source or Destination flag.
     *
     * @param accountId Account Number
     * @param onlySource To get only Transactions with this Account Number in source
     * @param onlyDestination To get only Transactions with this Account Number in destination
     * @return List of transactions
     */
    @GET
    @Path("/transactions")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    public List<Transaction> getTransactionsForAccount(@QueryParam("account_number") Long accountId,
                                                       @QueryParam("only_outgoing") boolean onlySource,
                                                       @QueryParam("only_incoming") boolean onlyDestination) {
        log.info("Account Id: {} \n Source: {} \n Destination: {}", accountId, onlySource, onlyDestination);
        return transactionService.getTransactionsForAccount(accountId, onlySource, onlyDestination);
    }

    /**
     * This API gets the {@link Transaction} with the given Transaction Id. If the transaction Id doesn't exist then it throws Exception.
     *
     * @param transactionId Transaction Number
     * @return Transaction Information
     * @throws TransactionNotFoundException Exception thrown if transaction number is not found
     */
    @GET
    @Path("/transactions/{transaction_id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    public Transaction getTransactionsForId(@PathParam("transaction_id") Long transactionId) throws TransactionNotFoundException {

        log.info("Transaction Id: {}", transactionId);
        return transactionService.getTransactionForId(transactionId);
    }

}
