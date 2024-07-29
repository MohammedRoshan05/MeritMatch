from fastapi import APIRouter,Depends
from .. import schemas,database
from sqlalchemy.orm import Session
from ..repository import user


router = APIRouter(tags=['User'],prefix='/user')

get_db = database.get_db

@router.get('/{User_name}',response_model=schemas.ShowUser)
def get_user(User_name:str,db:Session = Depends(get_db)):
    return user.show(User_name,db)

@router.post('/signup',response_model=schemas.ShowUser)
def create_user(request:schemas.User_operation, db:Session = Depends(get_db)):
    return user.create_user(request,db)


@router.post('/login')
def login(request:schemas.User_operation,db:Session = Depends(get_db)):
    return user.login(request,db)

