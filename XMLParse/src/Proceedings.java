import java.util.List;

public class Proceedings {
    String did;
    String title;
    String year;
    String booktitle;
    String publisher;
    String isbn;
    String url;

    public Proceedings() {

    }

    public String toString(){

        return this.did + "|" + this.title +  "|" +  this.year + "|" + this.booktitle + "|" + this.publisher + "|" + this.isbn + "|" + this.url;

    }
}
