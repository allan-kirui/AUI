package pl.edu.pg.eti.kask.rpg.effect;

import pl.edu.pg.eti.kask.rpg.creature.entity.Creature;

/**
 * Skills' effect contract. New effects to be used with skills should be implemented using this interface.
 */
public interface Effect {

    /**
     * Method used to make effect of some skill. Generally the source creature makes some skill targeting the target
     * creature. State of both object can change.
     *
     * @param source creature using skill
     * @param target creature being target of the skill
     */
    void makeEffect(Creature source, Creature target);

}
