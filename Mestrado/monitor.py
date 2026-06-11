from ultralytics import YOLO
from collections import Counter
import cv2
from datetime import datetime
import requests
import time

model = YOLO("yolov8s.pt")


def gerar_inventario(result):
    inventario = Counter()

    for box in result.boxes:
        classe = int(box.cls[0])
        nome = result.names[classe]

        inventario[nome] += 1

    return dict(inventario)


def diferenca(antes, depois):
    alteracoes = []

    objetos = set(antes.keys()) | set(depois.keys())

    for obj in objetos:
        qtd_antes = antes.get(obj, 0)
        qtd_depois = depois.get(obj, 0)

        delta = qtd_depois - qtd_antes

        if delta != 0:
            alteracoes.append({
                "objeto": obj,
                "delta": delta
            })

    return alteracoes


video = "auditoria2.mp4"

cap = cv2.VideoCapture(video)

fps = cap.get(cv2.CAP_PROP_FPS)

frame_interval = int(fps)

ultimo_estado = None

eventos = []

frame_num = 0

while True:
    ret, frame = cap.read()

    if not ret:
        print("Reiniciando vídeo...")

        cap.set(cv2.CAP_PROP_POS_FRAMES, 0)

        frame_num = 0

        continue

    if frame_num % frame_interval == 0:
        
        segundos = int(frame_num / fps)

        results = model(frame, conf=0.7)

        estado_atual = gerar_inventario(results[0])
        requests.post(
            "http://localhost:8000/estado",
            json={
                "estado": estado_atual
            }
        )

        print(f"\n[{segundos}s]")
        print(estado_atual)

        if ultimo_estado is not None:

            alteracoes = diferenca(
                ultimo_estado,
                estado_atual
            )

            if alteracoes:

                evento = {
                    "tempo_video": segundos,
                    "timestamp": datetime.now().strftime("%Y-%m-%d %H:%M:%S"),
                    "alteracoes": alteracoes
                }

                eventos.append(evento)
                requests.post(
                    "http://localhost:8000/evento",
                    json=evento
                )

                print("\nALTERAÇÃO DETECTADA")

                for a in alteracoes:
                    sinal = "+" if a["delta"] > 0 else ""
                    print(
                        f"{a['objeto']}: {sinal}{a['delta']}"
                    )

        ultimo_estado = estado_atual
        time.sleep(1)

    frame_num += 1

cap.release()

print("\n====================")
print("RELATÓRIO FINAL")
print("====================")

for evento in eventos:

    print(f"\nTempo: {evento['tempo']}s")

    for alt in evento["alteracoes"]:
        sinal = "+" if alt["delta"] > 0 else ""
        print(
            f"{alt['objeto']}: {sinal}{alt['delta']}"
        )