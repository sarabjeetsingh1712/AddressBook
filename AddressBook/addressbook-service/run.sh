echo "Cleaning & Building Address Book Application.."
mvn clean package

docker build -t addressbookapp:1.0 .
docker run -p 8080:8080 -t addressbookapp:1.0
