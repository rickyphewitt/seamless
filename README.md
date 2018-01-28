[![Build Status](https://travis-ci.org/rickyphewitt/seamless.svg?branch=master)](https://travis-ci.org/rickyphewitt/seamless)

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
* Update the EMBY.2018.01.21.07.38.56 config in build/sources with user/pass/server info
* Build/run project
``` make run_with_config ```
* Open http://localhost:8080 

# Contributor Info

## Thinking of contributing... awesome!
Here are a few things to consider
* CSS/HTML attributes that start with **app** are NOT to be used for syles. These are used only for JS click events talking to the Java backend

# License
This project is licensed under the GPLv2. See https://www.gnu.org/licenses/old-licenses/gpl-2.0.en.html