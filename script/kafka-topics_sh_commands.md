

kafka-topics.sh --bootstrap-server 127.0.0.1:9092 --list // connect and list topics 

kafka-topics.sh --bootstrap-server 127.0.0.1:9092 --create --topic my_topic2 --partitions 3 // create topic

kafka-topics.sh --bootstrap-server 127.0.0.1:9092 --describe --topic my_topic2 // describe a topic

kafka-topics.sh --bootstrap-server 127.0.0.1:9092 --delete --topic my_topic  //delete a topic

kafka-run-class.sh kafka.tools.GetOffsetShell   --broker-list localhost:9092 --topic wikimedia.recent-change   | awk -F  ":" '{sum += $3} END {print "Result: "sum}' // check number of messages in the topic 

kafka-log-dirs.sh --describe --bootstrap-server localhost:9092 --topic-list wikimedia.recent-change | grep '^{' | jq '[ ..|.size? | numbers ] | add' // topic size  in bytes