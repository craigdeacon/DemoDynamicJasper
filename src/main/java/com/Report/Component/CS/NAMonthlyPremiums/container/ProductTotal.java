/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Report.Component.CS.NAMonthlyPremiums.container;

/**
 *
 * @author craig.deacon
 */
public class ProductTotal
{
    private String productName;
    private Float premiumTotal; //current premium
    private Float pstTotal; //current_tax
    private Float retroactivePremiumTotal; // adjustment premium
    private Float retroactivePstTotal; //adjustment tax
    private Float grossPremiumTotal; //gross premium
    private Float administrationAmountTotal; //admin fee
    private Float commissionAmountTotal; // commission
    private Float netPremiumTotal; // total net premium
    private Float gstTotal; //

    public ProductTotal()
    {
    }

    public ProductTotal( String productName, Float premiumTotal, Float pstTotal, Float retroactivePremiumTotal, Float retroactivePstTotal, Float grossPremiumTotal, Float administrationAmountTotal, Float commissionAmountTotal, Float netPremiumTotal, Float gstTotal )
    {
        this.productName = productName;
        this.premiumTotal = premiumTotal;
        this.pstTotal = pstTotal;
        this.retroactivePremiumTotal = retroactivePremiumTotal;
        this.retroactivePstTotal = retroactivePstTotal;
        this.grossPremiumTotal = grossPremiumTotal;
        this.administrationAmountTotal = administrationAmountTotal;
        this.commissionAmountTotal = commissionAmountTotal;
        this.netPremiumTotal = netPremiumTotal;
        this.gstTotal = gstTotal;
    }

    public String getProductName()
    {
        return productName;
    }

    public void setProductName( String productName )
    {
        this.productName = productName;
    }

    public Float getPremiumTotal()
    {
        return premiumTotal;
    }

    public void setPremiumTotal( Float premiumTotal )
    {
        this.premiumTotal = premiumTotal;
    }

    public Float getPstTotal()
    {
        return pstTotal;
    }

    public void setPstTotal( Float pstTotal )
    {
        this.pstTotal = pstTotal;
    }

    public Float getRetroactivePremiumTotal()
    {
        return retroactivePremiumTotal;
    }

    public void setRetroactivePremiumTotal( Float retroactivePremiumTotal )
    {
        this.retroactivePremiumTotal = retroactivePremiumTotal;
    }

    public Float getRetroactivePstTotal()
    {
        return retroactivePstTotal;
    }

    public void setRetroactivePstTotal( Float retroactivePstTotal )
    {
        this.retroactivePstTotal = retroactivePstTotal;
    }

    public Float getGrossPremiumTotal()
    {
        return grossPremiumTotal;
    }

    public void setGrossPremiumTotal( Float grossPremiumTotal )
    {
        this.grossPremiumTotal = grossPremiumTotal;
    }

    public Float getAdministrationAmountTotal()
    {
        return administrationAmountTotal;
    }

    public void setAdministrationAmountTotal( Float administrationAmountTotal )
    {
        this.administrationAmountTotal = administrationAmountTotal;
    }

    public Float getCommissionAmountTotal()
    {
        return commissionAmountTotal;
    }

    public void setCommissionAmountTotal( Float commissionAmountTotal )
    {
        this.commissionAmountTotal = commissionAmountTotal;
    }

    public Float getNetPremiumTotal()
    {
        return netPremiumTotal;
    }

    public void setNetPremiumTotal( Float netPremiumTotal )
    {
        this.netPremiumTotal = netPremiumTotal;
    }

    public Float getGstTotal()
    {
        return gstTotal;
    }

    public void setGstTotal( Float gstTotal )
    {
        this.gstTotal = gstTotal;
    }
    
    
    
}
