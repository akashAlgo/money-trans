package drop.wiz.money.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Author: arastogi
 */

@Provider
public class TransactionNotFoundExceptionMapper implements ExceptionMapper<TransactionNotFoundException> {

    @Override
    public Response toResponse(TransactionNotFoundException exception) {
        return Response.status(TransferErrorCodes.TRANSACTION_NOT_FOUND.getCode())
                .entity(TransferErrorCodes.TRANSACTION_NOT_FOUND.getMessage())
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
