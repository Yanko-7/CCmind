package com.example.ccmindtest1;

import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurve;

public class Line extends CubicCurve {
    public void SetLine(double startX,double startY,double endX,double endY){
        super.setStartX(startX);super.setStartY(startY);
        super.setEndX(endX);super.setEndY(endY);
        super.setControlX1((startX+endX)/2);super.setControlY1(startY);
        super.setControlX2((startX+endX)/2);super.setControlY2(endY);
        super.setStrokeWidth(1);
        super.setFill(Color.WHITE);
        super.setStroke(Color.BLACK);
    }
}
