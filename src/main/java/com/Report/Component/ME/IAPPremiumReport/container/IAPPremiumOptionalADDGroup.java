package com.Report.Component.ME.IAPPremiumReport.container;

import java.sql.Date;

public class IAPPremiumOptionalADDGroup
{
    private String policyNumber;
    private String divisionNumber;
    private String divisionName;
    private Float commissionRate;
    private Date coverageEffective;
    private String divisionStatus;
    private String pctProvinceEmployeeSplit;
    private Integer currentNumberLives;
    private Float currentSinglePlanVolume;
    private Float currentFamilyPlanVolume;
    private Float grossSinglePlanPremiumRate;
    private Float grossFamilyPlanPremiumRate;
    private Float totalGrossPremium;
    private Float adjust;
    private Float totalGrossPremiumAndAdjust;
    private Float adminFee;
    private Float commission;
    private Float totalCommission;
    private Float ontarioTax;
    private Float quebecTax;
    private Float netPremiumPaid;
    private String comments;

    public IAPPremiumOptionalADDGroup(String policyNumber,
                                      String divisionNumber,
                                      String divisionName,
                                      Float commissionRate,
                                      Date coverageEffective,
                                      String divisionStatus,
                                      String pctProvinceEmployeeSplit,
                                      Integer currentNumberLives,
                                      Float currentSinglePlanVolume,
                                      Float currentFamilyPlanVolume,
                                      Float grossSinglePlanPremiumRate,
                                      Float grossFamilyPlanPremiumRate,
                                      Float totalGrossPremium,
                                      Float adjust,
                                      Float adminFee,
                                      Float commission,
                                      Float totalCommission,
                                      Float ontarioTax,
                                      Float quebecTax,
                                      Float netPremiumPaid,
                                      String comments)
    {
        this.policyNumber = policyNumber;
        this.divisionNumber = divisionNumber;
        this.divisionName = divisionName;
        this.commissionRate = commissionRate;
        this.coverageEffective = coverageEffective;
        this.divisionStatus = divisionStatus;
        this.pctProvinceEmployeeSplit = pctProvinceEmployeeSplit;
        this.currentNumberLives = currentNumberLives;
        this.currentSinglePlanVolume = currentSinglePlanVolume;
        this.currentFamilyPlanVolume = currentFamilyPlanVolume;
        this.grossSinglePlanPremiumRate = grossSinglePlanPremiumRate;
        this.grossFamilyPlanPremiumRate = grossFamilyPlanPremiumRate;
        this.totalGrossPremium = totalGrossPremium;
        this.adjust = adjust;
        this.totalGrossPremiumAndAdjust = totalGrossPremium + adjust;
        this.adminFee = adminFee;
        this.commission = commission;
        this.totalCommission = totalCommission;
        this.ontarioTax = ontarioTax;
        this.quebecTax = quebecTax;
        this.netPremiumPaid = netPremiumPaid;
        this.comments = comments;
    }

    public String getPolicyNumber()
    {
        return policyNumber;
    }

    public String getDivisionNumber()
    {
        return divisionNumber;
    }

    public String getDivisionName()
    {
        return divisionName;
    }

    public Float getCommissionRate()
    {
        return commissionRate;
    }

    public Date getCoverageEffective()
    {
        return coverageEffective;
    }

    public String getDivisionStatus()
    {
        return divisionStatus;
    }

    public String getPctProvinceEmployeeSplit()
    {
        return pctProvinceEmployeeSplit;
    }

    public Integer getCurrentNumberLives()
    {
        return currentNumberLives;
    }

    public Float getCurrentSinglePlanVolume()
    {
        return currentSinglePlanVolume;
    }

    public Float getCurrentFamilyPlanVolume()
    {
        return currentFamilyPlanVolume;
    }

    public Float getGrossSinglePlanPremiumRate()
    {
        return grossSinglePlanPremiumRate;
    }

    public Float getGrossFamilyPlanPremiumRate()
    {
        return grossFamilyPlanPremiumRate;
    }

    public Float getTotalGrossPremium()
    {
        return totalGrossPremium;
    }

    public Float getAdjust()
    {
        return adjust;
    }

    public Float getTotalGrossPremiumAndAdjust()
    {
        return totalGrossPremiumAndAdjust;
    }

    public Float getAdminFee()
    {
        return adminFee;
    }

    public Float getCommission()
    {
        return commission;
    }

    public Float getTotalCommission()
    {
        return totalCommission;
    }

    public Float getOntarioTax()
    {
        return ontarioTax;
    }

    public Float getQuebecTax()
    {
        return quebecTax;
    }

    public Float getNetPremiumPaid()
    {
        return netPremiumPaid;
    }

    public String getComments()
    {
        return comments;
    }

    @Override
    public String toString()
    {
        return "IAPPremiumOptionalADDGroup{" +
                "policyNumber='" + policyNumber + '\'' +
                ", divisionNumber='" + divisionNumber + '\'' +
                ", divisionName='" + divisionName + '\'' +
                ", commissionRate=" + commissionRate +
                ", coverageEffective=" + coverageEffective +
                ", divisionStatus='" + divisionStatus + '\'' +
                ", pctProvinceEmployeeSplit='" + pctProvinceEmployeeSplit + '\'' +
                ", currentNumberLives=" + currentNumberLives +
                ", currentSinglePlanVolume=" + currentSinglePlanVolume +
                ", currentFamilyPlanVolume=" + currentFamilyPlanVolume +
                ", grossSinglePlanPremiumRate=" + grossSinglePlanPremiumRate +
                ", grossFamilyPlanPremiumRate=" + grossFamilyPlanPremiumRate +
                ", totalGrossPremium=" + totalGrossPremium +
                ", adjust=" + adjust +
                ", totalGrossPremiumAndAdjust=" + totalGrossPremiumAndAdjust +
                ", adminFee=" + adminFee +
                ", commission=" + commission +
                ", totalCommission=" + totalCommission +
                ", ontarioTax=" + ontarioTax +
                ", quebecTax=" + quebecTax +
                ", netPremiumPaid=" + netPremiumPaid +
                ", comments='" + comments + '\'' +
                '}';
    }
}
