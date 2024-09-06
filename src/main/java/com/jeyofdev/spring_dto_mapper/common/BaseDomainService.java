package com.jeyofdev.spring_dto_mapper.common;

import com.jeyofdev.spring_dto_mapper.domain.actor.Actor;

import java.util.List;

public interface BaseDomainService<T> {
    public T save(T entity);

    public List<T> findAll();

    public T findById(Long entityId);

    public T updateById(Long entityId, T updateEntityData);

    public String deleteById(Long entityId) ;
}
