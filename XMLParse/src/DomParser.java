import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;

public class DomParser {

  //Create unique identifiers for both the documents and the authors
  Integer authorId;
  Integer did;
  HashMap authors;
  public List<Inproceedings> getInproceedings() {
    try {
      // Get the DOM Builder Factory
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      // Get the DOM Builder
      DocumentBuilder builder = factory.newDocumentBuilder();
      // Load and Parse the XML document
      // document contains the complete XML as a Tree
      Document document = builder.parse(ClassLoader.getSystemResourceAsStream("dblp-soc-papers.xml"));
      // Iterating through the nodes and extracting the data
      NodeList nodeList = document.getDocumentElement().getElementsByTagName("inproceedings");
      List<Inproceedings> inpList = new ArrayList<Inproceedings>();
      for (int i = 0; i < nodeList.getLength(); i++) {
        List<Author> authList = new ArrayList<Author>();
        List<String> eeList = new ArrayList<String>();
        Node node = nodeList.item(i);
        if (node instanceof Element) {
          // We have encountered an <employee> tag
          Inproceedings inp = new Inproceedings();
          inp.did = this.did.toString();
          this.did++;
          NodeList childNodes = node.getChildNodes();
          for (int j = 0; j < childNodes.getLength(); j++) {
            Node cNode = childNodes.item(j);
            // Identifying the child tag of each Inproceedings
            if (cNode instanceof Element) {
              String content = cNode.getLastChild().getTextContent().trim().replace("'","");
              switch (cNode.getNodeName()) {
                case "author":
                  Author a = new Author();
                  String name = content;
                  a.name = name;
                  if(this.authors.containsKey(name)){
                    a.aid = this.authors.get(name).toString();

                }
                  else{
                    a.aid = this.authorId.toString();
                    authors.put(name,this.authorId);

                    this.authorId++;
                  }

                  authList.add(a);
                  break;
                case "title":
                  inp.title = content;
                  break;
                case "pages":
                  inp.pages = content;
                  break;
                case "year":
                  inp.year = content;
                  break;
                case "booktitle":
                  inp.booktitle = content;
                  break;
                case "crossref":
                  inp.crossRef = content;
                  break;
                case "url":
                  inp.url = content;
                  break;
                case "ee":
                  eeList.add(content);
                  break;
              }
            }
          }
          inp.authors = authList;
          inp.ee = eeList;
          inpList.add(inp);
        }
      }
      return inpList;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public List<Proceedings> getProceedings() {
    try {
      // Get the DOM Builder Factory
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      // Get the DOM Builder
      DocumentBuilder builder = factory.newDocumentBuilder();
      // Load and Parse the XML document
      // document contains the complete XML as a Tree
      Document document = builder.parse(ClassLoader.getSystemResourceAsStream("dblp-soc-papers.xml"));
      // Iterating through the nodes and extracting the data
      NodeList nodeList = document.getDocumentElement().getElementsByTagName("proceedings");
      List<Proceedings> procList = new ArrayList<Proceedings>();
      for (int i = 0; i < nodeList.getLength(); i++) {
        Node node = nodeList.item(i);
        if (node instanceof Element) {
          Proceedings proc = new Proceedings();
          proc.did = this.did.toString();
          this.did++;
          NodeList childNodes = node.getChildNodes();
          for (int j = 0; j < childNodes.getLength(); j++) {
            Node cNode = childNodes.item(j);
            // Identifying the child tag of each Proceedings
            if (cNode instanceof Element) {
              String content = cNode.getLastChild().getTextContent().trim().replace("'","");
              switch (cNode.getNodeName()) {
                case "title":
                  proc.title = content;
                  break;
                case "isbn":
                  proc.isbn = content;
                  break;
                case "year":
                  proc.year = content;
                  break;
                case "booktitle":
                  proc.booktitle = content;
                  break;
                case "publisher":
                  proc.publisher = content;
                  break;
                case "url":
                  proc.url = content;
                  break;
              }
            }
          }
          procList.add(proc);
        }
      }
      return procList;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public List<Article> getArticles() {
    try {
      // Get the DOM Builder Factory
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      // Get the DOM Builder
      DocumentBuilder builder = factory.newDocumentBuilder();
      // Load and Parse the XML document
      // document contains the complete XML as a Tree
      Document document = builder.parse(ClassLoader.getSystemResourceAsStream("dblp-soc-papers.xml"));
      // Iterating through the nodes and extracting the data
      NodeList nodeList = document.getDocumentElement().getElementsByTagName("article");
      List<Article> artList = new ArrayList<Article>();
      for (int i = 0; i < nodeList.getLength(); i++) {
        List<Author> authList = new ArrayList<Author>();
        List<String> eeList = new ArrayList<String>();
        Node node = nodeList.item(i);
        if (node instanceof Element) {
          // We have encountered an <employee> tag
          Article art = new Article();
          art.did = this.did.toString();
          this.did++;
          NodeList childNodes = node.getChildNodes();
          for (int j = 0; j < childNodes.getLength(); j++) {
            Node cNode = childNodes.item(j);
            // Identifying the child tag of each Journal Article
            if (cNode instanceof Element) {
              String content = cNode.getLastChild().getTextContent().trim().replace("'","");
              switch (cNode.getNodeName()) {
                case "author":
                  Author a = new Author();
                  String name = content;
                  a.name = name;
                  if(this.authors.containsKey(name)){
                    a.aid = this.authors.get(name).toString();

                  }
                  else{
                    a.aid = this.authorId.toString();
                    authors.put(name,this.authorId);

                    this.authorId++;
                  }

                  authList.add(a);
                  break;
                case "title":
                  art.title = content;
                  break;
                case "pages":
                  art.pages = content;
                  break;
                case "year":
                  art.year = content;
                  break;
                case "journal":
                  art.journal = content;
                  break;
                case "volume":
                  art.volume = content;
                  break;
                case "number":
                  art.number = content;
                  break;
                case "url":
                  art.url = content;
                  break;
                case "ee":
                  eeList.add(content);
                  break;
              }
            }
          }
          art.authors = authList;
          art.ee = eeList;
          artList.add(art);
        }
      }


      return artList;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

}
