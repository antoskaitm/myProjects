package main.entities.constants;

import main.entities.interfaces.primitive.IPersonCondition;

public class PersonsConditions implements IPersonCondition {
    private final String message;

    private PersonsConditions(String message) {
        this.message = message;
    }

    public static final IPersonCondition DIDNOT_CALL_ELEVATOR = new PersonsConditions("Person didn't call elevator room");
    public static final IPersonCondition TRY_CALL_AGAIN_ELEVATOR = new PersonsConditions("Person must try to call elevator room again");
    public static final IPersonCondition CALLED_ELEVATOR = new PersonsConditions("Person wait to into elevator room");
    public static final IPersonCondition STAND_IN_ELEVATOR = new PersonsConditions("Person stand in elevator room");
    public static final IPersonCondition SENDED_ELEVATOR = new PersonsConditions("Person wait to out from elevator room");

    public String getMessage() {
        return message;
    }
}