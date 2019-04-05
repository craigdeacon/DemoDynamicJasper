/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Database.BO;

import com.Database.DAO.FABPPremiumReport.FABPPremiumDAO;
import com.Database.DAO.FABPPremiumReport.FABPPremiumDAOImpl;
import com.Report.Component.ME.FABPPremiumReport.container.FABPPremiumGroup;
import com.Report.Component.ME.FABPPremiumReport.container.FABPPremiumUnderwriterTotals;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author craig.deacon
 */
@Service
public class FABPPremiumBO
{

    private static final Logger LOGGER = Logger.getLogger( FABPPremiumBO.class.getName() );
    private FABPPremiumDAO fabpPremiumDAO;

    @Autowired
    public FABPPremiumBO( FABPPremiumDAOImpl fabpPremiumDAO )
    {
        this.fabpPremiumDAO = fabpPremiumDAO;
    }

    public ArrayList<FABPPremiumGroup> getFABPPremiumGroups()
    {
        return fabpPremiumDAO.getFABPPremiumGroupList();
    }

    public ArrayList<FABPPremiumUnderwriterTotals> getUnderwriterTotals()
    {
        return createUnderwriterTotals( fabpPremiumDAO.getFABPPremiumGroupList() );
    }

    private ArrayList<FABPPremiumUnderwriterTotals> createUnderwriterTotals( ArrayList<FABPPremiumGroup> fabpPremiumGroupList )
    {
        HashMap<String, FABPPremiumUnderwriterTotals> underwriterMap = new HashMap<>();
        Double grossPremTot = 0.;
        for ( FABPPremiumGroup group : fabpPremiumGroupList )
        {
            grossPremTot += group.getGrossPremiums();
            if ( underwriterMap.containsKey( group.getUnderwriter() ) )
            {
                FABPPremiumUnderwriterTotals underwriter = underwriterMap.get( group.getUnderwriter() );
                underwriterMap.put( group.getUnderwriter(), new FABPPremiumUnderwriterTotals(
                        group.getUnderwriter(),
                        group.getGrossPremiums() + underwriter.getGrossPremiums(),
                        group.getCommission() + underwriter.getCommission(),
                        group.getAdmin() + underwriter.getAdmin(),
                        group.getNetPremiums() + underwriter.getNetPremiums(),
                        group.getOnPst() + underwriter.getOnPst(),
                        group.getQcPst() + underwriter.getQcPst(),
                        group.getSkPst() + underwriter.getSkPst() ) );
            }
            else
            {
                underwriterMap.put( group.getUnderwriter(), new FABPPremiumUnderwriterTotals(
                        group.getUnderwriter(),
                        group.getGrossPremiums(),
                        group.getCommission(),
                        group.getAdmin(),
                        group.getNetPremiums(),
                        group.getOnPst(),
                        group.getQcPst(),
                        group.getSkPst() ) );
            }
        }
        ArrayList<FABPPremiumUnderwriterTotals> underwriterList = new ArrayList<>( underwriterMap.values() );
        System.out.println( "GRAND TOTAL GROSS PREMIUM: " + grossPremTot );
        return underwriterList;
    }

}
