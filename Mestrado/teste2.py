from ultralytics import YOLO
from collections import Counter

model = YOLO("yolov8s.pt")

imagem = r"C:\Users\marlus.marcos\Desktop\Mestrado\teste.jpg"
imagem2 = r"C:\Users\marlus.marcos\Desktop\Mestrado\teste_sem_celular.png"


results = model(
    imagem,conf=0.70
)

inventario = Counter()

for result in results:
    for box in result.boxes:
        classe = int(box.cls[0])
        nome = result.names[classe]
        inventario[nome] += 1

print("\nInventário encontrado:\n")

for objeto, quantidade in inventario.items():
    print(f"{objeto}: {quantidade}")