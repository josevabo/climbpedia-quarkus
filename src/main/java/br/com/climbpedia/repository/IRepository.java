package br.com.climbpedia.repository;

public Interface IRepository<T> {
  public List<T> listAll();
  public T getById(Long id);
  public void insert(T entity);
  public void update(T entity);
  public void delete(Long id);
}
