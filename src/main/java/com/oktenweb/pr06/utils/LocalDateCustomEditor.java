package com.oktenweb.pr06.utils;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;

@Component
public class LocalDateCustomEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        LocalDate date = new LocalDate(text);
        setValue(date);
    }
}
