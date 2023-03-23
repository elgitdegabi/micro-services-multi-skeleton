from src.config.database import database


def user_id_exist(user_id):
    cursor = database.cursor(prepared=True)
    query = "SELECT count(1) FROM user WHERE user_id = %s"
    cursor.execute(query, (user_id,))

    result = cursor.fetchone()
    cursor.close()

    return result[0] == 1


def user_alias_not_exist(alias):
    cursor = database.cursor(prepared=True)
    query = "SELECT count(1) FROM user WHERE user_alias = %s"
    cursor.execute(query, (alias,))

    result = cursor.fetchone()
    cursor.close()

    return result[0] == 0
