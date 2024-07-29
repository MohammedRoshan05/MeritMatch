from fastapi import HTTPException,status
from sqlalchemy.orm import Session
from .. import schemas,models
from ..hashing import Hash
from passlib.context import CryptContext


def show(User_name:str,db:Session):
    user = db.query(models.Users).filter(models.Users.User_name == User_name).first()
    if not user:
        raise HTTPException(status_code=status.HTTP_404_NOT_FOUND,detail="invalid id given")
    return user

def create_user(request:schemas.User_operation,db:Session):
    # if(request.User_name != "" and request.Password != ""):
    if(request.Password and request.Password.strip() and request.User_name and request.User_name.strip()):
        new_user = models.Users(User_name = request.User_name,Password = Hash.bcrypt(request.Password),
                            Statusof_posted_task = 'None',Karma = 300)
        db.add(new_user)
        db.commit()
        db.refresh(new_user)
        return new_user

# Password hashing context
pwd_context = CryptContext(schemes=["bcrypt"], deprecated="auto")
def verify_password(plain_password, hashed_password):
    return pwd_context.verify(plain_password, hashed_password)

def login(request:schemas.User_operation,db:Session):
    user = db.query(models.Users).filter(models.Users.User_name == request.User_name).first()
    if not user:
        return {"message": "Invalid username or password"}
        # raise HTTPException(status_code=status.HTTP_404_NOT_FOUND, detail="Invalid username or password")
    if not verify_password(request.Password, user.Password):
        return {"message": "Invalid password"}
        raise HTTPException(status_code=status.HTTP_404_NOT_FOUND, detail="Invalid password")
    return {"message": "Login successful"}
  