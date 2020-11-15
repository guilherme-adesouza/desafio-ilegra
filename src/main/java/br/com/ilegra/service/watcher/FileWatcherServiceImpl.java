package br.com.ilegra.service.watcher;

import br.com.ilegra.domain.SalesData;
import br.com.ilegra.service.reader.FileReaderFactory;
import br.com.ilegra.service.writer.FileWriterService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.*;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.OVERFLOW;

@Component("fileWatcherService")
public class FileWatcherServiceImpl implements FileWatcherService {

    private static final Logger logger = LogManager.getLogger(FileWatcherServiceImpl.class);

    private final String watchDir;
    private final FileWriterService fileWriterService;

    @Autowired
    public FileWatcherServiceImpl(@Value("${app.watch.dir}") String watchDir,
                                  FileWriterService fileWriterService) {
        this.watchDir = watchDir;
        this.fileWriterService = fileWriterService;
    }

    @Override
    public void run() {
        if (watchDir == null || watchDir.equals("")) {
            logger.error("Watch dir [{}] invalid! Check your config properties", watchDir);
            throw new RuntimeException();
        }

        Path dir = Path.of(watchDir);
        if (!Files.exists(dir)) {
            logger.error("Watch dir [{}] not exists. Aborting watchService job...", watchDir);
            throw new RuntimeException();
        }

        WatchService watcher = createWatcher(dir);
        if (watcher == null) {
            throw new RuntimeException();
        }

        for (;;) {
            WatchKey key;
            logger.debug("Waiting event...");
            try {
                key = watcher.take();
                logger.debug("New event catched");
            } catch (InterruptedException x) {
                logger.error("Error catching event: ", x);
                return;
            }

            for (WatchEvent<?> event: key.pollEvents()) {
                logger.debug("Processing event pool...");
                WatchEvent.Kind<?> kind = event.kind();
                if (kind == OVERFLOW) {
                    continue;
                }

                WatchEvent<Path> ev = (WatchEvent<Path>) event;
                Path filename = ev.context();
                Path filePath = resolveFile(dir, filename);
                if (filePath == null) continue;
                final SalesData salesData = FileReaderFactory.create().processFile(filePath);
                fileWriterService.generateFile(salesData, filename.getFileName());
            }
            boolean valid = key.reset();
            logger.debug("Valid key: [{}]", valid);
            if (!valid) {
                break;
            }
        }
    }

    private WatchService createWatcher(Path dir) {
        try {
            final WatchService service = FileSystems.getDefault().newWatchService();
            dir.register(service, ENTRY_CREATE);
            return service;
        } catch (IOException e) {
            logger.error("Error on create WatchService instance: ", e);
            return null;
        }
    }

    private Path resolveFile(Path dir, Path filename) {
        Path child = null;
        try {
            child = dir.resolve(filename);
            if (!Files.probeContentType(child).equals("text/plain")) {
                logger.error("New file [{}] is not a plain text fil", filename);
                return null;
            }
        } catch (IOException ex) {
            logger.error("Error resolving file [{}]: ", filename, ex);
            return null;
        }
        return child;
    }
}
