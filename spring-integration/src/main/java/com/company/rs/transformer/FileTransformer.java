package com.company.rs.transformer;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

@Slf4j
public class FileTransformer {

    public String transformFile(String directory) throws IOException {
        return Paths.get(directory).getFileName().toString();
/*        return Files.list(Paths.get(directory))
                .map(path -> path.getFileName().toString())
                .collect(Collectors.joining("\n"));*/
    }

}
