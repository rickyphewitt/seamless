# embyMini
Web app built upon Spring Boot

# Run
* TBD

# Dev
* Download and build Emby Api Java jar from https://github.com/MediaBrowser/Emby.ApiClient.Java
  * Andriod integration not required and will error out
  * Run ``` gradle build ```
  * .jar should be in $projectDir/build/libs
  * E.g. apiclient-1.0-SNAPSHOT.jar
* Include this jar in dependencies section of build.gradle file
  ```
  dependencies {
    compile('org.springframework.boot:spring-boot-starter-thymeleaf')
    compile('org.springframework.boot:spring-boot-starter-web')
    testCompile('org.springframework.boot:spring-boot-starter-test')
    compile files('../Emby.ApiClient.Java/build/libs/apiclient-1.0-SNAPSHOT.jar')
  }
  ```
* Build project
``` gradle build ```
* Run project
```java -jar build/libs/embyMini-0.0.1-SNAPSHOT.jar ```
