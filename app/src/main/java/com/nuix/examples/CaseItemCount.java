package com.nuix.examples;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nuix.Case;
import nuix.Utilities;
import nuix.engine.Engine;

import com.nuix.licensing.EngineFactory;
import com.nuix.tools.NuixTools;

public class CaseItemCount {
    private static final Logger LOG = LoggerFactory.getLogger(CaseItemCount.class);

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
