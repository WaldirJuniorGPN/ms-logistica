
build:
	mvn clean compile -Dmaven.test.skip=true

package:
	mvn clean package -Dmaven.test.skip=true

unit-test:
	mvn test

mutation-test:
	mvn test -DpitestSkip=false

integration-test:
	mvn test -P integration-test

docker-build: package
	docker build -t ms-logistica -f ./Dockerfile .

docker-start: docker-build
	docker compose -f docker-compose.yml up -d

docker-stop:
	docker compose -f docker-compose.yml down

start-app:
	mvn spring-boot:start

test: unit-test integration-test
