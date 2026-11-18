/*
    ParkingLot Automation System

    Step 1 : Create required enums
    Step 2 : Vehicle Hierarchy
    Step 3 : Vehicle Factory Creation (Factory Pattern)
    Step 4 : ParkingSpot Hierarchy
    Step 5 : ParkingObserver
    Step 6 : ParkingFloor class
    Step 7 : ParkingDisplayBoard (Observer Pattern)
    Step 8 : ParkingStrategy Class (strategy Pattern)
    Step 9 : PricingStrategy Class (strategy Pattern)
    Step 10 : PaymentStrategy
    Step 11 : ParkingTicket Class
    Step 12 : EntryGate Class
    Step 13 : ExitGate Class
    Step 14 : ParkingLot Class (Singleton Pattern)
    Step 15 : Main Class (Controller)
*/

// import required libraries
import java.util.*;
import java.time.Duration;
import java.time.LocalDateTime;

//////////////////////////////////////////////////////////
//
//  Step 1 : Create Enumns
//  Description : It is used to create fixed constants 
//                 which are required throughout the project 
//
//////////////////////////////////////////////////////////


// Represents different types of vehicles supported by the project
enum VehicleType
{
    BIKE,
    CAR,
    TRUCK
}

// Represents different types of spots
enum SpotType
{
    BIKE,
    CAR,
    TRUCK
}

// Represents current state of parking ticket
enum TicketStatus
{
    ACTIVE,
    CLOSED
}

//////////////////////////////////////////////////////////
//
//  Step 2          : Create Vehicle class hierarchy 
//  Description     : It is used to create multiple types of vehicles which represents the types of vehicles
//  Concepts Used   : Abstraction, Inheritance, Polymorphism, Encapsulation
//
//////////////////////////////////////////////////////////

// Class which represnts a Generic Vehicle type
abstract class Vehicle
{
    //Abstracted (Hidden) Characteristics of class

    private String vehicleNumber;

    private VehicleType VehicleType;

    // Parametrised Constructor
    public Vehicle(String vehicleNumber, VehicleType vehicleType)
    {
        this.vehicleNumber = vehicleNumber;
        this.VehicleType = vehicleType;
    }

    // Concrete getter method
    public VehicleType getVehicleType()
    {
        return this.VehicleType;
    }

    // Concrete getter method
    public String getVehicleNumber()
    {
        return this.vehicleNumber;
    }

    // Every concrete class will provide its own implementation
    public abstract void display();
}


// Class which represents the vehicle type as Bike
class Bike extends Vehicle
{
    // Parametrised Constructor
    public Bike(String vehicleNumber)
    {
        // Calls Vehicle class constructor
        super(vehicleNumber, VehicleType.BIKE);
    }

    // Method Overriding
    @Override 
    public void display()
    {
        System.out.println("Bike : " + getVehicleNumber());
    }
}

// Class which represents the vehicle type as Car
class Car extends Vehicle
{
    // Parametrised Constructor
    public Car(String vehicleNumber)
    {
        // Calls Vehicle class constructor
        super(vehicleNumber, VehicleType.CAR);
    }

    // Method Overriding
    @Override 
    public void display()
    {
        System.out.println("Car : " + getVehicleNumber());
    }
}

// Class which represents the vehicle type as Truck
class Truck extends Vehicle
{
    // Parametrised Constructor
    public Truck(String vehicleNumber)
    {
        // Calls Vehicle class constructor
        super(vehicleNumber, VehicleType.TRUCK);
    }

    // Method Overriding
    @Override 
    public void display()
    {
        System.out.println("Truck : " + getVehicleNumber());
    }
}

//////////////////////////////////////////////////////////
//
//  Step 3          : Create VehicleFactory class
//  Description     : It is used to centralised the creation of vehicle objects
//  Concepts Used   : Factory Design  Pattern
//
//////////////////////////////////////////////////////////


class VehicleFactory
{
    // Create and return the desired class object
    public static Vehicle createVehicle(VehicleType type, String number)
    {
        switch(type)
        {
            case BIKE :
                return new Bike(number);

            case CAR :
                return new Car(number);
            
            case TRUCK:
                return new Truck(number);

            default :
                throw new IllegalArgumentException("Invalid Vehicle Type");
        }
    }
}



