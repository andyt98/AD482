oc process \
 -f resources/create-topic.yaml \
 -p TOPIC_NAME='device-temperatures' \
 -p PARTITIONS=2 \
 | oc apply -f -