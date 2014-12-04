/**
1. Member variables in a class—these are called fields.

2. Variables in a method or block of code—these are called local variables.

3. Variables in method declarations—these are called parameters. 


Field declarations are composed of three components, in order:

    Zero or more modifiers, such as public or private.
    The field's type.
    The field's name.

Access Modifiers


    public modifier—the field is accessible from all classes.
    private modifier—the field is accessible only within its own class.


In the spirit of encapsulation, it is common to make fields private. This means that they can only be 
directly accessed from the Bicycle class. We still need access to these values, however. 
This can be done indirectly by adding public methods that obtain the field values for us: 


public class Bicycle {
	
	private int cadence;
	private int gear;
	private int speed;
	
	public Bicycle(int startCadence, int startSpeed, int startGear) {
		gear = startGear;
		cadence = startCadence;
		speed = startSpeed;
	}
	
	public int getCadence() {
		return cadence;
	}
	
	public void setCadence(int newValue) {
		cadence = newValue;
	}
	
	public int getGear() {
		return gear;
	}
	
	public void setGear(int newValue) {
		gear = newValue;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public void applyBrake(int decrement) {
		speed -= decrement;
	}
	
	public void speedUp(int increment) {
		speed += increment;
	}
	
}



*/
