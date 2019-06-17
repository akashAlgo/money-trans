package drop.wiz.money.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Author: arastogi
 */

@Provider
public class TransactionFailedExceptionMapper implements ExceptionMapper<TransactionFailedException> {

    @Override
    public Response toResponse(TransactionFailedException exception) {
        return Response.status(TransferErrorCodes.TRANSACTION_FAILED.getCode())
                .entity(TransferErrorCodes.TRANSACTION_FAILED.getMessage())
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
