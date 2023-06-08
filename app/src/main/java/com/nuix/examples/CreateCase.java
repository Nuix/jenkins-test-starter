package com.nuix.examples;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nuix.Case;
import nuix.Utilities;
import nuix.engine.Engine;

import com.nuix.licensing.EngineFactory;
import com.nuix.tools.NuixTools;

public class CreateCase {
    private static final Logger LOG = LoggerFactory.getLogger(CreateCase.class);

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
