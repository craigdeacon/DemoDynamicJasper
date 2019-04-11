package com.Report.Component.ME.IAPPremiumReport.container;

import java.sql.Date;

public class IAPPremiumADDGroup
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
    private Double benefitAmountPerEmployee;
    private Integer currentNumberLives;
    private Double currentVolumeOfInsurer;
    private Double grossMonthlyPremiumRate;
    private Double grossPremium;

    /*Spouse*/
    private Double benefitAmountSpouse;
    private Double currentNumberLivesSpouse;
    private Double currentVolumeOfInsurerSpouse;
    private Double grossMonthlyPremiumRateSpouse;
    private Double grossPremiumSpouse;

    /*Dependent*/
    private Double benefitAmountDependent;
    private Double currentNumberOfUnitsDependent;
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

    public IAPPremiumADDGroup(String policyNumber,
                              String divisionNumber,
                              String divisionName,
                              Double adminRate,
                              Double commRate,
                              Date coverageEffectiveDate,
                              Date renewalMonth,
                              String divisionStatus,
                              String pctProvinceEmployeeSplit,
                              Double benefitAmountPerEmployee,
                              Integer currentNumberLives,
                              Double currentVolumeOfInsurer,
                              Double grossMonthlyPremiumRate,
                              Double grossPremium,
                              Double benefitAmountSpouse,
                              Double currentNumberLivesSpouse,
                              Double currentVolumeOfInsurerSpouse,
                              Double grossMonthlyPremiumRateSpouse,
                              Double grossPremiumSpouse,
                              Double benefitAmountDependent,
                              Double currentNumberOfUnitsDependent,
                              Double grossMonthlyPremiumRateDependent,
                              Double grossPremiumDependent,
                              Double totalGrossPremium,
                              Double adjust,
                              Double adminFee,
                              Double commission,
                              Double totalCommission,
                              Double ontarioTax,
                              Double quebecTax,
                              Double manitobaTax,
                              Double netPremiumPaid,
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

    public Double getBenefitAmountPerEmployee()
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

    public Double getBenefitAmountSpouse()
    {
        return benefitAmountSpouse;
    }

    public Double getCurrentNumberLivesSpouse()
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

    public Double getGrossPremiumSpouse()
    {
        return grossPremiumSpouse;
    }

    public Double getBenefitAmountDependent()
    {
        return benefitAmountDependent;
    }

    public Double getCurrentNumberOfUnitsDependent()
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
