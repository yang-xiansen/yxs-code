mvn clean install  -Dmaven.test.skip=true

PID=$(netstat -nlp | grep ":8080" | awk '{print $7}' | awk -F '[ / ]' '{print $1}')

if [ $? == 0 ]; then
                kill -9 ${PID}
        echo "process id is:${PID}"
#else
#        echo "process 7000 no exit"
 #       exit 0

nohup java -jar xxxxx.jar --server.port=8080  >msg.log 2>&1 &

tail -100f msg.log
