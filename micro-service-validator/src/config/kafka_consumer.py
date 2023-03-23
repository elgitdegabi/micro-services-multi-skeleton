import os
from json import loads

from kafka import KafkaConsumer

validation_consumer = KafkaConsumer(
    'validation-queue',
    auto_offset_reset='earliest',
    enable_auto_commit=True,
    group_id='ms-skeleton-validation-group-1',
    value_deserializer=lambda m: loads(m.decode('utf-8')),
    bootstrap_servers=[os.getenv('KAFKA_BOOTSTRAP_SERVERS')]
)
