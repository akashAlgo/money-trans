package drop.wiz.money.resources;

import drop.wiz.money.api.TransactionRequest;
import drop.wiz.money.core.Transaction;
import drop.wiz.money.exception.TransactionFailedException;
import drop.wiz.money.exception.TransactionNotFoundException;
import drop.wiz.money.service.TransactionService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

public class TransactionEndpoint {

    private TransactionService transactionService;

    public TransactionEndpoint(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    //create transaction
    @POST
    @Path("/transaction")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Transaction createTransaction(TransactionRequest transactionRequest) throws TransactionFailedException {

        return transactionService.createTransaction(transactionRequest);
    }

    @GET
    @Path("/transaction")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Transaction> getTransactionsForAccount(Long accountId,
                                                       @QueryParam("onlyOutgoing") boolean onlySource,
                                                       @QueryParam("onlyIncoming") boolean onlyDestination) {
        return transactionService.getTransactionsForAccount(accountId, onlySource, onlyDestination);
    }

    @GET
    @Path("/transaction/{transaction_id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Transaction getTransactionsForId(@PathParam("transaction_id") Long transactionId) throws TransactionNotFoundException {
        return transactionService.getTransactionForId(transactionId);
    }

}
