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
    poster = db.query(models.Users).filter(models.Users.User_name == request.PostedBy).first()
    poster.Statusof_posted_task = 'Pending'
    db.add(new_task)
    db.commit()
    db.refresh(new_task)
    

    return 'Your task has been successfully Posted'

def reserve(request:schemas.Reserve_Operation,db:Session):
    usert = db.query(models.Tasks).filter(models.Tasks.PostedBy== request.PostedBy).first()
    user = db.query(models.Users).filter(models.Users.User_name == request.PostedBy).first()
    reserver = db.query(models.Users).filter(models.Users.User_name == request.Reserver).first()
    if not usert:
        raise HTTPException(status_code=status.HTTP_404_NOT_FOUND,detail="invalid id given")
    if usert.PostedBy != request.Reserver and reserver is not None:
        user.Statusof_posted_task = "Reserved"
        usert.Status = "Reserved"
        db.commit()
        db.refresh(usert)
        return {"Status":'Reserved status successfully'}
    elif reserver is None:
        return {"Status":'Invalid reserver'}
    else:
        return{"Status":"You cant reserve your own task"}

def get_Status(User_name:str,db:Session):
    user = db.query(models.Users).filter(models.Users.User_name == User_name).first()
    if not user:
        raise HTTPException(status_code=status.HTTP_404_NOT_FOUND,detail="invalid id given")
    status = user.Statusof_posted_task
    return {"Status":status}

def update_Karma(User_name:str,Karma:int,db:Session):
    user = db.query(models.Users).filter(models.Users.User_name == User_name).first()
    if not user:
        raise HTTPException(status_code=status.HTTP_404_NOT_FOUND,detail="invalid id given")
    user.Karma = Karma
    db.commit()
    db.refresh(user)
    return {'updated Karma successfully'} 