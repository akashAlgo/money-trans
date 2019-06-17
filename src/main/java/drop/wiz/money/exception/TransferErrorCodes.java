package drop.wiz.money.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Author: arastogi
 */

@Getter
@AllArgsConstructor
public enum TransferErrorCodes {

    ACCOUNT_NOT_FOUND(404,"Account Number not found"),
    TRANSACTION_FAILED(502, "Transaction could not be completed, either source or destination account doesn't exist"),
    TRANSACTION_NOT_FOUND(404, "Transaction Id not found"),
    INVALID_ACCOUNT_TYPE(502, "Account Type Invalid, valid values are CURRENT and SAVINGS"),
    INVALID_CURRENCY_CODE(502, "Invalid Currency Code, valid values are EURO, GBP and USD");

    private int code;
    private String message;
}
