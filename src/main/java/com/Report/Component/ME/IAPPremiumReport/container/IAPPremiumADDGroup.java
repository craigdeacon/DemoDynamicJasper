package com.Report.Component.ME.IAPPremiumReport.container;

import java.sql.Date;

public class IAPPremiumADDGroup
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
    private Float benefitAmountPerEmployee;
    private Integer currentNumberLives;
    private Float currentVolumeOfInsurer;
    private Float grossMonthlyPremiumRate;
    private Float grossPremium;

    /*Spouse*/
    private Float benefitAmountSpouse;
    private Float currentNumberLivesSpouse;
    private Float currentVolumeOfInsurerSpouse;
    private Float grossMonthlyPremiumRateSpouse;
    private Float grossPremiumSpouse;

    /*Dependent*/
    private Float benefitAmountDependent;
    private Float currentNumberOfUnitsDependent;
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

    public IAPPremiumADDGroup(String policyNumber,
                              String divisionNumber,
                              String divisionName,
                              Float adminRate,
                              Float commRate,
                              Date coverageEffectiveDate,
                              Date renewalMonth,
                              String divisionStatus,
                              String pctProvinceEmployeeSplit,
                              Float benefitAmountPerEmployee,
                              Integer currentNumberLives,
                              Float currentVolumeOfInsurer,
                              Float grossMonthlyPremiumRate,
                              Float grossPremium,
                              Float benefitAmountSpouse,
                              Float currentNumberLivesSpouse,
                              Float currentVolumeOfInsurerSpouse,
                              Float grossMonthlyPremiumRateSpouse,
                              Float grossPremiumSpouse,
                              Float benefitAmountDependent,
                              Float currentNumberOfUnitsDependent,
                              Float grossMonthlyPremiumRateDependent,
                              Float grossPremiumDependent,
                              Float totalGrossPremium,
                              Float adjust,
                              Float adminFee,
                              Float commission,
                              Float totalCommission,
                              Float ontarioTax,
                              Float quebecTax,
                              Float manitobaTax,
                              Float netPremiumPaid,
                              String comments)
    {
        this.policyNumber = policyNumber;
        this.divisionNumber = divisionNumber;
        this.divisionName = divisionName;
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
        this.totalCommissionRate = adminRate + commRate;
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

    public Float getBenefitAmountPerEmployee()
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

    public Float getBenefitAmountSpouse()
    {
        return benefitAmountSpouse;
    }

    public Float getCurrentNumberLivesSpouse()
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

    public Float getGrossPremiumSpouse()
    {
        return grossPremiumSpouse;
    }

    public Float getBenefitAmountDependent()
    {
        return benefitAmountDependent;
    }

    public Float getCurrentNumberOfUnitsDependent()
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

    @Override
    public String toString()
    {
        return "IAPPremiumADDGroup{" +
                "policyNumber='" + policyNumber + '\'' +
                ", divisionNumber='" + divisionNumber + '\'' +
                ", divisionName='" + divisionName + '\'' +
                ", totalCommissionRate=" + totalCommissionRate +
                ", coverageEffectiveDate=" + coverageEffectiveDate +
                ", renewalMonth=" + renewalMonth +
                ", divisionStatus='" + divisionStatus + '\'' +
                ", pctProvinceEmployeeSplit='" + pctProvinceEmployeeSplit + '\'' +
                ", benefitAmountPerEmployee=" + benefitAmountPerEmployee +
                ", currentNumberLives=" + currentNumberLives +
                ", currentVolumeOfInsurer=" + currentVolumeOfInsurer +
                ", grossMonthlyPremiumRate=" + grossMonthlyPremiumRate +
                ", grossPremium=" + grossPremium +
                ", benefitAmountSpouse=" + benefitAmountSpouse +
                ", currentNumberLivesSpouse=" + currentNumberLivesSpouse +
                ", currentVolumeOfInsurerSpouse=" + currentVolumeOfInsurerSpouse +
                ", grossMonthlyPremiumRateSpouse=" + grossMonthlyPremiumRateSpouse +
                ", grossPremiumSpouse=" + grossPremiumSpouse +
                ", benefitAmountDependent=" + benefitAmountDependent +
                ", currentNumberOfUnitsDependent=" + currentNumberOfUnitsDependent +
                ", grossMonthlyPremiumRateDependent=" + grossMonthlyPremiumRateDependent +
                ", grossPremiumDependent=" + grossPremiumDependent +
                ", totalGrossPremium=" + totalGrossPremium +
                ", adjust=" + adjust +
                ", adminFee=" + adminFee +
                ", commission=" + commission +
                ", totalCommission=" + totalCommission +
                ", ontarioTax=" + ontarioTax +
                ", quebecTax=" + quebecTax +
                ", manitobaTax=" + manitobaTax +
                ", netPremiumPaid=" + netPremiumPaid +
                ", comments='" + comments + '\'' +
                ", grossPremiumAndAdjust=" + grossPremiumAndAdjust +
                '}';
    }
}
