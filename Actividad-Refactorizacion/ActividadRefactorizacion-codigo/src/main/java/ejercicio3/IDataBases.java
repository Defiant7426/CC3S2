package ejercicio3;

import java.util.List;

public interface IDataBases {
    void save(Article article);
    Article findById(int id);
    List<Article> findAll();
    long count();
    void delete(Article article);
}