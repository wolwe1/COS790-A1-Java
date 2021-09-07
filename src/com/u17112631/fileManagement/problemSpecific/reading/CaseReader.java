package com.u17112631.fileManagement.problemSpecific.reading;

import com.u17112631.fileManagement.file.FileContents;
import com.u17112631.fileManagement.file.FileManager;
import com.u17112631.fileManagement.problemSpecific.parsing.FileParser;
import com.u17112631.models.TestCase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CaseReader
{
    private final FileManager _reader;
    private final ArrayList<FileParser> _problemParsers;
    private final ArrayList<FileParser> _solutionParsers;

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
        var problemFiles = _problemParsers.stream().map(x -> x.Read(_reader)).collect(Collectors.toList());
        var solutionFiles = _solutionParsers.stream().map(x -> x.Read(_reader)).collect(Collectors.toList());

        var problems = CombineAndRemoveFailures(problemFiles);
        var solutions = CombineAndRemoveFailures(solutionFiles);

        if(problems.size() == 0 || solutions.size() == 0)
            throw new RuntimeException("No problem or solution files loaded");

        return CreateTestCases(problems, solutions);
    }

    private static List<FileContents> CombineAndRemoveFailures(List<List<FileContents>> files)
    {
        return files
                .stream()
                .flatMap(Collection::stream)
                .filter(Objects::nonNull)
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
