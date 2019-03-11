/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Utilities;

/**
 *
 * @author craig.deacon
 */
public class DatabaseUtilities
{
    private static final String WILDCARD = "%_%";
    private static final String REPLACE = "_";

    public static String getFilterWithWildcards( String filter )
    {
        String search = null;

        if ( filter != null && !filter.isEmpty() )
        {
            search = WILDCARD.replace( REPLACE, filter );
        }

        return search;
    }
}