//////////////////////////////////////////////////////////
//
//  Step 4          : Create ParkingSpot Hierarchy
//  Description     : It is used to create hierarchy of Parking Spots
//  Concepts Used   : Enclapsulation, abstraction, Inheritance, Polymorphism
//
//////////////////////////////////////////////////////////

abstract class ParkingSpot
{
    // unique number for parking spot (Primary Key)
    private int spotNumber;

    // Type of parking spot
    private SpotType spotType;

    // Indicates whether spot is currently occupied or not
    private boolean occupied;

    // Stores information about the vehicle 
    private Vehicle vehicle;                // Composition (reusability)

    // Paramerized Constructor
    public ParkingSpot(int spotNumber, SpotType spotType)
    {
        this.spotNumber = spotNumber;
        this.spotType = spotType;
        
        // initialised with default values
        this.occupied = false;
        this.vehicle  = null;
    }

    public int getSpotNumber()
    {
        return this.spotNumber;
    }

    public SpotType getSpotType()
    {
        return this.spotType;
    }

    public boolean isOccupied()
    {
        return this.occupied;
    }

    public Vehicle getVehicle()
    {
        return this.vehicle;
    }

    // It is used to park the vehicle
    public void parkVehicle(Vehicle vehicle)
    {
        if(this.occupied == true)
        {
            throw new RuntimeException("Parking Spot is already occupied..");
        }
        else
        {
            this.vehicle = vehicle;
            this.occupied = true;
        }
    }

    // It is used to remove the vehicle
    public Vehicle removeVehicle()
    {
        if(this.occupied == true)
        {
            Vehicle temp = this.vehicle;

            this.vehicle = null;
            this.occupied = false;
    
            return temp;
        }
        else
        {
            throw new RuntimeException("Parking slot is already empty");
        }
    }

    // This  method decides whether we can park it in the spot or not
    public abstract boolean canFitVehicle(Vehicle vehicle);

    public void display()
    {
        System.out.println("Spot " + spotNumber + " [ "+ spotType +" ]");

        if(this.occupied == true)
        {
            System.out.println("Occupied by " + vehicle.getVehicleNumber());
        }
        else
        {
            System.out.println("Spot is available");
        }
    }
}// End of PaekingSpot class

class BikeSpot extends ParkingSpot
{
    public BikeSpot(int spotNumber)
    {
        super(spotNumber, SpotType.BIKE);
    }

    @Override 
    public boolean canFitVehicle(Vehicle vehicle)
    {
        if(vehicle.getVehicleType() == VehicleType.BIKE)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}


class CarSpot extends ParkingSpot
{
    public CarSpot(int spotNumber)
    {
        super(spotNumber, SpotType.CAR);
    }

    @Override 
    public boolean canFitVehicle(Vehicle vehicle)
    {
        if(vehicle.getVehicleType() == VehicleType.CAR)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}


class TruckSpot extends ParkingSpot
{
    public TruckSpot(int spotNumber)
    {
        super(spotNumber, SpotType.TRUCK);
    }

    @Override 
    public boolean canFitVehicle(Vehicle vehicle)
    {
        if(vehicle.getVehicleType() == VehicleType.TRUCK)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}



//////////////////////////////////////////////////////////
//
//  Step 5          : Create ParkingObserver class
//  Description     : It is used to Automatically update display board when the parking availability changes
//  Concepts Used   : Observer Design Pattern
//
//////////////////////////////////////////////////////////

interface ParkingObserver
{
    void Update();
}

//////////////////////////////////////////////////////////
//
//  Step 6          : Create ParkingFloor class
//  Description     : It is used to manage parking floor
//  Concepts Used   : Composition, ArrayList, ObjectManagement
//
//////////////////////////////////////////////////////////

class ParkingFloor
{
    // Unique floor number
    private int floorNumber;

    // Collection of all parking spots
    private List<ParkingSpot> parkingSpots;

    // Collection of observers registered for the floor
    private List<ParkingObserver> observers;

