from src.config.database import database


def product_id_exist(product_id):
    cursor = database.cursor(prepared=True)
    query = "SELECT count(1) FROM product WHERE product_id = %s"
    cursor.execute(query, (product_id,))

    result = cursor.fetchone()
    cursor.close()

    return result[0] == 1


def product_name_not_exist(name):
    cursor = database.cursor(prepared=True)
    query = "SELECT count(1) FROM product WHERE product_name = %s"
    cursor.execute(query, (name,))

    result = cursor.fetchone()
    cursor.close()

    return result[0] == 0
