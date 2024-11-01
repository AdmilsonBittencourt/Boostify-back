package com.boostify.boostify_back.service;

public interface BaseService<T, ID> {

    T create(T object);
    T findById(ID id);
    T update(ID id, T object);
    void delete(ID id);
}
