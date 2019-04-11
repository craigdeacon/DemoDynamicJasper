/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Report.Component.CS.EmployeeCostBreakdown.container;

/**
 *Dummy information for Employee Report subtitle
 * 
 * @author craig.deacon
 */
public class Group
{
    private String groupName;
    private String accountNo;
    private String division;
    private String productType;
    private Double grossPremiums;
    private Double bbdAdminFee;
    private Double advisorCommission;
    private Double netPremiums;

    /**
     *
     * @param groupName
     * @param accountNo
     * @param division
     * @param productType
     * @param grossPremiums
     * @param bbdAdminFee
     * @param advisorCommission
     * @param netPremiums
     */
    public Group( String groupName, String accountNo, String division, String productType, Double grossPremiums, Double bbdAdminFee, Double advisorCommission, Double netPremiums )
    {
        this.groupName = groupName;
        this.accountNo = accountNo;
        this.division = division;
        this.productType = productType;
        this.grossPremiums = grossPremiums;
        this.bbdAdminFee = bbdAdminFee;
        this.advisorCommission = advisorCommission;
        this.netPremiums = netPremiums;
    }

    /**
     *
     * @return
     */
    public String getAccountNo()
    {
        return accountNo;
    }

    /**
     *
     * @param accountNo
     */
    public void setAccountNo( String accountNo )
    {
        this.accountNo = accountNo;
    }

    /**
     *
     * @return
     */
    public String getDivision()
    {
        return division;
    }

    /**
     *
     * @param division
     */
    public void setDivision( String division )
    {
        this.division = division;
    }

    /**
     *
     * @return
     */
    public String getProductType()
    {
        return productType;
    }

    /**
     *
     * @param productType
     */
    public void setProductType( String productType )
    {
        this.productType = productType;
    }

    /**
     *
     * @return
     */
    public Double getGrossPremiums()
    {
        return grossPremiums;
    }

    /**
     *
     * @param grossPremiums
     */
    public void setGrossPremiums( Double grossPremiums )
    {
        this.grossPremiums = grossPremiums;
    }

    /**
     *
     * @return
     */
    public Double getBbdAdminFee()
    {
        return bbdAdminFee;
    }

    /**
     *
     * @param bbdAdminFee
     */
    public void setBbdAdminFee( Double bbdAdminFee )
    {
        this.bbdAdminFee = bbdAdminFee;
    }

    /**
     *
     * @return
     */
    public Double getAdvisorCommission()
    {
        return advisorCommission;
    }

    /**
     *
     * @param advisorCommission
     */
    public void setAdvisorCommission( Double advisorCommission )
    {
        this.advisorCommission = advisorCommission;
    }

    /**
     *
     * @return
     */
    public Double getNetPremiums()
    {
        return netPremiums;
    }

    /**
     *
     * @param netPremiums
     */
    public void setNetPremiums( Double netPremiums )
    {
        this.netPremiums = netPremiums;
    }
    
    /**
     *
     * @return
     */
    public String getGroupName()
    {
        return groupName;
    }

    /**
     *
     * @param groupName
     */
    public void setGroupName( String groupName )
    {
        this.groupName = groupName;
    }

    /**
     *
     * @param groupName
     */
    public Group( String groupName )
    {
        this.groupName = groupName;
    }

    @Override
    public String toString()
    {
        return "Group{" + "name=" + groupName + '}';
    }
    
    
}
