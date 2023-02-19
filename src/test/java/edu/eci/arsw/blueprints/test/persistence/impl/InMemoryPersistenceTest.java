/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.test.persistence.impl;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.impl.InMemoryBlueprintPersistence;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hcadavid
 */
public class InMemoryPersistenceTest {

    @Test
    public void saveNewAndLoadTest() throws BlueprintPersistenceException, BlueprintNotFoundException {
        InMemoryBlueprintPersistence ibpp = new InMemoryBlueprintPersistence();

        Point[] pts0 = new Point[] { new Point(40, 40), new Point(15, 15) };
        Blueprint bp0 = new Blueprint("mack", "mypaint", pts0);

        ibpp.saveBlueprint(bp0);

        Point[] pts = new Point[] { new Point(0, 0), new Point(10, 10) };
        Blueprint bp = new Blueprint("john", "thepaint", pts);

        ibpp.saveBlueprint(bp);

        assertNotNull("Loading a previously stored blueprint returned null.",
                ibpp.getBlueprint(bp.getAuthor(), bp.getName()));

        assertEquals("Loading a previously stored blueprint returned a different blueprint.",
                ibpp.getBlueprint(bp.getAuthor(), bp.getName()), bp);

    }

    @Test
    public void saveExistingBpTest() {
        InMemoryBlueprintPersistence ibpp = new InMemoryBlueprintPersistence();

        Point[] pts = new Point[] { new Point(0, 0), new Point(10, 10) };
        Blueprint bp = new Blueprint("john", "thepaint", pts);

        try {
            ibpp.saveBlueprint(bp);
        } catch (BlueprintPersistenceException ex) {
            fail("Blueprint persistence failed inserting the first blueprint.");
        }

        Point[] pts2 = new Point[] { new Point(10, 10), new Point(20, 20) };
        Blueprint bp2 = new Blueprint("john", "thepaint", pts2);

        try {
            ibpp.saveBlueprint(bp2);
            fail("An exception was expected after saving a second blueprint with the same name and autor");
        } catch (BlueprintPersistenceException ex) {

        }

    }

    @Test
    public void getBluePrintShouldReturnBP() {
        InMemoryBlueprintPersistence ibpp = new InMemoryBlueprintPersistence();
        Point[] pts1 = new Point[] { new Point(0, 0), new Point(10, 10) };
        Blueprint bp1 = new Blueprint("john", "thepaint#1", pts1);
        Point[] pts2 = new Point[] { new Point(10, 10), new Point(20, 20) };
        Blueprint bp2 = new Blueprint("john", "thepaint#2", pts2);
        try {
            ibpp.saveBlueprint(bp1);
            ibpp.saveBlueprint(bp2);
            assertNotNull(ibpp.getBlueprint(bp1.getAuthor(), bp1.getName()));
            assertNotNull(ibpp.getBlueprint(bp2.getAuthor(), bp2.getName()));
            assertNotEquals(ibpp.getBlueprint(bp1.getAuthor(), bp1.getName()), bp2);
            assertEquals(ibpp.getBlueprint(bp1.getAuthor(), bp1.getName()), bp1);
            assertEquals(ibpp.getBlueprint(bp2.getAuthor(), bp2.getName()), bp2);
        } catch (BlueprintPersistenceException | BlueprintNotFoundException ex) {
            fail("Blueprint persistence failed inserting blueprints.");
        }
    }

    @Test
    public void getBluePrintByAuthShouldReturnBPSet() {
        InMemoryBlueprintPersistence ibpp = new InMemoryBlueprintPersistence();
        Set<Blueprint> setBpOriginal = new HashSet<>();
        Point[] pts1 = new Point[] { new Point(0, 0), new Point(10, 10) };
        Blueprint bp1Aut1 = new Blueprint("john", "thepaint#11", pts1);
        Blueprint bp2Aut1 = new Blueprint("john", "thepaint#21", pts1);
        Blueprint bp3Aut1 = new Blueprint("john", "thepaint#31", pts1);
        setBpOriginal.add(bp1Aut1);
        setBpOriginal.add(bp2Aut1);
        setBpOriginal.add(bp3Aut1);
        Point[] pts2 = new Point[] { new Point(10, 10), new Point(20, 20) };
        Blueprint bp1Aut2 = new Blueprint("john", "thepaint#12", pts2);
        Blueprint bp2Aut2 = new Blueprint("john", "thepaint#22", pts2);
        try {
            ibpp.saveBlueprint(bp1Aut1);
            ibpp.saveBlueprint(bp2Aut1);
            ibpp.saveBlueprint(bp3Aut1);
            ibpp.saveBlueprint(bp1Aut2);
            ibpp.saveBlueprint(bp2Aut2);
            Set<Blueprint> setBpByMethod = ibpp.getBlueprintsByAuthor(bp1Aut1.getAuthor());
            assertTrue(setBpByMethod.containsAll(setBpOriginal));
            assertNotEquals(setBpByMethod, setBpOriginal);
        } catch (BlueprintPersistenceException | BlueprintNotFoundException ex) {
            fail("Blueprint persistence failed inserting blueprints.");
        }
    }

}
