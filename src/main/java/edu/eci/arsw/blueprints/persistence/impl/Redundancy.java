package edu.eci.arsw.blueprints.persistence.impl;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.Filter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//@Service
public class Redundancy implements Filter {

    @Override
    public Blueprint filterPoints(Blueprint bp) {
        List<Point> pts = bp.getPoints();
        List<Point> ptsRepeat = new ArrayList<>();
        for (int i = 0; i< pts.size() - 1; i++){
            if(equalsPoints(pts.get(i),pts.get(i+1))){
                ptsRepeat.add(pts.get(i));
            }
        }
        bp.setPoints(removeRepeatedPoints(ptsRepeat,pts));
        return bp;
    }

    public boolean equalsPoints(Point p1, Point p2) {
        boolean equalsp = false;
        if (p1.getX() == p2.getX() && p1.getY() == p2.getY()) {
            equalsp = true;
        }
        return equalsp;
    }


    /**
     *
     * @param duplicatedPts
     * @param allPts
     * @return
     */
    public List<Point> removeRepeatedPoints(List<Point> allPts, List<Point> duplicatedPts){
        List<Point> listNew = new ArrayList<>();
        for (int i = 0; i< allPts.size(); i++){
            listNew.add(allPts.get(i));
        }
        for (int i = 0; i< duplicatedPts.size(); i++){
            listNew.remove(duplicatedPts.get(i));
        }
        return listNew;
    }
}
