package com.redhat.telemetry.producer;

import java.util.Properties;
import java.util.Random;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.config.SslConfigs;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

public class ProducerApp {
    public static final String KAFKA_BOOTSTRAP_HOST = "my-cluster-kafka-bootstrap-sibtpd-kafka-cluster.apps.eu46r.prod.ole.redhat.com";
    public static final String KAFKA_BOOTSTRAP_PORT = "443";
    public static final String SSL_TRUSTSTORE_LOCATION = "/home/andy/AD482/truststore.jks";
    public static final String SSL_TRUSTSTORE_PASSWORD = "password";
    public static final String TOPIC_NAME = "total-connected-devices";

    public static Properties configureProperties() {
        Properties props = new Properties();

        // configure the bootstrap server
        props.put(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                KAFKA_BOOTSTRAP_HOST + ":" + KAFKA_BOOTSTRAP_PORT);

        // configure the key and value serializers
        props.put(
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class.getName());
        props.put(
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                IntegerSerializer.class.getName());

        // configure the SSL connection
        props.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SSL");
        props.put(
                SslConfigs.SSL_TRUSTSTORE_LOCATION_CONFIG,
                SSL_TRUSTSTORE_LOCATION);
        props.put(SslConfigs.SSL_TRUSTSTORE_PASSWORD_CONFIG, SSL_TRUSTSTORE_PASSWORD);

        return props;
    }

    public static void main(String[] args) {
        // Implement the Kafka producer
        Random random = new Random();
        Producer<Void, Integer> producer = new KafkaProducer<>(
                configureProperties());

        for (int i = 0; i < 10; i++) {
            ProducerRecord<Void, Integer> record = new ProducerRecord<>(
                    TOPIC_NAME,
                    random.nextInt(100));

            producer.send(record);
            printRecord(record);
        }

        producer.close();
    }

    private static void printRecord(ProducerRecord record) {
        System.out.println("Sent record:");
        System.out.println("\tTopic = " + record.topic());
        System.out.println("\tPartition = " + record.partition());
        System.out.println("\tKey = " + record.key());
        System.out.println("\tValue = " + record.value());
    }
}