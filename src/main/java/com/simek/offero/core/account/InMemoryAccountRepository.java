package com.simek.offero.core.account;

import com.simek.offero.core.account.model.Account;
import com.simek.offero.core.account.model.EmailAddress;
import com.simek.offero.core.account.ports.outgoing.AccountRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class InMemoryAccountRepository implements AccountRepository{

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
    public Optional<Account> findById(Long id) {
        return db.stream().filter(account -> account.getId().equals(id)).findFirst();
    }

    @Override
    public boolean existsById(Long id) {
        return db.stream().anyMatch(account -> account.getId().equals(id));
    }

    @Override
    public Iterable<Account> findAll() {
        return db;
    }

    @Override
    public Iterable<Account> findAllById(Iterable<Long> id) {
        return db;
    }

    @Override
    public long count() {
        return db.size();
    }

    @Override
    public void deleteById(Long id) {
        db.removeIf(account -> account.getId().equals(id));
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
