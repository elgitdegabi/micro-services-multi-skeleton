import logging
from json import dumps

from src.config.kafka_producer import topic_producer, EXECUTION_QUEUE, RESULT_TOPIC

log = logging.getLogger(__name__)


def publish_execution_message(message):
    log.info('publish execution message: {}', message)
    topic_producer.send(
        EXECUTION_QUEUE,
        value=message.value,
        headers=message.headers
    )


def publish_fail_message(original_message, result_message):
    message = {
        'originalMessage': original_message.value,
        'resultMessage': dumps(result_message)
    }

    log.info('publish fail message: {}', message)
    topic_producer.send(
        RESULT_TOPIC,
        value=dumps(message),
        headers=original_message.headers
    )
