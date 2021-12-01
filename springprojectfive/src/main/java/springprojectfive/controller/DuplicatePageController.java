package springprojectfive.controller;



import org.apache.pdfbox.multipdf.Splitter;
import org.apache.pdfbox.pdmodel.PDDocument;
  
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Iterator;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DuplicatePageController {
	
	
	@RequestMapping(value = "/splitPdf")
	public String splitPdf() {
	
	  try
	  {
	// Loading PDF
			File pdffile
				= new File("E:\\PdfEditor\\401.pdf");
			PDDocument document = PDDocument.load(pdffile);

			// Splitter Class
			Splitter splitting = new Splitter();

			// Splitting the pages into multiple PDFs
			List<PDDocument> Page = splitting.split(document);

			// Using a iterator to Traverse all pages
			Iterator<PDDocument> iteration
				= Page.listIterator();

			// Saving each page as an individual document
			int j = 1;
			while (iteration.hasNext()) {
				PDDocument pd = iteration.next();
				pd.save("E:\\PdfEditor\\Sample\\sample-"
						+ j++ + ".pdf");
			}
			System.out.println("Splitted Pdf Successfully.");
			document.close();
	  }
	  catch (Exception e) {
			e.printStackTrace();
		}
	  
			return "splitpdf";
	
}
}
