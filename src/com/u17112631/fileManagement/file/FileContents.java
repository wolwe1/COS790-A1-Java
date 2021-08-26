package com.u17112631.fileManagement.file;

import java.util.ArrayList;
import java.util.List;

public class FileContents
{
    private final ArrayList<String> _lines;

    public FileContents()
    {
        _lines = new ArrayList<>();
    }

    public void Add(String line)
    {
        _lines.add(line);
    }

    public String Get(int index)
    {
        return _lines.get(index);
    }

    public List<String> GetLinesFrom(int i, int numberOfItems)
    {
        return _lines.subList(i, numberOfItems);
    }
}
