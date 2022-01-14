package com.liangjiang.reports;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRGraphics2DExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleGraphics2DExporterOutput;
import net.sf.jasperreports.export.SimpleGraphics2DReportConfiguration;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.util.Map;

public class JasperReportExporter {
    private JasperPrint jasperPrint;

    public void loadJasperReportWithDataSource(
            String reportFilePath,
            Map<String, Object> parameters,
            JRDataSource dataSource) throws JRException {
        InputStream reportStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream(reportFilePath);

        JasperReport report = JasperCompileManager.compileReport(
                JRXmlLoader.load(reportStream));

        this.jasperPrint = JasperFillManager.fillReport(
                report, parameters, dataSource);
    }

    public BufferedImage exportToImage(int pageIndex, float zoom) throws JRException {
        if (jasperPrint == null) {
            throw new NullPointerException("Please load jasper report at first!!!");
        }
        BufferedImage bufferedImage = new BufferedImage(
                (int)(jasperPrint.getPageWidth() * zoom),
                (int)(jasperPrint.getPageHeight() * zoom),
                BufferedImage.TYPE_INT_RGB);

        JRGraphics2DExporter exporter = new JRGraphics2DExporter();
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));

        Graphics2D g2d = null;
        try
        {
            g2d = bufferedImage.createGraphics();
            SimpleGraphics2DExporterOutput output = new SimpleGraphics2DExporterOutput();
            output.setGraphics2D(g2d);
            exporter.setExporterOutput(output);

            SimpleGraphics2DReportConfiguration configuration = new SimpleGraphics2DReportConfiguration();
            configuration.setPageIndex(pageIndex);
            configuration.setZoomRatio(zoom);
            exporter.setConfiguration(configuration);

            exporter.exportReport();
        }
        finally {
            if (g2d != null) {
                g2d.dispose();
            }
        }
        return bufferedImage;
    }



}