    public ParkingFloor(int floorNumber)
    {
        this.floorNumber = floorNumber;
        this.parkingSpots = new ArrayList<>();
        this.observers = new ArrayList<>();
    }

    public int getFloorNumber()
    {
        return this.floorNumber;
    }

    public void addParkingSpot(ParkingSpot spot)
    {
        parkingSpots.add(spot);
    }
 
    public void addObserver(ParkingObserver observer)
    {
        observers.add(observer);
    }

    private void notifyObservers()
    {
        for(ParkingObserver observer : observers)
        {
            observer.Update();
        }
    }

    // Method is going to search parking spot for specific type of vehicle
    public ParkingSpot findAvailableSpot(Vehicle vehicle)
    {
        
        for(ParkingSpot spot : parkingSpots)
        {
            if(!spot.isOccupied() && spot.canFitVehicle(vehicle))
            {
                return spot;
            }
        }

        return null;
    }

    // Called when new vehicle gets parked
    public void occupySpot(ParkingSpot spot, Vehicle vehicle)
    {
        // Allocate spot for vehicle
        spot.parkVehicle(vehicle);

        // notify  all observers about availability of spots
        notifyObservers();
    }

    public void releaseSpot(ParkingSpot spot)
    {
        // Release the already allocated spot
        spot.removeVehicle();
                
        // notify  all observers about availability of spots
        notifyObservers();
    }

    public int getAvailableCount(SpotType type)
    {
        int Count = 0;
        
        for(ParkingSpot spot : parkingSpots)
        {
            if(spot.getSpotType() == type && !spot.isOccupied())
            {
                Count++;
            }
        }

        return Count;
    }

    // Display all parking spots on specific floor
    public void displayFloor()
    {
        System.out.println("Floor : " + floorNumber);  
        
        for(ParkingSpot spot : parkingSpots)
        {
            spot.display();
        }
    }
}


//////////////////////////////////////////////////////////
//
//  Step 7          : Create ParkingDisplayBoard class
//  Description     : It is used to create a class which displays the parking status
//  Subject         -> ParkingFloor
//  Observer        -> ParkingDisplayBoard
//  Note            : Any observer is going to observer subject
//                    There will be multiple observers for one subject
//  Concepts Used   : Observer Design Pattern
//
//////////////////////////////////////////////////////////


class ParkingDisplayBoard implements ParkingObserver
{
    // Floor whose availability is diaplayed by this board
    private ParkingFloor floor;

    // Constructor
    public ParkingDisplayBoard(ParkingFloor floor)
    {
        this.floor = floor;
    }

