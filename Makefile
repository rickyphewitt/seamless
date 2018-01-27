.PHONY: build
defauls: build run
	
build:
	mvn clean install
	
run: build
	java -jar web/target/web-0.0.1-SNAPSHOT.jar

run_with_config: build config_dir
	java -jar web/target/web-0.0.1-SNAPSHOT.jar
	

config_dir:
	mkdir -p /home/$$USER/.seamless/sources
	cp build/sources/EMBY.2018.01.21.07.38.56 /home/$$USER/.seamless/sources/
