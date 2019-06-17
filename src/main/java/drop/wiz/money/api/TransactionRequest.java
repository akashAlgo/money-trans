package drop.wiz.money.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Author: arastogi
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionRequest {

    @JsonProperty("source_account")
    private Long sourceAccount;

    @JsonProperty("destination_account")
    private Long destinationAccount;

    @JsonProperty
    private BigDecimal amount;
}
