package com.tuygun.sandbox.other.ocajp8.dates;

import java.time.*;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.FormatStyle;
import java.time.temporal.*;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;

public class DateTests {
    public static void main(String[] args) {
        //LocalDate, LocalTime, and LocalDateTime are without Zone info in the ISO-8601 calendar system
        //testLocalDate();
        //testLocalTime();
        //testLocalDateTime();
        //testInstant();
        //testPeriod();
        //testDuration();
        //testZoneIds();
        //testOffsetTime();
        //testOffsetDateTime();
        //testZonedDateTime();
        //testDateTimeFormatter();
        //testDayLightSaving();
        //testDifferenceBetweenPeriodAndDuration();
        //testTemporalUnits();
        //testAdjustInto();
    }

    private static void testAdjustInto() {
        LocalDate localDate = LocalDate.of(2011, 1,2);
        Year year = Year.of(2015);

        //The following of these 2 are equivalent.
        Temporal temporal1 = localDate.adjustInto(year.atDay(1));
        System.out.println(temporal1);//2011-01-02

        Temporal temporal2 = year.atDay(1).with(localDate);
        System.out.println(temporal2);//2011-01-02
    }

    private static void testTemporalUnits() {
        LocalDateTime localDateTime = LocalDateTime.of(2018, 6, 15, 20, 26, 00);
        localDateTime= localDateTime.
                minus(1, ChronoUnit.DECADES).
                plus(11, ChronoUnit.YEARS).
                plus(2, ChronoUnit.HOURS);
        System.out.println(localDateTime);//2019-06-15T22:26
    }

    private static void testDifferenceBetweenPeriodAndDuration() {
        //The only timeunit which is available for both period and duration is day
        //A period of one day is a conceptual day maintaining local time and ignoring daylight saving changes
        //A duration of one day is exactly 24 hours
        Period oneDayPeriod = Period.ofDays(1);
        Duration oneDayDuration = Duration.ofDays(1);

        ZoneId zoneNewyork = ZoneId.of("America/New_York");
        LocalDateTime localDateTime = LocalDateTime.of(2018, 3, 10, 12, 0, 0);
        ZonedDateTime referenceTime = ZonedDateTime.of(localDateTime, zoneNewyork);

        ZonedDateTime oneDayPeriodPlus = referenceTime.plus(oneDayPeriod);
        ZonedDateTime oneDayDurationPlus = referenceTime.plus(oneDayDuration);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm:ss");
        System.out.println(dateTimeFormatter.format(oneDayPeriodPlus));//2018-03-11 12-00:00
        System.out.println(dateTimeFormatter.format(oneDayDurationPlus));//2018-03-11 13-00:00
    }

    private static void testDayLightSaving() {
        //02 11 March 2018 -> daylight saving for chicago
        LocalDateTime localDateTime = LocalDateTime.of(2018, 3, 11, 2, 30, 0);
        ZoneId zoneId = ZoneId.of("America/Chicago");
        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, zoneId);

