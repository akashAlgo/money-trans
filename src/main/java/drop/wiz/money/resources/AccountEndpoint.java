package drop.wiz.money.resources;

import drop.wiz.money.api.AccountRequest;
import drop.wiz.money.core.Account;
import drop.wiz.money.exception.AccountNotFoundException;
import drop.wiz.money.service.AccountService;
import io.dropwizard.hibernate.UnitOfWork;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Author: arastogi
 */

@Path("")
@Slf4j
public class AccountEndpoint {

    public static final String USER = "user_id";

    private AccountService accountService;

    public AccountEndpoint(AccountService accountService) {
        this.accountService = accountService;
    }

    @GET
    @Path("/accounts/{account_number}")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    public Account getAccountsById(@PathParam("account_number") Long accountNumber)
    throws AccountNotFoundException {

        log.info("Account Number: {} ", accountNumber);
        return accountService.getAccountById(accountNumber);
    }

    @GET
    @Path("/accounts")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    public List<Account> getAccountsForUser(@Context final HttpServletRequest request) {

        String userId = request.getHeader(USER);
        log.info("User Id {}", userId);
        return accountService.getAccountsForUser(userId);
    }

    @POST
    @Path("/accounts")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    public Account createAccount(AccountRequest accountRequest) {

        log.info("Account Request: {}", accountRequest);
        return accountService.save(accountRequest);
    }

    @DELETE
    @Path("/accounts/{account_number}")
    @Consumes(MediaType.APPLICATION_JSON)
    @UnitOfWork
    public void deleteAccount(@PathParam("account_number") Long accountNumber) {

        log.warn("Account Number: {}", accountNumber);
        accountService.deleteAccount(accountNumber);
    }
}
