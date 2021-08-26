package com.u17112631.models;

import com.u17112631.helpers.Tuple;

import java.util.ArrayList;

public class Piece
{
    private ArrayList<Tuple<Integer, Integer>> _coords;

    public Piece()
    {
        _coords = new ArrayList<>();
    }

    public void AddCoord(int x, int y)
    {
        _coords.add(new Tuple<Integer, Integer>(x,y));
    }
    public String GetInfo()
    {
        var builder = new StringBuilder();

        for (var i = 0; i < _coords.size(); i++)
        {
            var coords = _coords.get(i);
            builder.append(i + "-" + coords.Item1 + ":" + coords.Item2);
        }

        return builder.toString();
    }
}