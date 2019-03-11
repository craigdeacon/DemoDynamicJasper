/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Component.ME.RevenueReportByProduct.container;

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
    private Float grossPremiums;
    private Float bbdAdminFee;
    private Float advisorCommission;
    private Float netPremiums;

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
    public Group( String groupName, String accountNo, String division, String productType, float grossPremiums, float bbdAdminFee, float advisorCommission, float netPremiums )
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
     */
    public Group()
    {
        
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
    public float getGrossPremiums()
    {
        return grossPremiums;
    }

    /**
     *
     * @param grossPremiums
     */
    public void setGrossPremiums( float grossPremiums )
    {
        this.grossPremiums = grossPremiums;
    }

    /**
     *
     * @return
     */
    public float getBbdAdminFee()
    {
        return bbdAdminFee;
    }

    /**
     *
     * @param bbdAdminFee
     */
    public void setBbdAdminFee( float bbdAdminFee )
    {
        this.bbdAdminFee = bbdAdminFee;
    }

    /**
     *
     * @return
     */
    public float getAdvisorCommission()
    {
        return advisorCommission;
    }

    /**
     *
     * @param advisorCommission
     */
    public void setAdvisorCommission( float advisorCommission )
    {
        this.advisorCommission = advisorCommission;
    }

    /**
     *
     * @return
     */
    public float getNetPremiums()
    {
        return netPremiums;
    }

    /**
     *
     * @param netPremiums
     */
    public void setNetPremiums( float netPremiums )
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
