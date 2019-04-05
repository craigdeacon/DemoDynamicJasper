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
        fabpPremiumGroupList.add( new FABPPremiumGroup( "Avengers", "Acclaim Ability", new Float( "125.5" ), new Float( "1.5" ), new Float( "24.0" ),
                new Float( "100.0" ), new Float( "0.15" ), new Float( "0" ), new Float( "0" ) ) );
        fabpPremiumGroupList.add( new FABPPremiumGroup( "Transformers", "Acclaim Ability", new Float( "125.5" ), new Float( "1.5" ), new Float( "24.0" ),
                new Float( "100.0" ), new Float( "0.15" ), new Float( "0" ), new Float( "0" ) ) );
         fabpPremiumGroupList.add( new FABPPremiumGroup( "Inhumans", "Benefits By Design", new Float( "327.5" ), new Float( "2.5" ), new Float( "25.0" ),
                new Float( "300.0" ), new Float( "0.15" ), new Float( "0" ), new Float( "0" ) ) );
         fabpPremiumGroupList.add( new FABPPremiumGroup( "Transformers", "Benefits By Design", new Float( "327.5" ), new Float( "2.5" ), new Float( "25.0" ),
                new Float( "300.0" ), new Float( "0.0" ), new Float( "0.20" ), new Float( "0" ) ) );
         fabpPremiumGroupList.add( new FABPPremiumGroup( "Fantastic Four", "CHUBB", new Float( "234.5" ), new Float( "4.5" ), new Float( "30.2" ),
                new Float( "198.8" ), new Float( "0.0" ), new Float( "0.0" ), new Float( "0.3" ) ) );
         fabpPremiumGroupList.add( new FABPPremiumGroup( "Defenders", "CHUBB", new Float( "234.5" ), new Float( "4.5" ), new Float( "30.2" ),
                new Float( "198.8" ), new Float( "0.0" ), new Float( "0.0" ), new Float( "0.3" ) ) );

        return fabpPremiumGroupList;
    }

}
