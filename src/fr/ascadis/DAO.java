package fr.ascadis;

import java.util.List;

public abstract class DAO<T, I>
{
  public abstract T find(I id);
  public abstract List<T> findAll();
  public abstract T save(T object);
  public abstract void delete(T object);
}
