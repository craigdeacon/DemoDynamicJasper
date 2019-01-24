/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.secondReport.utilities;

import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.constants.Border;
import ar.com.fdvs.dj.domain.constants.Font;
import ar.com.fdvs.dj.domain.constants.HorizontalAlign;
import ar.com.fdvs.dj.domain.constants.Transparency;
import java.awt.Color;

/**
 *
 * @author craig.deacon
 */
public class ReportStyles
{

    public static Style LEFT;
    public static Style HEADER_STYLE;
    public static Style AMOUNT_STYLE;
    public static Style SUBTITLE_STYLE_PARENT;
    public static Style SUBTITLE_STYLE;
    public static Style BOLD;
    public static Style BLANK;

    public ReportStyles()
    {
    }

    public static void initStyles()
    {

        LEFT = new Style();
        LEFT.setHorizontalAlign( HorizontalAlign.LEFT );
      
        
        BLANK = new Style();
        BLANK.setBorder( Border.THIN());
        BLANK.setBlankWhenNull( true);
        
        
        BOLD = new Style();
        BOLD.setFont( Font.ARIAL_MEDIUM_BOLD );
        

        HEADER_STYLE = new Style();
        HEADER_STYLE.setBackgroundColor( new Color( 230, 230, 230 ) );
        HEADER_STYLE.setBorderBottom( Border.THIN() );
        HEADER_STYLE.setHorizontalAlign( HorizontalAlign.CENTER );
        HEADER_STYLE.setTransparency( Transparency.OPAQUE );

        AMOUNT_STYLE = new Style();
        AMOUNT_STYLE.setHorizontalAlign( HorizontalAlign.RIGHT );
        AMOUNT_STYLE.setPattern( "$0.00" );

        SUBTITLE_STYLE_PARENT = new Style( "subtitleParent" );
        SUBTITLE_STYLE_PARENT.setBackgroundColor( Color.GRAY );
        SUBTITLE_STYLE_PARENT.setTransparency( Transparency.OPAQUE );

        SUBTITLE_STYLE = Style.createBlankStyle( "subtitleStyle", "subtitleParent" );
        SUBTITLE_STYLE.setFont( Font.GEORGIA_SMALL_BOLD );
    }
}
