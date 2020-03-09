from grip import GripPipeline
import networktables as nt
import cv2
import datetime
from time import sleep

def extra_processing(pipeline: GripPipeline):
    centre_xs = []
    centre_ys = []
    widths = []
    heights = []
    try:
        for contour in pipeline.find_contours_output:
            x, y, w, h = cv2.boundingRect(contour)
            if (w < 10 or h < 10): continue
            centre_xs.append(x + w / 2)
            centre_ys.append(y + h / 2)
            widths.append(w), heights.append(h)
    except: return None
    table = nt.NetworkTables.getTable("greenVision")
    centre_xs.sort(reverse=True)
    centre_ys.sort(reverse=True)
    widths.sort(reverse=True)
    heights.sort(reverse=True)
    if centre_xs != []:
        print(
f'''
Center X: {centre_xs[0]}
Center Y: {centre_ys[0]}
Width: {widths[0]}
Height: {heights[0]}
''')
        table.putNumber("CentreX", centre_xs[0])
        table.putNumber("CentreY", centre_ys[0])
        table.putNumber("width", widths[0])
        table.putNumber("height", heights[0])

def main():
    print("NetworkTableMan")
    nt.NetworkTables.startClientTeam(3560)
    nt.NetworkTables.initialize()
    print("Camera JI")
    cap = cv2.VideoCapture(0)
    bytes = ''
    f = False
    print("Pipeline Time")
    pipeline = GripPipeline()
    print("Didn't die... Yet")
    while cap.isOpened():
        b, frame = cap.read()
        if b:
            pipeline.process(frame)
            extra_processing(pipeline)

    print("Camera Died")


if __name__ == "__main__":
    main()

