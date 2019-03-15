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
public class ProvinceTotal
{
    private String province;
    private Float pstTotal;
    private Float retroactivePstTotal;

    public ProvinceTotal()
    {
    }

    public ProvinceTotal( String Province, Float pstTotal, Float retroactivePstTotal )
    {
        this.province = Province;
        this.pstTotal = pstTotal;
        this.retroactivePstTotal = retroactivePstTotal;
    }

    public String getProvince()
    {
        return province;
    }

    public void setProvince( String province )
    {
        this.province = province;
    }


    public Float getPstTotal()
    {
        return pstTotal;
    }

    public void setPstTotal( Float pstTotal )
    {
        this.pstTotal = pstTotal;
    }

    public Float getRetroactivePstTotal()
    {
        return retroactivePstTotal;
    }

    public void setRetroactivePstTotal( Float retroactivePstTotal )
    {
        this.retroactivePstTotal = retroactivePstTotal;
    }
    
    
}
