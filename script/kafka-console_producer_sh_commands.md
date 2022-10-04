

kafka-console-producer.sh --bootstrap-server 127.0.0.1:9092  --topic my_topic2 // create stream message without key 

kafka-console-producer.sh --bootstrap-server 127.0.0.1:9092  --topic my_topic2 --property parse.key=true --property key.separator=: // configure key value message e.g key001:value001