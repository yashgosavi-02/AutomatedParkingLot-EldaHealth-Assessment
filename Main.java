import java.util.*;
class ParkingLot {
    private final int capacity;
    private final Map<Integer, Car> slots;

    public ParkingLot(int capacity) {
        this.capacity = capacity;
        this.slots = new HashMap<>();
    }
    public int park(String regNumber, String color) {
        for (int i = 1; i <= capacity; i++) {
            if (!slots.containsKey(i)) {
                slots.put(i, new Car(regNumber, color));
                return i;
            }
        }
        return -1;
    }
    public boolean leave(int slotNumber) {
        if (slots.containsKey(slotNumber)) {
            slots.remove(slotNumber);
            return true;
        }
        return false;
    }
    public List<String> getRegistrationNumbersForColor(String color) {
        List<String> regNumbers = new ArrayList<>();
        for (Car car : slots.values()) {
            if (car.getColor().equalsIgnoreCase(color)) {
                regNumbers.add(car.getRegNumber());
            }
        }
        return regNumbers;
    }
    public List<Integer> getSlotNumbersForColor(String color) {
        List<Integer> slotNumbers = new ArrayList<>();
        for (Map.Entry<Integer, Car> entry : slots.entrySet()) {
            if (entry.getValue().getColor().equalsIgnoreCase(color)) {
                slotNumbers.add(entry.getKey());
            }
        }
        return slotNumbers;
    }
    public int getSlotNumberForRegistrationNumber(String regNumber) {
        for (Map.Entry<Integer, Car> entry : slots.entrySet()) {
            if (entry.getValue().getRegNumber().equalsIgnoreCase(regNumber)) {
                return entry.getKey();
            }
        }
        return -1;
    }
    public void status() {
        System.out.println("Slot No.\tRegistration No\t\tColour");
        for (Map.Entry<Integer, Car> entry : slots.entrySet()) {
            System.out.printf("%d\t\t\t%s\t\t%s\n", entry.getKey(), entry.getValue().getRegNumber(), entry.getValue().getColor());
        }
    }
}
class Car {
    private final String regNumber;
    private final String color;
    public Car(String regNumber, String color) {
        this.regNumber = regNumber;
        this.color = color;
    }
    public String getRegNumber() {
        return regNumber;
    }
    public String getColor() {
        return color;
    }
}
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ParkingLot parkingLot = null;
        while (true) {
            System.out.println();
            System.out.println("1. Create Parking Lot");
            System.out.println("2. Park a Car");
            System.out.println("3. Leave Parking Lot");
            System.out.println("4. Get Registration Numbers for Color");
            System.out.println("5. Get Slot Numbers for Color");
            System.out.println("6. Get Slot Number for Registration Number");
            System.out.println("7. Status");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter parking lot capacity: ");
                    int capacity = scanner.nextInt();
                    parkingLot = new ParkingLot(capacity);
                    System.out.println("Created a parking lot with " + capacity + " slots");
                    break;
                case 2:
                    if (parkingLot != null) {
                        System.out.print("Enter car registration number: ");
                        String regNumber = scanner.next();
                        System.out.print("Enter car color: ");
                        String color = scanner.next();
                        int slotNumber = parkingLot.park(regNumber, color);
                        if (slotNumber == -1) {
                            System.out.println("Sorry, parking lot is full");
                        } else {
                            System.out.println("Allocated slot number: " + slotNumber);
                        }
                    } else {
                        System.out.println("Please create parking lot first");
                    }
                    break;
                case 3:
                    if (parkingLot != null) {
                        System.out.print("Enter slot number to leave: ");
                        int slotNumber = scanner.nextInt();
                        if (parkingLot.leave(slotNumber)) {
                            System.out.println("Slot number " + slotNumber + " is free");
                        } else {
                            System.out.println("Invalid slot number");
                        }
                    } else {
                        System.out.println("Please create parking lot first");
                    }
                    break;
                case 4:
                    if (parkingLot != null) {
                        System.out.print("Enter car color: ");
                        String color = scanner.next();
                        List<String> regNumbers = parkingLot.getRegistrationNumbersForColor(color);
                        System.out.println("Registration numbers for color " + color + ": " + regNumbers);
                    } else {
                        System.out.println("Please create parking lot first");
                    }
                    break;
                case 5:
                    if (parkingLot != null) {
                        System.out.print("Enter car color: ");
                        String color = scanner.next();
                        List<Integer> slotNumbers = parkingLot.getSlotNumbersForColor(color);
                        System.out.println("Slot numbers for color " + color + ": " + slotNumbers);
                    } else {
                        System.out.println("Please create parking lot first");
                    }
                    break;
                case 6:
                    if (parkingLot != null) {
                        System.out.print("Enter car registration number: ");
                        String regNumber = scanner.next();
                        int slotNumber = parkingLot.getSlotNumberForRegistrationNumber(regNumber);
                        if (slotNumber == -1) {
                            System.out.println("Car not found");
                        } else {
                            System.out.println("Slot number for registration number " + regNumber + ": " + slotNumber);
                        }
                    } else {
                        System.out.println("Please create parking lot first");
                    }
                    break;
                case 7:
                    if (parkingLot != null) {
                        parkingLot.status();
                    } else {
                        System.out.println("Please create parking lot first");
                    }
                    break;
                case 8:
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}
