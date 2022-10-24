package pl.edu.pg.eti.kask.rpg.serialization;

import lombok.extern.java.Log;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.logging.Level;

/**
 * Utility class for cloning objects. Storing objects in memory instead of database can be dangerous because of
 * reference sharing. WHen some objects is returned from the data store is has the same reference as the on in the
 * store. Changing something in the object leads to changing the original (the same) objects in the data store. In
 * order to avoid that deep copy (new reference) of the object can be returned. One of the ways of obtaining deep copy
 * without external libraries is serialization mechanism,
 */
@Log
public class CloningUtility {

    /**
     * Created deep copy of provided object using serialization.
     *
     * @param object object to be cloned
     * @param <T>    type of the object
     * @return deep copy of the object
     * @throws IllegalStateException on any I/O or class cast error, should not happen
     */
    @SuppressWarnings("unchecked")
    public static <T extends Serializable> T clone(T object) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();//Closing this stream has no effect
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            return (T) ois.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            log.log(Level.SEVERE, ex.getMessage(), ex);
            throw new IllegalStateException(ex);
        }

    }

}
