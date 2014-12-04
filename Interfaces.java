/**
In the Java programming language, an interface is a reference type,
similar to a class, that can contain only constants, method signatures, 
and nested types. There are no method bodies. Interfaces cannot be 
instantiated—they can only be implemented by classes or extended
by other interfaces. Extension is discussed later in this lesson. 

 Note that the method signatures have no braces and are terminated with a semicolon. 
*/
public interface OperateCar {

   // constant declarations, if any

   // method signatures
   int turn(Direction direction,   // An enum with values RIGHT, LEFT
              double radius, double startSpeed, double endSpeed);
   int changeLanes(Direction direction, double startSpeed, double endSpeed);
   int signalTurn(Direction direction, boolean signalOn);
   int getRadarFront(double distanceToCar, double speedOfCar);
   int getRadarRear(double distanceToCar, double speedOfCar);
         ......
   // more method signatures
}

public class OperateBMW760i implements OperateCar {

   // the OperateCar method signatures, with implementation --
   // for example:
   int signalTurn(Direction direction, boolean signalOn) {
      //code to turn BMW's LEFT turn indicator lights on
      //code to turn BMW's LEFT turn indicator lights off
      //code to turn BMW's RIGHT turn indicator lights on
      //code to turn BMW's RIGHT turn indicator lights off
   }

   // other members, as needed -- for example, helper classes
   // not visible to clients of the interface

}
