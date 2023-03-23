import os
from json import dumps

from kafka import KafkaProducer

EXECUTION_QUEUE = 'execution-queue'
RESULT_TOPIC = 'result-topic'

topic_producer = KafkaProducer(
    value_serializer=lambda m: dumps(m).encode('utf-8'),
    bootstrap_servers=[os.getenv('KAFKA_BOOTSTRAP_SERVERS')]
)
