# Tempo-app

1- Download the source-code with git clone

2- Move to download folder and execute "docker-compose up application-db-postgres" to build and run the DDBB, if you open the proyect in the IntelliJ, you can open the file "docker-compose.yml" and click in the green arrow beside the name "application-db-postgres" to build and run the DDBB.

3- Run "mvn clean install" from comand line or "clean install" from IntelliJ run configuration to generate the target folder

4-

  a. If you want to run "Tempo-app" in a docker container you must execute "docker-compose up" or click in the green arrow beside the name "services" in file "docker-compose.yml" from IntelliJ.
  
  b. If you want to run and debug "Tempo-app" in the IntelliJ you must execute "spring-boot:run -Dspring-boot.run.fork=true" in the "run/debug configuration"

5- You can test the app in swagger in "http://localhost:8080/swagger-ui"


Ejercicio:

Debes desarrollar una API REST en Spring Boot utilizando java 11 o superior, con las siguientes funcionalidades:

Debe contener un servicio llamado por api-rest que reciba 2 números, los sume, y le aplique una suba de un porcentaje que debe ser adquirido de un servicio externo (por ejemplo, si el servicio recibe 5 y 5 como valores, y el porcentaje devuelto por el servicio externo es 10, entonces (5 + 5) + 10% = 11). Se deben tener en cuenta las siguientes consideraciones:

El servicio externo puede ser un mock, tiene que devolver el % sumado.

Dado que ese % varía poco, podemos considerar que el valor que devuelve ese servicio no va cambiar por 30 minutos.

Si el servicio externo falla, se debe devolver el último valor retornado. Si no hay valor, debe retornar un error la api.

Si el servicio falla, se puede reintentar hasta 3 veces.

Historial de todos los llamados a todos los endpoint junto con la respuesta en caso de haber sido exitoso. Responder en Json, con data paginada. El guardado del historial de llamadas no debe sumar tiempo al servicio invocado, y en caso de falla, no debe impactar el llamado al servicio principal.

La api soporta recibir como máximo 3 rpm (request / minuto), en caso de superar ese umbral, debe retornar un error con el código http y mensaje adecuado.

El historial se debe almacenar en una database PostgreSQL.

Incluir errores http. Mensajes y descripciones para la serie 4XX.


Se deben incluir tests unitarios.

Esta API debe ser desplegada en un docker container. Este docker puede estar en un dockerhub público. La base de datos también debe correr en un contenedor docker. Recomendación usar docker compose

Debes agregar un Postman Collection o Swagger para que probemos tu API

Tu código debe estar disponible en un repositorio público, junto con las instrucciones de cómo desplegar el servicio y cómo utilizarlo.

Tener en cuenta que la aplicación funcionará de la forma de un sistema distribuido donde puede existir más de una réplica del servicio funcionando en paralelo.
