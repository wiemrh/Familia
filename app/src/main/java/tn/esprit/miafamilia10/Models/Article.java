package tn.esprit.miafamilia10.Models;

public class Article {
    int id ;
    private String NomA ;

    public Article(int id, String NomA) {
        this.id = id;
        this.NomA = NomA;
    }

    public Article(int id) {
        this.id = id;
    }

    public Article(String NomA) {
        this.NomA = NomA;
    }

    public int getId() {
        return id;
    }

    public String getNomA() {
        return NomA;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setArticle(String NomA) {
        this.NomA = NomA;
    }

}
