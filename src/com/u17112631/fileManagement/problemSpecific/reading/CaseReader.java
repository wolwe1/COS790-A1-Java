package com.u17112631.fileManagement.problemSpecific.reading;

import com.u17112631.fileManagement.file.FileContents;
import com.u17112631.fileManagement.file.FileManager;
import com.u17112631.fileManagement.problemSpecific.parsing.FileParser;
import com.u17112631.models.TestCase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CaseReader
{
    private FileManager _reader;
    private ArrayList<FileParser> _problemParsers;
    private ArrayList<FileParser> _solutionParsers;

    public CaseReader() throws IOException {
        _reader = new FileManager();
        _problemParsers = new ArrayList<FileParser>();
        _solutionParsers = new ArrayList<FileParser>();
    }

    public CaseReader Include(String filePrefix,int start,int stop)
    {
        _problemParsers.add(new FileParser(filePrefix,start,stop));
        _solutionParsers.add(new FileParser("Op" + filePrefix,start,stop));

        return this;
    }

    public List<TestCase> Get()
    {
        var problemFiles = _problemParsers.stream().map(x -> x.Read(_reader));
        var solutionFiles = _solutionParsers.stream().map(x -> x.Read(_reader));

        var problems = CombineAndRemoveFailures(problemFiles);
        var solutions = CombineAndRemoveFailures(solutionFiles);

        return CreateTestCases(problems, solutions);
    }

    private static List<FileContents> CombineAndRemoveFailures(Stream<List<FileContents>> files)
    {
        return files
                .flatMap(x -> x.stream())
                .filter(x -> x != null)
                .collect(Collectors.toList());
    }
    private static List<TestCase> CreateTestCases(List<FileContents> problemFiles, List<FileContents> solutionFiles)
    {
        var cases = new ArrayList<TestCase>();
        for (var i = 0; i < problemFiles.size(); i++)
        {
            var problem = problemFiles.get(i);
            var solution = solutionFiles.get(i);

            cases.add(new TestCase(problem,solution));
        }

        return cases;
    }

}
