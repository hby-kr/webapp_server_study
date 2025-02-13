package com.tj703.webapp_server_study.model2.dao;

import java.util.List;

public interface CRUD<T, P> {
    void close();
    List<T> findAll() throws Exception;
    T findById(P id) throws Exception;
    int deleteById(P id) throws Exception;
    int update(T t) throws Exception;
    int insert(T t) throws Exception;

}
