package com.course.rabbitmqproducer;

import com.course.rabbitmqproducer.entity.Picture;
import com.course.rabbitmqproducer.producer.PictureProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
public class RabbitmqProducerApplication implements CommandLineRunner {

	@Autowired
	private PictureProducer pictureProducer;
	// picture valid sources
	private final List<String> SOURCES = Arrays.asList("mobile", "web");
	// picture valid types
	private final List<String> TYPES = Arrays.asList("jpg", "png", "svg");

	public static void main(String[] args) {
		SpringApplication.run(RabbitmqProducerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		for (int i = 0; i < 10; i++) {
			Picture p = new Picture();
			p.setName("Picture" + i);

			// random size between 1-9999
			p.setSize(ThreadLocalRandom.current().nextLong(1, 10000));

			// source from array, get from list, index 0-1
			p.setSource(SOURCES.get(i % SOURCES.size()));

			// type from array, get from list, index 0-2
			p.setType(TYPES.get(i % TYPES.size()));

			pictureProducer.sendMessage(p);
		}
	}

}
