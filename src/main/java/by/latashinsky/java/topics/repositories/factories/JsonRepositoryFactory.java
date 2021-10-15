package by.latashinsky.java.topics.repositories.factories;

import by.latashinsky.java.topics.entities.Account;
import by.latashinsky.java.topics.entities.Bank;
import by.latashinsky.java.topics.entities.Transaction;
import by.latashinsky.java.topics.entities.User;
import by.latashinsky.java.topics.repositories.*;
import by.latashinsky.java.topics.repositories.json.JsonAccountRepository;
import by.latashinsky.java.topics.repositories.json.JsonBankRepository;
import by.latashinsky.java.topics.repositories.json.JsonTransactionRepository;
import by.latashinsky.java.topics.repositories.json.JsonUserRepository;

public class JsonRepositoryFactory implements RepositoryFactory {
    @Override
    @SuppressWarnings("unchecked")
    public <T> MyRepository<T> getRepository(Class<T> clazz) {
        if (clazz.equals(Bank.class)) {
            return (MyRepository<T>) JsonBankRepository.getInstance();
        }
        if (clazz.equals(Account.class)) {
            return (MyRepository<T>) JsonAccountRepository.getInstance();
        }
        if (clazz.equals(User.class)) {
            return (MyRepository<T>) JsonUserRepository.getInstance();
        }
        if (clazz.equals(Transaction.class)) {
            return (MyRepository<T>) JsonTransactionRepository.getInstance();
        }
        return null;
    }
}
