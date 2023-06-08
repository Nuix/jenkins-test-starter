package com.nuix.examples;

import nuix.engine.Engine;
import com.nuix.licensing.EngineFactory;
import com.nuix.tools.NuixTools;

public class LicenseAcquisition {
    public static void main(String[] args) {
        NuixTools.banner();
        Engine engine = EngineFactory.acquireLicense();
        engine.close();
    }
}
