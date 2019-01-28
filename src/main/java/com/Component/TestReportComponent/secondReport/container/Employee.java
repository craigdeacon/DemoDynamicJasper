/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Component.TestReportComponent.secondReport.container;

/**
 *
 * @author craig.deacon
 */
public class Employee
{
    private String name;
    private Float life;
    private Float add;
    private Float dLife;
    private Float std;
    private Float ltd;
    private Float ci;
    private Float depCi;
    private Float ehb;
    private Float dental;
    private Float hcsa;
    private Float psa;
    private Float eap;
    private Float dsai;
    private Float total; 

    public Float getLtd()
    {
        return ltd;
    }

    public void setLtd( Float ltd )
    {
        this.ltd = ltd;
    }

    public Employee( String name, Float life, Float add, Float dLife, Float std, Float ltd, Float ci, Float depCi, Float ehb, Float dental, Float hcsa, Float psa, Float eap, Float dsai )
    {
        this.name = name;
        this.life = life;
        this.add = add;
        this.dLife = dLife;
        this.std = std;
        this.ltd = ltd;
        this.ci = ci;
        this.depCi = depCi;
        this.ehb = ehb;
        this.dental = dental;
        this.hcsa = hcsa;
        this.psa = psa;
        this.eap = eap;
        this.dsai = dsai;
        this.total = Float.MIN_VALUE;

        if (life!=null)
            total+=life;
        if (add!=null)
            total+=add;
        if (dLife!=null)
            total+=dLife;
        if (std!=null)
            total+=std;
        if (ltd!=null)
            total+=ltd;
        if (ci!=null)
            total+=ci;
        if (depCi!=null)
            total+=depCi;
        if (ehb!=null)
            total+=ehb;
        if (dental!=null)
            total+=dental;
        if (hcsa!=null)
            total+=hcsa;
        if (psa!=null)
            total+=psa;
        if (eap!=null)
            total+=eap;
        if (dsai!=null)
            total+=dsai;
        
    }

    public Employee()
    {
        this.total = Float.MIN_VALUE;
    }

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public Float getLife()
    {
        return life;
    }

    public void setLife( Float life )
    {
        this.life = life;
    }

    public Float getAdd()
    {
        return add;
    }

    public void setAdd( Float add )
    {
        this.add = add;
    }

    public Float getdLife()
    {
        return dLife;
    }

    public void setdLife( Float dLife )
    {
        this.dLife = dLife;
    }

    public Float getStd()
    {
        return std;
    }

    public void setStd( Float std )
    {
        this.std = std;
    }

    public Float getCi()
    {
        return ci;
    }

    public void setCi( Float ci )
    {
        this.ci = ci;
    }

    public Float getDepCi()
    {
        return depCi;
    }

    public void setDepCi( Float depCi )
    {
        this.depCi = depCi;
    }

    public Float getEhb()
    {
        return ehb;
    }

    public void setEhb( Float ehb )
    {
        this.ehb = ehb;
    }

    public Float getDental()
    {
        return dental;
    }

    public void setDental( Float dental )
    {
        this.dental = dental;
    }

    public Float getHcsa()
    {
        return hcsa;
    }

    public void setHcsa( Float hcsa )
    {
        this.hcsa = hcsa;
    }

    public Float getPsa()
    {
        return psa;
    }

    public void setPsa( Float psa )
    {
        this.psa = psa;
    }

    public Float getEap()
    {
        return eap;
    }

    public void setEap( Float eap )
    {
        this.eap = eap;
    }

    public Float getDsai()
    {
        return dsai;
    }

    public void setDsai( Float dsai )
    {
        this.dsai = dsai;
    }

    public Float getTotal()
    {
        if (life!=null)      
            total+=life;
        if (add!=null)
            total+=add;
        if (dLife!=null)
            total+=dLife;
        if (std!=null)
            total+=std;
        if (ltd!=null)
            total+=ltd;
        if (ci!=null)
            total+=ci;            
        if (depCi!=null)
            total+=depCi;
        if (ehb!=null)
            total+=ehb;
        if (dental!=null)
            total+=dental;
        if (hcsa!=null)
            total+=hcsa;
        if (psa!=null)
            total+=psa;
        if (eap!=null)
            total+=eap;
        if (dsai!=null)
            total+=dsai;
        return total;
    }

    public void setTotal( Float total )
    {
        this.total = total;
    }

    @Override
    public String toString()
    {
        return "Employee{" + "name=" + name + ", life=" + life + ", add=" + add + ", dLife=" + dLife + ", std=" + std + ", ci=" + ci + ", depCi=" + depCi + ", ehb=" + ehb + ", dental=" + dental + ", hcsa=" + hcsa + ", psa=" + psa + ", eap=" + eap + ", dsai=" + dsai + ", total=" + total + '}';
    }
    
    
}
