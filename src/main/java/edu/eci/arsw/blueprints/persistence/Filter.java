package edu.eci.arsw.blueprints.persistence;

import edu.eci.arsw.blueprints.model.Blueprint;

public interface Filter {
    Blueprint filterPoints(Blueprint bp);
}

