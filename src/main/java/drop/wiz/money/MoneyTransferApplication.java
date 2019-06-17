package drop.wiz.money;

import drop.wiz.money.core.Account;
import drop.wiz.money.core.Transaction;
import drop.wiz.money.db.AccountRepository;
import drop.wiz.money.db.TransactionRepository;
import drop.wiz.money.exception.*;
import drop.wiz.money.resources.AccountEndpoint;
import drop.wiz.money.resources.TransactionEndpoint;
import drop.wiz.money.service.AccountService;
import drop.wiz.money.service.ConversionRateService;
import drop.wiz.money.service.TransactionService;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * Author: arastogi
 */

public class MoneyTransferApplication extends Application<MoneyTransferConfiguration> {

    public static void main(final String[] args) throws Exception {
        new MoneyTransferApplication().run(args);
    }

    private final HibernateBundle<MoneyTransferConfiguration> hibernate =
            new HibernateBundle<MoneyTransferConfiguration>(Account.class, Transaction.class) {
                @Override
                public DataSourceFactory getDataSourceFactory(MoneyTransferConfiguration configuration) {
                    return configuration.getDataSourceFactory();
                }
            };

    @Override
    public String getName() {
        return "MoneyTransfer";
    }

    @Override
    public void initialize(final Bootstrap<MoneyTransferConfiguration> bootstrap) {

        bootstrap.addBundle(hibernate);
    }

    @Override
    public void run(final MoneyTransferConfiguration configuration,
                    final Environment environment) {

        final AccountRepository accountRepository = new AccountRepository(hibernate.getSessionFactory());
        final TransactionRepository transactionRepository = new TransactionRepository(hibernate.getSessionFactory());
        final AccountService accountService = new AccountService(accountRepository);
        final ConversionRateService conversionRateService = new ConversionRateService();
        final TransactionService transactionService = new TransactionService(transactionRepository, accountService,
                conversionRateService);

        environment.jersey().register(new AccountEndpoint(accountService));
        environment.jersey().register(new TransactionEndpoint(transactionService));
        environment.jersey().register(new AccountNotFoundExceptionMapper());
        environment.jersey().register(new TransactionFailedExceptionMapper());
        environment.jersey().register(new TransactionNotFoundExceptionMapper());
        environment.jersey().register(new InvalidAccountTypeExceptionMapper());
        environment.jersey().register(new InvalidCurrencyCodeExceptionMapper());
    }

}
