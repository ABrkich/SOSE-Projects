import java.util.List;

public class Article {
    String did;
    List<Author> authors;
    String title;
    String pages;
    String year;
    String journal;
    String volume;
    String number;
    List<String> ee;
    String url;

    public Article() {

    }

    public String toString(){

        return this.did + "|" + this.title + "|" + this.authors.toString() + "|" + this.journal +"|"+ this.volume +"|" + this.number +"|"+ this.pages + "|" + this.year + "|" + this.ee.toString() +  "|" + this.url;

    }
}
