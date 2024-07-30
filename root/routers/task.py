from fastapi import APIRouter,Depends
from .. import schemas,database
from sqlalchemy.orm import Session
from ..repository import task
from typing import List


router = APIRouter(tags=['Task'],prefix='/task')

get_db = database.get_db

@router.get('s',response_model=List[schemas.TaskDetails])
def get_Tasks(db:Session = Depends(get_db)):
    return task.get_Tasks(db)

@router.post('/postTask')
# def post_Task(PostedBy:str,Title:str,Description :str,Reward :str,Status :str,Resolver :str)
def post_Task(request:schemas.Task_Operation,db:Session = Depends(get_db)):
    return task.post_Task(request,db)

@router.put('/reserve')
def reserve(requst:schemas.Reserve_Operation,db:Session = Depends(get_db)):
    return task.reserve(requst,db)

@router.get('/getStatus')
def get_Status(User_name:str,db:Session = Depends(get_db)):
    return task.get_Status(User_name,db)

@router.put('/updateKarma')
def update_Karma(User_name:str,Karma:int,db:Session = Depends(get_db)):
    return task.update_Karma(User_name,Karma,db)