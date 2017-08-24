package at.tugraz.ist.compiler.rule;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

public class AlphaList {


    private List<AlphaEntry> entries;

    public AlphaList(List<AlphaEntry> entries)
    {
        this.entries = entries;
    }

    public float calculateWeight( float alpha)
    {
        throw new NotImplementedException();
    }
}
