# kafka-lab

## install Kafka on macOS
- Download kafka binary from https://kafka.apache.org/downloads - e.g kafka_2.13-3.2.3.tgz
- On ``` ~/.bash_profile``` file add ```export PATH="~/kafka-3.2.1-src/bin:$PATH"```
- zookeeper-server-start.sh ~/kafka-3.2.1-src/config/zookeeper.properties
- kafka-server-start.sh ~/kafka-3.2.1-src/config/server.properties
