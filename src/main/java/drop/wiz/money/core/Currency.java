/**
 * 
 */
package drop.wiz.money.core;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author ar
 * Jun 12, 2019
 */
@Getter
@AllArgsConstructor
public enum Currency {
	
	EURO("EUR"),
	USD("USD"),
	GBP("GBP");

	private String code;
	
}
