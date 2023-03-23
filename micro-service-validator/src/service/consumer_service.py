import logging

from src.config.constants import CONSUME_NO_VALID_MESSAGE_ERROR
from src.config.kafka_consumer import validation_consumer
from . import producer_service
from . import validation_service

log = logging.getLogger(__name__)


def execute_kafka_listener():
    log.info('execute kafka lister - start')
    validation_consumer.poll(timeout_ms=1000)
    for message in validation_consumer:
        consume_validation_message(message)


def consume_validation_message(message):
    try:
        log.info('consume validation message: {}', message)
        if validation_service.validate(message):
            producer_service.publish_execution_message(message)
        else:
            raise ValueError(CONSUME_NO_VALID_MESSAGE_ERROR, message)
    except ValueError:
        log.error('consume validation message - error: {}', message)
        producer_service.publish_fail_message(message, CONSUME_NO_VALID_MESSAGE_ERROR)
