import mysql.connector
import os

database = mysql.connector.connect(
    host=os.getenv('MYSQL_HOST'),
    port=3306,
    user=os.getenv('MYSQL_USER'),
    password=os.getenv('MYSQL_PASSWORD'),
    database=os.getenv('MYSQL_DATABASE'),
)
