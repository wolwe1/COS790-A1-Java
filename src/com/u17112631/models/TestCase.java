package com.u17112631.models;

import com.u17112631.fileManagement.file.FileContents;
import com.u17112631.fileManagement.problemSpecific.parsing.ProblemParser;
import com.u17112631.fileManagement.problemSpecific.parsing.SolutionParser;

public class TestCase
{
    private final ProblemSpecification _problem;
    private final Solution _solution;

    public TestCase(FileContents problem, FileContents solution)
    {
        _problem = ProblemParser.Parse(problem);
        _solution = SolutionParser.Parse(solution);
    }

    public void Summarise()
    {
        _problem.Summarise();
        _solution.summarise();
    }

    public ProblemSpecification GetProblem(){
        return _problem.GetCopy();
    }

    public Solution GetSolution(){
        return _solution.getCopy();
    }
}
