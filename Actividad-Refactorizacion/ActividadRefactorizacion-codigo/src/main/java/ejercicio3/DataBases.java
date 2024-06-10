package ejercicio3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataBases implements IDataBases{

    private Map<Integer, Article> articleMap = new HashMap<>();

    @Override
    public void save(Article article) {
        articleMap.put(articleMap.size(), article);
        System.out.println("Articulo guardado");
    }

    @Override
    public Article findById(int id) {
        return articleMap.get(id);
    }

    @Override
    public List<Article> findAll() {
        return new ArrayList<>(articleMap.values());
    }

    @Override
    public long count() {
        return articleMap.size();
    }

    @Override
    public void delete(Article article) {
        articleMap.values().remove(article);
        System.out.println("Articulo eliminado");
    }
}