package powergen.utils;

import java.io.File;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.apache.pdfbox.util.PDFTextStripperByArea;


public class PDFParser {
	public String pdfRead(String path) throws Exception {

		String st = "";

		try {
			PDDocument document = null;
			document = PDDocument.load(new File(path));
			document.getClass();
			if (!document.isEncrypted()) {
				PDFTextStripperByArea stripper = new PDFTextStripperByArea();
				stripper.setSortByPosition(true);
				PDFTextStripper Tstripper = new PDFTextStripper();
				st = Tstripper.getText(document);
				System.out.println("Text:" + st);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return st;
	}

}