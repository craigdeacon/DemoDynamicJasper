package com.Component.ME.IAPPremiumReport.container;

import java.sql.Date;

public class IAPPremiumADDGroup
{
    String policyNumber;
    String divisionNumber;
    String divisionName;
    Float totalCommissionRate;
    Date coverageEffectiveDate;
    Date renewalMonth;
    String divisionStatus;
    String pctProvinceEmployeeSplit;

    /*Employee*/
    Float benefitAmountPerEmployee;
    Integer currentNumberLives;
    Float currentVolumeOfInsurer;
    Float grossMonthlyPremiumRate;
    Float grossPremium;

    /*Spouse*/
    Float benefitAmountSpouse;
    Float currentNumberLivesSpouse;
    Float currentVolumeOfInsurerSpouse;
    Float grossMonthlyPremiumRateSpouse;
    Float grossPremiumSpouse;

    /*Dependent*/
    Float benefitAmountDependent;
    Float currentNumberOfUnitsDependent;
    Float grossMonthlyPremiumRateDependent;
    Float grossPremiumDependent;

    /*Total*/
    Float totalGrossPremium;
    Float adjust;
    Float adminFee;
    Float commission;
    Float totalCommission;

    /*Tax*/
    Float ontarioTax;
    Float quebecTax;
    Float manitobaTax;

    Float netPremiumPaid;
    String comments;

    Float adminRate;
    Float commRate;

