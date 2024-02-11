oc process \
 -f resources/create-topic.yaml \
 -p TOPIC_NAME='total-connected-devices' \
 | oc apply -f -