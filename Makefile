.PHONY: build
defauls: build run
embyApiClientRepoHash = aa109525928b62f1e0ba46ac07dcffdfcce50a50
	
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

docker_build: clean_build
	docker build --no-cache -t seamless -f build/docker/Dockerfile .

docker_compose_run: docker_build
	docker-compose -f build/docker/docker-compose.yml up

docker_compose_run_with_config: docker_build
	docker-compose -f build/docker/docker-compose-override.yml up

docker_compose_run_with_config_detach: docker_build
	docker-compose -f build/docker/docker-compose-override.yml up -d
