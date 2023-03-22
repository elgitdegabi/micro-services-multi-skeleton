from flask import Flask

from config import database as db
from service import consumer_service


app = Flask(__name__)


@app.route('/')
def home():
    return 'Health check OK'


if __name__ == '__main__':
    db.database.connect()
    consumer_service.execute_kafka_listener()
    app.run(debug=False, port=9024)
# https://www.youtube.com/watch?v=Zfpbnmdi-pE
# https://aitor-medrano.github.io/bigdata2122/apuntes/bdaplicado05kafka.html#produciendo-y-consumiendo
