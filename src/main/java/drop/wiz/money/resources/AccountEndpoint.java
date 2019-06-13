package drop.wiz.money.resources;

import drop.wiz.money.core.Account;
import drop.wiz.money.api.AccountRequest;
import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("")
@Slf4j
public class AccountEndpoint {

    @GET
    @Path("/account/{accountNumber}")
    @Produces(MediaType.APPLICATION_JSON)
    public Account getAccountsById(@PathParam("accountNumber") Long accountNumber) {
        return null;
    }

    @GET
    @Path("/account")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Account> getAccountsForUser(@QueryParam("userId") String userId) {
        return null;
    }

    @POST
    @Path("/account")
    @Consumes(MediaType.APPLICATION_JSON)
    public Account createAccount(AccountRequest accountInput) {

    }
}
