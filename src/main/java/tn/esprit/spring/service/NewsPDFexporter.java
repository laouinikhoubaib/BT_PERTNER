package tn.esprit.spring.service;

import java.awt.Color;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import tn.esprit.spring.entities.Events;
import tn.esprit.spring.entities.News;

public class NewsPDFexporter {
	
private List<News> ev;
    
    public NewsPDFexporter(List<News> ev) {
        this.ev = ev;
    }
 
    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);
         
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);
         
        cell.setPhrase(new Phrase("News ID", font));
         
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Title", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Description", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Rating", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Views", font));
        table.addCell(cell);  
     
    }
     
    private void writeTableData(PdfPTable table) {
        for (News user : ev ) {
        	
            table.addCell(String.valueOf(user.getNews_id()));
            table.addCell(user.getNewsTitle());
            table.addCell(user.getNewsDescription());
            table.addCell(String.valueOf(user.getRating()));
            table.addCell(String.valueOf(user.getViews()));

        }
    }
     
    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
         
        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);
         
        Paragraph p = new Paragraph("List of news", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
         
        document.add(p);
         
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.0f, 1.5f, 3.0f, 1.5f, 1.5f});
        table.setSpacingBefore(10);
         
        writeTableHeader(table);
        writeTableData(table);
         
        document.add(table);
         
        document.close();
         
    }
	
	

}
