import java.util.ArrayList;
import java.util.Scanner;

class Room {
    int roomId;
    String category;
    double pricePerNight;
    boolean isAvailable;

    public Room(int roomId, String category, double pricePerNight) {
        this.roomId = roomId;
        this.category = category;
        this.pricePerNight = pricePerNight;
        this.isAvailable = true;
    }

    @Override
    public String toString() {
        return "Room ID: " + roomId + ", Category: " + category + ", Price/Night: $" + pricePerNight + ", Available: " + (isAvailable ? "Yes" : "No");
    }
}

class Booking {
    int bookingId;
    String customerName;
    int roomId;
    String checkInDate;
    String checkOutDate;
    double totalCost;

    public Booking(int bookingId, String customerName, int roomId, String checkInDate, String checkOutDate, double totalCost) {
        this.bookingId = bookingId;
        this.customerName = customerName;
        this.roomId = roomId;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.totalCost = totalCost;
    }

    @Override
    public String toString() {
        return "Booking ID: " + bookingId + ", Customer: " + customerName + ", Room ID: " + roomId + 
               ", Check-in: " + checkInDate + ", Check-out: " + checkOutDate + ", Total Cost: $" + totalCost;
    }
}

public class HotelReservationSystem {
    static ArrayList<Room> rooms = new ArrayList<>();
    static ArrayList<Booking> bookings = new ArrayList<>();
    static int bookingCounter = 1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        initializeRooms();

        System.out.println("Welcome to the Hotel Reservation System!");

        while (true) {
            System.out.println("\n1. View Available Rooms");
            System.out.println("2. Make a Reservation");
            System.out.println("3. View Booking Details");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    viewAvailableRooms();
                    break;
                case 2:
                    makeReservation(scanner);
                    break;
                case 3:
                    viewBookings();
                    break;
                case 4:
                    System.out.println("Thank you for using the system!");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    static void initializeRooms() {
        rooms.add(new Room(101, "Deluxe", 200.0));
        rooms.add(new Room(102, "Standard", 150.0));
        rooms.add(new Room(103, "Suite", 300.0));
        rooms.add(new Room(104, "Standard", 150.0));
        rooms.add(new Room(105, "Deluxe", 200.0));
    }

    static void viewAvailableRooms() {
        System.out.println("\nAvailable Rooms:");
        for (Room room : rooms) {
            if (room.isAvailable) {
                System.out.println(room);
            }
        }
    }

    static void makeReservation(Scanner scanner) {
        System.out.print("\nEnter your name: ");
        String customerName = scanner.nextLine();

        System.out.print("Enter Room ID to book: ");
        int roomId = scanner.nextInt();
        scanner.nextLine(); // consume newline

        Room room = getRoomById(roomId);
        if (room == null || !room.isAvailable) {
            System.out.println("Room not available or invalid Room ID.");
            return;
        }

        System.out.print("Enter Check-in Date (YYYY-MM-DD): ");
        String checkInDate = scanner.nextLine();

        System.out.print("Enter Check-out Date (YYYY-MM-DD): ");
        String checkOutDate = scanner.nextLine();

        System.out.print("Enter number of nights: ");
        int nights = scanner.nextInt();

        double totalCost = room.pricePerNight * nights;
        Booking booking = new Booking(bookingCounter++, customerName, roomId, checkInDate, checkOutDate, totalCost);

        bookings.add(booking);
        room.isAvailable = false; // Mark room as booked

        System.out.println("Booking successful! Your booking details:");
        System.out.println(booking);
    }

    static Room getRoomById(int roomId) {
        for (Room room : rooms) {
            if (room.roomId == roomId) {
                return room;
            }
        }
        return null;
    }

    static void viewBookings() {
        System.out.println("\nBooking Details:");
        for (Booking booking : bookings) {
            System.out.println(booking);
        }
    }
}
