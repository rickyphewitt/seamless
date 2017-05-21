default: build run	
	
build:
	gradle build
	
run:
	java -jar build/libs/embyMini-0.0.1-SNAPSHOT.jar 