    // Automatically called whenever floor availability changes
    @Override 
    public void Update()
        {
            System.out.println();

            System.out.println("---------------Display Board--------------------");

            System.out.println("Floor :" +floor.getFloorNumber());
            System.out.println("Availabe Bike Spot :"+floor.getAvailableCount(SpotType.BIKE));
            System.out.println("Availabe Car Spot :"+floor.getAvailableCount(SpotType.CAR));
            System.out.println("Availabe Truck Spot :"+floor.getAvailableCount(SpotType.TRUCK));

            System.out.println("------------------------------------------------");

            System.out.println();
        }
}

// We can create a new observer for the same subject(parking floot)
/*
    class ParkingWebsites implement ParkingObserver
    {
        public void update()
        {
        
        }
    }
*/



//////////////////////////////////////////////////////////
//
//  Step 7          : Create ParkingDisplayBoard class
//  Description     : It is used to create a class which displays the parking status
//  Subject         -> ParkingFloor
//  Observer        -> ParkingDisplayBoard
//  Note            : Any observer is going to observer subject
//                    There will be multiple observers for one subject
//  Concepts Used   : Observer Design Pattern
//
//////////////////////////////////////////////////////////


//////////////////////////////////////////////////////////
//
//  Step 8          : Create ParkingStrategy class
//  Description     : It is used to create a class ParkingStrategy which is responsible to decide 
//                      the ParkingSpot selection 
//  Concepts Used   : Strategy Design Pattern
//
//////////////////////////////////////////////////////////

// Defines a common concepts for parking selection algorithm
interface ParkingStrategy
{
    ParkingSpot findSpot(List<ParkingFloor> floors, Vehicle vehicle);
}

// Selects the first available parking spots
class firstAvailableParkingStrategy implements ParkingStrategy
{
    @Override 
    public ParkingSpot findSpot(List<ParkingFloor> floors, Vehicle vehicle)
    {
        // Iterate over all available floors
        for(ParkingFloor floor : floors)
        {
            ParkingSpot spot = floor.findAvailableSpot(vehicle);

            if(spot != null)
            {
                return spot;
            }
        }
        return null;
    }
}

//////////////////////////////////////////////////////////
//
//  Step 9          : Create PricingStrategy Class
//  Description     : It s used to create class PricingStrategy
//                     It keeps the pricing algorithm independent of exit logic
//  Concepts Used   : Strategy Design Pattern
//
/////////////////////////////////////////////////////////

interface PricingStartegy
{
    double calculatePrice(Vehicle vehicle, long hours);
}

class NormalPricingStrategy implements PricingStartegy
{
    @Override 
    public double calculatePrice(Vehicle vehicle, long hours)
    {
        if(hours <= 0)
        {
            hours = 1;
        }

        switch (vehicle.getVehicleType()) 
        {
            case BIKE:
                return hours * 20;

            case CAR:
                return hours * 50;

            case TRUCK:
                return hours * 100;

            default:
                return 0;
        }
    }
}

class WeekendPricingStrategy implements PricingStartegy
{
    @Override 
    public double calculatePrice(Vehicle vehicle, long hours)
    {
        if(hours <= 0)
        {
            hours = 1;
        }

        switch (vehicle.getVehicleType()) 
        {
            case BIKE:
                return hours * 40;

            case CAR:
                return hours * 100;

            case TRUCK:
                return hours * 200;

            default:
                return 0;
        }
    }
}


//////////////////////////////////////////////////////////
//
//  Step 10         : Create PaymentStrategy Class
//  Description     : It s used to create class PaymentStrategy
//                     It supports different types of payment methods
//  Concepts Used   : Strategy Design Pattern
//
/////////////////////////////////////////////////////////


// Common contract for all payment methods
interface PaymentStrategy
{
    void pay(double amount);
}

class UPIPayment implements PaymentStrategy
{
    @Override 
    public void pay(double amount)
    {
        System.out.println("UPI Payment Successfully : Rs. " + amount);
    }
}


class CardPayment implements PaymentStrategy
{
    @Override 
    public void pay(double amount)
    {
        System.out.println("Card Payment Successfully : Rs. " + amount);
    }
}

class CashPayment implements PaymentStrategy
{
    @Override 
    public void pay(double amount)
    {
        System.out.println("Cash Payment Successfully : Rs. " + amount);
    }
}

//////////////////////////////////////////////////////////
//
//  Step 11         : Create ParkingTicket Class
//  Description     : It s used to represent oe complete parking transaction
//
/////////////////////////////////////////////////////////

class ParkingTicket 
{
    // Used for generating unique tickets
    private static int counter = 1000;

    // Ticket number for unique ticket
    private int ticketNumber;

    // Vehicle asssociated with that ticket
    private Vehicle vehicle;

    // Floor on which the vehicle is parked
    private ParkingFloor floor;

    // Actual spot on which the vehicle is parked
    private ParkingSpot spot;

    // Time at which vehicle arrives
    private LocalDateTime entryTime;

    // Time at which vehicle exited from parking floor
    private LocalDateTime exitTime;

    // It maintains the status of the ticket
    private TicketStatus status;

    // Constructor
    public ParkingTicket(
            Vehicle vehicle,
            ParkingFloor floor,
            ParkingSpot spot
        )
    {
        this.ticketNumber = ++counter;
        this.vehicle = vehicle;
        this.floor = floor;
        this.spot = spot;
        this.entryTime = LocalDateTime.now();
        this.status = TicketStatus.ACTIVE;
    }

    // Getter method for ticket number
    public int getTicketNumber()
    {
        return this.ticketNumber;
    }


    // Getter method for vehicle
    public Vehicle getVehicle()
    {
        return this.vehicle;
    }

