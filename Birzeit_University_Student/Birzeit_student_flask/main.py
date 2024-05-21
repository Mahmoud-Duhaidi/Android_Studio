import pymysql
from app import app
from config import mysql
from flask import jsonify
from flask import flash, request

# return all student
@app.route('/student')
def allStudent():
    try:
        conn = mysql.connect()
        cursor = conn.cursor(pymysql.cursors.DictCursor)
        cursor.execute("SELECT * FROM student")
        studentRows = cursor.fetchall()
        respone = jsonify(studentRows)
        respone.status_code = 200
        return respone
    except Exception as e:
        print(e)
    finally:
        cursor.close() 
        conn.close()

# return specific student by id
@app.route('/student/<int:stud_id>')
def studentId(stud_id):
    try:
        conn = mysql.connect()
        cursor = conn.cursor(pymysql.cursors.DictCursor)
        cursor.execute("SELECT * FROM student WHERE stud_id = %s", stud_id)
        studentRow = cursor.fetchone()
        respone = jsonify(studentRow)
        respone.status_code = 200
        return respone
    except Exception as e:
        print(e)
    finally:
        cursor.close() 
        conn.close()   


# add student
@app.route('/addStudent', methods=['POST'])
def add_student():
    try:        
        _json = request.json
        _stud_name= _json['stud_name']
        _stud_email= _json['stud_email']
        _stud_password= _json['stud_password']
        _stud_major= _json['stud_major']
       
            
        if _stud_name and _stud_email and _stud_password and _stud_major and request.method == 'POST':
            conn = mysql.connect()
            cursor = conn.cursor(pymysql.cursors.DictCursor)        
            sqlQuery = "INSERT INTO student VALUES(NULL, %s, %s, %s, %s)"
            bindData = (_stud_name, _stud_email, _stud_password, _stud_major)            
            cursor.execute(sqlQuery, bindData)
            conn.commit()
            respone = jsonify(result='Student added successfully!')
            respone.status_code = 200
            return respone
        else:
            return showMessage()
    except Exception as e:
        print(e)
    finally:
        cursor.close() 
        conn.close()                                  

# delete student        
@app.route('/deleteStudent/<int:stud_id>', methods=['DELETE'])
def delete_student(stud_id):
    try:
        conn = mysql.connect()
        cursor = conn.cursor()
        cursor.execute("DELETE FROM student WHERE stud_id =%s", (stud_id))
        conn.commit()
        respone = jsonify(result='Student deleted successfully!')
        respone.status_code = 200
        return respone
    except Exception as e:
        print(e)
    finally:
        cursor.close() 
        conn.close()       


# update student
@app.route('/updateStudent/<int:stud_id>', methods=['PUT'])
def update_student(stud_id):
    try:
        _json = request.json
        _stud_name= _json['stud_name']
        _stud_email= _json['stud_email']
        _stud_password= _json['stud_password']
        _stud_major= _json['stud_major']


        if _stud_name and _stud_email and _stud_password and _stud_major and request.method == 'PUT':
            sqlQuery = "UPDATE student SET stud_name=%s, stud_email=%s, stud_password=%s, stud_major=%s WHERE stud_id=%s"
            bindData = (_stud_name, _stud_email, _stud_password, _stud_major, stud_id,)
            conn = mysql.connect()
            cursor = conn.cursor()
            cursor.execute(sqlQuery, bindData)
            conn.commit()
            response = jsonify(result='Student updated successfully!')
            response.status_code = 200
            return response
        else:
            return showMessage()
    except Exception as e:
        print(e)
    finally:
        cursor.close()
        conn.close()
        
#-----------------------------------------------------------
# major
# return all major
@app.route('/major')
def allMajor():
    try:
        conn = mysql.connect()
        cursor = conn.cursor(pymysql.cursors.DictCursor)
        cursor.execute("SELECT * FROM major")
        majorRows = cursor.fetchall()
        respone = jsonify(majorRows)
        respone.status_code = 200
        return respone
    except Exception as e:
        print(e)
    finally:
        cursor.close() 
        conn.close()

# return major img
@app.route('/majorimage/<string:faculiteis>')
def MajorImage(faculiteis):
    try:
        conn = mysql.connect()
        cursor = conn.cursor(pymysql.cursors.DictCursor)
        cursor.execute("SELECT major_image FROM major WHERE faculiteis = %s", faculiteis)
        majorImgRows = cursor.fetchall()
        respone = jsonify(majorImgRows)
        respone.status_code = 200
        return respone
    except Exception as e:
        print(e)
    finally:
        cursor.close() 
        conn.close()  


# return major cost per hour
@app.route('/majorcost/<string:major_name>')
def costMajorPerHour(major_name):
    try:
        conn = mysql.connect()
        cursor = conn.cursor(pymysql.cursors.DictCursor)
        cursor.execute("SELECT major_cost_per_hour FROM major WHERE major_name = %s", major_name)
        costMajorPerHourRow = cursor.fetchone()
        respone = jsonify(costMajorPerHourRow)
        respone.status_code = 200
        return respone
    except Exception as e:
        print(e)
    finally:
        cursor.close() 
        conn.close()        

