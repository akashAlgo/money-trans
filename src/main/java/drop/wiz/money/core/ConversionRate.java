package drop.wiz.money.core;

import lombok.*;

/**
 * Author: arastogi
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
