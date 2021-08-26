package com.u17112631.fileManagement.problemSpecific.parsing;

import com.u17112631.fileManagement.file.FileContents;
import com.u17112631.fileManagement.file.FileManager;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FileParser
{
    private final String _filePrefix;
    private final int _start;
    private final int _stop;

    public FileParser(String filePrefix,int start,int stop)
    {
        _filePrefix = filePrefix;
        _start = start;
        _stop = stop;
    }

    public List<FileContents> Read(FileManager reader)
    {
        List<Integer> fileNumberRange;

        fileNumberRange = _stop == 0 ? new ArrayList<>(){ {add(0);}} : IntStream.rangeClosed(_start, _stop)
                .boxed().collect(Collectors.toList());;

        var fileNumber = fileNumberRange.stream()
                .map(x -> ConvertNumberToString(x))
                .map(x -> reader.Read(_filePrefix + x)).collect(Collectors.toList());

        return fileNumber;
    }

    private String ConvertNumberToString(int number)
    {
        if(number >= 100)
            return Integer.toString(number);

        if(number >= 10)
            return "0" + number;

        return "00" + number;

    }
}