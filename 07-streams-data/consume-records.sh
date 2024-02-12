 oc exec \
 -it my-cluster-kafka-0 \
 -c kafka -n iopjlc-kafka-cluster \
 -- bin/kafka-console-consumer.sh \
 --bootstrap-server my-cluster-kafka-brokers:9092 \
 --topic vehicle-feet-elevations \
 --key-deserializer org.apache.kafka.common.serialization.IntegerDeserializer \
 --value-deserializer org.apache.kafka.common.serialization.DoubleDeserializer \
 --property print.key=true --property key.separator=" - "