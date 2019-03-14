package com.cristi.mentool.mentool.domain.mentor;

import java.time.LocalDateTime;

import static com.cristi.mentool.mentool.domain.RomanianDateTimeFormatter.ROMANIAN_FORMATTER;

public class ValidRomanianIntervalGenerator {
    public static final LocalDateTime START_TIME = LocalDateTime.parse("23.06.2015 15:00", ROMANIAN_FORMATTER);
    public static final LocalDateTime END_TIME = LocalDateTime.parse("23.07.2015 15:00", ROMANIAN_FORMATTER);
}