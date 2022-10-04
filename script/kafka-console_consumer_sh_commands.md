

kafka-console-consumer.sh --bootstrap-server 127.0.0.1:9092  --topic my_topic2 // open a consumer connection from the last position in the topic

kafka-console-consumer.sh --bootstrap-server 127.0.0.1:9092  --topic my_topic2 --from-beginning // open a consumer connection from the first position in the topic

kafka-console-consumer.sh --bootstrap-server 127.0.0.1:9092  --topic my_topic2 --from-beginning --formatter kafka.tools.DefaultMessageFormatter --property print.timestamp=true --property print.key=true -- property print.value=true // open a consumer connection from the first position in the topic with format options enabled

kafka-console-consumer.sh --bootstrap-server 127.0.0.1:9092  --topic my_topic2 --group consumer-group1 //consumer group is  a way to balancing the consumming 