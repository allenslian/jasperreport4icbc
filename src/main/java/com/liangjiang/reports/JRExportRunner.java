package com.liangjiang.reports;

import com.liangjiang.reports.models.LJReceiptHeader;
import net.sf.jasperreports.engine.JRException;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

public class JRExportRunner {
    public static void main(String[] args) throws JRException, IOException {
        var receipt = generateLJReceiptHeader();
        var dataSource = new JRReceiptDataSource(receipt);
        var exporter = new JasperReportImageExporter();
        exporter.loadJasperReportWithDataSource(
                "LJReceiptTemplate.jrxml",
                dataSource.getParameters(),
                dataSource.getDataSource()
        );
        exporter.exportToImages(1, (i, image) -> ImageIO.write(image,
                "JPEG",
                new File(String.format("./ljreport%d.jpg", i+1))));
    }

    private static LJReceiptHeader generateLJReceiptHeader() {
        LJReceiptHeader receipt = new LJReceiptHeader();
        receipt.title = "辽宁沃锐达人力有限公司代发回执清单";
        receipt.number = String.format("%05d", (int)(Math.random() * 10000));
        receipt.date = new Date();
        receipt.agent = "辽宁沃锐达人力有限公司";
        receipt.customer = "工商银行建昌支行";
        receipt.digest = "代发其他";
        receipt.remarks = "打款企业信息";

        for (var i = 0; i < 100; i++) {
            receipt.getItems().add(receipt.new LJReceiptItem(
                    i + 1,
                    String.format("用户%d", i+1),
                    String.format("123456789%d", i),
                    BigDecimal.valueOf(100.0),
                    "代发其他",
                    "成功"
            ));
        }

        receipt.actualAmount = receipt.amount = receipt.getItems().stream()
                .map(LJReceiptHeader.LJReceiptItem::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        receipt.actualCount = receipt.count = receipt.getItems().size();
        receipt.failedAmount = BigDecimal.ZERO;
        receipt.failedCount = 0;
        return receipt;
    }
}
