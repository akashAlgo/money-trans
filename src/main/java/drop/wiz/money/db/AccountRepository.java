package drop.wiz.money.db;

import drop.wiz.money.core.Account;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

/**
 * Author: arastogi
 */

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

    public List<Account> findByUserId(String userId) {

        return list(namedQuery("account.byUserId").setParameter("userId", userId));
    }

    public Account saveOrUpdate(Account account) {
        return persist(account);
    }

    public void delete(Long id) {
        currentSession().delete(findById(id).get());
    }
}
