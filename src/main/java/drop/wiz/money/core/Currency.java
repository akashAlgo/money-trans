package drop.wiz.money.core;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Author: arastogi
 */

@Getter
@AllArgsConstructor
public enum Currency {
	
	EURO("EUR"),
	USD("USD"),
	GBP("GBP");

	private String code;
	
}
