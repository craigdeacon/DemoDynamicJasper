package com.Report.Component.ME.IAPPremiumReport.container;

import java.sql.Date;

public class IAPPremiumGroup
{
    private String policyNumber;
    private String divisionNumber;
    private String divisionName;
    private Float totalCommissionRate;
    private Date coverageEffectiveDate;
    private Date renewalMonth;
    private String divisionStatus;
    private String pctProvinceEmployeeSplit;

    /*Employee*/
    private String benefitAmountPerEmployee;
    private Integer currentNumberLives;
    private Float currentVolumeOfInsurer;
    private Float grossMonthlyPremiumRate;
    private Float grossPremium;

    /*Spouse*/
    private String benefitAmountSpouse;
    private Integer currentNumberLivesSpouse;
    private Float currentVolumeOfInsurerSpouse;
    private Float grossMonthlyPremiumRateSpouse;
    private String grossPremiumSpouse;

    /*Dependent*/
    private String benefitAmountDependent;
    private Integer currentNumberOfUnitsDependent;
    private Float grossMonthlyPremiumRateDependent;
    private Float grossPremiumDependent;

    /*Total*/
    private Float totalGrossPremium;
    private Float adjust;
    private Float adminFee;
    private Float commission;
    private Float totalCommission;

    /*Tax*/
    private Float ontarioTax;
    private Float quebecTax;
    private Float manitobaTax;

    private Float netPremiumPaid;
    private String comments;

    /*Functions*/
    private Float grossPremiumAndAdjust;

    public IAPPremiumGroup(String policyNumber, String divisionNumber, String divisionName, Float totalCommissionRate, Date coverageEffectiveDate, Date renewalMonth, String divisionStatus, String pctProvinceEmployeeSplit, String benefitAmountPerEmployee, Integer currentNumberLives, Float currentVolumeOfInsurer, Float grossMonthlyPremiumRate, Float grossPremium, String benefitAmountSpouse, Integer currentNumberLivesSpouse, Float currentVolumeOfInsurerSpouse, Float grossMonthlyPremiumRateSpouse, String grossPremiumSpouse, String benefitAmountDependent, Integer currentNumberOfUnitsDependent, Float grossMonthlyPremiumRateDependent, Float grossPremiumDependent, Float totalGrossPremium, Float adjust, Float adminFee, Float commission, Float totalCommission, Float ontarioTax, Float quebecTax, Float manitobaTax, Float netPremiumPaid, String comments)
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

    public Float getTotalCommissionRate()
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

    public Float getCurrentVolumeOfInsurer()
    {
        return currentVolumeOfInsurer;
    }

    public Float getGrossMonthlyPremiumRate()
    {
        return grossMonthlyPremiumRate;
    }

    public Float getGrossPremium()
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

    public Float getCurrentVolumeOfInsurerSpouse()
    {
        return currentVolumeOfInsurerSpouse;
    }

    public Float getGrossMonthlyPremiumRateSpouse()
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

    public Float getGrossMonthlyPremiumRateDependent()
    {
        return grossMonthlyPremiumRateDependent;
    }

    public Float getGrossPremiumDependent()
    {
        return grossPremiumDependent;
    }

    public Float getTotalGrossPremium()
    {
        return totalGrossPremium;
    }

    public Float getAdjust()
    {
        return adjust;
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

    public Float getManitobaTax()
    {
        return manitobaTax;
    }

    public Float getNetPremiumPaid()
    {
        return netPremiumPaid;
    }

    public String getComments()
    {
        return comments;
    }

    public Float getGrossPremiumAndAdjust()
    {
        return grossPremiumAndAdjust;
    }
}
