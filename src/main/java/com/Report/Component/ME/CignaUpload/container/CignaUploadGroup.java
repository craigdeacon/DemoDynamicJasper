package com.Report.Component.ME.CignaUpload.container;

public class CignaUploadGroup
{
    private Integer month;
    private Integer year;
    private String type;
    private String policyNumber;
    private Integer numEmployee;
    private Float sumInsured;
    private Float grossPremiums;
    private Float pst;
    private Float grossRate;
    private String companyName;
    private Float commissionsRetained;
    private Float tpaFee;
    private Float netRemittance;

    public CignaUploadGroup(Integer month, Integer year, String type, String policyNumber, Integer numEmployee, Float sumInsured, Float grossPremiums, Float pst, Float grossRate, String companyName, Float commissionsRetained, Float tpaFee, Float netRemittance)
    {
        this.month = month;
        this.year = year;
        this.type = type;
        this.policyNumber = policyNumber;
        this.numEmployee = numEmployee;
        this.sumInsured = sumInsured;
        this.grossPremiums = grossPremiums;
        this.pst = pst;
        this.grossRate = grossRate;
        this.companyName = companyName;
        this.commissionsRetained = commissionsRetained;
        this.tpaFee = tpaFee;
        this.netRemittance = netRemittance;
    }

    public CignaUploadGroup()
    {
    }

    public Integer getMonth()
    {
        return month;
    }

    public void setMonth(Integer month)
    {
        this.month = month;
    }

    public Integer getYear()
    {
        return year;
    }

    public void setYear(Integer year)
    {
        this.year = year;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getPolicyNumber()
    {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber)
    {
        this.policyNumber = policyNumber;
    }

    public Integer getNumEmployee()
    {
        return numEmployee;
    }

    public void setNumEmployee(Integer numEmployee)
    {
        this.numEmployee = numEmployee;
    }

    public Float getSumInsured()
    {
        return sumInsured;
    }

    public void setSumInsured(Float sumInsured)
    {
        this.sumInsured = sumInsured;
    }

    public Float getGrossPremiums()
    {
        return grossPremiums;
    }

    public void setGrossPremiums(Float grossPremiums)
    {
        this.grossPremiums = grossPremiums;
    }

    public Float getPst()
    {
        return pst;
    }

    public void setPst(Float pst)
    {
        this.pst = pst;
    }

    public Float getGrossRate()
    {
        return grossRate;
    }

    public void setGrossRate(Float grossRate)
    {
        this.grossRate = grossRate;
    }

    public String getCompanyName()
    {
        return companyName;
    }

    public void setCompanyName(String companyName)
    {
        this.companyName = companyName;
    }

    public Float getCommissionsRetained()
    {
        return commissionsRetained;
    }

    public void setCommissionsRetained(Float commissionsRetained)
    {
        this.commissionsRetained = commissionsRetained;
    }

    public Float getTpaFee()
    {
        return tpaFee;
    }

    public void setTpaFee(Float tpaFee)
    {
        this.tpaFee = tpaFee;
    }

    public Float getNetRemittance()
    {
        return netRemittance;
    }

    public void setNetRemittance(Float netRemittance)
    {
        this.netRemittance = netRemittance;
    }

    @Override
    public String toString()
    {
        return "CignaUploadGroup{" +
                "month=" + month +
                ", year=" + year +
                ", type='" + type + '\'' +
                ", policyNumber='" + policyNumber + '\'' +
                ", numEmployee=" + numEmployee +
                ", sumInsured=" + sumInsured +
                ", grossPremiums=" + grossPremiums +
                ", pst=" + pst +
                ", grossRate=" + grossRate +
                ", companyName='" + companyName + '\'' +
                ", commissionsRetained=" + commissionsRetained +
                ", tpaFee=" + tpaFee +
                ", netRemittance=" + netRemittance +
                '}';
    }
}
