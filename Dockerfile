#start with a base image containing java runtime
FROM  openjdk:17-jdk-slim

#information around who maintains the image
MAINTAINER easybytes.com

#add the applications jar to the image
COPY target/accounts-0.0.1-SNAPSHOT.jar accounts-0.0.1-SNAPSHOT.jar

#excute the application
ENTRYPOINT ["java", "-jar","accounts-0.0.1-SNAPSHOT.jar"]

#create cmd (docker build . -t eazybank/accounts:v1)
#check details cmd {docker inspect image dedb18ea6cb3}
#Run the image {docker run -p 8686:8686 eazybank/accounts:v2}
#run detaiched mode{docker run -d -p 8686:8686 eazybank/accounts:v2}