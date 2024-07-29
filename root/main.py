from fastapi import FastAPI
from .database import engine
from .routers import user,task
from . import models

models.Base.metadata.create_all(engine)

app = FastAPI()
app.include_router(user.router)
app.include_router(task.router)