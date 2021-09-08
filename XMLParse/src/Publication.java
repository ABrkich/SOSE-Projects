public class Publication {
    //(article|inproceedings|proceedings|book|incollection|
    //phdthesis|mastersthesis|www|person|data)*>

    //<!ATTLIST dblp mdate CDATA #IMPLIED >

    //author|editor|title|booktitle|pages|year|address|journal|volume|number|
    // month|url|ee|cdrom|cite|publisher|note|crossref|isbn|series|school|chapter|publnr">

    String type; //article, inproceedings, proceedings, book ,etc
    String author; //vector/array
    String editor;
    String title;
    String booktitle;
    String pages;
    String year;
    String address;
    String journal;
    String volume;
    String number;
    String month;
    String url;
    String ee; //vector/array
    String cdrom;
    String cite;
    String publisher;
    String note;
    String crossref;
    String isbn;
    String series;
    String school;
    String chapter;
    String publnr;
}
