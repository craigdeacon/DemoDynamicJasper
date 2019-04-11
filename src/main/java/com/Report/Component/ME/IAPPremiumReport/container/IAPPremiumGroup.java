package com.Report.Component.ME.IAPPremiumReport.container;

import java.sql.Date;

public class IAPPremiumGroup
{
    private String policyNumber;
    private String divisionNumber;
    private String divisionName;
    private Double totalCommissionRate;
    private Date coverageEffectiveDate;
    private Date renewalMonth;
    private String divisionStatus;
    private String pctProvinceEmployeeSplit;

    /*Employee*/
    private String benefitAmountPerEmployee;
    private Integer currentNumberLives;
    private Double currentVolumeOfInsurer;
    private Double grossMonthlyPremiumRate;
    private Double grossPremium;

    /*Spouse*/
    private String benefitAmountSpouse;
    private Integer currentNumberLivesSpouse;
    private Double currentVolumeOfInsurerSpouse;
    private Double grossMonthlyPremiumRateSpouse;
    private String grossPremiumSpouse;

    /*Dependent*/
    private String benefitAmountDependent;
    private Integer currentNumberOfUnitsDependent;
    private Double grossMonthlyPremiumRateDependent;
    private Double grossPremiumDependent;

    /*Total*/
    private Double totalGrossPremium;
    private Double adjust;
    private Double adminFee;
    private Double commission;
    private Double totalCommission;

    /*Tax*/
    private Double ontarioTax;
    private Double quebecTax;
    private Double manitobaTax;

    private Double netPremiumPaid;
    private String comments;

    /*Functions*/
    private Double grossPremiumAndAdjust;

    public IAPPremiumGroup(String policyNumber, String divisionNumber, String divisionName, Double totalCommissionRate, Date coverageEffectiveDate, Date renewalMonth, String divisionStatus, String pctProvinceEmployeeSplit, String benefitAmountPerEmployee, Integer currentNumberLives, Double currentVolumeOfInsurer, Double grossMonthlyPremiumRate, Double grossPremium, String benefitAmountSpouse, Integer currentNumberLivesSpouse, Double currentVolumeOfInsurerSpouse, Double grossMonthlyPremiumRateSpouse, String grossPremiumSpouse, String benefitAmountDependent, Integer currentNumberOfUnitsDependent, Double grossMonthlyPremiumRateDependent, Double grossPremiumDependent, Double totalGrossPremium, Double adjust, Double adminFee, Double commission, Double totalCommission, Double ontarioTax, Double quebecTax, Double manitobaTax, Double netPremiumPaid, String comments)
    {
        this.policyNumber = policyNumber;
        this.divisionNumber = divisionNumber;
        this.divisionName = divisionName;
        this.totalCommissionRate = totalCommissionRate;
        this.coverageEffectiveDate = coverageEffectiveDate;
        this.renewalMonth = renewalMonth;
        this.divisionStatus = divisionStatus;
        this.pctProvinceEmployeeSplit = pctProvinceEmployeeSplit;
        this.benefitAmountPerEmployee = benefitAmountPerEmployee;
        this.currentNumberLives = currentNumberLives;
        this.currentVolumeOfInsurer = currentVolumeOfInsurer;
        this.grossMonthlyPremiumRate = grossMonthlyPremiumRate;
        this.grossPremium = grossPremium;
        this.benefitAmountSpouse = benefitAmountSpouse;
        this.currentNumberLivesSpouse = currentNumberLivesSpouse;
        this.currentVolumeOfInsurerSpouse = currentVolumeOfInsurerSpouse;
        this.grossMonthlyPremiumRateSpouse = grossMonthlyPremiumRateSpouse;
        this.grossPremiumSpouse = grossPremiumSpouse;
        this.benefitAmountDependent = benefitAmountDependent;
        this.currentNumberOfUnitsDependent = currentNumberOfUnitsDependent;
        this.grossMonthlyPremiumRateDependent = grossMonthlyPremiumRateDependent;
        this.grossPremiumDependent = grossPremiumDependent;
        this.totalGrossPremium = totalGrossPremium;
        this.adjust = adjust;
        this.adminFee = adminFee;
        this.commission = commission;
        this.totalCommission = totalCommission;
        this.ontarioTax = ontarioTax;
        this.quebecTax = quebecTax;
        this.manitobaTax = manitobaTax;
        this.netPremiumPaid = netPremiumPaid;
        this.comments = comments;
        this.grossPremiumAndAdjust = totalGrossPremium + adjust;
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

    public Double getTotalCommissionRate()
    {
        return totalCommissionRate;
    }

    public Date getCoverageEffectiveDate()
    {
        return coverageEffectiveDate;
    }

    public Date getRenewalMonth()
    {
        return renewalMonth;
    }

    public String getDivisionStatus()
    {
        return divisionStatus;
    }

    public String getPctProvinceEmployeeSplit()
    {
        return pctProvinceEmployeeSplit;
    }

    public String getBenefitAmountPerEmployee()
    {
        return benefitAmountPerEmployee;
    }

    public Integer getCurrentNumberLives()
    {
        return currentNumberLives;
    }

    public Double getCurrentVolumeOfInsurer()
    {
        return currentVolumeOfInsurer;
    }

    public Double getGrossMonthlyPremiumRate()
    {
        return grossMonthlyPremiumRate;
    }

    public Double getGrossPremium()
    {
        return grossPremium;
    }

    public String getBenefitAmountSpouse()
    {
        return benefitAmountSpouse;
    }

    public Integer getCurrentNumberLivesSpouse()
    {
        return currentNumberLivesSpouse;
    }

    public Double getCurrentVolumeOfInsurerSpouse()
    {
        return currentVolumeOfInsurerSpouse;
    }

    public Double getGrossMonthlyPremiumRateSpouse()
    {
        return grossMonthlyPremiumRateSpouse;
    }

    public String getGrossPremiumSpouse()
    {
        return grossPremiumSpouse;
    }

    public String getBenefitAmountDependent()
    {
        return benefitAmountDependent;
    }

    public Integer getCurrentNumberOfUnitsDependent()
    {
        return currentNumberOfUnitsDependent;
    }

    public Double getGrossMonthlyPremiumRateDependent()
    {
        return grossMonthlyPremiumRateDependent;
    }

    public Double getGrossPremiumDependent()
    {
        return grossPremiumDependent;
    }

    public Double getTotalGrossPremium()
    {
        return totalGrossPremium;
    }

    public Double getAdjust()
    {
        return adjust;
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

    public Double getManitobaTax()
    {
        return manitobaTax;
    }

    public Double getNetPremiumPaid()
    {
        return netPremiumPaid;
    }

    public String getComments()
    {
        return comments;
    }

    public Double getGrossPremiumAndAdjust()
    {
        return grossPremiumAndAdjust;
    }
}
