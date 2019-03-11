/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Report.Component.ME.ProvincialSalesTaxReport.container;

/**
 *
 * @author craig.deacon
 */
public class ProvincialGroup
{

    private String groupName;
    private String policyNum;
    private Float taxPremiumOn;
    private Float salesTaxOn;
    private Float taxPremiumMa;
    private Float salesTaxMa;
    private Float taxPremiumQc;
    private Float salesTaxQc;
    private Float taxPremiumSa;
    private Float salesTaxSa;
    private Integer underwriterId;
    private String underwriterName;

    /**
     *
     * @return
     */
    public String getUnderwriterName()
    {
        return underwriterName;
    }

    /**
     *
     * @param underwriterName
     */
    public void setUnderwriterName( String underwriterName )
    {
        this.underwriterName = underwriterName;
    }

    /**
     *
     */
    public ProvincialGroup()
    {

    }

    /**
     *
     * @param groupName
     * @param policyNum
     * @param taxPremiumOn
     * @param salesTaxOn
     * @param taxPremiumMa
     * @param salesTaxMa
     * @param taxPremiumQc
     * @param salesTaxQc
     * @param taxPremiumSa
     * @param salesTaxSa
     * @param underwriterId
     */
    public ProvincialGroup( String groupName, String policyNum, Float taxPremiumOn, Float salesTaxOn, Float taxPremiumMa, Float salesTaxMa, Float taxPremiumQc, Float salesTaxQc, Float taxPremiumSa, Float salesTaxSa, Integer underwriterId )
    {
        this.groupName = groupName;
        this.policyNum = policyNum;
        this.taxPremiumOn = taxPremiumOn;
        this.salesTaxOn = salesTaxOn;
        this.taxPremiumMa = taxPremiumMa;
        this.salesTaxMa = salesTaxMa;
        this.taxPremiumQc = taxPremiumQc;
        this.salesTaxQc = salesTaxQc;
        this.taxPremiumSa = taxPremiumSa;
        this.salesTaxSa = salesTaxSa;
        this.underwriterId = underwriterId;
    }

    /**
     *
     * @return
     */
    public Integer getUnderwriterId()
    {
        return underwriterId;
    }

    /**
     *
     * @param underwriterId
     */
    public void setUnderwriterId( Integer underwriterId )
    {
        this.underwriterId = underwriterId;
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
     * @return
     */
    public String getPolicyNum()
    {
        return policyNum;
    }

    /**
     *
     * @param policyNum
     */
    public void setPolicyNum( String policyNum )
    {
        this.policyNum = policyNum;
    }

    /**
     *
     * @return
     */
    public Float getTaxPremiumOn()
    {
        if ( taxPremiumOn == 0 )
        {
            return null;
        }
        return taxPremiumOn;
    }

    /**
     *
     * @param taxPremiumOn
     */
    public void setTaxPremiumOn( Float taxPremiumOn )
    {
        this.taxPremiumOn = taxPremiumOn;
    }

    /**
     *
     * @return
     */
    public Float getSalesTaxOn()
    {
        if ( salesTaxOn == 0 )
        {
            return null;
        }
        return salesTaxOn;
    }

    /**
     *
     * @param salesTaxOn
     */
    public void setSalesTaxOn( Float salesTaxOn )
    {
        this.salesTaxOn = salesTaxOn;
    }

    /**
     *
     * @return
     */
    public Float getTaxPremiumMa()
    {
        if ( taxPremiumMa == 0 )
        {
            return null;
        }
        return taxPremiumMa;
    }

    /**
     *
     * @param taxPremiumMa
     */
    public void setTaxPremiumMa( Float taxPremiumMa )
    {
        this.taxPremiumMa = taxPremiumMa;
    }

    /**
     *
     * @return
     */
    public Float getSalesTaxMa()
    {
        if ( salesTaxMa == 0 )
        {
            return null;
        }
        return salesTaxMa;
    }

    /**
     *
     * @param salesTaxMa
     */
    public void setSalesTaxMa( Float salesTaxMa )
    {
        this.salesTaxMa = salesTaxMa;
    }

    /**
     *
     * @return
     */
    public Float getTaxPremiumQc()
    {
        if ( taxPremiumQc == 0 )
        {
            return null;
        }
        return taxPremiumQc;
    }

    /**
     *
     * @param taxPremiumQc
     */
    public void setTaxPremiumQc( Float taxPremiumQc )
    {
        this.taxPremiumQc = taxPremiumQc;
    }

    /**
     *
     * @return
     */
    public Float getSalesTaxQc()
    {
        if ( salesTaxQc == 0 )
        {
            return null;
        }
        return salesTaxQc;
    }

    /**
     *
     * @param salesTaxQc
     */
    public void setSalesTaxQc( Float salesTaxQc )
    {
        this.salesTaxQc = salesTaxQc;
    }

    /**
     *
     * @return
     */
    public Float getTaxPremiumSa()
    {
        if ( taxPremiumSa == 0 )
        {
            return null;
        }
        return taxPremiumSa;
    }

    /**
     *
     * @param taxPremiumSa
     */
    public void setTaxPremiumSa( Float taxPremiumSa )
    {
        this.taxPremiumSa = taxPremiumSa;
    }

    /**
     *
     * @return
     */
    public Float getSalesTaxSa()
    {
        if ( salesTaxSa == 0 )
        {
            return null;
        }
        return salesTaxSa;
    }

    /**
     *
     * @param salesTaxSa
     */
    public void setSalesTaxSa( Float salesTaxSa )
    {
        this.salesTaxSa = salesTaxSa;
    }

}
