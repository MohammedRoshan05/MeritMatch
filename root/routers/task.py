from fastapi import APIRouter,Depends
from .. import schemas,database
from sqlalchemy.orm import Session
from ..repository import task
from typing import List


router = APIRouter(tags=['Task'],prefix='/task')

get_db = database.get_db

@router.get('/',response_model=List[schemas.TaskDetails])
def get_Tasks(db:Session = Depends(get_db)):
    return task.get_Tasks(db)

@router.post('/postTask',response_model=schemas.Task_Operation)
# def post_Task(PostedBy:str,Title:str,Description :str,Reward :str,Status :str,Resolver :str)
def post_Task(request:schemas.Task_Operation,db:Session = Depends(get_db)):
    return task.post_Task(request,db)

@router.put('/updateStatus')
def update_Status(User_name:str,status:str,db:Session = Depends(get_db)):
    return task.update_Status(User_name,status,db)

@router.put('/updateKarma')
def update_Karma(User_name:str,Karma:int,db:Session = Depends(get_db)):
    return task.update_Karma(User_name,Karma,db)