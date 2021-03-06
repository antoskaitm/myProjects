package main.entities.primitive;

import main.entities.interfaces.primitive.IRoom;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class Room<T> implements IRoom<T>, Serializable {

	private static final long serialVersionUID = -4000000000000L;

	private Integer size;
	private Integer count = 0;
	private transient List<T> pool;

	public Room(int size) {
		this.size = size;
		pool = new LinkedList<>();
	}

	public boolean admit(T request) {
		return count < size && pool.add(request);
	}

	public boolean release(T request) {
		return pool.remove(request);
	}

	private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
		long serialVersionUID = stream.readLong();
		size = (Integer) stream.readObject();
		this.count = 0;
		pool = new LinkedList<>();
	}

	private void writeObject(ObjectOutputStream stream) throws IOException {
		stream.writeLong(serialVersionUID);
		stream.writeObject(size);
	}
}
