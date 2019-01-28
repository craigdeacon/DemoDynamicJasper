/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Component.TestReportComponent.secondReport.container;

/**
 *Dummy information for Employee Report subtitle
 * 
 * @author craig.deacon
 */
public class Group
{
    private String groupName;
    String accountNo;
    String division;
    String productType;
    Float grossPremiums;
    Float bbdAdminFee;
    Float advisorCommission;
    Float netPremiums;

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

    
    
    
    public String getAccountNo()
    {
        return accountNo;
    }

    public void setAccountNo( String accountNo )
    {
        this.accountNo = accountNo;
    }

    public String getDivision()
    {
        return division;
    }

    public void setDivision( String division )
    {
        this.division = division;
    }

    public String getProductType()
    {
        return productType;
    }

    public void setProductType( String productType )
    {
        this.productType = productType;
    }

    public float getGrossPremiums()
    {
        return grossPremiums;
    }

    public void setGrossPremiums( float grossPremiums )
    {
        this.grossPremiums = grossPremiums;
    }

    public float getBbdAdminFee()
    {
        return bbdAdminFee;
    }

    public void setBbdAdminFee( float bbdAdminFee )
    {
        this.bbdAdminFee = bbdAdminFee;
    }

    public float getAdvisorCommission()
    {
        return advisorCommission;
    }

    public void setAdvisorCommission( float advisorCommission )
    {
        this.advisorCommission = advisorCommission;
    }

    public float getNetPremiums()
    {
        return netPremiums;
    }

    public void setNetPremiums( float netPremiums )
    {
        this.netPremiums = netPremiums;
    }
    
    

    public String getGroupName()
    {
        return groupName;
    }

    public void setGroupName( String groupName )
    {
        this.groupName = groupName;
    }

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
