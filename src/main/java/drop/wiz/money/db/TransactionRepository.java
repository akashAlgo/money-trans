package drop.wiz.money.db;

import drop.wiz.money.core.Transaction;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.Optional;

public class TransactionRepository extends AbstractDAO<Transaction> {
    /**
     * Creates a new DAO with a given session provider.
     *
     * @param sessionFactory a session provider
     */
    public TransactionRepository(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Optional<Transaction> findById(Long id) {
        return Optional.ofNullable(get(id));
    }

    public Transaction create(Transaction transaction) {
        return persist(transaction);
    }
}
