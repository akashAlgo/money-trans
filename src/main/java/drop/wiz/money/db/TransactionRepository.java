package drop.wiz.money.db;

import drop.wiz.money.core.Transaction;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;
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

    public List<Transaction> findByAccountIdSource(Long accountNumber) {
        return list(namedQuery("transaction.bySourceAccNumber").setParameter("accNumber", accountNumber));
    }

    public List<Transaction> findByAccountIdDestination(Long accountNumber) {
        return list(namedQuery("transaction.byDestinationAccNumber").setParameter("accNumber", accountNumber));
    }

    public Transaction create(Transaction transaction) {
        return persist(transaction);
    }

}
