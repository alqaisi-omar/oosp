package adapters;

import java.io.*;
import java.util.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.w3c.dom.*;

public class XmlAdapter implements FileAdapter {

    @Override
    public List<Map<String, String>> read(File file) throws Exception {
        List<Map<String, String>> result = new ArrayList<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(file);

        NodeList records = doc.getElementsByTagName("record");
        for (int i = 0; i < records.getLength(); i++) {
            Node record = records.item(i);
            if (record.getNodeType() == Node.ELEMENT_NODE) {
                Map<String, String> row = new HashMap<>();
                NodeList children = record.getChildNodes();
                for (int j = 0; j < children.getLength(); j++) {
                    Node child = children.item(j);
                    if (child.getNodeType() == Node.ELEMENT_NODE) {
                        row.put(child.getNodeName(), child.getTextContent());
                    }
                }
                result.add(row);
            }
        }
        return result;
    }

    @Override
    public void write(File file, List<Map<String, String>> data) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();

        Element root = doc.createElement("data");
        doc.appendChild(root);

        for (Map<String, String> row : data) {
            Element record = doc.createElement("record");
            for (Map.Entry<String, String> entry : row.entrySet()) {
                Element field = doc.createElement(entry.getKey());
                field.appendChild(doc.createTextNode(entry.getValue()));
                record.appendChild(field);
            }
            root.appendChild(record);
        }

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(file);
        transformer.transform(source, result);
    }
}
