package drop.wiz.money.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Author: arastogi
 */

@Provider
public class AccountNotFoundExceptionMapper implements ExceptionMapper<AccountNotFoundException> {

    @Override
    public Response toResponse(AccountNotFoundException exception) {
        return Response.status(TransferErrorCodes.ACCOUNT_NOT_FOUND.getCode())
                .entity(TransferErrorCodes.ACCOUNT_NOT_FOUND.getMessage())
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
