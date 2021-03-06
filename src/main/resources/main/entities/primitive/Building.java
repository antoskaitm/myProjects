package main.entities.primitive;

import main.entities.interfaces.IAutomobileElevatorRoom;
import main.entities.interfaces.primitive.IFloorsRange;
import main.entities.primitive.abstractclass.AbstractBuilding;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * numbers of floors begin from 0
 */
public class Building extends AbstractBuilding{
	private static final long serialVersionUID = 0;
	private Integer floorHeight;

	public Building(IFloorsRange floorsRange, int buildingHeight, IAutomobileElevatorRoom<Person>... elevators) {
		super(floorsRange,elevators);
		Integer floorCount = floorsRange.getFloorCount();
		Integer minFloorHeight = 3;
		if (buildingHeight < floorCount * minFloorHeight) {
			throw new IllegalArgumentException("Value of building height for lastFloor must be more than "
					+ (floorCount * minFloorHeight));
		}
		this.floorHeight = buildingHeight / floorCount;
	}

	@Override
	public Integer getFloorHeight(Integer floor) {
		return floorHeight;
	}

	@Override
	protected void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
		super.readObject(stream);
		long serialVersionUID = stream.readLong();
		floorHeight = (Integer) stream.readObject();
	}

	@Override
	protected void writeObject(ObjectOutputStream stream) throws IOException {
		super.writeObject(stream);
		stream.writeLong(serialVersionUID);
		stream.writeObject(floorHeight);
	}
}
