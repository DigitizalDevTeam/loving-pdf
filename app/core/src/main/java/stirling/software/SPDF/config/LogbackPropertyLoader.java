package stirling.software.SPDF.config;

import java.io.File;

import ch.qos.logback.core.spi.ContextAwareBase;
import ch.qos.logback.core.spi.PropertyDefiner;

public class LogbackPropertyLoader extends ContextAwareBase implements PropertyDefiner {

    @Override
    public String getPropertyValue() {
        try {
            String env = System.getenv("STIRLING_LOG_PATH");
            if (env != null && !env.isEmpty()) {
                File d = new File(env);
                if (!d.exists()) d.mkdirs();
                return d.getAbsolutePath();
            }

            // Default to ./logs inside working directory
            File defaultDir = new File(System.getProperty("user.dir", "."), "logs");
            if (!defaultDir.exists()) defaultDir.mkdirs();
            return defaultDir.getAbsolutePath();
        } catch (Exception e) {
            addError("Failed to determine log path", e);
            return System.getProperty("user.dir", ".");
        }
    }
}
