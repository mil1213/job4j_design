package ru.job4j.io.duplicates;

import ru.job4j.set.Set;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.stream.Collectors;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    Map<FileProperty, List<Path>> allFiles = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty current = new FileProperty(file.toFile().length(), file.getFileName().toString());
        if (!allFiles.containsKey(current)) {
            allFiles.put(current, new ArrayList<>());
        }
            allFiles.get(current).add(file.toAbsolutePath());
        return super.visitFile(file, attrs);
    }

    public void getFiles() {
        allFiles.entrySet().stream()
                .filter(n -> n.getValue().size() > 1)
                .forEach(x -> {
                    System.out.println(x.getKey().getName() + " - " + x.getKey().getSize());
                    for (Path path : x.getValue()) {
                        System.out.println(path);
                    }
                });
    }
}