    // Getter method for floor 
    public ParkingFloor getFloor()
    {
        return this.floor;
    }

    // Getter method for floor 
    public ParkingSpot getSpot()
    {
        return this.spot;
    }

    // Getter method for entryTime 
    public LocalDateTime getEntryTime()
    {
        return this.entryTime;
    }

    // Getter method for exit time 
    public LocalDateTime getExitTime()
    {
        return this.exitTime;
    }

    // Getter method for status 
    public TicketStatus getStatus()
    {
        return this.status;
    }

    // Method gets called when vehicle is going out
    public void closeTicket()
    {
        this.exitTime = LocalDateTime.now();

        this.status = TicketStatus.CLOSED;
    }


    // calculate the total number of hours the vehicle is parked
    public long calculateHours()
    {
        LocalDateTime  endTime;

        if(exitTime == null)
        {
            endTime = LocalDateTime.now();
        }
        else
        {
            endTime = exitTime;
        }

        // calculate the actual time
        long minutes = Duration.between(entryTime, endTime).toMinutes();

        // calculate minues to hours
        long hours = minutes / 60;

        if(minutes % 60 != 0)
        {
            hours++;
        }

        if(hours == 0)
        {
            hours = 1;
        }

        return hours;
    }

    // It will display complete ticket on screen
    public void displayTicket()
    {
            System.out.println();

            System.out.println("------------------------------------------------");
            System.out.println("---------------Parking Ticket-------------------");
            System.out.println("------------------------------------------------");

            System.out.println("Ticket Number :" + this.ticketNumber);
            System.out.println("Vehicle Number :" + this.vehicle.getVehicleNumber());
            System.out.println("Vehicle Type :" + this.vehicle.getVehicleType());
            System.out.println("Floor Number :" + this.floor.getFloorNumber());
            System.out.println("Spot Number :" + this.spot.getSpotNumber());
            System.out.println("Entry Time :" + this.entryTime);
            System.out.println("Status  :" + this.status);

            System.out.println("------------------------------------------------");

            System.out.println();
    }
}

//////////////////////////////////////////////////////////
//
//  Step 12         : Create EntryGate Class
//  Description     : It is used to handle entry of vehicle and is ticket generation
//
/////////////////////////////////////////////////////////

class EntryGate
{
    private int gateNumber;

    public EntryGate(int gateNumber)
    {
        this.gateNumber = gateNumber;
    }

    public int getGateNumber()
    {
        return this.gateNumber;
    }

    // It generates the new parking ticket when vehicle enters 
    public ParkingTicket generateTicket(Vehicle vehicle, ParkingFloor floor, ParkingSpot spot)
    {
        System.out.println("Vehicle entring from gate : " + this.gateNumber);

        // New parking ticket gets generated for the vehicle 
        return new ParkingTicket(vehicle, floor, spot);
    }

}

//////////////////////////////////////////////////////////
//
//  Step 13         : Create ExitGate Class
//  Description     : It is used to billing and payment during the vehicle exit
//
/////////////////////////////////////////////////////////

class ExitGate
{
    private int gateNumber;

    public ExitGate(int gateNumber)
    {
        this.gateNumber = gateNumber;
    }

    public int getGateNumber()
    {
        return this.gateNumber;
    }

    // This performs complete exit operatinos
    public void processExit(
                ParkingTicket ticket, 
                PricingStartegy pricingStartegy,
                PaymentStrategy paymentStrategy
            )
    {
        // Step 1 : Close the ticket and record the exit time
        ticket.closeTicket();

        // Step 2 : Calculate the parking duration
        long hours = ticket.calculateHours();

        // Step 3 : Calculate the parking charges
        double amount = pricingStartegy.calculatePrice(ticket.getVehicle(), hours);

        System.out.println();

        System.out.println("Vehicle exiting from gate : " + gateNumber);

        System.out.println("Parking duration : " + hours);

        System.out.println("Parking Charges : " + amount);

        // Step 4 : Process the payment using selected payment strategy
        paymentStrategy.pay(amount);
    }
}

//////////////////////////////////////////////////////////
//
//  Step 14         : Create ParkingLot Class
//  Description     : This class is the main controller of complete parking system
//  Concept         : Singleton pattern
//        
/////////////////////////////////////////////////////////

// Singlton
class ParkingLot
{
    // Instance of class
    private static ParkingLot instance;

