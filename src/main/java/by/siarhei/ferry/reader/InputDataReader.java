package by.siarhei.ferry.reader;

import by.siarhei.ferry.exception.InvalidInputFilePathException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class InputDataReader {
    // TODO: 11.12.2019 rename
    private static final String DEFAULT_INPUT_FILE = "input/input.txt";
    private static final Logger logger = LogManager.getLogger();

    public List<String> readData() throws InvalidInputFilePathException {
        logger.info("The input was obtained from the default source");
        return readData(DEFAULT_INPUT_FILE);
    }

    public List<String> readData(String fileFolder) throws InvalidInputFilePathException {
        Path path = Paths.get(fileFolder);
        try (BufferedReader br = Files.newBufferedReader(path)) {
            return br.lines().collect(Collectors.toList());

        } catch (IOException e) {
            throw new InvalidInputFilePathException("File cannot be opened or does not exists", e);
        }
    }
}
