package com.Component.ME.EAPReport.container;

import java.sql.Date;

public class EapGroup
{
    Integer sequenceNumber;
    Date loadDate;
    Integer groupId;
    String accountNumber;
    String employerName;
    Date premiumMonth;
    Date effectiveDate;
    String insurer;
    String product;
    String province;
    Integer lives;

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

    public EapGroup()
    {

    }

    public Integer getSequenceNumber()
    {
        return sequenceNumber;
    }

    public void setSequenceNumber(Integer sequenceNumber)
    {
        this.sequenceNumber = sequenceNumber;
    }

    public Date getLoadDate()
    {
        return loadDate;
    }

    public void setLoadDate(Date loadDate)
    {
        this.loadDate = loadDate;
    }

    public Integer getGroupId()
    {
        return groupId;
    }

    public void setGroupId(Integer groupId)
    {
        this.groupId = groupId;
    }

    public String getAccountNumber()
    {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber)
    {
        this.accountNumber = accountNumber;
    }

    public String getEmployerName()
    {
        return employerName;
    }

    public void setEmployerName(String employerName)
    {
        this.employerName = employerName;
    }

    public Date getPremiumMonth()
    {
        return premiumMonth;
    }

    public void setPremiumMonth(Date premiumMonth)
    {
        this.premiumMonth = premiumMonth;
    }

    public Date getEffectiveDate()
    {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate)
    {
        this.effectiveDate = effectiveDate;
    }

    public String getInsurer()
    {
        return insurer;
    }

    public void setInsurer(String insurer)
    {
        this.insurer = insurer;
    }

    public String getProduct()
    {
        return product;
    }

    public void setProduct(String product)
    {
        this.product = product;
    }

    public String getProvince()
    {
        return province;
    }

    public void setProvince(String province)
    {
        this.province = province;
    }

    public Integer getLives()
    {
        return lives;
    }

    public void setLives(Integer lives)
    {
        this.lives = lives;
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
