#设置引用的jdk环境容器
#FROM jdk:17
FROM openjdk:17
LABEL version="1.0"
LABEL description="gateway "
#目录是终端默认在此目录下
WORKDIR /app

#如果是到文件夹后面必须加上“/”,不然会找不到文件，./代表当前目录即是/app
COPY target/gateway-0.0.1-SNAPSHOT.jar gateway.jar
#开机执行
ENTRYPOINT ["java", "-jar", "gateway.jar"]
