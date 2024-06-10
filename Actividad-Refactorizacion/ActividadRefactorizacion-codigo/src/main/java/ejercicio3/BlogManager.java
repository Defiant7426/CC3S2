package ejercicio3;

public class BlogManager {
    private IDataBases dataBases = new DataBases();

    public void saveArticle(String title, String content) {
        Article article = new Article(title, content);
        dataBases.save(article);
    }

    public void deleteArticle(Article article) {
        dataBases.delete(article);
    }

    public Article findArticle(int id) {
        return dataBases.findById(id);
    }

    public long countArticles() {
        return dataBases.count();
    }

    public void printAllArticles() {
        for (Article article : dataBases.findAll()) {
            System.out.println("Title: " + article.getTitle() + " Content: " + article.getContent());
        }
    }
}
