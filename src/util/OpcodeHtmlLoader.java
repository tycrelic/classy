package util;

import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.text.*;
import javax.swing.text.html.*;
import javax.swing.text.html.parser.*;

public class OpcodeHtmlLoader extends HTMLEditorKit.ParserCallback {

    Map<String, Map<String, String>> opcodes = new TreeMap<String, Map<String, String>>();
    int insideOpcode = -1;// -1 - finished, 0 - name, 1 - property name, 2 - property value
    String opcode;
    String propName;
    String propValue;
    Map<String, String> properties;

    @Override
    public void flush() throws BadLocationException {
        System.out.println("#flush");
    }

    @Override
    public void handleText(char[] data, int pos) {
        if (insideOpcode == 0) {
            opcode = String.valueOf(data);
            properties = new TreeMap<String, String>();
        } else if (insideOpcode == 1) {
            propName = String.valueOf(data);
        } else {
            System.out.println("#text:");
            System.out.println(new String(data));
        }
    }

    @Override
    public void handleComment(char[] data, int pos) {
        System.out.println("#comment:");
        System.out.println(new String(data));
    }

    @Override
    public void handleStartTag(HTML.Tag t, MutableAttributeSet a, int pos) {
        if (t == HTML.Tag.H2 && insideOpcode == -1) {
            insideOpcode = 0;
        } else if (t == HTML.Tag.B && insideOpcode == 0) {
            insideOpcode = 1;
        } else {
            System.out.print("#start tag:");
            System.out.println(t);
        }
    }

    @Override
    public void handleEndTag(HTML.Tag t, int pos) {
        if (t == HTML.Tag.H2) {
        } else {
            System.out.print("#end tag:");
            System.out.println(t);
        }
    }

    @Override
    public void handleSimpleTag(HTML.Tag t, MutableAttributeSet a, int pos) {
        if (t == HTML.Tag.HR) {
            if (opcode != null) {
                opcodes.put(opcode, properties);
                insideOpcode = -1;
            }
        } else if (t == HTML.Tag.IMG) {
            String src = (String) a.getAttribute(HTML.Attribute.SRC);
            System.out.println(src);

            /*
            Enumeration e = a.getAttributeNames();
            System.out.println("<");
            while(e.hasMoreElements()) {
            Object attr = e.nextElement();
            System.out.println(attr.getClass());
            System.out.println(attr);
            }
            System.out.println(">");
             */
        } else {
            System.out.print("#simple tag:");
            System.out.println(t);
        }
    }

    @Override
    public void handleError(String errorMsg, int pos) {
        System.out.println("#errorMsg: ");
        System.out.println(errorMsg);
    }

    @Override
    public void handleEndOfLineString(String eol) {
        System.out.println("#eol: " + eol);
        System.out.println(eol);
    }

    public static void main(String[] args) throws Exception {
        String urlString = "http://java.sun.com/docs/books/jvms/second_edition/html/Instructions2.doc.html";
        URL url = new URL(urlString);

        OpcodeHtmlLoader loader = new OpcodeHtmlLoader();
        Reader reader = new InputStreamReader(url.openStream());
        new ParserDelegator().parse(reader, loader, false);
        reader.close();

        System.out.println();
    }
}
