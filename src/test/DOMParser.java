package test;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class DOMParser {
    DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = null;

    public static void main(String[] a) {
        new DOMParser().setup();
    }

    public void setup() {
        try {
            builder = documentBuilderFactory.newDocumentBuilder();
            String xmlString = "<application><applicant><status-code>1</status-code><marital-status>single</marital-status><phone-number>9876543210</phone-number></applicant><applicant><marital-status>single</marital-status><phone-number>9876543210</phone-number></applicant></application>";
            InputStream inputStream = new ByteArrayInputStream(xmlString.getBytes(Charset.forName("UTF-8")));
            Document document = builder.parse(inputStream);

            Element element = document.getDocumentElement();

            NodeList nodeList = element.getElementsByTagName("applicant");
            for (int i = 0; i < nodeList.getLength(); i++) {
                getProperties((Element) nodeList.item(i));
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getProperties(Element element) {
        List<String> props = Properties.getAll();
        for (String propString : props) {
            NodeList nodeList = element.getElementsByTagName(propString);
            if (nodeList != null && nodeList.getLength() > 0)
            {
                if(propString.equals(Properties.MARITAL_STATUS_CODE))
                {
                    int code = findMaritalStatusCode(Properties.MARITAL_STATUS);
                    nodeList.item(0).getFirstChild().setNodeValue(code + "");
                }
                String value = nodeList.item(0).getFirstChild().getNodeValue();
                System.out.println(propString + " : " + nodeList.item(0).getFirstChild().getNodeValue());

            }

        }
    }

    private int findMaritalStatusCode(String s) {
        return 0;
    }


}
