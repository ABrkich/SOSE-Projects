//package com.tutorialspoint.xquery;

import com.saxonica.xqj.SaxonXQDataSource;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQPreparedExpression;
import javax.xml.xquery.XQResultSequence;

//ideas taken from http://www.tutorialspoint.com/xquery/xquery_environment.htm
public class XQueryTester {
    public static void main(String[] args) throws XQException {
        //System.out.println(xQuery1());
        System.out.println(xQuery2("Jia Zhang", "2018"));
        //System.out.println(xQuery3());
        //System.out.println(xQuery4("Ontology Classification for Semantic-Web-Based Software Engineering."));

    }



    public static String xQuery1() throws XQException {
        //InputStream inputStream = new FileInputStream(new File("src/publications.xqy"));

        String inputStream = "for $x in doc(\"dblp-soc-papers.xml\")/dblp/*\n" +
                "return $x/title";

        XQDataSource ds = new SaxonXQDataSource();
        XQConnection conn = ds.getConnection();
        XQPreparedExpression exp = conn.prepareExpression(inputStream);
        XQResultSequence result = exp.executeQuery();

        String outstring = "";

        while (result.next()) {
            outstring += result.getItemAsString(null) + "\n";
        }

        return outstring;

    }

    public static String xQuery2(String author, String year) throws XQException {
        //InputStream inputStream = new FileInputStream(new File("src/publications.xqy"));

        String inputStream = "for $x in doc(\"dblp-soc-papers.xml\")/dblp/*\n" +
                "where $x/author = '"+ author + "'\n" +
                "where $x/year = '"+ year + "'\n" +
                "return $x/title";

        System.out.println(inputStream);

        XQDataSource ds = new SaxonXQDataSource();
        XQConnection conn = ds.getConnection();
        XQPreparedExpression exp = conn.prepareExpression(inputStream);
        XQResultSequence result = exp.executeQuery();

        String outstring = "";

        while (result.next()) {
            System.out.println("here");
            outstring += result.getItemAsString(null) + "\n";
        }

        System.out.println(outstring);
        return outstring;
    }

    public static String xQuery3() throws XQException {
        //InputStream inputStream = new FileInputStream(new File("src/publications.xqy"));

        String inputStream = "for $name in doc(\"dblp-soc-papers.xml\")/dblp/*/author\n" +
                "  let $full :=\n" +
                "    if(not($name/@p)) then concat($name, '')\n" +
                "    else()\n" +
                "  group by $full\n" +
                "  order by count($name) descending\n" +
                "  return if (count($name) >= 10) then concat($full, ',', count($name)) else()";

        XQDataSource ds = new SaxonXQDataSource();
        XQConnection conn = ds.getConnection();
        XQPreparedExpression exp = conn.prepareExpression(inputStream);
        XQResultSequence result = exp.executeQuery();

        String outstring = "";

        while (result.next()) {

            String out = result.getItemAsString(null);

            String[] stringArr = out.split(",");



            outstring += ("Author: " + stringArr[0] +"\n");
            outstring += "Number of Publications: " + stringArr[1] + "\n";
            outstring += "___________________________________________________\n";
        }

        return outstring;
    }

    public static String xQuery4(String title) throws XQException {
        //InputStream inputStream = new FileInputStream(new File("src/publications.xqy"));

        String inputStream = "declare variable $papername := '"+ title +"';\n" +
                "for $x in doc(\"dblp-soc-papers.xml\")/dblp/*\n" +
                "where $x/title = $papername\n" +
                "return $x";

        XQDataSource ds = new SaxonXQDataSource();
        XQConnection conn = ds.getConnection();
        XQPreparedExpression exp = conn.prepareExpression(inputStream);
        XQResultSequence result = exp.executeQuery();

        String outstring = "";

        while (result.next()) {
            outstring += result.getItemAsString(null) + "\n";
        }

        return outstring;
    }
}
