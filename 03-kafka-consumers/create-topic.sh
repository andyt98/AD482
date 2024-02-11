oc process \
 -f resources/create-topic.yaml \
 -p TOPIC_NAME='humidity-condition' \
 | oc apply -f -