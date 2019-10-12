FROM registry.cn-hangzhou.aliyuncs.com/kennylee/maven:latest

# add pom.xml and source code
ADD ./pom.xml pom.xml
ADD ./src src/

# package jar and remove source code and temporary class files
# RUN mvn clean package && cp -f target/dockerHelloworld-0.0.1-SNAPSHOT.jar dockerHelloworld.jar && rm -rf pom.xml src/ target/

# run jar
CMD ["pwd"]
