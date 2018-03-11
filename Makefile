.PHONY: build
defauls: build run
embyApiClientRepoHash = 1ffd2683b805bb22abff2daa3bbe07e769c805de
	
build:
	mvn clean install

clean_build: build_dependencies
	mvn clean install

run: build
	java -jar web/target/web-0.0.1-SNAPSHOT.jar

run_with_config: build config_dir
	java -jar web/target/web-0.0.1-SNAPSHOT.jar


config_dir:
	mkdir -p /home/$$USER/.seamless/sources
	cp build/sources/EMBY.2018.01.21.07.38.56 /home/$$USER/.seamless/sources/

build_dependencies: pull_dependencies
	cd embyApiClient && git checkout $(embyApiClientRepoHash) &&  mvn clean install

pull_dependencies:
	rm -fr embyApiClient && git clone https://github.com/rickyphewitt/embyClientJava.git embyApiClient
