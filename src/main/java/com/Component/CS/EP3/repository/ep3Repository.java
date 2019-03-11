/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Component.CS.EP3.repository;

import com.Component.CS.EP3.container.EP3ProcessEntry;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author craig.deacon
 */
class ep3Repository
{

    public static List<EP3ProcessEntry> getEP3List() throws ParseException
    {
        List<EP3ProcessEntry> ep3List = new ArrayList<>();
        
        
        

        ep3List.add( new EP3ProcessEntry( "27650", "Boss Roofing Division of 9461833 Canada Ltd.", parseDate( "2020-01-01" ), parseDate( "2013-09-01" ), "$10,000", "", "", parseDate( "2019-01-01" ), parseDate( "2019-01-01" ) ) );
        ep3List.add( new EP3ProcessEntry( "34860-03", "	MedReleaf Corp.", parseDate( "2019-05-01" ), parseDate( "2017-01-01" ), "$10,000", "", "", parseDate( "2019-01-01" ), parseDate( "2019-01-03" ) ) );
        ep3List.add( new EP3ProcessEntry( "34860-02", "	MedReleaf Corp.", parseDate( "2019-05-01" ), parseDate( "2017-01-01" ), "$10,000", "", "", parseDate( "2019-01-01" ), parseDate( "2019-01-03" ) ) );
        ep3List.add( new EP3ProcessEntry( "34860-01", "	MedReleaf Corp.", parseDate( "2019-05-01" ), parseDate( "2017-01-01" ), "$10,000", "", "", parseDate( "2019-01-01" ), parseDate( "2019-01-03" ) ) );
        return ep3List;
    }
    
    private static Date parseDate(String date)
    {
        try
        {
            java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
            return new Date(utilDate.getTime());
        }
        catch (ParseException e)
        {
            return null;
        }
    }
   
   
 
}
