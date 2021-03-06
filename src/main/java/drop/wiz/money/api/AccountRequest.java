package drop.wiz.money.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author: arastogi
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountRequest {

    @JsonProperty(value = "is_active", defaultValue = "true")
    private Boolean isActive;

    @JsonProperty(value = "account_type")
    private String accountType;

    @JsonProperty
    private String currency;

    @JsonProperty(value = "balance", defaultValue = "0")
    private Double balance;

    @JsonProperty("user_id")
    private String userId;
}
