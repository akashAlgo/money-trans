package drop.wiz.money.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Author: arastogi
 */

@Provider
public class InvalidCurrencyCodeExceptionMapper implements ExceptionMapper<InvalidCurrencyCodeException> {

    @Override
    public Response toResponse(InvalidCurrencyCodeException exception) {
        return Response.status(TransferErrorCodes.INVALID_CURRENCY_CODE.getCode())
                .entity(TransferErrorCodes.INVALID_CURRENCY_CODE.getMessage())
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
