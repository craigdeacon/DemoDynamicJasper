/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Report.TestingRepositories;

import com.Report.Component.ME.RevenueReportByProduct.container.Group;
import com.Report.Component.ME.RevenueReportByProduct.container.ProductTypes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author craig.deacon
 */
public class RevenueByProductRepository
{

    /**
     *
     * @return
     */
    public static List<Group> getGroupList()
    {
        List<Group> groupList = new ArrayList<>();
        groupList.add( new Group("Group 1", "3309978", "Division 1", "Benepac", new Float("1417.58" ), new Float("135.75" ), new Float("141.78" ), new Float("1140.05" ) ) );
        groupList.add( new Group("Group 2", "3030436", "Division 2", "Benepac", new Float("472.75" ), new Float("73.77" ), new Float("56.57" ), new Float("342.41" ) ) );
        groupList.add( new Group("Group 3", "3034498", "Division 8", "ASO", new Float("983.34" ), new Float("93.07" ), new Float("103.26" ), new Float("783.90" ) ) );

        return groupList;

    }

    /**
     *
     * @return
     */
    public static List<ProductTypes> getProductTypesList()
    {
        ProductTypes[] productTypesArray = new ProductTypes[]
        {
            new ProductTypes("ASO" ), new ProductTypes("Benepac" ), new ProductTypes("Non-Benepac" ),
            new ProductTypes("Non-Benepac (pure)" ), new ProductTypes("Spending Account" )
        };
        int asoIndex = 0;
        int benepacIndex = 1;
        int nonBenepacIndex = 2;
        int nonBenepacPureIndex = 3;
        int spendingAccountIndex = 4;
        List<Group> groupList = getGroupList();
        for (int ctr = 0; ctr < 1000 ; ctr++)
        {
            int random = (int) ( 0 + Math.random() * 5 );
            String product = "";
            switch (random )
            {
                case 0:
                    product = "ASO";
                    break;
                case 1:
                    product = "Benepac";
                    break;
                case 2:
                    product = "Non-Benepac";
                    break;
                case 3:
                    product = "Non-Benepac (pure)";
                    break;
                case 4:
                    product = "Spending Account";
                    break;
            }
            groupList.add( (new Group("Group "+ ctr + 1, "1000" + ctr, "Division "+ ctr + 1, product, new Float ("834.45"), new Float("339.20"), new Float ("560.87"), new Float ("1123.48"))));
        }
        for ( Group g : groupList )
        {
            switch (g.getProductType())
            {
                case "ASO":
                    productTypesArray[asoIndex].setGroupCount( productTypesArray[asoIndex].getGroupCount() + 1 );
                    productTypesArray[asoIndex].setTotalGrossPremiums( productTypesArray[asoIndex].getTotalGrossPremiums() + g.getGrossPremiums() );
                    productTypesArray[asoIndex].setTotalBbdAdminFee( productTypesArray[asoIndex].getTotalBbdAdminFee() + g.getBbdAdminFee() );
                    productTypesArray[asoIndex].setTotalAdvisorCommissions( productTypesArray[asoIndex].getTotalAdvisorCommissions() + g.getAdvisorCommission() );
                    productTypesArray[asoIndex].setTotalNetPremiums( productTypesArray[asoIndex].getTotalNetPremiums() + g.getNetPremiums() );
                    break;
                case "Benepac":
                    productTypesArray[benepacIndex].setGroupCount( productTypesArray[benepacIndex].getGroupCount() + 1 );
                    productTypesArray[benepacIndex].setTotalGrossPremiums( productTypesArray[benepacIndex].getTotalGrossPremiums() + g.getGrossPremiums() );
                    productTypesArray[benepacIndex].setTotalBbdAdminFee( productTypesArray[benepacIndex].getTotalBbdAdminFee() + g.getBbdAdminFee() );
                    productTypesArray[benepacIndex].setTotalAdvisorCommissions( productTypesArray[benepacIndex].getTotalAdvisorCommissions() + g.getAdvisorCommission() );
                    productTypesArray[benepacIndex].setTotalNetPremiums( productTypesArray[benepacIndex].getTotalNetPremiums() + g.getNetPremiums() );
                    break;
                case "Non-Benepac":
                    productTypesArray[nonBenepacIndex].setGroupCount( productTypesArray[nonBenepacIndex].getGroupCount() + 1 );
                    productTypesArray[nonBenepacIndex].setTotalGrossPremiums( productTypesArray[nonBenepacIndex].getTotalGrossPremiums() + g.getGrossPremiums() );
                    productTypesArray[nonBenepacIndex].setTotalBbdAdminFee( productTypesArray[nonBenepacIndex].getTotalBbdAdminFee() + g.getBbdAdminFee() );
                    productTypesArray[nonBenepacIndex].setTotalAdvisorCommissions( productTypesArray[nonBenepacIndex].getTotalAdvisorCommissions() + g.getAdvisorCommission() );
                    productTypesArray[nonBenepacIndex].setTotalNetPremiums( productTypesArray[nonBenepacIndex].getTotalNetPremiums() + g.getNetPremiums() );
                    break;
                case "Non-Benepac (pure)":
                    productTypesArray[nonBenepacPureIndex].setGroupCount( productTypesArray[nonBenepacPureIndex].getGroupCount() + 1 );
                    productTypesArray[nonBenepacPureIndex].setTotalGrossPremiums( productTypesArray[nonBenepacPureIndex].getTotalGrossPremiums() + g.getGrossPremiums() );
                    productTypesArray[nonBenepacPureIndex].setTotalBbdAdminFee( productTypesArray[nonBenepacPureIndex].getTotalBbdAdminFee() + g.getBbdAdminFee() );
                    productTypesArray[nonBenepacPureIndex].setTotalAdvisorCommissions( productTypesArray[nonBenepacPureIndex].getTotalAdvisorCommissions() + g.getAdvisorCommission() );
                    productTypesArray[nonBenepacPureIndex].setTotalNetPremiums( productTypesArray[nonBenepacPureIndex].getTotalNetPremiums() + g.getNetPremiums() );
                    break;
                case "Spending Account":
                    productTypesArray[spendingAccountIndex].setGroupCount( productTypesArray[spendingAccountIndex].getGroupCount() + 1 );
                    productTypesArray[spendingAccountIndex].setTotalGrossPremiums( productTypesArray[spendingAccountIndex].getTotalGrossPremiums() + g.getGrossPremiums() );
                    productTypesArray[spendingAccountIndex].setTotalBbdAdminFee( productTypesArray[spendingAccountIndex].getTotalBbdAdminFee() + g.getBbdAdminFee() );
                    productTypesArray[spendingAccountIndex].setTotalAdvisorCommissions( productTypesArray[spendingAccountIndex].getTotalAdvisorCommissions() + g.getAdvisorCommission() );
                    productTypesArray[spendingAccountIndex].setTotalNetPremiums( productTypesArray[spendingAccountIndex].getTotalNetPremiums() + g.getNetPremiums() );
                    break;
            }
        }
        return new ArrayList<>(Arrays.asList(productTypesArray ) );
    }
}