    //Store the parking lot name
    private String ParkingLotName;

    // Store all floors of the parking lot
    private List<ParkingFloor> floors;

    // Maps the ticket number with active parking slot
    private Map<Integer, ParkingTicket> activeTickets;

    // Maps vehicle number with active tickets
    // Used for searching vehicle
    // It prevents duplicate parking
    private Map<String, ParkingTicket> vehicleTicketMap;

    // Algorithm used for selecting parking spot
    private ParkingStrategy parkingStrategy;

    // Algortihtm used for calculating parking charges
    private PricingStartegy pricingStartegy;

    //Private constructor for singleton class
    private ParkingLot()
    {
        floors = new ArrayList<>();

        activeTickets = new HashMap<>();

        vehicleTicketMap = new HashMap<>();

        // Default parking strategy
        parkingStrategy = new firstAvailableParkingStrategy();

        // Default pricing strategy
        pricingStartegy = new NormalPricingStrategy();
    }

    // Method to return singleton class object
    public static synchronized ParkingLot getInstance()
    {
        if(instance == null)
        {
            instance = new ParkingLot();
        }
        return instance;
    }

    // used to set name for complete parking spot
    public void setParkingLotName(String parkingLotName)
    {
        this.ParkingLotName = parkingLotName;
    }

    // Used to add new Parking Floor
    public void addFloor(ParkingFloor floor)
    {
        // Insert in the arraylist
        floors.add(floor);
    }

    // This methods returns list of all floors
    public List<ParkingFloor> getFloors()
    {
        return floors;
    }

    // This method can be used to change the default parking strategy
    public void setParkingStrategy(ParkingStrategy parkingStrategy)
    {
        this.parkingStrategy = parkingStrategy;
    }
    
    // This method can be used to change the default pricing strategy
    public void setPricingStrategy(PricingStartegy pricingStartegy)
    {
        this.pricingStartegy = pricingStartegy;
    }

    /*
        Algorithm for parking the vehicle

        Check duplicate vehicle
                |
                v
        Find available spot
                |
                v
        Identify floor for vehicle
                |
                V
        Occupy spot for ticket
                |
                v
        Generate ticket for vehicle
                |
                v
        Store the final ticket

    */

    public ParkingTicket parkVehicle(
        Vehicle vehicle,
        EntryGate entryGate
    )
    {
        // Step 1 : Prevent the same vehicle for being  parked multiple times
        if(vehicleTicketMap.containsKey(vehicle.getVehicleNumber()))
        {
            System.out.println("This vehicle is already parked");

            throw new RuntimeException("This vehicle is already parked");
        }

        // Step 2 : Find the available spot
        ParkingSpot spot = parkingStrategy.findSpot(floors, vehicle);

        // If there is not empty spot
        if(spot == null)
        {
            throw new RuntimeException("Parking is full");
        }

        // Step 3 : Identify the exact floor for the vehicle

        ParkingFloor selectedFloor = null;

        for(ParkingFloor floor : floors)
        {
            ParkingSpot temp = floor.findAvailableSpot(vehicle);

            if(temp == spot)
            {
                selectedFloor = floor;
                break;
            }
        }

        if(selectedFloor == null)
        {
            throw new RuntimeException("Unable to identify floor");
        }

        // Step 4  : Occupy the spot
        selectedFloor.occupySpot(spot, vehicle);
    
        // Step 5 : Generate parking ticket from entry gate
        ParkingTicket ticket = entryGate.generateTicket(vehicle, selectedFloor, spot);

        // Step 6 : Store the ticket using ticket number
        activeTickets.put(ticket.getTicketNumber(), ticket);

        // Step 7 : Store the ticket using vehicle number
        vehicleTicketMap.put(vehicle.getVehicleNumber(), ticket);

        return ticket;
    }

    /*
        find ticket
            |
        process exit
            |
        calculate charges
            |
        Payment 
            |
        Release spot
            |
        remove active records

    */

