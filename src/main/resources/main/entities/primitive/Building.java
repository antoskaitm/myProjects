package main.entities.primitive;

import main.entities.interfaces.IAutomobileElevatorRoom;
import main.entities.interfaces.primitive.IBuilding;
import main.entities.view.AbstractElevatorRoomView;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**numbers of floors begin from 0
 *
 */
public class Building implements IBuilding, Serializable {
    static final long serialVersionUID = -3000000000000L;
    private Integer lowerBorder;
    private Integer upperBorder;
    private Integer floorCount;
    private Integer floorHeight;

    private IAutomobileElevatorRoom[] elevators;

    public Building(int lowerBorder,int floorCount, int buildingHeight,IAutomobileElevatorRoom... elevators) {
        this.elevators = elevators;
        Integer minFloorCount = 3;
        if (floorCount < minFloorCount) {
            throw new IllegalArgumentException("Floor count must be " + minFloorCount + " or more");
        }
        Integer minFloorHeight = 3;
        if (buildingHeight < floorCount * minFloorHeight) {
            throw new IllegalArgumentException("Value of building height for lastFloor must be more than "
                    + (floorCount * minFloorHeight));
        }
        this.lowerBorder = lowerBorder;
        this.floorCount = floorCount;
        this.upperBorder = floorCount - 1 + lowerBorder;
        floorHeight = buildingHeight / floorCount;
    }

    private void checkFloor(int floor) {
        if (floor < lowerBorder || floor > upperBorder) {
            throw new NullPointerException("Floor does not exist");
        }
    }

    public Boolean hasFloor(int floor) {
        return floor >= lowerBorder && floor <=upperBorder;
    }

    public Integer getLastFloor() {
        return upperBorder;
    }

    public Integer getFloorCount() {
        return floorCount;
    }

    public Integer getFloorHeight() {
        return floorHeight;
    }

    public Integer getGroundFloor() {
        return lowerBorder;
    }

    @Override
    public IAutomobileElevatorRoom getElevator(Integer elevatorNumber)
    {
        if(elevators.length>elevatorNumber && elevatorNumber>=0)
        {
            return  new AbstractElevatorRoomView(elevators[elevatorNumber]){
                @Override
                public Integer callElevator(int floor) {
                    checkFloor(floor);
                    return super.callElevator(floor);
                }

                @Override
                public Boolean sendElevator(int floor, int personId) {
                    checkFloor(floor);
                    return super.sendElevator(floor,personId);
                }
            };
        }
        throw new IllegalStateException("Elevator isn't founded");
    }

    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        long serialVersionUID = stream.readLong();
        floorHeight = (Integer) stream.readObject();
        floorCount = (Integer) stream.readObject();
    }

    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.writeLong(serialVersionUID);
        stream.writeObject(floorHeight);
        stream.writeObject(floorCount);
    }
}
