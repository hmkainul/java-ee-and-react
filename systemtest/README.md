# Build
mvn clean package && docker build -t com.example/systemtest .

# RUN

docker rm -f systemtest || true && docker run -d -p 8080:8080 -p 4848:4848 --name systemtest com.example/systemtest 