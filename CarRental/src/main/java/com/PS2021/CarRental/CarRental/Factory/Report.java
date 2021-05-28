package com.PS2021.CarRental.CarRental.Factory;

import com.PS2021.CarRental.CarRental.entity.Car;
import org.dom4j.DocumentException;

import java.io.IOException;
import java.net.URISyntaxException;

public interface Report {
    String generatePDF(Car c) throws IOException, DocumentException, URISyntaxException, com.itextpdf.text.DocumentException;
}

