package com.pathways.dev.trail1;

import com.pathways.dev.trail1.model.Event;
import com.pathways.dev.trail1.model.Pathway;
import com.pathways.dev.trail1.model.Group;
import com.pathways.dev.trail1.model.GroupRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Collections;
import java.util.stream.Stream;

@Component
class Initializer implements CommandLineRunner {

    private final GroupRepository repository;

    public Initializer(GroupRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... strings) {
        Stream.of("Software Engineer - Frontend", "Software Engineer - Backend", "Software Engineer - Fullstack",
                "Data analyst").forEach(name ->
                repository.save(new Group(name))
        );

        Group djug = repository.findByName("Software Engineer - Frontend");
        Event e = Event.builder().title("Micro Frontends for Java Developers")
                .description("JHipster now has microfrontend support!")
                .date(Instant.parse("2022-09-13T17:00:00.000Z"))
                .build();

        Pathway p = Pathway.builder().title("Backend Software Engineer")
                    .description("Learning Backend software tech")
                    .build();
        djug.setEvents(Collections.singleton(e));
        repository.save(djug);

        repository.findAll().forEach(System.out::println);
    }
}