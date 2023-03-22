from src.config.kafka_producer import topic_producer
from json import dumps


def publish_execution_message(flow, session_id, message):
    topic_producer.send(
        '',
        value=dumps(message),
        headers={}
    )


def publish_fail_message(flow, session_id, message):
    topic_producer.send(
        '',
        value=dumps(message),
        headers={}
    )
