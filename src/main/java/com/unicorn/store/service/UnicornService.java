package com.unicorn.store.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unicorn.store.data.UnicornRepository;
import com.unicorn.store.exceptions.ResourceNotFoundException;
import com.unicorn.store.model.Unicorn;

@Service
public class UnicornService {
    private final UnicornRepository unicornRepository;
    private static final Logger logger = LoggerFactory.getLogger(UnicornService.class);

    public UnicornService(UnicornRepository unicornRepository) {
        this.unicornRepository = unicornRepository;
    }

    @Transactional
    public Unicorn createUnicorn(Unicorn unicorn) {
        logger.debug("Creating unicorn: {}", unicorn);
        if (unicorn.getId() == null) {
            unicorn.setId(UUID.randomUUID().toString());
        }
        validateUnicorn(unicorn);

        var savedUnicorn = unicornRepository.save(unicorn);

        logger.debug("Created unicorn with ID: {}", savedUnicorn.getId());
        return savedUnicorn;
    }

    public List<Unicorn> getAllUnicorns() {
        logger.debug("Retrieving all unicorns");
        return StreamSupport
                .stream(unicornRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<Unicorn> createUnicorns(List<Unicorn> unicorns) {
        return unicorns.stream()
                .map(this::createUnicorn)
                .collect(Collectors.toList());
    }

    @Transactional
    public Unicorn updateUnicorn(Unicorn unicorn, String unicornId) {
        logger.debug("Updating unicorn with ID: {}", unicornId);
        validateUnicorn(unicorn);

        // Verify existence
        getUnicorn(unicornId);

        unicorn.setId(unicornId);
        var savedUnicorn = unicornRepository.save(unicorn);

        logger.debug("Updated unicorn with ID: {}", unicornId);
        return savedUnicorn;
    }

    public Unicorn getUnicorn(String unicornId) {
        logger.debug("Retrieving unicorn with ID: {}", unicornId);
        return unicornRepository.findById(unicornId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Unicorn not found with ID: %s", unicornId)));
    }

    @Transactional
    public void deleteUnicorn(String unicornId) {
        logger.debug("Deleting unicorn with ID: {}", unicornId);
        var unicorn = getUnicorn(unicornId);

        unicornRepository.delete(unicorn);

        logger.debug("Deleted unicorn with ID: {}", unicornId);
    }

    private void validateUnicorn(Unicorn unicorn) {
        if (unicorn == null) {
            throw new IllegalArgumentException("Unicorn cannot be null");
        }
        // Add additional validation rules as needed
    }
}
