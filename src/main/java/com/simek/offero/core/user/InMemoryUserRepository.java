package com.simek.offero.core.user;

import com.simek.offero.core.user.model.User;
import com.simek.offero.core.user.model.EmailAddress;
import com.simek.offero.core.user.ports.outgoing.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class InMemoryUserRepository implements UserRepository {

    List<User> db = new ArrayList<>();

    @Override
    public Optional<User> findUserByEmailEquals(EmailAddress email) {
        return db.stream().filter(user -> user.getEmail().equals(email)).findFirst();
    }

    @Override
    public <S extends User> S save(S entity) {
        this.db.add(entity);
        return entity;
    }

    @Override
    public <S extends User> Iterable<S> saveAll(Iterable<S> entities) {
        entities.forEach(db::add);
        return entities;
    }

    @Override
    public Optional<User> findById(Long id) {
        return db.stream().filter(user -> user.getId().equals(id)).findFirst();
    }

    @Override
    public boolean existsById(Long id) {
        return db.stream().anyMatch(user -> user.getId().equals(id));
    }

    @Override
    public Iterable<User> findAll() {
        return db;
    }

    @Override
    public Iterable<User> findAllById(Iterable<Long> id) {
        return db;
    }

    @Override
    public long count() {
        return db.size();
    }

    @Override
    public void deleteById(Long id) {
        db.removeIf(user -> user.getId().equals(id));
    }

    @Override
    public void delete(User entity) {
        db.removeIf(user -> user.equals(entity));
    }

    @Override
    public void deleteAll(Iterable<? extends User> entities) {
    }

    @Override
    public void deleteAll() {
        db.clear();
    }
}
