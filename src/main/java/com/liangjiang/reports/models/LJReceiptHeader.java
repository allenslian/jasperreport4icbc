package com.liangjiang.reports.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

public class LJReceiptHeader {
    public String title;
    public String number;
    public Date date;
    public String agent;
    public String customer;
    public String digest;
    public BigDecimal amount;
    public int count;
    public BigDecimal actualAmount;
    public int actualCount;
    public BigDecimal failedAmount;
    public int failedCount;
    public String remarks;

    private ArrayList<LJReceiptItem> items;

    public LJReceiptHeader() {
        items = new ArrayList<>(20);
    }

    public ArrayList<LJReceiptItem> getItems() {
        return items;
    }

    public class LJReceiptItem {
        private int sequence;
        private String name;
        private String accountNumber;
        private BigDecimal amount;
        private String digest;
        private String status;

        public LJReceiptItem(int sequence, String name, String accountNumber,
                             BigDecimal amount, String digest, String status) {
            this.sequence = sequence;
            this.name = name;
            this.accountNumber = accountNumber;
            this.amount = amount;
            this.digest = digest;
            this.status = status;
        }

        public void setSequence(int sequence) {
            this.sequence = sequence;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setAccountNumber(String accountNumber) {
            this.accountNumber = accountNumber;
        }

        public void setAmount(BigDecimal amount) {
            this.amount = amount;
        }

        public void setDigest(String digest) {
            this.digest = digest;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public int getSequence() {
            return sequence;
        }

        public String getName() {
            return name;
        }

        public String getAccountNumber() {
            return accountNumber;
        }

        public BigDecimal getAmount() {
            return amount;
        }

        public String getDigest() {
            return digest;
        }

        public String getStatus() {
            return status;
        }
    }
}
