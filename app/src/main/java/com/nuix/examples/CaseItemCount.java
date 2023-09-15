package com.nuix.examples;

import java.io.IOException;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import nuix.Case;
import nuix.Utilities;
import nuix.engine.Engine;

import com.nuix.licensing.EngineFactory;
import com.nuix.tools.NuixTools;

public class CaseItemCount {
    private static final Logger LOG = LogManager.getLogger(CaseItemCount.class);

    public static void main(String[] args) throws IOException {
        NuixTools.banner();
        try (Engine engine = EngineFactory.acquireLicense()) {

            Utilities u = engine.getUtilities();

            final String casePath = System.getProperty("casePath");
            try (Case c = u.getCaseFactory().open(casePath)) {
                LOG.info("Opening case: {}", c.getLocation());

                long itemCount = c.count("");
                LOG.info("Case {} has {} items", c.getName(), itemCount);
            }
        }
    }
}
