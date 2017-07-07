default: build run	
	
build:
	mvn clean install
	
run:
	java -jar web/target/web-0.0.1-SNAPSHOT.jar 