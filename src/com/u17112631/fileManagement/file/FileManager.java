package com.u17112631.fileManagement.file;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class FileManager
{
    private String _basePath;

    public FileManager() throws IOException {
        _basePath = GetBasePath();
        System.out.println("Using base path: " + _basePath);
    }

    private String GetBasePath() throws IOException {
        File currentDirFile = new File(".");
        String helper = currentDirFile.getAbsolutePath();
        String absPath = helper.substring(0, helper.length() - 1);
        absPath += "cases\\";
        return absPath;
    }

    private String GetFilePath()
    {
        return _basePath;
    }

    public FileContents Read(String fileName)
    {
        FileContents contents = null;

        var targetFile = GetFilePath() + fileName + ".txt";
        try
        {
            var file = new File(targetFile);
            Scanner myReader = new Scanner(file);

            contents = ReadFile(myReader);
            contents.setFileName(fileName);
            myReader.close();
        }
        catch(Exception e)
        {
            System.out.println("Exception: " + e.getMessage());
        }

        return contents;
    }

    private FileContents ReadFile(Scanner streamReader)
    {
        var contents = new FileContents();

        while (streamReader.hasNextLine())
        {
            String line = streamReader.nextLine();
            contents.Add(line.trim());
        }

        return contents;
    }
}
