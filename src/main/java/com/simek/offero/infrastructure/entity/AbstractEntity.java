package com.simek.offero.infrastructure.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@Getter
@Setter
public abstract class AbstractEntity {

    protected Date creationDate;
    protected Date modificationDate;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @PrePersist
    public void prePersist() {
        setCreationDate(new Date());
        preUpdate();
    }

    @PreUpdate
    public void preUpdate() {
        setModificationDate(new Date());
    }
}
