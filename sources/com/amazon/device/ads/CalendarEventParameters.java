package com.amazon.device.ads;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

class CalendarEventParameters {
    public static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mmZZZZZ";
    public static final List<String> DATE_FORMATS = Collections.unmodifiableList(new ArrayList<String>() {
        {
            add(CalendarEventParameters.DATE_FORMAT);
            add("yyyy-MM-dd'T'HH:mmZ");
            add("yyyy-MM-dd'T'HH:mm");
            add("yyyy-MM-dd");
        }
    });
    private String description;
    private Date end;
    private String location;
    private Date start;
    private String summary;

    public CalendarEventParameters(String description2, String location2, String summary2, String start2, String end2) {
        if (StringUtils.isNullOrEmpty(description2)) {
            throw new IllegalArgumentException("No description for event");
        }
        this.description = description2;
        this.location = location2;
        this.summary = summary2;
        if (StringUtils.isNullOrEmpty(start2)) {
            throw new IllegalArgumentException("No start date for event");
        }
        this.start = convertToDate(start2);
        if (StringUtils.isNullOrEmpty(end2)) {
            this.end = null;
        } else {
            this.end = convertToDate(end2);
        }
    }

    public String getDescription() {
        return this.description;
    }

    public String getLocation() {
        return this.location;
    }

    public String getSummary() {
        return this.summary;
    }

    public Date getStart() {
        return this.start;
    }

    public Date getEnd() {
        return this.end;
    }

    private Date convertToDate(String dateTimeString) {
        Date dateTime = null;
        for (String format : DATE_FORMATS) {
            try {
                dateTime = new SimpleDateFormat(format, Locale.US).parse(dateTimeString);
                break;
            } catch (ParseException e) {
            }
        }
        if (dateTime != null) {
            return dateTime;
        }
        throw new IllegalArgumentException("Could not parse datetime string " + dateTimeString);
    }
}
