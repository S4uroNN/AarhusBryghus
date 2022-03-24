package application.model;

import java.util.ArrayList;
import java.util.List;

public class OrdreLinje {
    private int id;
    private int antal;

    private final List<Vare> varer = new ArrayList<Vare>();

    public OrdreLinje(int id, int antal){
        this.id = id;
        this.antal = antal;
    }
}
