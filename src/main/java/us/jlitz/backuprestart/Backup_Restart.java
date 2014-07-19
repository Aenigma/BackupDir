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

public class Backup_Restart extends JFrame {

    public static final String DATE_FORMAT_NOW = "yyyy-MM-dd HH-mm-ss";
    private static final DateTimeFormatter timeStampFormat = new DateTimeFormatterBuilder()
            .append(DateTimeFormatter.ISO_LOCAL_DATE)
            .appendLiteral('T')
            .appendPattern("HHmmss")
            .appendLiteral('Z')
            .appendOffset("+HH", "+00")
            .toFormatter();
    
    private static final Logger LOG = Logger.getLogger(Backup_Restart.class
            .getName());

    public static void main(String... args) {
        Path srcDir = Paths.get("C:/Users/kevin/arduino/");
        Path destDir = Paths.get("C:/Users/kevin/", ZonedDateTime.now().format(
                timeStampFormat));

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
