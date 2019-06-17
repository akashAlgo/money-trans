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

    //create transaction
    @POST
    @Path("/transactions")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    public Transaction createTransaction(TransactionRequest transactionRequest) throws TransactionFailedException {

        log.info("Request: {}", transactionRequest);
        return transactionService.createTransaction(transactionRequest);
    }

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
