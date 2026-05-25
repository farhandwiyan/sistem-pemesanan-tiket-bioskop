package controller;

import java.util.List;

public interface Controller<T> {
    public void handleSave(T item) throws Exception;
    public List<T> getAll() throws Exception;
}
