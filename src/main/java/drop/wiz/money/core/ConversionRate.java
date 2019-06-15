package drop.wiz.money.core;

import lombok.*;

/**
 * Author: arastogi
 * Date: 2019-06-15
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConversionRate {

    private Currency source;
    private Currency destination;
    private Double rate;

}
