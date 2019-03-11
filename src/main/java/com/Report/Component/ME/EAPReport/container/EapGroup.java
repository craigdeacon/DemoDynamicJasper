package com.Report.Component.ME.EAPReport.container;

import java.sql.Date;

public class EapGroup
{
    private Integer sequenceNumber;
    private Date loadDate;
    private Integer groupId;
    private String accountNumber;
    private String employerName;
    private Date premiumMonth;
    private Date effectiveDate;
    private String insurer;
    private String product;
    private String province;
    private Integer lives;

    public EapGroup(Integer sequenceNumber, Date loadDate, Integer groupId, String accountNumber, String employerName, Date premiumMonth, Date effectiveDate, String insurer, String product, String province, Integer lives)
    {
        this.sequenceNumber = sequenceNumber;
        this.loadDate = loadDate;
        this.groupId = groupId;
        this.accountNumber = accountNumber;
        this.employerName = employerName;
        this.premiumMonth = premiumMonth;
        this.effectiveDate = effectiveDate;
        this.insurer = insurer;
        this.product = product;
        this.province = province;
        this.lives = lives;
    }

    public Integer getSequenceNumber()
    {
        return sequenceNumber;
    }

    public Date getLoadDate()
    {
        return loadDate;
    }

    public Integer getGroupId()
    {
        return groupId;
    }

    public String getAccountNumber()
    {
        return accountNumber;
    }

    public String getEmployerName()
    {
        return employerName;
    }

    public Date getPremiumMonth()
    {
        return premiumMonth;
    }

    public Date getEffectiveDate()
    {
        return effectiveDate;
    }

    public String getInsurer()
    {
        return insurer;
    }

    public String getProduct()
    {
        return product;
    }

    public String getProvince()
    {
        return province;
    }

    public Integer getLives()
    {
        return lives;
    }

    @Override
    public String toString()
    {
        return "EapGroup{" +
                "sequenceNumber=" + sequenceNumber +
                ", loadDate=" + loadDate +
                ", groupId=" + groupId +
                ", accountNumber='" + accountNumber + '\'' +
                ", employerName='" + employerName + '\'' +
                ", premiumMonth=" + premiumMonth +
                ", effectiveDate=" + effectiveDate +
                ", insurer='" + insurer + '\'' +
                ", product='" + product + '\'' +
                ", province='" + province + '\'' +
                ", lives=" + lives +
                '}';
    }
}
