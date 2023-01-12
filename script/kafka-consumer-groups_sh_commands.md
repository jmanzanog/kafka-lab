
kafka-consumer-groups.sh  --bootstrap-server 127.0.0.1:9092 --list // list consumers groups

 kafka-consumer-groups.sh  --bootstrap-server 127.0.0.1:9092 --describe --group consumer-group1 // describe consumer group, CURRENT-OFFSET is the quantity of read message, LOG-END-OFFSET is the size of message in the partition, LAG is de difference between LOG-END-OFFSET and CURRENT-OFFSET

 kafka-consumer-groups.sh  --bootstrap-server 127.0.0.1:9092 --group consumer-group1 --reset-offsets --to-earliest --execute --all-topics // this will be reset the offset, that's means all messages will appear as not read

 kafka-consumer-groups.sh  --bootstrap-server 127.0.0.1:9092 --group consumer-group1 --reset-offsets --shift-by -2 --execute --all-topics // it will be applied last behavior but only 2 offsets
