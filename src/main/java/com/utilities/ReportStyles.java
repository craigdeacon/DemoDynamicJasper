/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utilities;

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

    /**
     *
     */
    public static Style LEFT;

    /**
     *
     */
    public static Style RIGHT;

    /**
     *
     */
    public static Style HEADER_STYLE;

    /**
     *
     */
    public static Style AMOUNT_STYLE;

    /**
     *
     */
    public static Style SUBTITLE_STYLE_PARENT;

    /**
     *
     */
    public static Style SUBTITLE_STYLE;

    /**
     *
     */
    public static Style BOLD_RIGHT;

    /**
     *
     */
    public static Style BOLD_RIGHT_BLUE;

    /**
     *
     */
    public static Style BLANK;

    /**
     *
     */
    public static Style BOLD_LEFT;

    /**
     *
     */
    public static Style BOLD_LEFT_BLUE;

    /**
     *
     */
    public static Style BLUE_LEFT_GREY_BG;

    /**
     *
     */
    public static Style BLUE_LEFT_GREY_BG_BT_BORDER;

    /**
     *
     */
    public static Style BLUE_LEFT_GREY_BG_T_BORDER;

    /**
     *
     */
    public static Style LARGE;

    /**
     *
     */
    public static Style SMALL;

    /**
     *
     */
    public static Style SMALL_RIGHT;

    /**
     *
     */
    public ReportStyles()
    {
    }

    /**
     *
     */
    public static void initStyles()
    {

        LEFT = new Style();
        LEFT.setHorizontalAlign( HorizontalAlign.LEFT );
      
        RIGHT = new Style();
        RIGHT.setHorizontalAlign( HorizontalAlign.RIGHT );
        
        BLANK = new Style();
        BLANK.setBorder( Border.THIN());
        BLANK.setBlankWhenNull( true);
        
        
        BOLD_RIGHT = new Style();
        BOLD_RIGHT.setFont( Font.ARIAL_MEDIUM_BOLD );
        BOLD_RIGHT.setHorizontalAlign( HorizontalAlign.RIGHT );
        BOLD_RIGHT.setPattern( "$0.00" );
        
        BOLD_RIGHT_BLUE = new Style();
        BOLD_RIGHT_BLUE.setFont( Font.ARIAL_MEDIUM_BOLD );
        BOLD_RIGHT_BLUE.setHorizontalAlign( HorizontalAlign.RIGHT );
        BOLD_RIGHT_BLUE.setPattern( "$0.00" );
        BOLD_RIGHT_BLUE.setTextColor( Color.BLUE );
        
        BOLD_LEFT = new Style();
        BOLD_LEFT.setFont( Font.ARIAL_MEDIUM_BOLD );
        BOLD_LEFT.setHorizontalAlign( HorizontalAlign.LEFT );
        BOLD_LEFT.setPattern( "$0.00" );
        
        BOLD_LEFT_BLUE = new Style();
        BOLD_LEFT_BLUE.setFont( Font.ARIAL_MEDIUM_BOLD );
        BOLD_LEFT_BLUE.setHorizontalAlign( HorizontalAlign.LEFT );
        BOLD_LEFT_BLUE.setPattern( "$0.00" );
        BOLD_LEFT_BLUE.setTextColor( Color.BLUE );
        
        BLUE_LEFT_GREY_BG = new Style();
        BLUE_LEFT_GREY_BG.setHorizontalAlign( HorizontalAlign.LEFT );
        BLUE_LEFT_GREY_BG.setTextColor( Color.BLUE );
        BLUE_LEFT_GREY_BG.setBackgroundColor( new Color( 230, 230, 230 ) );
        BLUE_LEFT_GREY_BG.setTransparency( Transparency.OPAQUE );
        
        
        BLUE_LEFT_GREY_BG_T_BORDER = new Style();
        BLUE_LEFT_GREY_BG_T_BORDER.setHorizontalAlign( HorizontalAlign.LEFT );
        BLUE_LEFT_GREY_BG_T_BORDER.setTextColor( Color.BLUE );
        BLUE_LEFT_GREY_BG_T_BORDER.setBackgroundColor( new Color( 230, 230, 230 ) );
        BLUE_LEFT_GREY_BG_T_BORDER.setTransparency( Transparency.OPAQUE );
        BLUE_LEFT_GREY_BG_T_BORDER.setBorderTop( Border.THIN() );
        
        
        BLUE_LEFT_GREY_BG_BT_BORDER = new Style();
        BLUE_LEFT_GREY_BG_BT_BORDER.setHorizontalAlign( HorizontalAlign.LEFT );
        BLUE_LEFT_GREY_BG_BT_BORDER.setTextColor( Color.BLUE );
        BLUE_LEFT_GREY_BG_BT_BORDER.setBackgroundColor( new Color( 230, 230, 230 ) );
        BLUE_LEFT_GREY_BG_BT_BORDER.setTransparency( Transparency.OPAQUE );
        BLUE_LEFT_GREY_BG_BT_BORDER.setBorderBottom( Border.THIN() );
        BLUE_LEFT_GREY_BG_BT_BORDER.setBorderTop(Border.THIN() );
        
        
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
        SUBTITLE_STYLE.setTextColor( Color.BLUE );
        
        LARGE = new Style();
        LARGE.setFont( Font.ARIAL_BIG_BOLD );
        
        SMALL = new Style();
        SMALL.setFont( Font.ARIAL_SMALL );
        SMALL.setPattern( "$0.00" );
        SMALL.setStretchWithOverflow( false);
         
        SMALL_RIGHT = new Style();
        SMALL_RIGHT.setFont( Font.ARIAL_SMALL );
        SMALL_RIGHT.setPattern( "$0.00" );
        SMALL_RIGHT.setStretchWithOverflow( false);
        SMALL_RIGHT.setHorizontalAlign( HorizontalAlign.CENTER );
    }
}
