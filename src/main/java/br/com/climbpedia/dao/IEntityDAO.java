package br.com.climbpedia.dao;

public class IEntityDAO<T> {
  public static List<T> listAll();
  public static T get(Long id);
  public static void insert(T entity);
  public static void update(T entity);
  public static void delete(Long id);
}