package com.jeyofdev.spring_dto_mapper.common;

public interface BaseDomainMapper<T, D, S> {
    public D mapFromEntity(T entity, boolean... args);

    public T mapToEntity(S saveEntityDTO);
}
