package com.filesaver.domain.core.interfaces;


public interface Repository<T, Query> {
  T getOne(Query query) throws Exception;

  T save(T object) throws Exception;

  T[] getMany(Query query, String options) throws Exception;
  T[] getMany(Query query) throws Exception;

  T[] getAll() throws Exception;
  T[] getAll(Query options) throws Exception;

  T update(Query query, T object) throws Exception;
  T update(T object) throws Exception;
}
