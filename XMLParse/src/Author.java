public class Author {
    String name; //author name
    String title; //title of the book //need to be array/vector for all the books anyway

    //to find all authors of the book, query all distinct authors with author.title = book name
    //to find all total co-authors of one author, query all titles that author worked on,
        // then query all distinct authors with those titles
}
