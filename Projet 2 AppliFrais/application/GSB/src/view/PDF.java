package view;

import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.Image.*;

public class PDF {

  public static void main(String[] args) {

    Document document = new Document(PageSize.A4);
    
    try {
    	
      PdfWriter.getInstance(document,new FileOutputStream("P:/SLAM4/PDF/Facture.pdf"));
      
      document.open();
      
      Image image = Image.getInstance("images/logo.png");
      
      image.scaleAbsolute(200, 150);
      //image.setAbsolutePosition(200, 650);

      Chunk chunk = new Chunk("\n\n\n\n\n\n\n\n\n\nETAT DE FICHE DE FRAIS", FontFactory.getFont(FontFactory.COURIER, 20, Font.BOLD));

      //chunk.setBackground(Color.BLUE);
	  
      
      document.add(image);
	  document.add(chunk);

      //document.add(new Paragraph("Hello World"));
      
    } catch (DocumentException de) {
    	
      de.printStackTrace();
      
    } catch (IOException ioe) {
    	
      ioe.printStackTrace();
      
    }

    document.close();
  }
}