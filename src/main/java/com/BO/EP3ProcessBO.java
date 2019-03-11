/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.BO;

import com.Component.CS.EP3.container.EP3ProcessEntry;
import com.DAO.EP3.EP3ProcessDAO;
import com.DemoDynamicJasper.spring.config.AppConfig;
import java.sql.Date;
import java.util.ArrayList;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

/**
 *
 * @author craig.deacon
 */
@Service
public class EP3ProcessBO
{
    private static final Logger LOGGER = Logger.getLogger( ProvincialSalesTaxBO.class.getName() );
    EP3ProcessDAO ep3ProcessDAO;

    @Autowired
    public EP3ProcessBO(EP3ProcessDAO ep3ProcessDAO)
    {
        this.ep3ProcessDAO = ep3ProcessDAO;
    }

    private ArrayList<EP3ProcessEntry> getEP3ProcessList(Date invoiceDate )
    {
        return ep3ProcessDAO.getGroupList( invoiceDate );
    }

    public ArrayList<EP3ProcessEntry> filterAffiliatedGroupsList( Date invoiceDate )
    {
        ArrayList<EP3ProcessEntry> originalList = getEP3ProcessList( invoiceDate );
        ArrayList<EP3ProcessEntry> filteredList = new ArrayList<>();
        for ( EP3ProcessEntry ep3ProcessEntry : originalList )
        {
            LOGGER.info( "Original: " + ep3ProcessEntry.getGreenShieldDivision() );
            LOGGER.info( "Altered:  " + getGreenShieldPolicyNumber( ep3ProcessEntry.getGreenShieldDivision() ) );
            //Add to EP3 List if the group doesn't have a active Affiliation.
            if ( !ep3ProcessDAO.getGroupHasActiveAffiliation( getGreenShieldPolicyNumber( ep3ProcessEntry.getGreenShieldDivision() ), ep3ProcessEntry.getGroupId() ) )
            {
                filteredList.add( ep3ProcessEntry );
            }
        }
        return filteredList;
    }

    private String getGreenShieldPolicyNumber( String polNum )
    {

        String tmpStr = "";
        String validStr = "0123456789-";
        String singleStr;
        int iPos;
        int iLen = polNum.length();
        for ( iPos = 0; iPos < iLen; iPos++ )
        {
            singleStr = polNum.substring( iPos, iPos + 1 );
            if ( validStr.contains( singleStr ) )
            {
                tmpStr = tmpStr + singleStr;
            }
            else
            {
                return tmpStr;
            }
        }
        return tmpStr;
    }
    
    
}
