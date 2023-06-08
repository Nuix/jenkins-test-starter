package com.nuix.examples;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nuix.engine.Engine;

import com.nuix.licensing.EngineFactory;
import com.nuix.tools.NuixTools;

public class EngineVersion {
    private static final Logger LOG = LoggerFactory.getLogger(EngineVersion.class);

    public String getVersion() {
        Engine engine = EngineFactory.acquireLicense();
        final String version = engine.getVersion();
        engine.close();
        return version;
    }

    public static void main(String[] args) {
        NuixTools.banner();
        EngineVersion app = new EngineVersion();
        LOG.info("Engine version: {}", app.getVersion());
    }
}
