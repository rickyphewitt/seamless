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
  * Run ``` mvn clean install ``` inside that repo

* Build project
``` mvn clean install ```
* Run project
```java -jar build/libs/embyMini-0.0.1-SNAPSHOT.jar ```


# Contributor Info

## Thinking of contributing... awesome!
Here are a few things to consider
* CSS/HTML attributes that start with **app** are NOT to be used for syles. These are used only for JS click events talking to the Java backend
