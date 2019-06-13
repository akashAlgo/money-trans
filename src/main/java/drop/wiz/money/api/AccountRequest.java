package drop.wiz.money.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import drop.wiz.money.core.AccountType;
import drop.wiz.money.core.Currency;

public class AccountRequest {

    @JsonProperty(value = "is_active", defaultValue = "true")
    private Boolean isActive;

    @JsonProperty("account_type")
    private AccountType accountType;

    @JsonProperty
    private Currency currency;

    @JsonProperty("user_id")
    private String userId;
}
