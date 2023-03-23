import signal
import logging

from flask import Flask

from config import database as db
from config import kafka_consumer
from config import kafka_producer
from service import consumer_service

log = logging.getLogger(__name__)
app = Flask(__name__)


@app.route('/')
def home():
    return 'Health check OK'


def on_exit(signum, frame):
    log.info('on exit - graceful shutdown start')
    kafka_consumer.validation_consumer.close()
    kafka_producer.topic_producer.close()
    db.database.close()
    log.info('on exit - graceful shutdown completed')


if __name__ == '__main__':
    db.database.connect()
    consumer_service.execute_kafka_listener()

    signal.signal(signal.SIGTERM, on_exit)
    signal.signal(signal.SIGKILL, on_exit)
    signal.signal(signal.SIGINT, on_exit)
    signal.signal(signal.CTRL_C_EVENT, on_exit)

    app.run(debug=False, port=9024)