#-----------------------------------------------------------
# Cafeteria
        
# return all cafeterias
@app.route('/cafeterias')
def cafeterias():
    try:
        conn = mysql.connect()
        cursor = conn.cursor(pymysql.cursors.DictCursor)
        cursor.execute("SELECT cafeteriaName FROM cafeterianame")
        cafeteriaRows = cursor.fetchall()
        respone = jsonify(cafeteriaRows)
        respone.status_code = 200
        return respone
    except Exception as e:
        print(e)
    finally:
        cursor.close() 
        conn.close()

# resturn specific category for specific cafeteria by cafeteria name
@app.route('/category/<string:cafeteriaName>')
def category(cafeteriaName):
    try:
       conn = mysql.connect()
       cursor = conn.cursor(pymysql.cursors. DictCursor)
       cursor.execute("SELECT cafeteriaId FROM cafeterianame where cafeteriaName=%s", cafeteriaName)
       cid = cursor.fetchone()
       cid = cid['cafeteriaId']
       cursor.execute("SELECT DISTINCT category FROM product where cafeteriaId=%s", (cid))
       categoryRows = cursor.fetchall()
       respone = jsonify(categoryRows)
       respone.status_code = 200
       return respone
    except Exception as e:
        print(e)
    finally:
        cursor.close() 
        conn.close()

# resturn specific products for specific cafeteria by cafeteria name and it's category
@app.route('/products/<string:cafeteriaName>/<string:category>')
def products(cafeteriaName, category):
    try:
       conn = mysql.connect()
       cursor = conn.cursor(pymysql.cursors.DictCursor)
       cursor.execute("SELECT cafeteriaId FROM cafeterianame where cafeteriaName=%s", (cafeteriaName,))
       cid = cursor.fetchone()
       cid = cid['cafeteriaId']
       cursor.execute("SELECT productId, productName, size, price FROM product where cafeteriaId=%s and category=%s", (cid, category))
       productRows = cursor.fetchall()
       respone = jsonify(productRows)
       respone.status_code = 200
       return respone
    except Exception as e:
       print(e)
    finally:
       cursor.close() 
       conn.close()


# add item to order
@app.route('/addItem', methods=['POST'])
def addItem():
    try:        
        _json = request.json
        _stud_id= _json['stud_id']
        _productId= _json['productId']
        _productqun= _json['qun']
        
        
        if _stud_id and _productId :
            conn = mysql.connect()
            cursor = conn.cursor(pymysql.cursors.DictCursor)        
            sqlQuery = "SELECT qun FROM orderitem WHERE stud_id=%s and productId=%s"
            bindData = (_stud_id,_productId)            
            cursor.execute(sqlQuery, bindData)
            item = cursor.fetchone()
            if item:
                print(item["qun"])
                sqlQuery = "UPDATE orderitem SET qun=%s WHERE stud_id=%s and productId=%s"
                bindData = (int(item["qun"])+int(_productqun),_stud_id,_productId) 
                cursor.execute(sqlQuery, bindData)
                conn.commit()
                respone = jsonify(result='item added successfully!')
                respone.status_code = 200
                return respone
            sqlQuery = "INSERT INTO orderitem VALUES(%s, %s,%s)"
            bindData = ( _productId,_stud_id,_productqun)            
            cursor.execute(sqlQuery, bindData)
            conn.commit()
            respone = jsonify(result='item added successfully!')
            respone.status_code = 200
            return respone
        
        else:
            return showMessage()
        
    except Exception as e:
        print(e)
    finally:
        cursor.close() 
        conn.close() 


# resturn specific order by student id
@app.route('/myorder/<int:stud_id>')
def myorder(stud_id):
    try:
        conn = mysql.connect()
        cursor = conn.cursor(pymysql.cursors.DictCursor)
        cursor.execute("SELECT productId ,qun FROM orderitem WHERE stud_id = %s", (stud_id,))
        productRows = cursor.fetchall()
    
        all=[]
        total_price = 0
        for product in productRows :   
            cursor.execute("SELECT cafeteriaId,productId,productName, size, price FROM product where productId=%s ", product["productId"])
            productInfo = cursor.fetchone()
            total_price += float(productInfo["price"])
            cursor.execute("SELECT cafeteriaName FROM  cafeterianame WHERE cafeteriaId = %s", productInfo["cafeteriaId"])
            cafeteriaName = cursor.fetchone()
            cafeteriaName = cafeteriaName['cafeteriaName']
            productInfo["cafeteriaName"]=cafeteriaName
            productInfo["qun"]=product["qun"]
            del productInfo["cafeteriaId"]
            all.append(productInfo)

        respone = jsonify(all)
        respone.status_code = 200
        return respone
    except Exception as e:
        print(e)
    finally:
        cursor.close() 
        conn.close() 



# delete all item from order        
@app.route('/deleteAllItem/<int:stud_id>', methods=['DELETE'])
def deleteAllItem(stud_id):
    try:
        conn = mysql.connect()
        cursor = conn.cursor()
        cursor.execute("DELETE FROM orderitem WHERE stud_id =%s", (stud_id))
        conn.commit()
        respone = jsonify('Item deleted successfully!')
        respone.status_code = 200
        return respone
    except Exception as e:
        print(e)
    finally:
        cursor.close() 
        conn.close() 


