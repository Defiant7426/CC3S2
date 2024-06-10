package ejercicio3;

public class Main {
    public static void main(String[] args) {
        BlogManager blogManager = new BlogManager();
        blogManager.saveArticle("Title 1", "Content 1");
        blogManager.saveArticle("Title 2", "Content 2");
        blogManager.saveArticle("Title 3", "Content 3");
        blogManager.printAllArticles();
        System.out.println("Count: " + blogManager.countArticles());
        Article article = blogManager.findArticle(2);
        System.out.println("Title: " + article.getTitle() + " Content: " + article.getContent());
        blogManager.deleteArticle(article);
        blogManager.printAllArticles();
        System.out.println("Count: " + blogManager.countArticles());
    }
}
