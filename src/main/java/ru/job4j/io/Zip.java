package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path path : sources) {
                zip.putNextEntry(new ZipEntry(path.toString()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(path.toString()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void validate(ArgsName arg) {
        if (arg.size() != 3) {
            throw new IllegalArgumentException("Amount of arguments is not enough");
        }
        Path dir = Paths.get(arg.get("d"));
        if (!Files.isDirectory(dir) || !Files.exists(dir)) {
            throw new IllegalArgumentException("There's no directory");
        }
        if (!".class".equals(arg.get("e"))) {
            throw new IllegalArgumentException("Extension of excluding files is not correct");
        }
        if (!arg.get("o").endsWith(".zip")) {
            throw new IllegalArgumentException("Extension of Zip file is not correct");
        }
    }

    public static void main(String[] args) throws IOException {
        ArgsName arg = ArgsName.of(args);
        Zip zipFiles = new Zip();
        zipFiles.validate(arg);
        List<Path> paths = Search.search(Paths.get(arg.get("d")), p -> {
                    return !p.toFile().getName().endsWith(arg.get("e"));
                });
        zipFiles.packFiles(paths, new File(arg.get("o")));

        Zip zip = new Zip();
        zip.packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
        );
    }
}
