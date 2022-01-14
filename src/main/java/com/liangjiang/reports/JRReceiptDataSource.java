package com.liangjiang.reports;

import com.liangjiang.reports.models.LJReceiptHeader;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.util.HashMap;
import java.util.Map;

public class JRReceiptDataSource {
    private LJReceiptHeader receipt;

    public JRReceiptDataSource(LJReceiptHeader receipt) {
        if (receipt == null) {
            throw new IllegalArgumentException("receipt is required!!!");
        }
        this.receipt = receipt;
    }

    public Map<String, Object> getParameters() {
        return new HashMap<>() {{
            put("p_title", receipt.title);
            put("p_no", receipt.number);
            put("p_date", receipt.date);
            put("p_customer", receipt.customer);
            put("p_agent", receipt.agent);
            put("p_count", receipt.count);
            put("p_amount", receipt.amount);
            put("p_digest", receipt.digest);
            put("p_remarks", receipt.remarks);
            put("p_actual_amount", receipt.actualAmount);
            put("p_actual_count", receipt.actualCount);
            put("p_failed_count", receipt.failedCount);
            put("p_failed_amount", receipt.failedAmount);
        }};
    }

    public JRBeanCollectionDataSource getDataSource() {
        return new JRBeanCollectionDataSource(
                this.receipt.getItems(),
                true);
    }
}




