# 基础镜像
FROM  openjdk:8-jre
# author
MAINTAINER thinglinks
# 设定时区
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
# 挂载目录
VOLUME /www/wwwroot/thinglinks
# 创建目录
RUN mkdir -p /www/wwwroot/thinglinks
# 指定路径
WORKDIR /www/wwwroot/thinglinks
# 复制jar文件到路径
COPY ./jar/thinglinks-modules-tdengine-1.0.0.RELEASE.jar /www/wwwroot/thinglinks/tdengine/tdengine.jar
COPY ./jar/resources  /www/wwwroot/thinglinks/tdengine/resources
# 启动TDengine服务
ENTRYPOINT ["java", "-Xmx400m", "-Xms400m", "-Xmn150m", "-Xss1024k", "-jar", "./tdengine/tdengine.jar"]
