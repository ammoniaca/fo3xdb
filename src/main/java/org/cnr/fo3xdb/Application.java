package org.cnr.fo3xdb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {

		ZoneId zone = ZoneId.of("Europe/Rome");
		LocalDate date = LocalDate.parse("2021-02-11");
		OffsetDateTime odt = date.atStartOfDay(zone)
				.toOffsetDateTime();

		Instant instant = Instant.parse("2021-02-11T13:00:00Z");
		OffsetDateTime xx = instant.atZone(ZoneId.of("Europe/Rome")).toOffsetDateTime();


		SpringApplication.run(Application.class, args);
	}

}