    /*Functions*/
    Float grossPremiumAndAdjust;

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
        this.adminRate = adminRate;
        this.commRate = commRate;
        this.totalCommissionRate = adminRate + commRate;
    }

    public IAPPremiumADDGroup()
    {

    }

    public String getPolicyNumber()
    {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber)
    {
        this.policyNumber = policyNumber;
    }

    public String getDivisionNumber()
    {
        return divisionNumber;
    }

    public void setDivisionNumber(String divisionNumber)
    {
        this.divisionNumber = divisionNumber;
    }

    public String getDivisionName()
    {
        return divisionName;
    }

    public void setDivisionName(String divisionName)
    {
        this.divisionName = divisionName;
    }

    public Float getTotalCommissionRate()
    {
        return totalCommissionRate;
    }

    public void setTotalCommissionRate(Float totalCommissionRate)
    {
        this.totalCommissionRate = totalCommissionRate;
    }

    public Date getCoverageEffectiveDate()
    {
        return coverageEffectiveDate;
    }

    public void setCoverageEffectiveDate(Date coverageEffectiveDate)
    {
        this.coverageEffectiveDate = coverageEffectiveDate;
    }

    public Date getRenewalMonth()
    {
        return renewalMonth;
    }

    public void setRenewalMonth(Date renewalMonth)
    {
        this.renewalMonth = renewalMonth;
    }

    public String getDivisionStatus()
    {
        return divisionStatus;
    }

    public void setDivisionStatus(String divisionStatus)
    {
        this.divisionStatus = divisionStatus;
    }

    public String getPctProvinceEmployeeSplit()
    {
        return pctProvinceEmployeeSplit;
    }

    public void setPctProvinceEmployeeSplit(String pctProvinceEmployeeSplit)
    {
        this.pctProvinceEmployeeSplit = pctProvinceEmployeeSplit;
    }

    public Float getBenefitAmountPerEmployee()
    {
        return benefitAmountPerEmployee;
    }

    public void setBenefitAmountPerEmployee(Float benefitAmountPerEmployee)
    {
        this.benefitAmountPerEmployee = benefitAmountPerEmployee;
    }

    public Integer getCurrentNumberLives()
    {
        return currentNumberLives;
    }

    public void setCurrentNumberLives(Integer currentNumberLives)
    {
        this.currentNumberLives = currentNumberLives;
    }

    public Float getCurrentVolumeOfInsurer()
    {
        return currentVolumeOfInsurer;
    }

    public void setCurrentVolumeOfInsurer(Float currentVolumeOfInsurer)
    {
        this.currentVolumeOfInsurer = currentVolumeOfInsurer;
    }

    public Float getGrossMonthlyPremiumRate()
    {
        return grossMonthlyPremiumRate;
    }

    public void setGrossMonthlyPremiumRate(Float grossMonthlyPremiumRate)
    {
        this.grossMonthlyPremiumRate = grossMonthlyPremiumRate;
    }

    public Float getGrossPremium()
    {
        return grossPremium;
    }

    public void setGrossPremium(Float grossPremium)
    {
        this.grossPremium = grossPremium;
    }

    public Float getBenefitAmountSpouse()
    {
        return benefitAmountSpouse;
    }

    public void setBenefitAmountSpouse(Float benefitAmountSpouse)
    {
        this.benefitAmountSpouse = benefitAmountSpouse;
    }

    public Float getCurrentNumberLivesSpouse()
    {
        return currentNumberLivesSpouse;
    }

    public void setCurrentNumberLivesSpouse(Float currentNumberLivesSpouse)
    {
        this.currentNumberLivesSpouse = currentNumberLivesSpouse;
    }

    public Float getCurrentVolumeOfInsurerSpouse()
    {
        return currentVolumeOfInsurerSpouse;
    }

    public void setCurrentVolumeOfInsurerSpouse(Float currentVolumeOfInsurerSpouse)
    {
        this.currentVolumeOfInsurerSpouse = currentVolumeOfInsurerSpouse;
    }

    public Float getGrossMonthlyPremiumRateSpouse()
    {
        return grossMonthlyPremiumRateSpouse;
    }

    public void setGrossMonthlyPremiumRateSpouse(Float grossMonthlyPremiumRateSpouse)
    {
        this.grossMonthlyPremiumRateSpouse = grossMonthlyPremiumRateSpouse;
    }

    public Float getGrossPremiumSpouse()
    {
        return grossPremiumSpouse;
    }

    public void setGrossPremiumSpouse(Float grossPremiumSpouse)
    {
        this.grossPremiumSpouse = grossPremiumSpouse;
    }

    public Float getBenefitAmountDependent()
    {
        return benefitAmountDependent;
    }

    public void setBenefitAmountDependent(Float benefitAmountDependent)
    {
        this.benefitAmountDependent = benefitAmountDependent;
    }

    public Float getCurrentNumberOfUnitsDependent()
    {
        return currentNumberOfUnitsDependent;
    }

    public void setCurrentNumberOfUnitsDependent(Float currentNumberOfUnitsDependent)
    {
        this.currentNumberOfUnitsDependent = currentNumberOfUnitsDependent;
    }

    public Float getGrossMonthlyPremiumRateDependent()
    {
        return grossMonthlyPremiumRateDependent;
    }

    public void setGrossMonthlyPremiumRateDependent(Float grossMonthlyPremiumRateDependent)
    {
        this.grossMonthlyPremiumRateDependent = grossMonthlyPremiumRateDependent;
    }

    public Float getGrossPremiumDependent()
    {
        return grossPremiumDependent;
    }

    public void setGrossPremiumDependent(Float grossPremiumDependent)
    {
        this.grossPremiumDependent = grossPremiumDependent;
    }

    public Float getTotalGrossPremium()
    {
        return totalGrossPremium;
    }

    public void setTotalGrossPremium(Float totalGrossPremium)
    {
        this.totalGrossPremium = totalGrossPremium;
    }

    public Float getAdjust()
    {
        return adjust;
    }

    public void setAdjust(Float adjust)
    {
        this.adjust = adjust;
    }

    public Float getAdminFee()
    {
        return adminFee;
    }

    public void setAdminFee(Float adminFee)
    {
        this.adminFee = adminFee;
    }

    public Float getCommission()
    {
        return commission;
    }

    public void setCommission(Float commission)
    {
        this.commission = commission;
    }

    public Float getTotalCommission()
    {
        return totalCommission;
    }

    public void setTotalCommission(Float totalCommission)
    {
        this.totalCommission = totalCommission;
    }

    public Float getOntarioTax()
    {
        return ontarioTax;
    }

    public void setOntarioTax(Float ontarioTax)
    {
        this.ontarioTax = ontarioTax;
    }

    public Float getQuebecTax()
    {
        return quebecTax;
    }

    public void setQuebecTax(Float quebecTax)
    {
        this.quebecTax = quebecTax;
    }

    public Float getManitobaTax()
    {
        return manitobaTax;
    }

    public void setManitobaTax(Float manitobaTax)
    {
        this.manitobaTax = manitobaTax;
    }

    public Float getNetPremiumPaid()
    {
        return netPremiumPaid;
    }

    public void setNetPremiumPaid(Float netPremiumPaid)
    {
        this.netPremiumPaid = netPremiumPaid;
    }

    public String getComments()
    {
        return comments;
    }

    public void setComments(String comments)
    {
        this.comments = comments;
    }

    public Float getGrossPremiumAndAdjust()
    {
        return grossPremiumAndAdjust;
    }

    public void setGrossPremiumAndAdjust(Float grossPremiumAndAdjust)
    {
        this.grossPremiumAndAdjust = grossPremiumAndAdjust;
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
                '}';
    }
}
