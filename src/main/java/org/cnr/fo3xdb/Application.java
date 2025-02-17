package org.cnr.fo3xdb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {

		Instant instant = Instant.parse("2021-02-11T13:00:00Z");
		OffsetDateTime xx = instant.atZone(ZoneId.of("Europe/Rome")).toOffsetDateTime();


		SpringApplication.run(Application.class, args);
	}

}
