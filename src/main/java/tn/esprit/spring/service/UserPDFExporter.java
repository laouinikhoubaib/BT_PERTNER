package tn.esprit.spring.service;

import java.awt.Color;
import java.io.IOException;
import java.sql.Date;
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

public class UserPDFExporter {
	
	private List<Events> ev;
    
    public UserPDFExporter(List<Events> ev) {
        this.ev = ev;
    }
 
    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);
         
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);
         
        cell.setPhrase(new Phrase("Event ID", font));
         
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Title", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Description", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("number", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("maximal number", font));
        table.addCell(cell);  
       
    }
     
    private void writeTableData(PdfPTable table) {
        for (Events user : ev ) {
        	
            table.addCell(String.valueOf(user.getEvent_id()));
            table.addCell(user.getEventTitle());
            table.addCell(user.getEventDescription());
            table.addCell(String.valueOf(user.getNb()));
            table.addCell(String.valueOf(user.getNbmax()));

        }
    }
     
    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
         
        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);
         
        Paragraph p = new Paragraph("List of events", font);
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


