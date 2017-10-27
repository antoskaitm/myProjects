package main.dao;

import main.entities.interfaces.primitive.IBuilding;
import main.entities.interfaces.primitive.IElevatorRoom;
import java.io.File;

import java.io.*;
import java.nio.file.Paths;

/**
 *
 */
public class DaoState implements IDao {
    private File file;

    public DaoState() throws IOException {
        file = CurrentDir();
    }

    @Override
    public IElevatorRoom getElevatorRoom() throws IOException {
        try (ObjectInputStream stream = new ObjectInputStream(new FileInputStream(file))) {
            try {
                return (IElevatorRoom) stream.readObject();
            }
           catch (ClassNotFoundException e) {
                e.printStackTrace();
               throw new IllegalStateException(e);
            }
        }
    }

    @Override
    public void save(IElevatorRoom room,IBuilding building) throws IOException {
        try (ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(file))) {
            stream.writeObject(room);
        }
    }

    @Override
    public IBuilding getBuilding() throws IOException {
        throw new UnsupportedOperationException();
    }

    private static File CurrentDir() throws IOException {
        String path = DaoState.class.getProtectionDomain().getCodeSource().getLocation().getFile().substring(1);
        Integer index = path.lastIndexOf("classes");
        path = path.subSequence(0, index).toString();
        File file = Paths.get(path, "lift").toFile();
        return file;
    }

}
