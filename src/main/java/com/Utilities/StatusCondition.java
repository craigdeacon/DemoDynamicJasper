/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Utilities;

import ar.com.fdvs.dj.domain.CustomExpression;
import ar.com.fdvs.dj.domain.entities.Entity;
import ar.com.fdvs.dj.domain.entities.conditionalStyle.ConditionStyleExpression;

import java.util.Map;

/**
 * Special CustomExpression that complements very well with Conditionl Styles.
 */
public class StatusCondition extends ConditionStyleExpression implements CustomExpression {

	private static final long serialVersionUID = Entity.SERIAL_VERSION_UID;
	
	private Float min;
	private Float max;

	private int mode = 0; // 0: x < min, 1: min < x < max, 2: x > max

	public StatusCondition(Float min, Float max) {
		this.min = min;
		this.max = max;

		if (min != null && max == null)
			mode = 0;
		else if (min != null && max != null)
			mode = 1;
		else if (min == null && max != null)
			mode = 2;
	}

        @Override
	public Object evaluate(Map fields, Map variables, Map parameters) {
		Object value = getCurrentValue();
		if (value == null)
			return null;

		Number number = (Number)value;

            switch (mode)
            {
                case 0:
                    return (min > number.floatValue());
                case 1:
                    return min <= number.floatValue()&& max > number.floatValue();
                default:
                    return max <= number.floatValue();
            }
	}

        @Override
	public String getClassName() {
		return Boolean.class.getName();
	}

}

