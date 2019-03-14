package com.cristi.mentool.mentool.domain.mentor;

import org.assertj.core.api.Assertions;
import org.junit.Ignore;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static com.cristi.mentool.mentool.domain.RomanianDateTimeFormatter.ROMANIAN_FORMATTER;
import static org.assertj.core.api.Assertions.assertThat;

public class ZonedDateTimeTest {


    @Test
    @Ignore
    public void how_zonedDate_is_convert_to_local_date_time_and_back() {
        LocalDateTime someDate = LocalDateTime.parse("23.01.2018 12:00", ROMANIAN_FORMATTER);
        ZonedDateTime bucharestDate = someDate.atZone(ZoneId.of("Europe/Bucharest"));
        assertThat(bucharestDate.toLocalDateTime()).isEqualTo(someDate);
        ZonedDateTime parisTime = ZonedDateTime.of(bucharestDate.toLocalDateTime(), ZoneId.of("Europe/Paris"));
        LocalDateTime parisLocalDateTime = parisTime.toLocalDateTime();
        System.out.println(someDate);
        System.out.println(bucharestDate.toInstant());
        System.out.println(parisTime.toInstant());
        assertThat(parisLocalDateTime).isEqualTo(someDate);
        assertThat(parisTime.toInstant()).isEqualTo(bucharestDate.toInstant());
    }

    @Test
    public void how_dates_are_compared() {
        LocalDateTime someDate = LocalDateTime.parse("2017-05-23T00:00");
        Assertions.assertThat(LocalDateTime.MAX).isAfter(someDate);
    }
}
