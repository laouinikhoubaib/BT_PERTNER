package tn.esprit.spring.service;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

import tn.esprit.spring.entities.Reservation;
import tn.esprit.spring.entities.Trip;
import tn.esprit.spring.repository.ReservationRepository;


@Service
public class UserPDFExporter {

	private List<Trip> listTrips;
    
    public UserPDFExporter(List<Trip> listTrips) {
        this.listTrips=listTrips;
    }
 
    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);
         
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);
         
        cell.setPhrase(new Phrase("Trip ID", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("trip_depart", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("trip_arrival", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Trip Object", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Trip patners", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("trip_destination", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("trip_duration", font));
        table.addCell(cell);    
        cell.setPhrase(new Phrase("trip_price", font));
        table.addCell(cell);  
        cell.setPhrase(new Phrase("Trip favoris", font));
        table.addCell(cell);
    }
     
    private void writeTableData(PdfPTable table) {
        for (Trip trip : listTrips) {
            table.addCell(String.valueOf(trip.getTrip_id()));
            table.addCell(String.valueOf(trip.getTripDeparature()));
            table.addCell(String.valueOf(trip.getTripArrival()));
            table.addCell(String.valueOf(trip.getTripObject()));
            table.addCell(String.valueOf(trip.getNbrPartenaire()));
            table.addCell(String.valueOf(trip.getTripDestination()));
            table.addCell(String.valueOf(trip.getTripDuration()));
            table.addCell(String.valueOf(trip.getTripPrice()));
            table.addCell(String.valueOf(trip.isTripFavoris()));
       
        }
    }
     
    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
         
        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);
         
        Paragraph p = new Paragraph("List of Trips", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
         
        document.add(p);
         
        PdfPTable table = new PdfPTable(9);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {3.5f,5.5f,5.5f,5.5f,4.0f, 4.0f,4.0f,4.5f,5.0f});
        table.setSpacingBefore(10);
         
        writeTableHeader(table);
        writeTableData(table);
         
        document.add(table);
         
        document.close();
         
    }
}
