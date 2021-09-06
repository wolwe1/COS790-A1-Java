package com.u17112631;

import com.u17112631.ProblemDomain.BinPackingProblemDomain;
import com.u17112631.drawing.CustomPaintComponent;
import com.u17112631.drawing.Drawer;
import com.u17112631.fitnessFunctions.UtilizedSpaceFitnessFunction;
import com.u17112631.fitnessFunctions.maxmin.MinimisationStrategy;
import com.u17112631.heuristics.binSelection.BestFitHeuristic;
import com.u17112631.heuristics.binSelection.FirstFitHeuristic;
import com.u17112631.heuristics.binSelection.WorstFitHeuristic;
import com.u17112631.heuristics.objectSelection.*;
import com.u17112631.models.Piece;
import com.u17112631.models.ProblemSpecification;
import com.u17112631.models.Solution;
import com.u17112631.simulation.HeuristicMapper;
import com.u17112631.simulation.SimulationBuilder;
import genalg.GenAlg;

import java.awt.*;
import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        //Get tests
        TestManager testManager = new TestManager();

        ProblemSpecification specification = testManager.getNextProblem();
        Solution solution = testManager.GetNextSolution();

        System.out.println("Hello world");
        UtilizedSpaceFitnessFunction usff = new UtilizedSpaceFitnessFunction(new MinimisationStrategy());

        HeuristicMapper mapper = new HeuristicMapper()
                .addMapping('a',new BestFitHeuristic())
                .addMapping('a',new BiggestPieceHeuristic())
                .addMapping('b',new FirstFitHeuristic())
                .addMapping('b',new LeastComplexPieceHeuristic())
                .addMapping('c',new WorstFitHeuristic())
                .addMapping('c',new MostComplexPieceHeuristic())
                .addMapping('d',new NextPieceHeuristic())
                .addMapping('e', new SmallestPieceHeuristic())
                //Even out the mappings
                .addMapping('d',new BestFitHeuristic())
                .addMapping('e',new WorstFitHeuristic());

        SimulationBuilder builder = new SimulationBuilder(mapper,specification);

        BinPackingProblemDomain problemDomain = new BinPackingProblemDomain(usff, builder);
//
        GenAlg alg = new GenAlg(0,"abc");
        alg.setParameters("GeneticAlgParams.txt");
        alg.setProblem(problemDomain);
        alg.evolve();

        ;
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


    static void canvas(){
        Frame frame = new Frame();

        int frameWidth = 1050;

        int frameHeight = 1050;

        frame.setSize(frameWidth, frameHeight);

// Add a component with a custom paint method


        Piece rectangle = new Piece(1);
        rectangle.addCoord(0,0);
        rectangle.addCoord(0,500);
        rectangle.addCoord(500,500);
        rectangle.addCoord(500,0);

        Piece rectangle2 = new Piece(2);
        rectangle2.addCoord(500,0);
        rectangle2.addCoord(500,500);
        rectangle2.addCoord(1000,500);
        rectangle2.addCoord(500,0);

        Piece rectangle3 = new Piece(3);
        rectangle3.addCoord(0,500);
        rectangle3.addCoord(0,1000);
        rectangle3.addCoord(500,1000);
        rectangle3.addCoord(500,500);

        Piece rectangle4 = new Piece(4);
        rectangle4.addCoord(500,500);
        rectangle4.addCoord(500,1000);
        rectangle4.addCoord(1000,1000);
        rectangle4.addCoord(500,500);

        var paintComponent = new CustomPaintComponent();
        frame.add(paintComponent);
        paintComponent.setEnds();

        //paintComponent.addPoly(rectangle);
        paintComponent.addPoly(rectangle2);
        //paintComponent.addPoly(rectangle3);
        //paintComponent.addPoly(rectangle4);


// Display the frame



        frame.setVisible(true);
    }

    static void testDraw(){
        Piece rectangle = new Piece(1);
        rectangle.addCoord(0,0);
        rectangle.addCoord(0,25);
        rectangle.addCoord(25,25);
        rectangle.addCoord(25,0);


        Piece triangle = new Piece(2);
        triangle.addCoord(0,0);
        triangle.addCoord(25,25);
        triangle.addCoord(50,0);


        Drawer drawer = new Drawer();
        drawer.addPiece(rectangle);
        drawer.addPiece(triangle);
        drawer.draw();
    }
}

