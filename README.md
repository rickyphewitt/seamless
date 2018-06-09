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
### Required Libraries
* maven
* Java 8 (Openjdk)
### Build Project
* Run ``` make clean build ``` inside that repo
* If you are on 18.04 and run into an https issue, you can fix it by following the instructins here: https://gist.github.com/mikaelhg/527204e746984cf9a33f7910bb8b4cb6

### Update Config
* Update/Create the EMBY.2018.01.21.07.38.56 config in build/sources with user/pass/server info
### Run Project
* Build/run project
``` make run_with_config ```
* Open http://localhost:8080 

# Contributor Info

## Thinking of contributing... awesome!
Here are a few things to consider
* CSS/HTML attributes that start with **app** are NOT to be used for syles. These are used only for JS click events talking to the Java backend

# License
This project is licensed under the GPLv2. See https://www.gnu.org/licenses/old-licenses/gpl-2.0.en.html