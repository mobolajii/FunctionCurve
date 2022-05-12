package com.example.lernen;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class FunctionCurve extends Application {



    @Override
    public void start(Stage primaryStage) throws Exception {

        Label c1 = new Label("c1");
        Label d1 = new Label("c1");
        Label c2 = new Label("c1");
        Label d2 = new Label("c1");

        c1.setTextFill(Color.BROWN);
        d1.setTextFill(Color.BROWN);
        c2.setTextFill(Color.BROWN);
        d2.setTextFill(Color.BROWN);



        Spinner<Double> spinner1 = new Spinner<>(-5, 5, 1, 0.5);
        Spinner<Double> spinner2 = new Spinner<>(-5, 5, 0, 0.5);
        Spinner<Double> spinner3 = new Spinner<>(-5, 5, 1, 0.5);
        Spinner<Double> spinner4 = new Spinner<>(-5, 5, 0, 0.5);


        spinner1.setMaxWidth(60);
        spinner2.setMaxWidth(60);
        spinner3.setMaxWidth(60);
        spinner4.setMaxWidth(60);

        Button draw = new Button("draw");
        Button reset = new Button("reset");

        draw.setTextFill(Color.DARKSLATEBLUE);
        reset.setTextFill(Color.DARKSLATEBLUE);


        Canva canva = new Canva();
        canva.setHeight(500);
        canva.setWidth(500);
        canva.drawCurve();



        draw.setOnAction(actionEvent -> {
            canva.parameters(spinner1.getValue(), spinner2.getValue(), spinner3.getValue(), spinner4.getValue());
            canva.drawCurve();
        });

        reset.setOnAction(actionEvent -> {
            spinner1.getValueFactory().setValue(1d);
            spinner2.getValueFactory().setValue(0d);
            spinner3.getValueFactory().setValue(1d);
            spinner4.getValueFactory().setValue(0d);
            canva.parameters(spinner1.getValue(), spinner2.getValue(), spinner3.getValue(), spinner4.getValue());
            canva.drawCurve();
        });

       /*
            Slider and ColorPicker
        */

        Slider slider = new Slider(1, 5,1); // min value, the max value and the initial value.
        slider.setMajorTickUnit(1);
        slider.setShowTickMarks(true);
        slider.setShowTickLabels(true);

        slider.valueProperty().addListener(observable -> {
            canva.thickness(slider.getValue());
            canva.drawCurve();
        });


        ColorPicker colorPicker = new ColorPicker();
        colorPicker.setOnAction(actionEvent -> {
            canva.chooseColor(colorPicker.getValue());
            canva.drawCurve();
        });



        FlowPane flowPane = new FlowPane();
        flowPane.getChildren().addAll(c1, spinner1, d1, spinner2, c2, spinner3, d2, spinner4, draw );
        flowPane.getChildren().add(canva);
        flowPane.getChildren().addAll(reset, slider, colorPicker);
        flowPane.setHgap(20);
        flowPane.setVgap(20);





        primaryStage.setScene(new Scene(flowPane));
        primaryStage.show();


    }

    /*
    Canvas is a component just like button
    Canvas is an image that can be drawn on using a set of graphics
    commands provided by a GraphicsContext
    ->Leinwand
 */
    static class Canva extends Canvas{
        Canva(){}
        private double c1;
        private double d1;
        private double c2;
        private double d2;

        void parameters(double c1, double d1, double c2, double d2) {
            this.c1 = c1;
            this.d1 = d1;
            this.c2 = c2;
            this.d2 = d2;
        }



        private static final int yOffset = 20;
        
        void drawCurve(){
            GraphicsContext curve = getGraphicsContext2D();
            curve.fillRect(0, 0, getWidth(), getHeight());
            double h = this.getHeight() - 2 * yOffset;
            int xOffset = 40;
            double w = this.getWidth() - 2 * xOffset;
            double yZero = yOffset + h / 2;
            curve.setFill(Color.BLACK);
            curve.fillText("f(x)", xOffset - 30, yOffset);
            curve.fillText("0", xOffset - 25, yZero + 5);
            curve.fillText("2\u03c0", xOffset + w - 5, yZero - 5);
            curve.setLineWidth(1.0);
            curve.setStroke(Color.GRAY);
            curve.strokeLine(xOffset, yOffset, xOffset, yOffset + h);
            curve.strokeLine(xOffset, yZero, xOffset + w, yZero);
            /////////////////////////
            curve.setStroke(color);
            curve.setLineWidth(line);
            ////////////////////////
            for (int x = 0; x < w; x++)
            {
                int y = (int) ((h / 2) * sinCosFunc(x * 2 * Math.PI / w));
                curve.strokeLine(xOffset + x, yZero - y, xOffset + x, yZero - y);
            }

            //Arrow at y
            curve.fillPolygon(new double[]{30,40,50}, new double[]{20,10,20}, 3);
            // Arrow at x
            curve.fillPolygon(new double[]{xOffset + w, xOffset + w + 10, xOffset + w}, new double[]{yZero - 10, yZero, yZero + 10}, 3);
        }

        private double sinCosFunc(double x)
        {
            return Math.sin(c1 * x + d1) * Math.cos(c2 * x + d2);
        }


        private double line = 1.5d;
        private void thickness(Number line){
            this.line = (double) line;
        }


        private Color color = Color.RED;

        private void chooseColor(Color color){
            this.color = color;
        }



    }
}



