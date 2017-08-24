package at.tugraz.ist.compiler.rule;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;

public class AlphaList {

    private List<AlphaEntry> entries = new ArrayList<>();;

    static AlphaList getDefaultAlphaList()
    {
        List<AlphaEntry> entires = new ArrayList<>();
        entires.add(new AlphaEntry("1"));
        return new AlphaList(entires);
    }

    private void add(AlphaEntry alphaEntry) {
    }


    public AlphaList(List<AlphaEntry> entries)
    {
        this.entries = entries;
    }

    public float calculateWeight( float alpha)
    {
        throw new NotImplementedException();
    }
}
