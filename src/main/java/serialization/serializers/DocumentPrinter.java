package serialization.serializers;

import org.w3c.dom.Document;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;

public class DocumentPrinter {
    private Document document;
    private String filePath;

    public DocumentPrinter(Document document, String file) {
        this.document = document;
        this.filePath = file;
    }

    public void printToFile() throws Exception {
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.transform(new DOMSource(document), new StreamResult(
                new FileOutputStream(filePath)));
    }

    public Document getDocument() {
        return document;
    }

    public String getFilePath() {
        return filePath;
    }
}
