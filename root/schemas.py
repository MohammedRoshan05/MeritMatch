from pydantic import BaseModel

class User_operation(BaseModel):
    User_name:str
    Password:str

class ShowUser(BaseModel):
    User_name:str
    Karma:int
    class Config():
        orm_mode = True


class UserDetails(BaseModel):
    User_name:str
    Password:str
    Statusof_posted_task:str
    Karma:int

class TaskDetails(BaseModel):
    PostedBy:str
    Title:str
    Description:str
    Reward:int
    Status:str
    Resolver:str
    class Config():
        orm_mode = True

class Task_Operation(BaseModel):
    PostedBy:str
    Title:str
    Description:str
    Reward:int