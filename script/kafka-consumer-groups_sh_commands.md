
kafka-consumer-groups.sh  --bootstrap-server 127.0.0.1:9092 --list // list consumers groups

 kafka-consumer-groups.sh  --bootstrap-server 127.0.0.1:9092 --describe --group consumer-group1 // describe consumer group, CURRENT-OFFSET is the cuantity of readed message, LOG-END-OFFSET is the size of message in the parition, LAG is de diference between LOG-END-OFFSET and CURRENT-OFFSET

 kafka-consumer-groups.sh  --bootstrap-server 127.0.0.1:9092 --group consumer-group1 --reset-offsets --to-earliest --execute --all-topics // this will be reset the offset, thats means all messaessage will appears as not readed

 kafka-consumer-groups.sh  --bootstrap-server 127.0.0.1:9092 --group consumer-group1 --reset-offsets --shift-by -2 --execute --all-topics // it will be apply 