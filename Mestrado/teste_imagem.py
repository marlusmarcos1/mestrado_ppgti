from ultralytics import YOLO
from collections import Counter

imagens = {0: "teste.jpg", 1:"teste_sem_celular.png"}

model = YOLO("yolov8s.pt")

results = model(imagens.get(1), conf=0.70)

inventario = Counter()

for result in results:
    for box in result.boxes:
        nome = result.names[int(box.cls[0])]
        inventario[nome] += 1

print(inventario)