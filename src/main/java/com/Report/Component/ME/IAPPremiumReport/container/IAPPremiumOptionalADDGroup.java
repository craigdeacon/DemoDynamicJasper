package com.Report.Component.ME.IAPPremiumReport.container;

import java.sql.Date;

public class IAPPremiumOptionalADDGroup
{
    private String policyNumber;
    private String divisionNumber;
    private String divisionName;
    private Double fullCommissionRate;
    private Date coverageEffective;
    private String divisionStatus;
    private String pctProvinceEmployeeSplit;
    private Integer currentNumberLives;
    private Double currentSinglePlanVolume;
    private Double currentFamilyPlanVolume;
    private Double grossSinglePlanPremiumRate;
    private Double grossFamilyPlanPremiumRate;
    private Double totalGrossPremium;
    private Double adjust;
    private Double totalGrossPremiumAndAdjust;
    private Double adminFee;
    private Double commission;
    private Double totalCommission;
    private Double ontarioTax;
    private Double quebecTax;
    private Double netPremiumPaid;
    private String comments;

    public IAPPremiumOptionalADDGroup(String policyNumber,
                                      String divisionNumber,
                                      String divisionName,
                                      Double adminRate,
                                      Double commissionRate,
                                      Double totalCommission,
                                      Date coverageEffective,
                                      String divisionStatus,
                                      String pctProvinceEmployeeSplit,
                                      Integer currentNumberLives,
                                      Double currentSinglePlanVolume,
                                      Double currentFamilyPlanVolume,
                                      Double grossSinglePlanPremiumRate,
                                      Double grossFamilyPlanPremiumRate,
                                      Double totalGrossPremium,
                                      Double adjust,
                                      Double adminFee,
                                      Double commission,
                                      Double ontarioTax,
                                      Double quebecTax,
                                      Double netPremiumPaid,
                                      String comments)
    {
        this.policyNumber = policyNumber;
        this.divisionNumber = divisionNumber;
        this.divisionName = divisionName;
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
        this.fullCommissionRate = commissionRate + adminRate;
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

    public Double getCurrentSinglePlanVolume()
    {
        return currentSinglePlanVolume;
    }

    public Double getCurrentFamilyPlanVolume()
    {
        return currentFamilyPlanVolume;
    }

    public Double getGrossSinglePlanPremiumRate()
    {
        return grossSinglePlanPremiumRate;
    }

    public Double getGrossFamilyPlanPremiumRate()
    {
        return grossFamilyPlanPremiumRate;
    }

    public Double getTotalGrossPremium()
    {
        return totalGrossPremium;
    }

    public Double getAdjust()
    {
        return adjust;
    }

    public Double getTotalGrossPremiumAndAdjust()
    {
        return totalGrossPremiumAndAdjust;
    }

    public Double getAdminFee()
    {
        return adminFee;
    }

    public Double getCommission()
    {
        return commission;
    }

    public Double getTotalCommission()
    {
        return totalCommission;
    }

    public Double getOntarioTax()
    {
        return ontarioTax;
    }

    public Double getQuebecTax()
    {
        return quebecTax;
    }

    public Double getNetPremiumPaid()
    {
        return netPremiumPaid;
    }

    public String getComments()
    {
        return comments;
    }

    public Double getFullCommissionRate()
    {
        return fullCommissionRate;
    }

    @Override
    public String toString()
    {
        return "IAPPremiumOptionalADDGroup{" +
                "policyNumber='" + policyNumber + '\'' +
                ", divisionNumber='" + divisionNumber + '\'' +
                ", divisionName='" + divisionName + '\'' +
                ", fullCommissionRate=" + fullCommissionRate +
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
