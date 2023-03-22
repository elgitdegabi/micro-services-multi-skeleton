from json import dumps

from kafka import KafkaProducer

topic_producer = KafkaProducer(
    value_serializer=lambda m: dumps(m).encode('utf-8'),
    bootstrap_servers=['localhost:9092']
)
