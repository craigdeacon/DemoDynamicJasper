/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Component.ME.RevenueReportByProduct.container;

/**
 *
 * @author craig.deacon
 */
public class ProductTypes
{
    private String productName;
    private Integer groupCount;
    private Float totalGrossPremiums;
    private Float totalBbdAdminFee;
    private Float totalAdvisorCommissions;
    private Float totalNetPremiums;

    /**
     *
     * @param productName
     */
    public ProductTypes( String productName )
    {
        this.productName = productName;
        groupCount = 0;
        totalGrossPremiums = new Float("0");
        totalBbdAdminFee = new Float("0");
        totalAdvisorCommissions = new Float("0"); 
        totalNetPremiums = new Float("0");        
    }
    
    /**
     *
     */
    public ProductTypes()
    {
        groupCount = 0;
        totalGrossPremiums = new Float("0");
        totalBbdAdminFee = new Float("0");
        totalAdvisorCommissions = new Float("0"); 
        totalNetPremiums = new Float("0");
    }

    /**
     *
     * @return
     */
    public String getProductName()
    {
        return productName;
    }

    /**
     *
     * @param productName
     */
    public void setProductName( String productName )
    {
        this.productName = productName;
    }

    /**
     *
     * @return
     */
    public int getGroupCount()
    {
        return groupCount;
    }

    /**
     *
     * @param groupCount
     */
    public void setGroupCount( int groupCount )
    {
        this.groupCount = groupCount;
    }

    /**
     *
     * @return
     */
    public Float getTotalGrossPremiums()
    {
        return totalGrossPremiums;
    }

    /**
     *
     * @param totalGrossPremiums
     */
    public void setTotalGrossPremiums( Float totalGrossPremiums )
    {
        this.totalGrossPremiums = totalGrossPremiums;
    }

    /**
     *
     * @return
     */
    public Float getTotalBbdAdminFee()
    {
        return totalBbdAdminFee;
    }

    /**
     *
     * @param totalBbdAdminFee
     */
    public void setTotalBbdAdminFee( Float totalBbdAdminFee )
    {
        this.totalBbdAdminFee = totalBbdAdminFee;
    }

    /**
     *
     * @return
     */
    public Float getTotalAdvisorCommissions()
    {
        return totalAdvisorCommissions;
    }

    /**
     *
     * @param totalAdvisorCommissions
     */
    public void setTotalAdvisorCommissions( Float totalAdvisorCommissions )
    {
        this.totalAdvisorCommissions = totalAdvisorCommissions;
    }

    /**
     *
     * @return
     */
    public Float getTotalNetPremiums()
    {
        return totalNetPremiums;
    }

    /**
     *
     * @param totalNetPremiums
     */
    public void setTotalNetPremiums( Float totalNetPremiums )
    {
        this.totalNetPremiums = totalNetPremiums;
    }
    
    
}
