package drop.wiz.money.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author: arastogi
 * Date: 2019-06-16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountNumberRequest {

    @JsonProperty("account_number")
    private Long accountNumber;
}
