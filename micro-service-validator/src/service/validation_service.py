from src.config.constants import USER_FLOW, PRODUCT_FLOW, ORDER_FLOW, INSERT_OPERATION, UPDATE_OPERATION, \
    DELETE_OPERATION, USER_NO_VALID_MESSAGE_ERROR, \
    PRODUCT_NO_VALID_MESSAGE_ERROR, ORDER_NO_VALID_MESSAGE_ERROR
from src.repository.order_repository import order_id_exist, order_quantity_is_valid
from src.repository.product_repository import product_id_exist, product_name_not_exist
from src.repository.user_repository import user_id_exist, user_alias_not_exist


def validate(message):
    try:
        flow = message.headers[0][1].decode()

        if flow == USER_FLOW:
            return validate_user(message)
        elif flow == PRODUCT_FLOW:
            return validate_product(message)
        elif flow == ORDER_FLOW:
            return validate_order(message)
        else:
            return False
    except Exception:
        return False


def validate_user(message):
    try:
        execution_type = message.value['executionType']

        if execution_type == INSERT_OPERATION and len(message.value) == 4:
            name = message.value['name']
            alias = message.value['alias']
            address = message.value['address']

            is_new = user_alias_not_exist(alias)

            return len(name) > 0 and len(alias) > 0 and len(address) > 0 and is_new
        elif execution_type == UPDATE_OPERATION and len(message.value) == 5:
            user_id = message.value['id']
            name = message.value['name']
            alias = message.value['alias']
            address = message.value['address']

            is_update = user_id_exist(user_id)

            return len(user_id) and len(name) > 0 and len(alias) > 0 and len(address) > 0 and is_update
        elif execution_type == DELETE_OPERATION and len(message.value) > 0:
            user_id = message.value['id']

            is_delete = user_id_exist(user_id)

            return len(user_id) and is_delete
        else:
            raise ValueError
    except Exception:
        raise ValueError(USER_NO_VALID_MESSAGE_ERROR)


def validate_product(message):
    try:
        execution_type = message.value['executionType']

        if execution_type == INSERT_OPERATION and len(message.value) == 5:
            name = message.value['name']
            description = message.value['description']
            quantity = message.value['quantity']
            price = message.value['price']

            is_new = product_name_not_exist(name)

            return len(name) > 0 and len(description) > 0 and quantity > 0 and price > 0 and is_new
        elif execution_type == UPDATE_OPERATION and len(message.value) == 6:
            product_id = message.value['id']
            name = message.value['name']
            description = message.value['description']
            quantity = message.value['quantity']
            price = message.value['price']

            is_update = product_id_exist(product_id)

            return len(product_id) > 0 and len(name) > 0 and len(
                description) > 0 and quantity > 0 and price > 0 and is_update
        elif execution_type == DELETE_OPERATION and len(message.value) > 0:
            product_id = message.value['id']

            is_delete = product_id_exist(product_id)

            return len(product_id) > 0 and is_delete
        else:
            raise ValueError
    except Exception:
        raise ValueError(PRODUCT_NO_VALID_MESSAGE_ERROR)


def validate_order(message):
    try:
        execution_type = message.value['executionType']

        if execution_type == INSERT_OPERATION and len(message.value) == 4:
            user_id = message.value['userId']
            product_id = message.value['productId']
            quantity = message.value['quantity']

            is_new = user_id_exist(user_id) and order_quantity_is_valid(product_id, quantity)

            return len(user_id) > 0 and len(product_id) > 0 and quantity > 0 and is_new
        elif execution_type == UPDATE_OPERATION and len(message.value) == 5:
            order_id = message.value['id']
            user_id = message.value['userId']
            product_id = message.value['productId']
            quantity = message.value['quantity']

            is_update = user_id_exist(user_id) and order_id_exist(order_id) and order_quantity_is_valid(product_id,
                                                                                                        quantity)

            return len(order_id) > 0 and len(user_id) > 0 and len(product_id) > 0 and quantity > 0 and is_update
        elif execution_type == DELETE_OPERATION and len(message.value) > 0:
            order_id = message.value['id']

            is_delete = order_id_exist(order_id)

            return len(order_id) > 0 and is_delete
        else:
            raise ValueError
    except Exception:
        raise ValueError(ORDER_NO_VALID_MESSAGE_ERROR)
