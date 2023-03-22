from src.config.kafka_consumer import validation_consumer
from . import producer_service
from . import validation_service


def execute_kafka_listener():
    validation_consumer.poll(timeout_ms=1000)
    for message in validation_consumer:
        consume_validation_message(message)


def consume_validation_message(message):
    try:
        flow = message.headers[0][1].decode()
        session_id = message.headers[1][1].decode()

        if flow == 'USER':
            validation_service.validate_user(message)
            producer_service.publish_execution_message(flow, session_id, message)
        elif flow == 'PRODUCT':
            validation_service.validate_product(message)
            producer_service.publish_execution_message(flow, session_id, message)
        elif flow == 'ORDER':
            validation_service.validate_order(message)
            producer_service.publish_execution_message(flow, session_id, message)
        else:
            raise ValueError("consume_validation_message - no valid message: ", message)
    except ValueError:
        producer_service.publish_fail_message(message)
