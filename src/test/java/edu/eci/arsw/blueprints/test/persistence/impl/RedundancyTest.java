package edu.eci.arsw.blueprints.test.persistence.impl;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.impl.Redundancy;
import org.junit.Before;
import org.junit.Test;

import javax.swing.plaf.basic.BasicLabelUI;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class RedundancyTest {

    Point p1, p2, p3, p4, p5, p6, p7, p8, bp1p1, bp1p2, bp1p3, bp1p4, bp2p1, bp2p2, bp2p3;
    Point[] pointsBP1, shouldBePTSBP1,repeatsInBP1PTS, pointsBP2, shouldBePTSBP2, repeatsInBP2PTS;
    Redundancy rd;
    Blueprint bp1, bp2, bp3, bp4;


    @Before
    public void prepareTest(){
        rd = new Redundancy();
        p1 = new Point(5, 1);
        p2 = new Point(0, 0);
        p3 = new Point(5, 1);
        p4 = new Point(0, 0);
        p5 = new Point(-7, -4);
        p6 = new Point(-7, -4);
        p7 = new Point(1, 7);
        p8 = new Point(6, 4);

        bp1p1 = new Point(0, 0);
        bp1p2 = new Point(5, 0);
        bp1p3 = new Point(3, 3);
        bp1p4 = new Point(8, 3);

        bp2p1 = new Point(0,0);
        bp2p2 = new Point(2,2);
        bp2p3 = new Point(4,0);

        pointsBP1 = new Point[]{bp1p1, bp1p2, bp1p3, bp1p4, bp1p3, bp1p1, bp1p2};
        repeatsInBP1PTS = new Point[] {bp1p1, bp1p2, bp1p3};
        shouldBePTSBP1 = new Point[]{bp1p4, bp1p3, bp1p1, bp1p2};

        bp1 = new Blueprint("Miguel", "BP1", pointsBP1);

        pointsBP2 = new Point[]{bp2p1, bp2p2, bp2p3, bp2p2, bp2p2};
        repeatsInBP2PTS = new Point[]{bp2p2, bp2p2};
        shouldBePTSBP2 = new Point[]{bp2p1, bp2p3, bp2p2};
        bp2 = new Blueprint("Juan Carlos", "BP2", pointsBP2);




    }

    @Test
    public void equalsPointsTest(){
        assertTrue(rd.equalsPoints(p1, p3));
        assertTrue(rd.equalsPoints(p2, p4));
        assertTrue(rd.equalsPoints(p5, p6));
        assertFalse(rd.equalsPoints(p1, p2));
        assertFalse(rd.equalsPoints(p2, p7));
        assertFalse(rd.equalsPoints(p8, p5));
    }

    @Test
    public void removeRepeatedPointsTest(){
        List<Point> removedPTSBP1 = rd.removeRepeatedPoints(Arrays.asList(repeatsInBP1PTS), Arrays.asList(pointsBP1));
        assertEquals(Arrays.asList(shouldBePTSBP1), removedPTSBP1);

        List<Point> removedPTSBP2 = rd.removeRepeatedPoints(Arrays.asList(repeatsInBP2PTS), Arrays.asList(pointsBP2));
        assertEquals(Arrays.asList(shouldBePTSBP2), removedPTSBP2);

    }

    @Test
    public void filterPointsTest(){
        assertEquals(new Blueprint("Miguel", "BP1", shouldBePTSBP1), rd.filterPoints(bp1));
        assertEquals(new Blueprint("Juan Carlos", "BP2", shouldBePTSBP2), rd.filterPoints(bp2));
    }
}
