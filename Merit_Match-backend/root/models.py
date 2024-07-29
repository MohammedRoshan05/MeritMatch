from .database import Base
from sqlalchemy import Column,Integer,String,ForeignKey
from sqlalchemy.orm import relationship

#defines the structure for the SQL tables
class Tasks(Base):
    __tablename__ = 'Tasks'
    id = Column(Integer,primary_key=True,index=True)
    PostedBy = Column(String)
    Title = Column(String)
    Description = Column(String)
    Reward = Column(Integer)
    Status = Column(String)
    Resolver = Column(String)


class Users(Base):
    __tablename__ = 'Users'

    id = Column(Integer,primary_key=True,index=True)
    User_name = Column(String)
    Password = Column(String)
    Statusof_posted_task = Column(String)
    Karma = Column(Integer)
