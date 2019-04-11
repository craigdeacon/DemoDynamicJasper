package com.Report.Component.ME.CignaUpload.container;

public class CignaUploadGroup
{
    private Integer month;
    private Integer year;
    private String type;
    private String policyNumber;
    private Integer numEmployee;
    private Double sumInsured;
    private Double grossPremiums;
    private Double pst;
    private Double grossRate;
    private String companyName;
    private Double commissionsRetained;
    private Double tpaFee;
    private Double netRemittance;

    public CignaUploadGroup(Integer month, Integer year, String type, String policyNumber, Integer numEmployee, Double sumInsured, Double grossPremiums, Double pst, Double grossRate, String companyName, Double commissionsRetained, Double tpaFee, Double netRemittance)
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

    public Double getSumInsured()
    {
        return sumInsured;
    }

    public void setSumInsured(Double sumInsured)
    {
        this.sumInsured = sumInsured;
    }

    public Double getGrossPremiums()
    {
        return grossPremiums;
    }

    public void setGrossPremiums(Double grossPremiums)
    {
        this.grossPremiums = grossPremiums;
    }

    public Double getPst()
    {
        return pst;
    }

    public void setPst(Double pst)
    {
        this.pst = pst;
    }

    public Double getGrossRate()
    {
        return grossRate;
    }

    public void setGrossRate(Double grossRate)
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

    public Double getCommissionsRetained()
    {
        return commissionsRetained;
    }

    public void setCommissionsRetained(Double commissionsRetained)
    {
        this.commissionsRetained = commissionsRetained;
    }

    public Double getTpaFee()
    {
        return tpaFee;
    }

    public void setTpaFee(Double tpaFee)
    {
        this.tpaFee = tpaFee;
    }

    public Double getNetRemittance()
    {
        return netRemittance;
    }

    public void setNetRemittance(Double netRemittance)
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
