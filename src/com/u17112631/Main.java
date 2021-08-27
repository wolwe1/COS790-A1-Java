package com.u17112631;

import com.u17112631.drawing.CustomPaintComponent;
import com.u17112631.models.Piece;

import java.awt.*;
import java.util.List;

public class Main {

    public static void main(String[] args) {
	// write your code here
//        System.out.println("Hello world");
//        UtilizedSpaceFitnessFunction usff = new UtilizedSpaceFitnessFunction(new MinimisationStrategy());
//
//        HeuristicMapper mapper = new HeuristicMapper()
//                .addMapping('a',)
//
//        SimulationBuilder builder = new SimulationBuilder(mapper);
//
//        BinPackingProblemDomain problemDomain = new BinPackingProblemDomain(usff, builder);
//
//        GenAlg alg = new GenAlg(0,"abc");
//        alg.setParameters("GeneticAlgParams.txt");
//        alg.setProblem(problemDomain);
//        alg.evolve();

        Frame frame = new Frame();

// Add a component with a custom paint method


        Piece rectangle = new Piece();
        rectangle.AddCoord(0,0);
        rectangle.AddCoord(0,500);
        rectangle.AddCoord(500,500);
        rectangle.AddCoord(500,0);

        var paintComponent = new CustomPaintComponent();
        paintComponent.addPoly(rectangle);

        frame.add(paintComponent);

// Display the frame

        int frameWidth = 1000;

        int frameHeight = 1000;

        frame.setSize(frameWidth, frameHeight);

        frame.setVisible(true);
    }

    private static void fillShape()
    {
        Piece rectangle = new Piece();
        rectangle.AddCoord(0,0);
        rectangle.AddCoord(0,500);
        rectangle.AddCoord(500,500);
        rectangle.AddCoord(500,0);
        //rectangle.FillShape();



    }

    boolean pnpoly(int numberOfVertices, List<Integer> polygonVerticesXValues, List<Integer> polygonVerticesYValues, int testx, int testy)
    {
        int i, j = 0;
        boolean c = false;
        for (i = 0, j = numberOfVertices-1; i < numberOfVertices; j = i++) {
            if ( ((polygonVerticesYValues.get(i) >testy) != (polygonVerticesYValues.get(j)> testy)) &&
                    (testx < (polygonVerticesXValues.get(j)-polygonVerticesXValues.get(i)) * (testy-polygonVerticesYValues.get(i) / (polygonVerticesYValues.get(j)-polygonVerticesYValues.get(i)) + polygonVerticesXValues.get(i)) ) )
                c = !c;
        }
        return c;
    }
}

