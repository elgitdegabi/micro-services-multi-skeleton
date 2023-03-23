from src.config.database import database


def order_id_exist(order_id):
    cursor = database.cursor(prepared=True)
    query = "SELECT count(1) FROM order WHERE order_id = %s"
    cursor.execute(query, (order_id,))

    result = cursor.fetchone()
    cursor.close()

    return result[0] == 1


def order_quantity_is_valid(product_id, quantity):
    cursor = database.cursor(prepared=True)
    query = "SELECT count(1) FROM product WHERE product_id = %s and product_quantity <= %s"
    cursor.execute(query, (product_id, quantity,))

    result = cursor.fetchone()
    cursor.close()

    return result[0] == 0
