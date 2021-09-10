import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.ArrayList;
import java.util.List;

public class PublicationParser {
    public static List<Publication> getParserResult() {
        try {
            // Get the DOM Builder Factory
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setIgnoringElementContentWhitespace(true);
            // Get the DOM Builder
            DocumentBuilder builder = factory.newDocumentBuilder();
            // Load and Parse the XML document
            // document contains the complete XML as a Tree
            Document document = builder.parse(ClassLoader.getSystemResourceAsStream("dblp-soc-papers.xml"));
            // Iterating through the nodes and extracting the data
            NodeList publications = document.getElementsByTagName("inproceedings");
            int numItems = publications.getLength();
            List<Publication> pubList = new ArrayList<Publication>();
            for (int i = 0; i < publications.getLength(); i++) {
                Node node = publications.item(i);
                NodeList childNode = node.getChildNodes();
                int numChildren = childNode.getLength();
                Publication pub = new Publication();
                for (int z = 0; z < numChildren; z++)
                {
                    Node child = childNode.item(z);
                    //System.out.println((child));
                    String x = child.getNodeName();
                    //System.out.println(x);
                    if (x == "title")
                    {
                        pub.title = child.getNodeValue();
                        String y = pub.title;
                    }
                }
////////////////
//                    try {
//                        pub.title = node.getAttributes().getNamedItem("title").getNodeValue();
//                    }
//                    catch(NullPointerException e) {
//                        throw new Exception("No Title on Publication, crash at item " + i + "!");
//                    }
                        // Identifying the child tag of a publication encountered
//                        if (cNode instanceof Element) {
//                            String content = cNode.getLastChild().getTextContent().trim();
//                            switch (cNode.getNodeName()) {
//                                case "editor":
//                                    pub.editor = content;
//                                    break;
//                                case "author":
//                                    pub.author = content;
//                                    break;
//                                case "booktitle":
//                                    pub.booktitle = content;
//                                    break;
//                                case "pages":
//                                    pub.pages = content;
//                                    break;
//                                case "year":
//                                    pub.year = content;
//                                    break;
//                                case "address":
//                                    pub.address = content;
//                                    break;
//                                case "journal":
//                                    pub.journal = content;
//                                    break;
//                                case "volume":
//                                    pub.volume = content;
//                                    break;
//                                case "number":
//                                    pub.number = content;
//                                    break;
//                                case "month":
//                                    pub.month = content;
//                                    break;
//                                case "url":
//                                    pub.url = content;
//                                    break;
//                                case "ee":
//                                    pub.ee = content;
//                                    break;
//                                case "cdrom":
//                                    pub.cdrom = content;
//                                    break;
//                                case "cite":
//                                    pub.cite = content;
//                                    break;
//                                case "publisher":
//                                    pub.publisher = content;
//                                    break;
//                                case "note":
//                                    pub.note = content;
//                                    break;
//                                case "crossref":
//                                    pub.crossref = content;
//                                    break;
//                                case "isbn":
//                                    pub.isbn = content;
//                                    break;
//                                case "series":
//                                    pub.series = content;
//                                    break;
//                                case "school":
//                                    pub.school = content;
//                                    break;
//                                case "chapter":
//                                    pub.chapter = content;
//                                    break;
//                                case "publnr":
//                                    pub.publnr = content;
//                                    break;
//                            }
//                        }

                    pubList.add(pub);
                //}
            }
            // Print the Publication list
            for (Publication e : pubList) {
                System.out.println(e);
            }
            return pubList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args)
    {
        List<Publication> testing123 = getParserResult();
    }
}
