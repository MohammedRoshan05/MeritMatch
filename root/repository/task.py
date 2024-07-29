from typing import List
from fastapi import HTTPException,status
from sqlalchemy.orm import Session
from .. import schemas,models

def get_Tasks(db:Session):
    tasks = db.query(models.Tasks).all()
    return tasks 

def post_Task(request:schemas.Task_Operation,db:Session):
    new_task = models.Tasks(PostedBy = request.PostedBy,Title = request.Title,
                Description = request.Description,Reward = request.Reward,Status = 'Pending',Resolver = 'N.A.')
    db.add(new_task)
    db.commit()
    db.refresh(new_task)
    return 'Your task has been successfully Posted'

def update_Status(User_name:str,Status:str,db:Session):
    user = db.query(models.Users).filter(models.Users.User_name == User_name).first()
    if not user:
        raise HTTPException(status_code=status.HTTP_404_NOT_FOUND,detail="invalid id given")
    user.Statusof_posted_task = Status
    db.commit()
    db.refresh(user)
    return {'updated status successfully'}

def update_Karma(User_name:str,Karma:int,db:Session):
    user = db.query(models.Users).filter(models.Users.User_name == User_name).first()
    if not user:
        raise HTTPException(status_code=status.HTTP_404_NOT_FOUND,detail="invalid id given")
    user.Karma = Karma
    db.commit()
    db.refresh(user)
    return {'updated Karma successfully'} 