import java.util.List;

public class Inproceedings {
    String did;
    List<Author> authors;
    String title;
    String pages;
    String year;
    String booktitle;
    List<String> ee;
    String crossRef;
    String url;

    public Inproceedings() {

    }

    public String toString(){

        return this.did + "|" + this.title + "|" + this.authors.toString() + "|" + this.pages + "|" + this.year + "|" + this.booktitle + "|" + this.ee.toString() + "|"  + "|" + this.crossRef + "|" + this.url;

    }

}
