package drop.wiz.money.db;

import drop.wiz.money.core.Account;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.Optional;

public class AccountRepository extends AbstractDAO<Account> {
    /**
     * Creates a new DAO with a given session provider.
     *
     * @param sessionFactory a session provider
     */
    public AccountRepository(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Optional<Account> findById(Long id) {
        return Optional.ofNullable(get(id));
    }

    public Account create(Account account) {
        return persist(account);
    }
}
