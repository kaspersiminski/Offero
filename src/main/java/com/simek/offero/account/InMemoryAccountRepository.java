package com.simek.offero.account;

import com.simek.offero.account.model.Account;
import com.simek.offero.account.model.AccountId;
import com.simek.offero.account.model.EmailAddress;
import com.simek.offero.account.ports.outgoing.AccountRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryAccountRepository implements AccountRepository{

    List<Account> db = new ArrayList<>();

    @Override
    public Optional<Account> findAccountByEmailEquals(EmailAddress email) {
        return db.stream().filter(account -> account.getEmail().equals(email)).findFirst();
    }

    @Override
    public <S extends Account> S save(S entity) {
        this.db.add(entity);
        return entity;
    }

    @Override
    public <S extends Account> Iterable<S> saveAll(Iterable<S> entities) {
        entities.forEach(db::add);
        return entities;
    }

    @Override
    public Optional<Account> findById(AccountId accountId) {
        return db.stream().filter(account -> account.getAccountId().equals(accountId)).findFirst();
    }

    @Override
    public boolean existsById(AccountId accountId) {
        return db.stream().anyMatch(account -> account.getAccountId().equals(accountId));
    }

    @Override
    public Iterable<Account> findAll() {
        return db;
    }

    @Override
    public Iterable<Account> findAllById(Iterable<AccountId> accountIds) {
        return db;
    }

    @Override
    public long count() {
        return db.size();
    }

    @Override
    public void deleteById(AccountId accountId) {
        db.removeIf(account -> account.getAccountId().equals(accountId));
    }

    @Override
    public void delete(Account entity) {
        db.removeIf(account -> account.equals(entity));
    }

    @Override
    public void deleteAll(Iterable<? extends Account> entities) {
    }

    @Override
    public void deleteAll() {
        db.clear();
    }
}
