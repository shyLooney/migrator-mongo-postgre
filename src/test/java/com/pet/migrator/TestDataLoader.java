package com.pet.migrator;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pet.migrator.mongo.house.model.HouseDoc;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class TestDataLoader {
    public static List<HouseDoc> load(String dir) {
        List<HouseDoc> houseDocs = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();

        try (Stream<Path> file = Files.walk(Path.of(dir), 1)) {
            file.map(Path::toFile)
                    .filter(File::isFile)
                    .forEach(item -> {
                        try {
                            houseDocs.add(mapper.readValue(item, HouseDoc.class));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return houseDocs;
    }
}
