package us.jlitz.backuprestart;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Backup implements Runnable {

    private static final DateTimeFormatter defaultTimeStampFormat = new DateTimeFormatterBuilder()
            .append(DateTimeFormatter.ISO_LOCAL_DATE)
            .appendLiteral('T')
            .appendPattern("HHmmss")
            .appendLiteral('Z')
            .appendOffset("+HH", "+00")
            .toFormatter();

    private static final Logger LOG = Logger.getLogger(Backup.class.getName());
    private static final Path workingDirectory = Paths.get(".").toAbsolutePath();

    private Path srcDir;
    private Path destDir;
    private final DateTimeFormatter timeStampFormat;

    public Backup() {
        this(workingDirectory, workingDirectory, defaultTimeStampFormat);
    }

    public Backup(Path srcDir, Path destDir) {
        this(srcDir, destDir, defaultTimeStampFormat);
    }

    public Backup(Path srcDir, Path destDir, DateTimeFormatter timeStampFormat) {
        this.srcDir = srcDir;
        this.destDir = destDir.resolve((ZonedDateTime.now().format(timeStampFormat)));
        this.timeStampFormat = timeStampFormat;
    }

    @Override
    public void run() {
        try {
            Files.createDirectories(destDir);
            Files.walk(srcDir).forEach((Path p) -> {
                try {
                    Path dest = destDir.resolve(srcDir.relativize(p));
                    if (!Files.exists(dest)) {
                        Files.copy(p, dest);
                    }
                } catch (IOException ex) {
                    LOG.log(Level.SEVERE, null, ex);
                }
            });
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
    }
}
