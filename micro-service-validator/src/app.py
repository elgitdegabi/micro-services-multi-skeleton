# https://www.youtube.com/watch?v=Zfpbnmdi-pE
from flask import Flask

from config import database as db

app = Flask(__name__)


@app.route('/')
def home():
    return 'Health check OK'


if __name__ == '__main__':
    db.database.connect()
    app.run(debug=False, port=9024)
