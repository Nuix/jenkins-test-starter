package com.nuix.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class NuixTools {
    private static final Logger LOG = LoggerFactory.getLogger(NuixTools.class);

    private NuixTools() {

    }

    public static void banner() {
        LOG.info("Java Version: {}", System.getProperty("java.version"));
        LOG.info("libdir: {}", System.getProperty("nuix.libdir"));
        LOG.info("logdir: {}", System.getProperty("nuix.logdir"));
    }
}
