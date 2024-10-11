package com.Persistence;

public interface GenericRepository <T> {
    T add(T entity);

    public T get(int id);

    public void update(T entity);

    public void delete(T entity);

    public int getSize();
    public void show();

}
