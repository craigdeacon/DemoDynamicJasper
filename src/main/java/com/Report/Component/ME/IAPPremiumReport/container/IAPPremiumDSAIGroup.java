package com.Report.Component.ME.IAPPremiumReport.container;

public class IAPPremiumDSAIGroup
{
    private String divisionName;
    private String policyNumber;
    private Integer divisionNumber;
    private Integer singleLives;
    private Integer coupleLives;
    private Double currentPremium;
    private Double retroPremium;
    private Double pst;

    public IAPPremiumDSAIGroup(String divisionName, String policyNumber, Integer divisionNumber, Integer singleLives, Integer coupleLives, Double currentPremium, Double retroPremium, Double pst)
    {
        this.divisionName = divisionName;
        this.policyNumber = policyNumber;
        this.divisionNumber = divisionNumber;
        this.singleLives = singleLives;
        this.coupleLives = coupleLives;
        this.currentPremium = currentPremium;
        this.retroPremium = retroPremium;
        this.pst = pst;
    }

    public String getDivisionName()
    {
        return divisionName;
    }

    public String getPolicyNumber()
    {
        return policyNumber;
    }

    public Integer getDivisionNumber()
    {
        return divisionNumber;
    }

    public Integer getSingleLives()
    {
        return singleLives;
    }

    public Integer getCoupleLives()
    {
        return coupleLives;
    }

    public Double getCurrentPremium()
    {
        return currentPremium;
    }

    public Double getRetroPremium()
    {
        return retroPremium;
    }

    public Double getPst()
    {
        return pst;
    }

    @Override
    public String toString()
    {
        return "IAPPremiumDSAIGroup{" +
                "divisionName='" + divisionName + '\'' +
                ", policyNumber='" + policyNumber + '\'' +
                ", divisionNumber=" + divisionNumber +
                ", singleLives=" + singleLives +
                ", coupleLives=" + coupleLives +
                ", currentPremium=" + currentPremium +
                ", retroPremium=" + retroPremium +
                ", pst=" + pst +
                '}';
    }
}
