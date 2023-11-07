# Tempo-app

1- Download the source-code with git clone

2- Move to download folder and execute "docker-compose up application-db-postgres" to build and run the DDBB, if you open the proyect in the IntelliJ, you can open the file "docker-compose.yml" and click in the green arrow beside the name "application-db-postgres" to build and run the DDBB.

3- Run "mvn clean install" from comand line or "clean install" from IntelliJ run configuration to generate the target folder

4-

  a. If you want to run "Tempo-app" in a docker container you must execute "docker-compose up" or click in the green arrow beside the name "services" in file "docker-compose.yml" from IntelliJ.
  
  b. If you want to run and debug "Tempo-app" in the IntelliJ you must execute "spring-boot:run -Dspring-boot.run.fork=true" in the "run/debug configuration"
