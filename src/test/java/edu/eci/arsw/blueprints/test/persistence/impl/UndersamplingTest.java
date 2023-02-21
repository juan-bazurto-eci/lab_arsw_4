package edu.eci.arsw.blueprints.test.persistence.impl;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.impl.Redundancy;
import edu.eci.arsw.blueprints.persistence.impl.Undersampling;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class UndersamplingTest {

    Blueprint bp1,shouldBeBP1, bp2, shouldBeBP2, bp3, shouldBeBP3;

    Point bp1p1, bp1p2, bp1p3, bp1p4, bp1p5, bp1p6, bp1p7, bp1p8;
    Point bp2p1,bp2p2,bp2p3,bp2p4,bp2p5,bp2p6;
    Point bp3p1,bp3p2,bp3p3,bp3p4;
    Undersampling us = new Undersampling();

    @Before
    public void prepareTest(){
        bp1p1 = new Point(0,0);
        bp1p1 = new Point(0,1);
        bp1p1 = new Point(0,2);
        bp1p1 = new Point(1,2);
        bp1p1 = new Point(2,2);
        bp1p1 = new Point(2,1);
        bp1p1 = new Point(2,0);
        bp1p1 = new Point(1,0);

        bp1 = new Blueprint("Juan Camilo", "BP1",new Point[]{bp1p1, bp1p2, bp1p3, bp1p4, bp1p5, bp1p6, bp1p7, bp1p8});
        shouldBeBP1 = new Blueprint("Juan Camilo", "BP1", new Point[]{bp1p1, bp1p3, bp1p5, bp1p7});

        bp2p1 = new Point(0,0);
        bp2p2 = new Point(1,1);
        bp2p3 = new Point(2,2);
        bp2p4 = new Point(3,1);
        bp2p5 = new Point(4,0);
        bp2p6 = new Point(2,0);

        bp2 = new Blueprint("Juan Carlos", "BP2", new Point[]{bp2p1,bp2p2,bp2p3,bp2p4,bp2p5,bp2p6});
        shouldBeBP2 = new Blueprint("Juan Carlos", "BP2", new Point[]{bp2p1,bp2p3,bp2p5});
    }

    @Test
    public void filterPointsTest(){
        Blueprint newBP1 = us.filterPoints(bp1);
        Blueprint newBP2 = us.filterPoints(bp2);
        assertEquals(shouldBeBP1, newBP1);
        assertEquals(shouldBeBP2, newBP2);
    }
}
