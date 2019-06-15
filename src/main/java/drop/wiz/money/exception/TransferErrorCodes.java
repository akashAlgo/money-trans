package drop.wiz.money.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Author: arastogi
 * Date: 2019-06-15
 */
@Getter
@AllArgsConstructor
public enum TransferErrorCodes {

    ACCOUNT_NOT_FOUND(404,"Account not found"),
    TRANSACTION_FAILED(502, "Transaction could not be completed, please try later"),
    TRANSACTION_NOT_FOUND(404, "Transaction not found");

    private int code;
    private String message;
}
