package se.hulot.jbpm6jboss7example.dao;

public interface Dao<T> {
	public T create(T t);
	public T find(Object id);
	public T update(T t);
	public void delete(Object id);
}
