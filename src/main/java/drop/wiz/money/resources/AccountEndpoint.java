package drop.wiz.money.resources;

import drop.wiz.money.core.Account;
import drop.wiz.money.api.AccountRequest;
import drop.wiz.money.db.AccountRepository;
import drop.wiz.money.exception.AccountNotFoundException;
import drop.wiz.money.service.AccountService;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("")
@Slf4j
public class AccountEndpoint {

    public static final String USER = "user_id";

    private AccountService accountService;

    public AccountEndpoint(AccountService accountService) {
        this.accountService = accountService;
    }

    @GET
    @Path("/account/{accountNumber}")
    @Produces(MediaType.APPLICATION_JSON)
    public Account getAccountsById(@PathParam("accountNumber") Long accountNumber)
    throws AccountNotFoundException {
        return accountService.getAccountById(accountNumber);
    }

    @GET
    @Path("/account")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Account> getAccountsForUser(@Context final HttpServletRequest request) {

        String userId = request.getHeader(USER);
        return accountService.getAccountsForUser(userId);
    }

    @POST
    @Path("/account")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Account createAccount(AccountRequest accountRequest) {
        return accountService.saveOrUpdate(accountRequest);
    }

    @PUT
    @Path("/account")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Account updateAccount(AccountRequest accountRequest) {
        return accountService.saveOrUpdate(accountRequest);
    }

    @DELETE
    @Path("/account")
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteAccount(Long accountNumber, @Context final HttpServletRequest request) {
        accountService.deleteAccount(accountNumber);
    }
}
