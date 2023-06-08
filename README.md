INTRODUCTION
------------

![Nuix Engine 9.10.x.x](https://img.shields.io/badge/Nuix%20Engine-9.10.x.x-green.svg)

This following code demonstrates how to use this project as a baseline for developing Nuix applications with the Java SDK.

REQUIREMENTS
------------

* Java SDK 11
* Nuix license
* Nuix engine

DIRECTORY STRUCTURE
-------------------

    .
    |-- app                         # Java application code.
        |-- build                   # Gradle build directory.
        |-- engine                  # Unzip the engine you want to use to this directory.
        |-- src             
            |-- main
                |-- java            # Application source.
                |-- resources       # Application resources.
            |-- test
                |-- java            # Test source.
                |-- resources       # Test resources.
        |-- build.gradle            # The gradle build file.
    |-- gradle
    |-- logs                        # The log directory.
    |-- gradle.properties           # The gradle properties file.
    |-- gradlew                     # The gradle executable.
    |-- settings.gradle             # Gradle settings file.
    |-- README.md                   # This README file.

CONFIGURATION
-------------

1. Download the Nuix engine from https://download.nuix.com/releases/engine/
2. Unzip the engine to the app/engine directory.  If the directory does not exist create the engine directory.
3. Copy `gradle.properties.example` to `gradle.properties` and update the file with the appropriate license server, 
username, password, and license source.

    ```java
    nuix.license.server.username=YOUR_LICENSE_SERVER_USERNAME
    nuix.license.server.password=YOUR_LICENSE_SERVER_PASSWORD
    nuix.license.shortname=LICENSE_SHORTNAME
    nuix.registry.servers=LICENSE_SERVER_URL
    nuix.license.source=LICENSE_SOURCE
    workerCount=2
    ```

### License Sources

Valid license sources include:
`['server', 'local-relay-server-local-users', 'local-relay-server-cls-users', 'cloud-server']`

BUILD
----------------

To build the project execute the following command from a command prompt.  By default the application will acquire a 
license as part of the unit tests.

`./gradlew clean build`

RUNNING
----------------

To run the application execute the following command from a command prompt.

`./gradlew run`

By default the `build.gradle` file will execute `mainClass = 'com.nuix.examples.LicenseAcquisition'` when running
the application.  To switch and execute a different main method you can override the `mainClassName` parameter.

`./gradlew -PmainClassName=com.nuix.examples.EngineVersion run`

You can pass arguments to an application main method using `-P` or `-D` flags.

`./gradlew -PmainClassName=com.nuix.examples.CreateCase -DcasePath=PATH/TO/CASE run`
