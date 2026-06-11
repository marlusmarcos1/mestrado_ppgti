from fastapi import FastAPI
from pydantic import BaseModel
from typing import List, Dict, Any
app = FastAPI()

estado_atual = {}
eventos = []

class EstadoRequest(BaseModel):
    estado: dict

class EventoRequest(BaseModel):
    tempo_video: int
    timestamp: str
    alteracoes: List[Dict[str, Any]]

@app.get("/estado")
def get_estado():
    return estado_atual

@app.post("/estado")
def atualizar_estado(req: EstadoRequest):
    global estado_atual
    estado_atual = req.estado
    return {"ok": True}

@app.get("/eventos")
def get_eventos():
    return eventos

@app.post("/evento")
def adicionar_evento(req: EventoRequest):
    eventos.append(req.dict())
    return {"ok": True}

@app.get("/health")
def health():
    return {"status": "online"}