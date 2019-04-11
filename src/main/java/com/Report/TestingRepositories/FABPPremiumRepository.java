/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Report.TestingRepositories;

import com.Database.DAO.FABPPremiumReport.FABPPremiumDAO;
import com.Report.Component.ME.FABPPremiumReport.container.FABPPremiumGroup;
import java.util.ArrayList;
import org.springframework.stereotype.Component;

/**
 *
 * @author craig.deacon
 */
@Component
public class FABPPremiumRepository implements FABPPremiumDAO
{

    @Override
    public ArrayList<FABPPremiumGroup> getFABPPremiumGroupList()
    {
        ArrayList<FABPPremiumGroup> fabpPremiumGroupList = new ArrayList<>();
        fabpPremiumGroupList.add( new FABPPremiumGroup( "Avengers", "Acclaim Ability", new Double( "125.5" ), new Double( "1.5" ), new Double( "24.0" ),
                new Double( "100.0" ), new Double( "0.15" ), new Double( "0" ), new Double( "0" ) ) );
        fabpPremiumGroupList.add( new FABPPremiumGroup( "Transformers", "Acclaim Ability", new Double( "125.5" ), new Double( "1.5" ), new Double( "24.0" ),
                new Double( "100.0" ), new Double( "0.15" ), new Double( "0" ), new Double( "0" ) ) );
         fabpPremiumGroupList.add( new FABPPremiumGroup( "Inhumans", "Benefits By Design", new Double( "327.5" ), new Double( "2.5" ), new Double( "25.0" ),
                new Double( "300.0" ), new Double( "0.15" ), new Double( "0" ), new Double( "0" ) ) );
         fabpPremiumGroupList.add( new FABPPremiumGroup( "Transformers", "Benefits By Design", new Double( "327.5" ), new Double( "2.5" ), new Double( "25.0" ),
                new Double( "300.0" ), new Double( "0.0" ), new Double( "0.20" ), new Double( "0" ) ) );
         fabpPremiumGroupList.add( new FABPPremiumGroup( "Fantastic Four", "CHUBB", new Double( "234.5" ), new Double( "4.5" ), new Double( "30.2" ),
                new Double( "198.8" ), new Double( "0.0" ), new Double( "0.0" ), new Double( "0.3" ) ) );
         fabpPremiumGroupList.add( new FABPPremiumGroup( "Defenders", "CHUBB", new Double( "234.5" ), new Double( "4.5" ), new Double( "30.2" ),
                new Double( "198.8" ), new Double( "0.0" ), new Double( "0.0" ), new Double( "0.3" ) ) );

        return fabpPremiumGroupList;
    }

}
