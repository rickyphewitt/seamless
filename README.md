# Seamless
Web app built upon Spring boot
Seamless aims to blend all sources of music into a single interface

# Current Integrations
* Emby (BETA)

# Potential Future Integrations
* local
* Subsonic

# Run
* See Dev for now

# Dev
* Download and build Emby Api Java jar from https://github.com/rickyphewitt/embyClientJava
  * Run ``` mvn clean install ``` inside that repo
* Input the required user/pass for emby in the file: services/src/main/java/com/rickyphewitt/seamless/services/SourceConfigService.java
* Build project
``` mvn clean install ```
* Run project
```java -jar web/target/web-0.0.1-SNAPSHOT.jar ```
* Open http://localhost:8080 

# Contributor Info

## Thinking of contributing... awesome!
Here are a few things to consider
* CSS/HTML attributes that start with **app** are NOT to be used for syles. These are used only for JS click events talking to the Java backend

# License
This project is licensed under the GPLv2. See https://www.gnu.org/licenses/old-licenses/gpl-2.0.en.html