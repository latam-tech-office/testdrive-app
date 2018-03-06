oc new-project survey
oc new-app -f https://raw.githubusercontent.com/latam-tech-office/testdrive-cicd/master/ocp/template/wildfly-mongo.yaml -p APPLICATION_NAME=survey -p DB_USERNAME=testdrive -p DB_PASSWORD=r3dh4t1! -p DB_DATABASE=testdrive -p BROKER_ADDRESS=broker-amq-tcp.broker -p BROKER_USERNAME=testdrive -p BROKER_PASSWORD=r3dh4t1! -p BROKER_TOPICS=testdrive
oc set probe dc/survey-app --readiness --initial-delay-seconds=20 --period-seconds=15 --get-url=http://:8080/api/ping
