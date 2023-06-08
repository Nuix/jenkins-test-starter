package com.nuix.examples;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nuix.Case;
import nuix.SimpleCase;
import nuix.Utilities;
import nuix.engine.Engine;

import com.nuix.licensing.EngineFactory;
import com.nuix.tools.NuixTools;

public class DefaultFileProcessing {
    private static final Logger LOG = LoggerFactory.getLogger(DefaultFileProcessing.class);

    public static void main(String[] args) throws IOException {
        NuixTools.banner();
        try (Engine engine = EngineFactory.acquireLicense()) {

            Utilities u = engine.getUtilities();

            final String casePath = System.getProperty("casePath");
            final String rawData = System.getProperty("rawData");
            try (Case c = u.getCaseFactory().open(casePath)) {
                LOG.info("Opening case: {}", c.getLocation());

                if (!c.isCompound()) {
                    SimpleCase caze = (SimpleCase) c;
                    var processor = caze.createProcessor();
                    processor.setProcessingProfile("Default");
                    var container = processor.newEvidenceContainer(UUID.randomUUID().toString());
                    container.addFile(rawData);
                    container.save();
                    processor.whenItemProcessed(callback -> LOG.info("Processing: {}", String.join(File.separator, callback.getPath())));
                    processor.process();
                }
            }
        }
    }
}
