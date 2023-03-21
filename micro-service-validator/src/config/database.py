import mysql.connector

database = mysql.connector.connect(
    host='localhost',
    port=3306,
    user='user',
    password='password',
    database='local-db'
)
