package main.entities.primitive.abstractclass;

import main.entities.interfaces.IAutomobileElevatorRoom;
import main.entities.interfaces.primitive.*;
import main.entities.primitive.Person;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public abstract class AbstractBuilding<T extends IRequesting> implements IBuilding,Serializable{
	private static final long serialVersionUID = 0;
	private IFloorsRange floorsRange;
	private  IAutomobileElevatorRoom<T>[] elevators;

	public AbstractBuilding(IFloorsRange floorsRange,  IAutomobileElevatorRoom<T>... elevators) {
		this.elevators = elevators;
		this.floorsRange = floorsRange;
		checkFloorsRanges(elevators);
		this.elevators = elevators;
	}

	@Override
	public IFloorsRange getFloorsRange() {
		return this.floorsRange;
	}

	@Override
	public  IAutomobileElevatorRoom<T> getElevator(Integer elevatorNumber) {
		return elevators[elevatorNumber];
	}

	private void checkFloorsRanges(IElevator<T>[] elevators) {
		for (int index = 0; index < elevators.length; index++) {
			if (!floorsRange.contains(elevators[index].getFloorsRange())) {
				throw new IllegalStateException("Elevator by index " + index + " go out of building floors range");
			}
		}
	}

	protected void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
		long serialVersionUID = stream.readLong();
		floorsRange = (IFloorsRange) stream.readObject();
		elevators = (IAutomobileElevatorRoom<T>[]) stream.readObject();
	}

	protected void writeObject(ObjectOutputStream stream) throws IOException {
		stream.writeLong(serialVersionUID);
		stream.writeObject(floorsRange);
		stream.writeObject(elevators);
	}
}
