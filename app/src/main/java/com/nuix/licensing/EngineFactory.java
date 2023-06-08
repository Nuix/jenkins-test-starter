package com.nuix.licensing;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nuix.LicenceException;
import nuix.engine.AvailableLicence;
import nuix.engine.Engine;
import nuix.engine.GlobalContainer;
import nuix.engine.GlobalContainerFactory;
import nuix.engine.LicenceSource;

public class EngineFactory {
    private static final Logger LOG = LoggerFactory.getLogger(EngineFactory.class);
    private static final GlobalContainer GLOBAL_CONTAINER  = GlobalContainerFactory.newContainer();

    private EngineFactory() { }

    public static Engine acquireLicense() {
        final String username = System.getProperty("nuix.license.server.username");
        final String password = System.getProperty("nuix.license.server.password");
        final String licenseShortName = System.getProperty("nuix.license.shortname");
        final String workerCount = System.getProperty("workerCount");

        LOG.info("Acquiring license {} for user {}", licenseShortName, username);
        List<String> userDataDirs = Arrays.asList(System.getProperty("nuix.engine.userDataDirs").split(","));

        return EngineFactory.getEngineForUser(
                username,
                password,
                licenseShortName,
                userDataDirs,
                Integer.parseInt(workerCount));
    }

    public static Engine getEngineForUser(String username, String password, String licenseShortName, List<String> userDataDirs, int workerCount) {
        Engine engine = GLOBAL_CONTAINER.newEngine(Map.of("user", username, "userDataDirs", userDataDirs));
        engine.whenAskedForCredentials(callback -> {
            callback.setUsername(username);
            callback.setPassword(password);
            LOG.info("Authenticating user: {}", username);
        });
        engine.whenAskedForCertificateTrust(callback -> callback.setTrusted(true));

        LicenceSource licenseSource = engine.getLicensor().findLicenceSourcesStream(Map.of("sources", System.getProperty("nuix.license.source").split(",")))
                .filter(source -> System.getProperty("nuix.registry.servers").contains(
                        source.getLocation()
                                .replace("http://", "")
                                .replace("https://", "")))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Could not find license server: " + System.getProperty("nuix.registry.servers")));

        Optional<AvailableLicence> license = getAvailableLicense(licenseSource, licenseShortName);

        license.ifPresentOrElse(l -> {
            try {
                l.acquire(Map.of("workerCount", workerCount));
            } catch (LicenceException ex) {
                LOG.error("Failed to acquire license: {} for user: {}", licenseShortName, username);
            }
        }, () -> {
            throw new RuntimeException("License " + licenseShortName + "  is not available.");
        });

        LOG.info("Engine acquired for user: {} with license {}", username, licenseShortName);
        return engine;
    }

    public static Optional<AvailableLicence> getAvailableLicense(LicenceSource licenseSource, String licenseShortName) {
        for(AvailableLicence license: licenseSource.findAvailableLicences()) {
            if (licenseShortName.equals(license.getShortName())) {
                return Optional.of(license);
            }
        }

        return Optional.empty();
    }

}