        //It will print 3 as, between [02 and 03] it is day light saving. Between 02 and 03, the hour 2 will be set as 3
        //as shown in the output.
        System.out.println("Hour: " + zonedDateTime.getHour());//Hour: 3
    }

    private static void testDateTimeFormatter() {
        LocalDateTime now = LocalDateTime.now();
        ZoneId zone = ZoneId.of("Europe/Istanbul");
        ZonedDateTime zoneDateTime = ZonedDateTime.of(now, zone);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println(dateTimeFormatter.format(zoneDateTime));//2019-06-17 11:15:42
    }

    private static void testZonedDateTime() {
        LocalDateTime localDateTime = LocalDateTime.of(2019, 6, 15, 18, 32, 0);
        ZoneId zone = ZoneId.of("America/Chicago");
        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, zone);
        System.out.println(zonedDateTime);//2019-06-15T18:32-05:00[America/Chicago]
    }

    private static void testOffsetDateTime() {
        LocalDateTime localDateTime = LocalDateTime.of(2019, 6, 15, 17, 16, 0);
        LocalDate localDate = LocalDate.of(2019, 6, 15);
        LocalTime localTime = LocalTime.of(16, 16, 0);
        ZoneOffset zone1 = ZoneOffset.of("+3");
        ZoneOffset zone2 = ZoneOffset.of("+2");
        OffsetDateTime fromDateTime = OffsetDateTime.of(localDateTime, zone1);
        System.out.println(fromDateTime);//2019-06-15T17:16+03:00
        OffsetDateTime toDateTime = OffsetDateTime.of(localDate, localTime, zone2);
        System.out.println(toDateTime);//2019-06-15T16:16+02:0

        LocalDateTime tempDateTime = LocalDateTime.from(fromDateTime);

        long years = tempDateTime.until(toDateTime, ChronoUnit.YEARS);
        tempDateTime = tempDateTime.plusYears(years);

        long months = tempDateTime.until(toDateTime, ChronoUnit.MONTHS);
        tempDateTime = tempDateTime.plusMonths(months);

        long days = tempDateTime.until(toDateTime, ChronoUnit.DAYS);
        tempDateTime = tempDateTime.plusDays(days);


        long hours = tempDateTime.until( toDateTime, ChronoUnit.HOURS);
        tempDateTime = tempDateTime.plusHours(hours);

        long minutes = tempDateTime.until( toDateTime, ChronoUnit.MINUTES);
        tempDateTime = tempDateTime.plusMinutes(minutes);

        long seconds = tempDateTime.until(toDateTime, ChronoUnit.SECONDS);

        System.out.println( years + " years " + months + " months " + days + " days " + hours + " hours " +
                minutes + " minutes " + seconds + " seconds.");//0 years 0 months 0 days -1 hours 0 minutes 0 seconds.


    }

    private static void testOffsetTime() {
        LocalTime localTime = LocalTime.of(15, 30);
        ZoneOffset zoneOffset = ZoneOffset.of("+6");
        OffsetTime offsetTime = OffsetTime.of(localTime, zoneOffset);
        System.out.println(offsetTime);//15:30+06:00

        OffsetTime offsetTime1 = offsetTime.plusHours(2).minusMinutes(12);
        System.out.println(offsetTime1);//17:18+06:00

        ZoneOffset zoneOffset1 = ZoneOffset.of("+7");
        OffsetTime offsetTime2 = offsetTime1.withOffsetSameInstant(zoneOffset1);
        System.out.println(offsetTime2);//18:18+07:00
    }

    private static void testZoneIds() {
        ZoneId id1 = ZoneId.of("+03:00");
        ZoneId id2 = ZoneId.of("America/Chicago");

        Set<String> availableZoneIds = ZoneId.getAvailableZoneIds();
        TreeSet<String> zoneIdsSorted = new TreeSet<>(availableZoneIds);
        zoneIdsSorted.forEach(System.out::println);
    }

    private static void testDuration() {
        Duration duration = Duration.parse("-PT3H32M");
        LocalDateTime now = LocalDateTime.now();
        System.out.println("now: " + now);//now: 2019-06-17T10:38:57.048
        LocalDateTime diff = now.plus(duration);
        System.out.println(diff);//2019-06-17T07:06:57.048
    }

    private static void testPeriod() {
        Period period = Period.of(4, 5, 30);
        Period period1 = period.minusDays(11);

        LocalDate localDate = LocalDate.of(1981, Month.MARCH, 3);
        LocalDate plus = localDate.plus(period1);
        System.out.println(plus);//1985-08-22
    }

    private static void testInstant() {
        Instant now = Instant.now();
        System.out.println(now);//2019-06-17T07:37:13.550Z

        Instant instant = Instant.ofEpochMilli(1500000000000L);
        System.out.println(instant);//2017-07-14T02:40:00Z

        System.out.println(now.isAfter(instant));//true
    }

    private static void testLocalDateTime() {
        LocalDate localDate = LocalDate.parse("2019-06-15");
        LocalTime localTime = LocalTime.of(15, 36, 47);
        LocalDateTime localDateTime = localDate.atTime(localTime);
        System.out.println(localDateTime);//2019-06-15T15:36:47

        LocalDateTime localDateTime2 = localDateTime.plusDays(16).minusHours(2).plusMinutes(14).minusSeconds(17);
        System.out.println(localDateTime2);//2019-07-01T13:50:30

        LocalDate localDate1 = LocalDate.from(localDateTime2);
        System.out.println(localDate1);//2019-07-01

        LocalTime localTime1 = LocalTime.from(localDateTime2);
        System.out.println(localTime1);//13:50:30
    }

    private static void testLocalTime() {
        LocalTime localTime = LocalTime.parse("20:30:53");
        System.out.println(localTime);//20:30:53

        LocalTime localTime1 = localTime.with(ChronoField.MINUTE_OF_HOUR, 15);
        System.out.println(localTime1);//20:15:53

        System.out.println(localTime1.isAfter(localTime));//false

        LocalTime localTime2 = localTime1.withSecond(33);
        System.out.println(localTime2);//20:15:33

        System.out.println("seconds: " + localTime2.get(ChronoField.SECOND_OF_MINUTE));//seconds: 33
    }

    private static void testLocalDate() {
        LocalDate localdate1 = LocalDate.of(2019, 6, 15);
        System.out.println(localdate1);//2019-06-15

        LocalDate localDate2 = localdate1.withMonth(4);
        System.out.println(localDate2);//2019-04-15

        LocalDate localDate3 = localDate2.withDayOfYear(32);
        System.out.println(localDate3);//2019-02-01

        LocalDate localDate4 = localDate3.plusDays(202);
        System.out.println(localDate4);//2019-08-22

        LocalDate localDate5 = localDate4.minusDays(172);
        System.out.println(localDate5);//2019-03-03

        System.out.println("localDate5 day of month: " + localDate5.getDayOfMonth());

        LocalDate mine = LocalDate.of(1981, 3, 3);
        System.out.println("mine is leap year: " + mine.isLeapYear());
        LocalDate yours = LocalDate.of(1985, 8, 22);
        System.out.println("yours is leap year: " + yours.isLeapYear());
        Period between = Period.between(mine, yours);
        System.out.println(between.getYears() + " years, " + between.getMonths() + " months, " + between.getDays() + " days");
        //4 years, 5 months, 19 days
    }
}
