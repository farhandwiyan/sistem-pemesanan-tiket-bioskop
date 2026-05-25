package repository;

import java.util.List;

public interface Repository<T> {
    public void save(T item) throws Exception;
    public List<T> findAll() throws Exception;
}
