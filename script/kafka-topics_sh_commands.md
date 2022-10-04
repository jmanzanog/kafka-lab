

kafka-topics.sh --bootstrap-server 127.0.0.1:9092 --list // conect and list topics 

kafka-topics.sh --bootstrap-server 127.0.0.1:9092 --create --topic my_topic2 --partitions 3 // create topic

kafka-topics.sh --bootstrap-server 127.0.0.1:9092 --describe --topic my_topic2 // describe a topic

kafka-topics.sh --bootstrap-server 127.0.0.1:9092 --delete --topic my_topic  //delete a topic