"""import cv2

for i in range(5):
    cap = cv2.VideoCapture(i)

    if cap.isOpened():
        ret, frame = cap.read()

        if ret:
            print(f"Camera {i}: OK")
        else:
            print(f"Camera {i}: abriu mas não leu frame")

    cap.release()"""

import cv2

cap = cv2.VideoCapture(0, cv2.CAP_ANY)
while True:
    ret, frame = cap.read()

    if not ret:
        break

    cv2.imshow("Teste", frame)

    if cv2.waitKey(1) == 27:
        break

cap.release()
cv2.destroyAllWindows()