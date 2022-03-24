package model;

import java.util.HashSet;
import java.util.Set;

public class Salg {
    private final int id;
    private final Set<OrdreLinje> ordrer = new HashSet<>();

    public Salg(int id){
        this.id = id;
    }

}
