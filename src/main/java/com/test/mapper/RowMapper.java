package com.test.mapper;

public interface RowMapper<T> {
    T mapFrom(String line);
}