# delete item from order        
@app.route('/deleteItem/<int:stud_id>/<int:productId>', methods=['DELETE'])
def deleteItem(stud_id,productId):
    try:
        conn = mysql.connect()
        cursor = conn.cursor()
        cursor.execute("DELETE FROM orderitem WHERE stud_id =%s and productId=%s", (stud_id, productId))
        conn.commit()
        respone = jsonify('Item deleted successfully!')
        respone.status_code = 200
        return respone
    except Exception as e:
        print(e)
    finally:
        cursor.close() 
        conn.close() 


#-----------------------------------------------------------
# task

# return all due task
@app.route('/duetask/<int:stud_id>')
def allduetaskforstudent(stud_id):
    try:
        conn = mysql.connect()
        cursor = conn.cursor(pymysql.cursors.DictCursor)
        cursor.execute("SELECT * FROM task WHERE stud_id = %s AND task_status = 'Due'",stud_id)
        taskRows = cursor.fetchall()
        respone = jsonify(taskRows)
        respone.status_code = 200
        return respone
    except Exception as e:
        print(e)
    finally:
        cursor.close() 
        conn.close()

# return all done task
@app.route('/donetask/<int:stud_id>')
def alldonetaskforstudent(stud_id):
    try:
        conn = mysql.connect()
        cursor = conn.cursor(pymysql.cursors.DictCursor)
        cursor.execute("SELECT * FROM task WHERE stud_id = %s AND task_status = 'Done'",stud_id)
        taskRows = cursor.fetchall()
        respone = jsonify(taskRows)
        respone.status_code = 200
        return respone
    except Exception as e:
        print(e)
    finally:
        cursor.close() 
        conn.close()        
        
# return specific task by id
@app.route('/task/<int:stud_id>/<int:task_id>')
def taskId(stud_id, task_id):
    try:
        conn = mysql.connect()
        cursor = conn.cursor(pymysql.cursors.DictCursor)
        cursor.execute("SELECT * FROM task WHERE task_id = %s AND stud_id = %s", (task_id, stud_id))
        taskRow = cursor.fetchone()
        respone = jsonify(taskRow)
        respone.status_code = 200
        return respone
    except Exception as e:
        print(e)
    finally:
        cursor.close() 
        conn.close()   


# add task
@app.route('/addTask', methods=['POST'])
def add_task():
    try:        
        _json = request.json
        _task_title= _json['task_title']
        _task_description= _json['task_description']
        _task_date= _json['task_date']
        _task_time= _json['task_time']
        _task_status= _json['task_status']
        _stud_id= _json['stud_id']
            
        if _task_title and _task_description and _task_date and _task_time and _task_status and _stud_id and request.method == 'POST':
            conn = mysql.connect()
            cursor = conn.cursor(pymysql.cursors.DictCursor)        
            sqlQuery = "INSERT INTO task VALUES(NULL, %s, %s, %s, %s, %s, %s)"
            bindData = (_task_title, _task_description, _task_date, _task_time, _task_status, _stud_id)            
            cursor.execute(sqlQuery, bindData)
            conn.commit()
            respone = jsonify(result='Task added successfully!')
            respone.status_code = 200
            return respone
        else:
            return showMessage()
    except Exception as e:
        print(e)
    finally:
        cursor.close() 
        conn.close()                                  

# delete task       
@app.route('/deleteTask/<int:stud_id>/<int:task_id>', methods=['DELETE'])
def delete_task(stud_id, task_id):
    try:
        conn = mysql.connect()
        cursor = conn.cursor()
        cursor.execute("DELETE FROM task WHERE task_id=%s AND stud_id =%s", (task_id,stud_id))
        conn.commit()
        respone = jsonify('Task deleted successfully!')
        respone.status_code = 200
        return respone
    except Exception as e:
        print(e)
    finally:
        cursor.close() 
        conn.close()       


# update task
@app.route('/update_task/<int:stud_id>/<int:task_id>', methods=['PUT'])
def update_task(stud_id, task_id):
    try:
        _json = request.json
        _task_status= _json['task_status']


        if  _task_status and request.method == 'PUT':
            sqlQuery = "UPDATE task SET task_status=%s WHERE task_id=%s AND stud_id=%s"
            bindData = (_task_status, task_id, stud_id)
            conn = mysql.connect()
            cursor = conn.cursor()
            cursor.execute(sqlQuery, bindData)
            conn.commit()
            response = jsonify('Task updated successfully!')
            response.status_code = 200
            return response
        else:
            return showMessage()
    except Exception as e:
        print(e)
    finally:
        cursor.close()
        conn.close()




@app.errorhandler(404)
def showMessage(error=None):
    message = {
        'status': 404,
        'message': 'Record not found: ' + request.url, 
    }
    respone = jsonify(message)
    respone.status_code = 404
    return respone
        
if __name__ == "__main__":
    app.debug = True
    app.run()        