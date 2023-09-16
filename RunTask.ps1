Start-Transcript Transcript.log
"Starting Unit Run Task" | Out-File -FilePath Task.log
&"app\engine\jre\bin\java" `
    -cp "app\build\classes\java\main;app\build\resources\main;app\engine\lib\*;app\engine\lib\non-fips\*;app\libs\*" `
    -D"nuix.license.server.username"="rest-service" `
    -D"nuix.license.server.password"="Welcome2020!" `
    -D"nuix.license.shortname"="enterprise-workstation" `
    -D"nuix.registry.servers"="https://licence-api.nuix.com" `
    -D"nuix.license.source"="cloud-server" `
    -DworkerCount=2 `
    -Xmx4g `
    -D"nuix.logdir"="logs" `
    -D"nuix.libdir"="app\engine\lib" `
    -D"nuix.engine.userDataDirs"="app\engine\user-data" `
    -D"java.library.path"="app\engine\bin;app\engine\bin\x86;app\engine\lib" `
    -D"nuix.engine.path"="app\engine" `
    -D"nuix.worker.jvm.arguments"="-Dlog4j.configuration=file:app\engine\config\log4j2.yml" `
    com.nuix.examples.EngineVersion
    | OUT-File -Append -FilePath Task.log