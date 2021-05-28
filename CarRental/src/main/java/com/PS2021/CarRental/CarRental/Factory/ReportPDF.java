package com.PS2021.CarRental.CarRental.Factory;

import com.PS2021.CarRental.CarRental.entity.Car;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;

public class ReportPDF implements Report{

    @Override
    public String generatePDF(Car c) throws IOException, DocumentException, URISyntaxException {
        Document document = new Document(PageSize.A4.rotate());
        PdfWriter.getInstance(document, new FileOutputStream("Car_nr." + c.getId() + ".pdf"));

        document.open();
        Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
        document.setPageSize(PageSize.A4.rotate());

        document.add( new Paragraph() );

        Chunk chunk = new Chunk("Car Number: " + c.getId(), font);
        Chunk chunk1 = new Chunk("Car Name: " + c.getName(), font);
        Chunk chunk2 = new Chunk("Car Year: " + c.getYearOfFabrication(), font);
        Chunk chunk3 = new Chunk("Car Price: " + c.getPrice(), font);

        document.add(chunk);
        document.add( new Paragraph() );
        document.add(chunk1);
        document.add( new Paragraph() );
        document.add(chunk2);
        document.add( new Paragraph() );
        document.add(chunk3);
        document.add( new Paragraph() );

        document.close();

        return "Car_nr." + c.getId() + ".pdf";
    }

}
