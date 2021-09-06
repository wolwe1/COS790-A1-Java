package com.u17112631;

import com.u17112631.fileManagement.problemSpecific.reading.CaseReader;
import com.u17112631.models.ProblemSpecification;
import com.u17112631.models.Solution;
import com.u17112631.models.TestCase;

import java.io.IOException;
import java.util.List;

public class TestManager {

    private final List<TestCase> testCases;

    private int currentTestIndex;
    private int currentSolutionIndex;

    public TestManager() throws IOException {
        CaseReader reader = new CaseReader()
                .Include("TA",0,1);

        testCases = reader.Get();
        currentTestIndex = 0;
        currentSolutionIndex = 0;
    }

    public ProblemSpecification getNextProblem(){
        return testCases.get(currentTestIndex++).GetProblem();
    }

    public Solution GetNextSolution(){
        return testCases.get(currentSolutionIndex++).GetSolution();
    }
}
