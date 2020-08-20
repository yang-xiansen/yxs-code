```
#打包命令
mvn clean install  -Dmaven.test.skip=true

#后台运行jar 并指定日志输出文件
nohup java -jar xxxxx.jar --server.port=8080  >msg.log 2>&1 &


```
