package com.nuix.tools;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


public class NuixTools {
    private static final Logger LOG = LogManager.getLogger(NuixTools.class);

    private NuixTools() {

    }

    public static void banner() {
        LOG.info("Java Version: {}", System.getProperty("java.version"));
        LOG.info("libdir: {}", System.getProperty("nuix.libdir"));
        LOG.info("logdir: {}", System.getProperty("nuix.logdir"));
    }
}
