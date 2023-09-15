package com.nuix.examples;

import java.io.IOException;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import nuix.Case;
import nuix.Utilities;
import nuix.engine.Engine;

import com.nuix.licensing.EngineFactory;
import com.nuix.tools.NuixTools;

public class CreateCase {
    private static final Logger LOG = LogManager.getLogger(CreateCase.class);

    public static void main(String[] args) throws IOException {
        NuixTools.banner();
        try (Engine engine = EngineFactory.acquireLicense()) {

            Utilities u = engine.getUtilities();

            final String casePath = System.getProperty("casePath");
            try (Case caze = u.getCaseFactory().create(casePath)) {
                LOG.info("Case created: {}", caze.getLocation());
            }
        }
    }
}
