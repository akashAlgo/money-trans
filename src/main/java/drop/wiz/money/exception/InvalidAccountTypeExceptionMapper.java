package drop.wiz.money.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Author: arastogi
 */

@Provider
public class InvalidAccountTypeExceptionMapper implements ExceptionMapper<InvalidAccountTypeException> {

    @Override
    public Response toResponse(InvalidAccountTypeException exception) {
        return Response.status(TransferErrorCodes.INVALID_ACCOUNT_TYPE.getCode())
                .entity(TransferErrorCodes.INVALID_ACCOUNT_TYPE.getMessage())
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
