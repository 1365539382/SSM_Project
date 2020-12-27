package nuc.zy.util;

import org.springframework.beans.propertyeditors.PropertiesEditor;

import java.text.ParseException;
import java.util.Date;

public class DateStringEditor extends PropertiesEditor {
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        try {
            Date date = DateUtils.stringToDate(text, "yyyy-MM-dd HH:mm");
            super.setValue(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
