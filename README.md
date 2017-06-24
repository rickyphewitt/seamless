# Seamless
Web app built upon Spring boot
Seamless aims to blend all sources of music into a single interface

# Current Integrations
* Emby (BETA)

# Potential Future Integrations
* local
* Subsonic

# Run
* TBD

# Dev
* Download and build Emby Api Java jar from https://github.com/rickyphewitt/embyClientJava
  * Run ``` gradle jar ```
  * .jar should be in $projectDir/build/libs
  * E.g. embyApiClient-1.0-SNAPSHOT.jar
* Include this jar in dependencies section of build.gradle file
  ```
  dependencies {
    compile('org.springframework.boot:spring-boot-starter-thymeleaf')
    compile('org.springframework.boot:spring-boot-starter-web')
    testCompile('org.springframework.boot:spring-boot-starter-test')
    compile files('../$projectDir/build/libs/apiclient-1.0-SNAPSHOT.jar')
  }
  ```
* Build project
``` gradle build ```
* Run project
```java -jar build/libs/embyMini-0.0.1-SNAPSHOT.jar ```
