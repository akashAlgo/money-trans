package drop.wiz.money.service;

import drop.wiz.money.core.ConversionRate;
import drop.wiz.money.core.Currency;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: arastogi
 * Date: 2019-06-15
 */
public class ConversionRateService {

    static List<ConversionRate> conversionRates = new ArrayList<>();

    public ConversionRateService() {
        init();
    }

    /*
     * Ideally this data should come from either a real-time service or a database
     */
    private static void init() {

        conversionRates.add(new ConversionRate(Currency.USD, Currency.GBP, 0.84));
        conversionRates.add(new ConversionRate(Currency.GBP, Currency.USD, 1.21));
        conversionRates.add(new ConversionRate(Currency.GBP, Currency.USD, 1.21));
        conversionRates.add(new ConversionRate(Currency.GBP, Currency.EURO, 1.09));
        conversionRates.add(new ConversionRate(Currency.EURO, Currency.GBP, 0.91));
        conversionRates.add(new ConversionRate(Currency.USD, Currency.EURO, 0.91));
        conversionRates.add(new ConversionRate(Currency.EURO, Currency.USD, 1.09));
    }

    public Double getConversionDate(Currency source, Currency destination) {
        return conversionRates.parallelStream().filter(rate -> rate.getSource().equals(source)
                && rate.getDestination().equals(destination)).findAny().get().getRate();
    }
}