    public void removeVehicle(
        int ticketNumber,
        ExitGate exitGate,
        PaymentStrategy paymentStrategy
    )
    {
        // Step 1 : find active tickets using ticket number
        ParkingTicket ticket = activeTickets.get(ticketNumber);

        if(ticket == null)
        {
            throw new RuntimeException("There is no such tickets");
        }

        // Step 2 : perform billing and payment
        exitGate.processExit(ticket, pricingStartegy, paymentStrategy);

        // Step 3 :  release the occupied spot
        ticket.getFloor().releaseSpot(ticket.getSpot());

        // Step 4 : remove vehicle
        activeTickets.remove(ticket.getVehicle().getVehicleNumber());

        // Step 5 : Remove ticket from active vehicles
        vehicleTicketMap.remove(ticket.getVehicle().getVehicleNumber());

        System.out.println("Vehicle removed successfully");

    }

    public ParkingTicket searchVehicle(String vehicleNumber)
    {
        return vehicleTicketMap.get(vehicleNumber);
    }

    public void displayParkingLot()
    {
        System.out.println();
        System.out.println("-----------------------------------------");
        System.out.println("-------- Parking Lot Details ------------");
        System.out.println("-----------------------------------------");
        
        for(ParkingFloor floor : floors)
        {
            floor.displayFloor();
        }
    }
}// End of ParkingLot Class

//////////////////////////////////////////////////////////
//
//  Step 15 : Controller of project
//        
/////////////////////////////////////////////////////////

/*
    1 : create parking lot class object
    
    2 : Create floors 

    3 : Add parking spots

    4 : Display board

    5 : register observer

    6 : add floor to parking lot

    7 : Create entry exit gate

    8 : display menu
*/

class ParkEngine
{
    public static void main(String A[]) throws Exception
    {
        Scanner sobj = new Scanner(System.in);

        /////////////////////////////////////////////
        /// 
        /// Step 1 : Create single Parking Lot object
        /// 
        /////////////////////////////////////////////
        
        ParkingLot parkingLot = ParkingLot.getInstance();
        
        parkingLot.setParkingLotName("ParkEngine");

        /////////////////////////////////////////////
        /// 
        /// Step 2 : Create multiple floors
        /// 
        /////////////////////////////////////////////
        
        //Add first floor
        ParkingFloor floor1 = new ParkingFloor(1);

        /////////////////////////////////////////////
        /// 
        /// Step 3 : Create multiple spots
        /// 
        /////////////////////////////////////////////

        floor1.addParkingSpot(new BikeSpot(101));
        floor1.addParkingSpot(new BikeSpot(102));

        floor1.addParkingSpot(new CarSpot(103));
        floor1.addParkingSpot(new CarSpot(104));

        floor1.addParkingSpot(new TruckSpot(105));
        floor1.addParkingSpot(new TruckSpot(106));

        /////////////////////////////////////////////
        /// 
        /// Step 4 : Create Display board
        /// 
        /////////////////////////////////////////////

        ParkingDisplayBoard board1 = new ParkingDisplayBoard(floor1);

        // Register the display board with observer
        floor1.addObserver(board1);


        // Add second floor

        ParkingFloor floor2 = new ParkingFloor(2);

        /////////////////////////////////////////////
        /// 
        /// Step 3 : Create multiple spots
        /// 
        /////////////////////////////////////////////

        floor2.addParkingSpot(new BikeSpot(201));
        floor2.addParkingSpot(new BikeSpot(202));

        floor2.addParkingSpot(new CarSpot(203));
        floor2.addParkingSpot(new CarSpot(204));

        floor2.addParkingSpot(new TruckSpot(205));
        floor2.addParkingSpot(new TruckSpot(206));

        /////////////////////////////////////////////
        /// 
        /// Step 4 : Create Display board
        /// 
        /////////////////////////////////////////////

        ParkingDisplayBoard board2 = new ParkingDisplayBoard(floor2);

        // Register the display board with observer
        floor2.addObserver(board2);

        /////////////////////////////////////////////
        /// 
        /// Step 6 : Add floors to ParkingLot
        /// 
        /////////////////////////////////////////////

        parkingLot.addFloor(floor1);
        parkingLot.addFloor(floor2);


        /////////////////////////////////////////////
        /// 
        /// Step 7 : Create entry gate and exit gate
        /// 
        /////////////////////////////////////////////
        
        EntryGate entryGate = new EntryGate(1);

        ExitGate exitGate = new ExitGate(1);

        /////////////////////////////////////////////
        /// 
        /// Step 8 : Display Menu
        /// 
        /////////////////////////////////////////////

        int choice = 0;

        while(true)
        {
            System.out.println("---------------------------------------------");
            System.out.println("---------------- Park Engine ----------------");
            System.out.println("---------------------------------------------");

            System.out.println("1 : Park Vehicle");
            System.out.println("2 : Exit Vehicle");
            System.out.println("3 : Search Vehicle");
            System.out.println("4 : Display Parking Lot");
            System.out.println("5 : Exit");

            System.out.println("Enter your choice : ");
            choice = sobj.nextInt();

            try
            {
                switch (choice) {

                    case 1: // Park vehicle
                    {
                        System.out.println();

                        System.out.println("Select vehicle type");

                        System.out.println("1 : BIKE");
                        System.out.println("2 : CAR");
                        System.out.println("3 : TRUCK");

                        int type = sobj.nextInt();

                        System.out.println("Enter vehicle number : ");
                        String number = sobj.next();

                        Vehicle vehicle;

                        // factory pattern used
                        switch(type)
                        {
                            case 1 : // BIKE
                                vehicle = VehicleFactory.createVehicle(VehicleType.BIKE, number);
                                break;
                            
                            case 2 : // CAR
                                vehicle = VehicleFactory.createVehicle(VehicleType.CAR, number);
                                break;

                            case 3 : // TRUCK
                                vehicle = VehicleFactory.createVehicle(VehicleType.TRUCK, number);
                                break;

                            default:
                                System.out.println("Invalid type of vehicle");
                                continue;
                        }

                        // park the vehicle and generate the ticker
                        ParkingTicket parkingTicket = parkingLot.parkVehicle(vehicle, entryGate);
                        
                        // Display generated ticket
                        parkingTicket.displayTicket();
                        
                        break;

                    }// End of case 1

                    case 2: // Exit vehicle
                    {
                        System.out.println("Enter Ticket number : ");
                        
                        int ticketNumber = sobj.nextInt();
                        
                        System.out.println();

                        System.out.println("Enter the payment option : ");
                        System.out.println("1 : CASH");
                        System.out.println("2 : UPI");
                        System.out.println("3 : CARD");

                        int paymentType = sobj.nextInt();

                        PaymentStrategy paymentStrategy;

                        switch(paymentType)
                        {
                            case 1 : // cash
                                paymentStrategy = new CashPayment();
                                break;

                            case 2 : // upi
                                paymentStrategy = new UPIPayment();
                                break;

                            case 3 : // card
                                paymentStrategy = new CardPayment();
                                break;

                            default : 
                                System.out.println("Invalid payment option");
                                continue;
                        }// End of payment switch

                        parkingLot.removeVehicle(ticketNumber, exitGate, paymentStrategy);
                        
                        break;
                    }

                    case 3: // search vehicle
                    {
                        System.out.println("Enter Vehicle Number : ");
                        String vehicleNumber = sobj.next();

                        ParkingTicket ticket = parkingLot.searchVehicle(vehicleNumber);
                        
                        if(ticket == null)
                        {
                            System.out.println("Vehicle is not parked");
                        }
                        else
                        {
                            ticket.displayTicket();
                        }

                        break;
                    }

                    case 4: // display parking lot
                        {
                            parkingLot.displayParkingLot();

                            break;
                        }

                        case 5:
                        {
                           System.out.println("Thank you for using ParkEngine");
                           sobj.close();
                           return ; 
                        }
                    
                
                    default:
                        System.out.println("Invalid option");

                }// End of switch

            }// end of try

            catch(Exception e)
            {
                System.out.println("Exception Occurred : " + e);
            }// End of catch

        }// End of while

    }// End of main
}// End of main class
