import java.io.*;
import java.util.*;

// Room class
class Room {
    int roomNumber;
    String type;
    boolean isAvailable;
    double price;

    Room(int roomNumber, String type, double price) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.price = price;
        this.isAvailable = true;
    }

    @Override
    public String toString() {
        return "Room " + roomNumber + " | Type: " + type + " | Price: $" + price + " | Available: " + isAvailable;
    }
}

// Booking class
class Booking {
    String customerName;
    int roomNumber;
    String type;
    double price;

    Booking(String customerName, int roomNumber, String type, double price) {
        this.customerName = customerName;
        this.roomNumber = roomNumber;
        this.type = type;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Customer: " + customerName + " | Room: " + roomNumber + " | Type: " + type + " | Price: $" + price;
    }
}

// Hotel class
class Hotel {
    List<Room> rooms = new ArrayList<>();
    List<Booking> bookings = new ArrayList<>();
    final String FILE_NAME = "bookings.txt";

    Hotel() {
        // Initialize rooms
        rooms.add(new Room(101, "Standard", 50));
        rooms.add(new Room(102, "Standard", 50));
        rooms.add(new Room(201, "Deluxe", 100));
        rooms.add(new Room(202, "Deluxe", 100));
        rooms.add(new Room(301, "Suite", 200));

        // Load previous bookings
        loadBookings();
    }

    // Display all rooms
    void displayRooms() {
        System.out.println("Available Rooms:");
        for (Room r : rooms) {
            System.out.println(r);
        }
    }

    // Search available rooms by type
    void searchRooms(String type) {
        System.out.println("Searching for " + type + " rooms:");
        boolean found = false;
        for (Room r : rooms) {
            if (r.type.equalsIgnoreCase(type) && r.isAvailable) {
                System.out.println(r);
                found = true;
            }
        }
        if (!found) System.out.println("No available " + type + " rooms.");
    }

    // Book a room
    void bookRoom(String customerName, int roomNumber) {
        for (Room r : rooms) {
            if (r.roomNumber == roomNumber && r.isAvailable) {
                r.isAvailable = false;
                Booking booking = new Booking(customerName, roomNumber, r.type, r.price);
                bookings.add(booking);
                saveBookings();
                System.out.println("Booking Successful!\n" + booking);
                return;
            }
        }
        System.out.println("Room not available or does not exist.");
    }

    // Cancel a booking
    void cancelBooking(int roomNumber) {
        Iterator<Booking> it = bookings.iterator();
        while (it.hasNext()) {
            Booking b = it.next();
            if (b.roomNumber == roomNumber) {
                it.remove();
                for (Room r : rooms) {
                    if (r.roomNumber == roomNumber) {
                        r.isAvailable = true;
                        break;
                    }
                }
                saveBookings();
                System.out.println("Booking for room " + roomNumber + " cancelled.");
                return;
            }
        }
        System.out.println("No booking found for room " + roomNumber);
    }

    // View all bookings
    void viewBookings() {
        if (bookings.isEmpty()) {
            System.out.println("No bookings yet.");
            return;
        }
        System.out.println("All Bookings:");
        for (Booking b : bookings) {
            System.out.println(b);
        }
    }

    // Save bookings to file
    void saveBookings() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Booking b : bookings) {
                pw.println(b.customerName + "," + b.roomNumber + "," + b.type + "," + b.price);
            }
        } catch (IOException e) {
            System.out.println("Error saving bookings: " + e.getMessage());
        }
    }

    // Load bookings from file
    void loadBookings() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] parts = line.split(",");
                String customerName = parts[0];
                int roomNumber = Integer.parseInt(parts[1]);
                String type = parts[2];
                double price = Double.parseDouble(parts[3]);
                bookings.add(new Booking(customerName, roomNumber, type, price));
                for (Room r : rooms) {
                    if (r.roomNumber == roomNumber) r.isAvailable = false;
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading bookings: " + e.getMessage());
        }
    }
}

// Main class
public class HotelReservationSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Hotel hotel = new Hotel();

        while (true) {
            System.out.println("\n--- Hotel Reservation System ---");
            System.out.println("1. Display Rooms");
            System.out.println("2. Search Rooms");
            System.out.println("3. Book Room");
            System.out.println("4. Cancel Booking");
            System.out.println("5. View All Bookings");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1 -> hotel.displayRooms();
                case 2 -> {
                    System.out.print("Enter room type (Standard/Deluxe/Suite): ");
                    String type = sc.nextLine();
                    hotel.searchRooms(type);
                }
                case 3 -> {
                    System.out.print("Enter your name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter room number to book: ");
                    int roomNo = sc.nextInt();
                    hotel.bookRoom(name, roomNo);
                }
                case 4 -> {
                    System.out.print("Enter room number to cancel booking: ");
                    int roomNo = sc.nextInt();
                    hotel.cancelBooking(roomNo);
                }
                case 5 -> hotel.viewBookings();
                case 6 -> {
                    System.out.println("Exiting... Thank you!");
                    System.exit(0);
                }
                default -> System.out.println("Invalid option, try again!");
            }
        }
    }
}