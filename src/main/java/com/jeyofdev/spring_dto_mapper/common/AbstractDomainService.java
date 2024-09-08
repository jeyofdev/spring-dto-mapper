package com.jeyofdev.spring_dto_mapper.common;

import com.jeyofdev.spring_dto_mapper.util.Helper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public abstract class AbstractDomainService<T> implements BaseDomainService<T> {
    protected JpaRepository<T, Long> repository;
    private final String entityName;

    public AbstractDomainService(JpaRepository<T, Long> repository, String entityName) {
        this.repository = repository;
        this.entityName = entityName;
    }

    @Override
    public T save(T entity) {
        return repository.save(entity);
    }

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    public T findById(Long entityId) {
        return repository.findById(entityId).orElseThrow(() -> new EntityNotFoundException(Helper.capitalize(entityName) + " with id " + entityId + " cannot be found"));
    }

    @Override
    public abstract T updateById(Long entityId, T updateEntityData);

    @Override
    public String deleteById(Long entityId) {
        findById(entityId);
        repository.deleteById(entityId);
        return entityName + " with id " + entityId + " deleted";
    }
}
