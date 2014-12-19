package com.rectuscorp.evetool.web.component.labels;

import org.apache.wicket.util.convert.converter.AbstractNumberConverter;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

/**
 * User: olivier
 * Date: 04/04/13
 * Time: 09:41
 * Project : mismacore
 */
public class DoubleCurrencyConverter extends AbstractNumberConverter {
    @Override
    public NumberFormat getNumberFormat(Locale locale) {
        return NumberFormat.getCurrencyInstance(Locale.FRANCE);
    }

    @Override
    protected Class getTargetType() {
        return Double.class;
    }

    @Override
    public Object convertToObject(String s, Locale locale) {
        // string to double.
        try {
            return getNumberFormat(locale).parse(s).doubleValue();
        } catch (ParseException ex) {
            throw new RuntimeException("exception when trying to parse currency value:" + ex.getMessage());
        }
    }

    @Override
    public String convertToString(Object value, Locale locale) {
        // double to string
        return getNumberFormat(locale).format(value);
    }

}
