package org.example;
import java.util.*;
import java.util.Scanner;
class User {
    private String name;
    private int age;
    private String mobile_no;
    private  String email_id;
    private  String password;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getMobile_no() {
        return mobile_no;
    }
    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }
    public String getEmail_id() {
        return email_id;
    }
    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public User(String name, int age, String mobile_no, String email_id, String password) {
        this.name = name;
        this.age = age;
        this.mobile_no = mobile_no;
        this.email_id = email_id;
        this.password = password;
    }
    public User(){

    }
    public boolean login(String email_id,String password) {
        return this.getEmail_id().equals(email_id) && this.getPassword().equals(password);
    }
}
class Visitor extends User{
    private double balance;
    private String experience;
    private ArrayList<Ticket> acquired_ticket;
    private ArrayList<String> feedbacks;

    public Visitor(String emailId, String password,double balance,String experience) {
        this.balance=balance;
        this.setEmail_id(emailId);
        this.setPassword(password);
        this.feedbacks=new ArrayList<>();
        this.experience=experience;
        this.acquired_ticket=new ArrayList<>();
    }

    public ArrayList<String> getFeedbacks() {
        return feedbacks;
    }
    public void setFeedbacks(ArrayList<String> feedbacks) {
        this.feedbacks = feedbacks;
    }
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
    public String getExperience() {
        return experience;
    }
    public void setExperience(String experience) {
        this.experience = experience;
    }
    public ArrayList<Ticket> getAcquired_ticket() {
        return acquired_ticket;
    }
    public void setAcquired_ticket(ArrayList<Ticket> acquired_ticket) {
        this.acquired_ticket = acquired_ticket;
    }
    public Visitor(String name, int age, String mobile_no, String email_id, String password, double balance, String experience) {
        super(name, age, mobile_no, email_id, password);
        this.balance = balance;
        this.experience = experience;
        this.acquired_ticket = new ArrayList<>();
        this.feedbacks=new ArrayList<>();
    }
    public Visitor() {
        // Default constructor, does nothing
    }

    public void login_v(String enteredEmail, String enteredPassword) {
        if (!super.login(enteredEmail, enteredPassword)) {
            System.out.println("Login failed. Incorrect email or password.");
            return;
        }
        System.out.println("Successful login as a visitor");
        // Set the visitor's experience based on your logic.
    }

    public void exploreTheZoo() {
        Scanner input = new Scanner(System.in);
        int choice;
        do {
            System.out.println("Explore the Zoo:");
            System.out.println("1. View Attractions");
            System.out.println("2. View Animals");
            System.out.println("3. Exit");
            choice = input.nextInt();
            switch (choice) {
                case 1 -> viewAttractions();
                case 2 -> viewAnimals();
            }
        } while (choice != 3);
    }
    private void viewAttractions() {
        List<String> adminAddedAttractions = Admin.getAdminAddedAttractions();
        System.out.println("Attractions in the Zoo:");
        for (int i = 0; i < adminAddedAttractions.size(); i++) {
            System.out.println((i + 1) + ". " + adminAddedAttractions.get(i));
        }
    }
    private void viewAnimals() {
        List<String> adminAddedAnimals = Admin.getAdminAddedAnimals();
        System.out.println("Animals in the Zoo:");
        for (int i = 0; i < adminAddedAnimals.size(); i++) {
            System.out.println((i + 1) + ". " + adminAddedAnimals.get(i));
        }
    }
    public void buyMembership() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Buy Membership:");
        System.out.println("1. Basic Membership (₹20)");
        System.out.println("2. Premium Membership (₹50)");
        int choice = scanner.nextInt();
        if (choice < 1 || choice > 2) {
            System.out.println("Invalid choice.");
            return;
        }
        double membershipPrice = choice == 1 ? 20.0 : 50.0;
        String membershipType = choice == 1 ? "Basic Membership" : "Premium Membership";
        viewAllDiscounts();
        System.out.println("Enter the discount code (or 'None' for no discount):");
        scanner.nextLine();
        String discountCode = scanner.nextLine();

        System.out.println("Membership Price: " + membershipPrice);
        System.out.println("Membership Type: " + membershipType);
        System.out.println("Discount Code: " + discountCode);

        if (!(isValidDiscount(discountCode))&&!("None".equals(discountCode))){
            System.out.println("Invalid discount code.");
            return;
        }
        double discountedPrice = membershipPrice;
        System.out.println("balance :" + balance);
        System.out.println("Discounted Price before calculation: " + discountedPrice);
        if (!discountCode.equalsIgnoreCase("None")) {
            discountedPrice = membershipPrice - (membershipPrice * getDiscountPercentage(discountCode) / 100.0);
            balance-=discountedPrice;
            System.out.println(membershipType + " purchased successfully. Your balance is now ₹" + balance);
        }
        else {
            balance -= membershipPrice;
            experience = (choice == 1) ? "Basic" : "Premium";
            System.out.println(membershipType + " purchased successfully. Your balance is now ₹" + balance);
        }
        System.out.println("Discount Percentage: " + getDiscountPercentage(discountCode));
        System.out.println("Discounted Price after calculation: " + discountedPrice);
        // Display success message
        System.out.println(membershipType + " purchased successfully. Your balance is now ₹" + balance);
    }
    public Double getDiscountPercentage(String discountCode) {
        List<String> adminDiscountCode = Admin.getAdminAddedDiscountCode(); // Assuming it's a list of admin-added discount codes
        List<Double> adminDiscountPercentage = Admin.getAdminAddedDiscountPercentage(); // Assuming it's a list of admin-added discount percentages

        for (int i = 0; i < adminDiscountCode.size(); i++) {
            if (adminDiscountCode.get(i).equals(discountCode)) {
                // If the provided discountCode matches an admin-added code, return the corresponding percentage
                return adminDiscountPercentage.get(i);
            }
        }
        return 0.0;
    }
    private boolean isValidDiscount(String discountCode) {
        // Check if the provided discount code exists in the list of admin-added discount codes
        List<String> adminDiscounts = Admin.getAdminAddedDiscountCode();
        return adminDiscounts != null && adminDiscounts.contains(discountCode);
    }

    public void buyTickets() {
        Scanner input = new Scanner(System.in);
        System.out.println("Buy Tickets:");
        List<String> adminAddedAttractions = Admin.getAdminAddedAttractions();
        List<Double> adminAddedPrices = Admin.getAdminAddedPrice();
        Map<String, Boolean> attractionStatus = new HashMap<>();

        if (adminAddedAttractions.size() != adminAddedPrices.size()) {
            System.out.println("Error: Attractions and prices lists are not properly synchronized.");
            return;
        }

        for (String attraction : adminAddedAttractions) {
            attractionStatus.put(attraction, true); // You can set the status as needed.
        }

        if (experience.equalsIgnoreCase("Premium")) {
            // The visitor does not have to buy tickets, so exit the method
            System.out.println("You have a premium membership, so you do not have to buy tickets.");
            return;
        }

        System.out.println("Available Attractions:");
        int index = 1;
        for (int i = 0; i < adminAddedAttractions.size(); i++) {
            System.out.println(index + ". " + adminAddedAttractions.get(i) + " (₹" + adminAddedPrices.get(i) + ")");
            index++;
        }

        System.out.println("Select an attraction to buy a ticket:");
        int choice = input.nextInt();

        if (choice < 1 || choice > adminAddedAttractions.size()) {
            System.out.println("Invalid choice.");
            return;
        }

        String selectedAttraction = adminAddedAttractions.get(choice - 1);
        double selectedAttractionPrice = adminAddedPrices.get(choice - 1);

        if (!attractionStatus.get(selectedAttraction)) {
            System.out.println("Sorry, the " + selectedAttraction + " is currently closed.");
            return;
        }

        // Check if the user has enough balance to buy the ticket
        System.out.println("Do you have twins? (yes/no)");
        if (input.next().equalsIgnoreCase("yes")) {
            selectedAttractionPrice *= 0.55; // Apply a discount for twins
        }

        if (balance >= selectedAttractionPrice) {
            if (experience.equalsIgnoreCase("Basic")) {
                System.out.println("You have a basic membership, so you need to buy tickets.");
            }

            Ticket ticket = new Ticket(selectedAttraction, selectedAttractionPrice);
            getAcquired_ticket().add(ticket);
            balance -= (int) selectedAttractionPrice;
            System.out.println("The ticket for " + selectedAttraction + " was purchased successfully. Your balance is now ₹" + balance);
        } else {
            System.out.println("Insufficient balance to buy the ticket.");
        }
    }


    public void viewAllDiscounts() {
        List<String> adminAddedDiscountCode = Admin.getAdminAddedDiscountCode();
        List<String> adminAddedDiscountName = Admin.getAdminAddedDiscountName();
        List<Double> adminAddedDiscountPercentage = Admin.getAdminAddedDiscountPercentage();
        System.out.println("Discounts:");
        for (int i = 0; i < adminAddedDiscountCode.size(); i++) {
            String code = adminAddedDiscountCode.get(i);
            String name = adminAddedDiscountName.get(i);
            double percentage = adminAddedDiscountPercentage.get(i);
            System.out.println((i + 1) + ". Code: " + code + ", Name: " + name + ", Percentage: " + percentage + "%");
        }
    }
    public void visitAttractions() {
        Scanner scanner = new Scanner(System.in);
        List<String> adminAddedAttractions = Admin.getAdminAddedAttractions();

        if (adminAddedAttractions.isEmpty()) {
            System.out.println("No attractions available.");
            return;
        }

        System.out.println("Visit Attractions:");
        System.out.println("Select an attraction to visit:");

        for (int i = 0; i < adminAddedAttractions.size(); i++) {
            System.out.println((i + 1) + ". " + adminAddedAttractions.get(i));
        }

        int choice = scanner.nextInt();
        if (choice >= 1 && choice <= adminAddedAttractions.size()) {
            String selectedAttraction = adminAddedAttractions.get(choice - 1);
            System.out.println("Welcome to the " + selectedAttraction + ".");
        } else {
            System.out.println("Invalid choice.");
        }
    }

    public void visitAnimals() {
        Scanner scanner = new Scanner(System.in);
        List<String> adminAddedAnimals = Admin.getAdminAddedAnimals();

        if (adminAddedAnimals.isEmpty()) {
            System.out.println("No animals available.");
            return;
        }
        System.out.println("Visit Animals:");
        System.out.println("Select an animal to visit:");

        for (int i = 0; i < adminAddedAnimals.size(); i++) {
            System.out.println((i + 1) + ". " + adminAddedAnimals.get(i));
        }

        int choice = scanner.nextInt();
        if (choice >= 1 && choice <= adminAddedAnimals.size()) {
            String selectedAnimal = adminAddedAnimals.get(choice - 1);
            System.out.println("You are now visiting the " + selectedAnimal + ".");
            System.out.println("What would you like to do?");
            System.out.println("1. Feed the " + selectedAnimal);
            System.out.println("2. Read about the " + selectedAnimal);
            int actionChoice = scanner.nextInt();
            switch (actionChoice) {
                case 1 -> feedAnimal(selectedAnimal);
                case 2 -> readAboutAnimal(selectedAnimal);
                default -> System.out.println("Invalid choice.");
            }
        } else {
            System.out.println("Invalid choice.");
        }
    }

    private void feedAnimal(String animal) {
        // Implement the logic for feeding the animal.
        System.out.println("You are feeding the " + animal + ".");
    }
    private void readAboutAnimal(String animal) {
        List<String> adminAddedAnimals = Admin.getAdminAddedAnimals();

        if (adminAddedAnimals.contains(animal)) {
            // Provide some general information about the selected admin-added animal.
            System.out.println("Information about the " + animal + ":");
            System.out.println("This is a fascinating animal.");
        } else {
            System.out.println("Invalid animal choice.");
        }
    }
    public void viewSpecialDeals() {
        System.out.println("View Special Deals:");
        List<String> adminAddedSpecialDeals = Admin.getAdminAddedSpecialDeals();
        for (int i = 0; i < adminAddedSpecialDeals.size(); i++) {
            System.out.println((i + 1) + ". " + adminAddedSpecialDeals.get(i));
        }
    }

    public static List<String> getVisitorAddedFeedback() {
        return visitorAddedFeedback;
    }

    public static void setVisitorAddedFeedback(List<String> visitorAddedFeedback) {
        Visitor.visitorAddedFeedback = visitorAddedFeedback;
    }

    private static List<String>  visitorAddedFeedback=new ArrayList<>();
    public void leaveFeedback() {
        Scanner input = new Scanner(System.in);
        System.out.println("Leave Feedback:");
        System.out.print("Enter your feedback (max 200 characters): ");
        String feedback = input.next();
        if (feedback.length() > 200) {
            System.out.println("Feedback is too long. Max 200 characters allowed.");
        } else {
            feedbacks.add(feedback);
            visitorAddedFeedback.add(feedback);
            System.out.println("Thank you for your feedback.");
        }
    }
    public void logout() {
        System.out.println("Logged out.");
    }
}
class Ticket {
    private String attraction;
    private double price;
    private boolean isValid;
    public void setAttraction(String attraction) {
        this.attraction = attraction;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public void setValid(boolean valid) {
        isValid = valid;
    }
    public Ticket(String attraction, double price) {
        this.attraction = attraction;
        this.price = price;
        this.isValid = true;
    }
    public String getAttraction() {
        return attraction;
    }
    public double getPrice() {
        return price;
    }
    public boolean isValid() {
        return isValid;
    }
    public void markAsUsed() {
        isValid = false;
    }
}
class Admin extends User {
    public ArrayList<Attraction> getAttractions() {
        return attractions;
    }
    public void setAttractions(ArrayList<Attraction> attractions) {
        this.attractions = attractions;
    }
    public ArrayList<Event> getEvents() {
        return events;
    }
    public void setEvents(ArrayList<Event> events) {
        this.events = events;
    }
    public ArrayList<Animal> getAnimals() {
        return animals;
    }
    public void setAnimals(ArrayList<Animal> animals) {
        this.animals = animals;
    }
    public ArrayList<String> getDiscountNames() {
        return discountNames;
    }
    public void setDiscountNames(ArrayList<String> discountNames) {
        this.discountNames = discountNames;
    }
    public ArrayList<Double> getDiscountPercentages() {
        return discountPercentages;
    }
    public void setDiscountPercentages(ArrayList<Double> discountPercentages) {
        this.discountPercentages = discountPercentages;
    }
    public ArrayList<String> getSpecialDeals() {
        return specialDeals;
    }
    public void setSpecialDeals(ArrayList<String> specialDeals) {
        this.specialDeals = specialDeals;
    }
    public ArrayList<Integer> getVisitorCounts() {
        return visitorCounts;
    }

    public void setVisitorCounts(ArrayList<Integer> visitorCounts) {
        this.visitorCounts = visitorCounts;
    }

    public ArrayList<Double> getRevenueGenerated() {
        return revenueGenerated;
    }

    public void setRevenueGenerated(ArrayList<Double> revenueGenerated) {
        this.revenueGenerated = revenueGenerated;
    }

    public ArrayList<Feedback> getFeedbackList() {
        return feedbackList;
    }

    public void setFeedbackList(ArrayList<Feedback> feedbackList) {
        this.feedbackList = feedbackList;
    }

    private ArrayList<Attraction> attractions;
    private ArrayList<Event> events;
    private ArrayList<Animal> animals;
    private ArrayList<String> discountNames;
    private ArrayList<Double> discountPercentages;

    public ArrayList<String> getDiscountCodes() {
        return discountCodes;
    }

    public void setDiscountCodes(ArrayList<String> discountCodes) {
        this.discountCodes = discountCodes;
    }

    private ArrayList<String> discountCodes;
    private ArrayList<String> specialDeals;
    private ArrayList<Integer> visitorCounts;
    private ArrayList<Double> revenueGenerated;
    private ArrayList<Feedback> feedbackList;

    public Admin( String name,int age, String mobile_no, String email_id, String password) {
        super(name,age, mobile_no, email_id, password);
        attractions = new ArrayList<>();
        events = new ArrayList<>();
        animals = new ArrayList<>();
        discountNames = new ArrayList<>();
        discountPercentages = new ArrayList<>();
        discountCodes=new ArrayList<>();
        specialDeals = new ArrayList<>();
        visitorCounts = new ArrayList<>();
        revenueGenerated = new ArrayList<>();
        feedbackList = new ArrayList<>();
    }
    public boolean loginAdmin() {
        if (super.getEmail_id().equals("admin") && super.getPassword().equals("admin123")) {
            System.out.println("Successful login as an admin");
            return true;
        } else {
            System.out.println("Login failed");
            return false;
        }

    }
    public static List<String> getAdminAddedAttractions() {
        return adminAddedAttractions;
    }

    public void setAdminAddedAttractions(List<String> adminAddedAttractions) {
        Admin.adminAddedAttractions = adminAddedAttractions;
    }

    private static List<String> adminAddedAttractions = new ArrayList<>();

    public static List<Double> getAdminAddedPrice() {
        return adminAddedPrice;
    }

    public static void setAdminAddedPrice(List<Double> adminAddedPrice) {
        Admin.adminAddedPrice = adminAddedPrice;
    }

    private static List<Double> adminAddedPrice=new ArrayList<>();
    public void addAttraction(String name, String description, double price, boolean isFreeForPremium) {
        Attraction attraction = new Attraction(name, description, price, isFreeForPremium);
        attractions.add(attraction);
        adminAddedAttractions.add(name);
        adminAddedPrice.add(price);
        System.out.println("Attraction added successfully.");
    }
    public void viewAttractions() {
        System.out.println("List of Attractions:");
        for (Attraction attraction : attractions) {
            System.out.println(attraction);
        }
    }
    public void modifyAttraction(String currentName, String newDescription, double newPrice) {
        Attraction attraction = attractions.stream()
                .filter(a -> a.getName().equalsIgnoreCase(currentName))
                .findFirst()
                .orElse(null);
        if (attraction != null) {
            attraction.setDescription(newDescription);
            attraction.setPrice(newPrice);
            System.out.println("Attraction modified successfully.");
        } else {
            System.out.println("Attraction not found. Modification failed.");
        }
    }
    public void removeAttraction(String attractionName) {
        boolean attractionRemoved = attractions.removeIf(attraction -> attraction.getName().equalsIgnoreCase(attractionName));
        if (attractionRemoved) {
            System.out.println("Attraction removed successfully: " + attractionName);
        } else {
            System.out.println("Attraction not found. Removal failed.");
        }
    }

    // Method to add a new event
    public void addEvent(String eventName, String attractionName, boolean isOpen, double ticketPrice) {
        Optional<Attraction> attraction = attractions.stream().filter(a -> a.getName().equals(attractionName)).findFirst();
        if (attraction.isPresent()) {
            events.add(new Event(eventName, attraction.get(), isOpen, ticketPrice));
            System.out.println("Event added successfully.");
        } else {
            System.out.println("Attraction not found. Event addition failed.");
        }
    }
    public void viewEvents() {
        System.out.println("List of Events:");
        events.forEach(System.out::println);
    }

    // Method to modify an existing event
    public void modifyEvent(String eventName, boolean isOpen, double newTicketPrice) {
        boolean eventFound = false;

        for (Event event : events) {
            if (event.getEventName().equals(eventName)) {
                event.setOpen(isOpen);
                event.setTicketPrice(newTicketPrice);
                eventFound = true;
                System.out.println("Event modified successfully.");
                break; // Exit the loop once the event is found and modified
            }
        }

        if (!eventFound) {
            System.out.println("Event not found. Event modification failed.");
        }
    }


    // Method to remove an event
    public void removeEvent(String eventName) {
        Event removedEvent = null;

        for (Event event : events) {
            if (event.getEventName().equals(eventName)) {
                removedEvent = event;
                break; // Exit the loop once the event is found
            }
        }

        if (removedEvent != null) {
            events.remove(removedEvent);
            System.out.println("Event removed successfully: " + removedEvent.getEventName());
        } else {
            System.out.println("Event not found. Removal failed.");
        }
    }

    public static List<String> getAdminAddedAnimals() {
        return adminAddedAnimals;
    }

    public void setAdminAddedAnimals(List<String> adminAddedAnimals) {
        Admin.adminAddedAnimals = adminAddedAnimals;
    }

    // Method to add a new animal to an attraction
    private static List<String> adminAddedAnimals = new ArrayList<>();
    public void addAnimal(String animalName, String attractionName, String animalType) {
        AnimalType type = AnimalType.fromString(animalType);

        if (type != null) {
            Attraction attraction = findAttractionByName(attractionName);
            if (attraction != null) {
                Animal animal = new Animal(animalName, type);
                attraction.addAnimal(animal);
                adminAddedAnimals.add(animalName);
                System.out.println("Animal added successfully.");
            } else {
                System.out.println("Attraction not found. Animal addition failed.");
            }
        } else {
            System.out.println("Invalid animal type. Animal addition failed.");
        }
    }

    // Method to view animals in an attraction
    public void viewAnimals(String attractionName) {
        Attraction attraction = findAttractionByName(attractionName);
        if (attraction != null) {
            attraction.listAnimals();
        } else {
            System.out.println("Attraction not found. Unable to view animals.");
        }
    }

    // Method to modify an existing animal
    public void modifyAnimal(String attractionName, String animalName, String newDescription) {
        Attraction attraction = findAttractionByName(attractionName);
        if (attraction != null) {
            Animal animal = null;
            for (Animal animalInAttraction : attraction.getAnimals()) {
                if (animalInAttraction.getName().equalsIgnoreCase(animalName)) {
                    animal = animalInAttraction;
                    break;
                }
            }
            if (animal != null) {
                animal.setDescription(newDescription);
                System.out.println("Animal modified successfully: " + animalName);
            } else {
                System.out.println("Animal not found in attraction. Animal modification failed.");
            }
        } else {
            System.out.println("Attraction not found. Animal modification failed.");
        }
    }

    // Method to remove an animal from an attraction
    public void removeAnimal(String attractionName, String animalName) {
        Attraction attraction = findAttractionByName(attractionName);
        if (attraction != null) {
            boolean animalRemoved = attraction.removeAnimal(animalName);
            if (animalRemoved) {
                System.out.println("Animal removed successfully: " + animalName);
            } else {
                System.out.println("Animal not found in attraction. Removal failed.");
            }
        } else {
            System.out.println("Attraction not found. Animal removal failed.");
        }
    }

    // Find attraction by name
    private Attraction findAttractionByName(String attractionName) {
        return attractions.stream().filter(a -> a.getName().equalsIgnoreCase(attractionName)).findFirst().orElse(null);
    }

    public static List<String> getAdminAddedDiscountName() {
        return adminAddedDiscountName;
    }
    public static void setAdminAddedDiscountName(List<String> adminAddedDiscountName) {
        Admin.adminAddedDiscountName = adminAddedDiscountName;
    }

    public static List<Double> getAdminAddedDiscountPercentage() {
        return adminAddedDiscountPercentage;
    }

    public static void setAdminAddedDiscountPercentage(List<Double> adminAddedDiscountPercentage) {
        Admin.adminAddedDiscountPercentage = adminAddedDiscountPercentage;
    }

    public static List<String> getAdminAddedDiscountCode() {
        return adminAddedDiscountCode;
    }

    public static void setAdminAddedDiscountCode(List<String> adminAddedDiscountCode) {
        Admin.adminAddedDiscountCode = adminAddedDiscountCode;
    }

    private static List<String> adminAddedDiscountCode = new ArrayList<>();
    private static List<String> adminAddedDiscountName = new ArrayList<>();
    private static List<Double> adminAddedDiscountPercentage = new ArrayList<>();
    public void setDiscount(String discountCode, String discountName, double discountPercentage) {
        if (discountPercentage >= 0 && discountPercentage <= 100) {
            discountCodes.add(discountCode);
            adminAddedDiscountCode.add(discountCode);
            discountNames.add(discountName);
            adminAddedDiscountName.add(discountName);
            discountPercentages.add(discountPercentage);
            adminAddedDiscountPercentage.add(discountPercentage);
            System.out.println("Discount code '" + discountCode + "' for '" + discountName + "' set to " + discountPercentage + "%.");
        } else {
            System.out.println("Invalid discount percentage. Discount not set.");
        }
    }

    // Method to remove a discount
    public void removeDiscount(String discountName) {
        int index = discountNames.indexOf(discountName);
        if (index != -1) {
            discountNames.remove(index);
            discountPercentages.remove(index);
            System.out.println("Discount '" + discountName + "' removed.");
        } else {
            System.out.println("Discount '" + discountName + "' not found. Removal failed.");
        }
    }
    public void modifyDiscount(String discountCode, double newDiscountPercentage) {
        boolean discountFound = false;

        for (int i = 0; i < discountCodes.size(); i++) {
            if (discountCodes.get(i).equals(discountCode)) {
                discountPercentages.set(i, newDiscountPercentage);
                System.out.println("Discount code '" + discountCode + "' updated to " + newDiscountPercentage + "%.");
                discountFound = true;
                break; // Exit the loop once the discount is found and updated
            }
        }

        if (!discountFound) {
            System.out.println("Discount code not found. Modification failed.");
        }
    }



    // Method to view available discounts
    public void viewDiscounts() {
        System.out.println("Available Discounts:");
        for (int i = 0; i < discountNames.size(); i++) {
            String name = discountNames.get(i);
            double percentage = discountPercentages.get(i);
            System.out.println(name + ": " + percentage + "%");
        }
    }

    public static List<String> getAdminAddedSpecialDeals() {
        return adminAddedSpecialDeals;
    }

    public static void setAdminAddedSpecialDeals(List<String> adminAddedSpecialDeals) {
        Admin.adminAddedSpecialDeals = adminAddedSpecialDeals;
    }

    private static List<String> adminAddedSpecialDeals = new ArrayList<>();

    // Method to set a special deal
    public void setSpecialDeal(String dealName) {
        specialDeals.add(dealName);
        adminAddedSpecialDeals.add(dealName);
        System.out.println("Special deal '" + dealName + "' added.");
    }

    // Method to remove a special deal
    public void removeSpecialDeal(String dealName) {
        if (specialDeals.contains(dealName)) {
            specialDeals.remove(dealName);
            System.out.println("Special deal '" + dealName + "' removed.");
        } else {
            System.out.println("Special deal '" + dealName + "' not found. Removal failed.");
        }
    }

    // Method to view available special deals
    public void viewSpecialDeals() {
        System.out.println("Available Special Deals:");
        specialDeals.forEach(System.out::println);
    }

    public void addVisitorStats(String attractionName, int visitorCount, double revenue) {
        Attraction attraction = findAttractionByName(attractionName);
        if (attraction != null) {
            int index = attractions.indexOf(attraction);
            if (index >= 0 && index < attractions.size()) {
                visitorCounts.set(index, visitorCounts.get(index) + visitorCount);
                revenueGenerated.set(index, revenueGenerated.get(index) + revenue);
            }
        }
    }
    public void viewVisitorStats() {
        // Calculate the total visitors and total revenue
        int totalVisitors = 0;
        double totalRevenue = 0.0;

        for (int i = 0; i < attractions.size(); i++) {
            totalVisitors += visitorCounts.get(i);
            totalRevenue += revenueGenerated.get(i);
        }

        // Find the most popular attraction by visitor count
        Attraction mostPopularAttraction = null;
        int maxVisitorCount = 0;

        for (Attraction attraction : attractions) {
            int visitorCount = visitorCounts.get(attractions.indexOf(attraction));

            if (visitorCount > maxVisitorCount) {
                maxVisitorCount = visitorCount;
                mostPopularAttraction = attraction;
            }
        }

        // Print the visitor statistics
        System.out.println("Visitor Statistics:");
        System.out.println("- Total Visitors: " + totalVisitors);
        System.out.println("- Total Revenue: $" + totalRevenue);

        if (mostPopularAttraction != null) {
            System.out.println("- Most Popular Attraction: " + mostPopularAttraction.getName());
        } else {
            System.out.println("- Most Popular Attraction: No attractions visited yet.");
        }
    }


    // Method to view visitor feedback
    public void viewFeedback() {
        List<String> visitorAddedFeedback=Visitor.getVisitorAddedFeedback();
        System.out.println("Visitor Feedback:");
        for (String feedback : visitorAddedFeedback) {
            System.out.println(feedback);
        }
    }
}
class Attraction {
    private String name;
    private String description;
    private double price;
    private boolean isFreeForPremium;

    public ArrayList<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(ArrayList<Animal> animals) {
        this.animals = animals;
    }

    private ArrayList<Animal> animals;
    private int visitorCount;
    private double revenue;

    public Attraction(String name, String description, double price, boolean isFreeForPremium) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.isFreeForPremium = isFreeForPremium;
        animals = new ArrayList<>();
        visitorCount=0;
        revenue=0.0;
    }
    public int getVisitorCount() {
        return visitorCount;
    }

    public double getRevenue() {
        return revenue;
    }

    // Method to increment visitor count
    public void incrementVisitorCount(int count) {
        visitorCount += count;
    }

    // Method to add revenue
    public void addRevenue(double amount) {
        revenue += amount;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isFreeForPremium() {
        return isFreeForPremium;
    }

    public void setFreeForPremium(boolean freeForPremium) {
        isFreeForPremium = freeForPremium;
    }

    public void addAnimal(Animal animal) {
        animals.add(animal);
    }

    public boolean removeAnimal(String animalName) {
        Iterator<Animal> iterator = animals.iterator();
        while (iterator.hasNext()) {
            Animal animal = iterator.next();
            if (animal.getName().equalsIgnoreCase(animalName)) {
                iterator.remove(); // Safely remove the animal
                return true; // Animal found and removed
            }
        }
        return false; // Animal not found in the list
    }


    public void modifyAnimal(String animalName, String newDescription) {
        animals.stream()
                .filter(animal -> animal.getName().equalsIgnoreCase(animalName))
                .findFirst()
                .ifPresent(animal -> animal.setDescription(newDescription));
    }


    public void listAnimals() {
        System.out.println("Animals in Attraction " + name + ":");
        for (Animal animal : animals) {
            System.out.println(animal);
        }
    }

    @Override
    public String toString() {
        return "Attraction: " + name + "\nDescription: " + description + "\nPrice: $" + price + "\nFree for Premium Members: " + (isFreeForPremium ? "Yes" : "No") + "\n";
    }
}

class Event {
    private String eventName;
    private Attraction attraction;
    private boolean isOpen;
    private double ticketPrice;

    public Event(String eventName, Attraction attraction, boolean isOpen, double ticketPrice) {
        this.eventName = eventName;
        this.attraction = attraction;
        this.isOpen = isOpen;
        this.ticketPrice = ticketPrice;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Attraction getAttraction() {
        return attraction;
    }

    public void setAttraction(Attraction attraction) {
        this.attraction = attraction;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    @Override
    public String toString() {
        return "Event Name: " + eventName + "\nAttraction: " + attraction.getName() + "\nIs Open: " + (isOpen ? "Yes" : "No") + "\nTicket Price: $" + ticketPrice + "\n";
    }
}

class Animal {
    private String name;

    public String getDescription() {
        return description;
    }

    private String description;

    public void setType(AnimalType type) {
        this.type = type;
    }

    private AnimalType type;

    public Animal(String name, AnimalType type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AnimalType getType() {
        return type;
    }
    public void setDescription(String newDescription) {
        this.description = newDescription;
    }


    @Override
    public String toString() {
        return "Animal Name: " + name + "\nType: " + type + "\n";
    }
}

enum AnimalType {
    MAMMAL,
    AMPHIBIAN,
    REPTILE;

    public static AnimalType fromString(String type) {
        try {
            return AnimalType.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
class Feedback {
    public void setVisitorName(String visitorName) {
        this.visitorName = visitorName;
    }

    public void setFeedbackText(String feedbackText) {
        this.feedbackText = feedbackText;
    }

    private String visitorName;
    private String feedbackText;
    public Feedback(String visitorName, String feedbackText) {
        this.visitorName = visitorName;
        this.feedbackText = feedbackText;
    }
    public String getVisitorName() {
        return visitorName;
    }
    public String getFeedbackText() {
        return feedbackText;
    }
    @Override
    public String toString() {
        return "Visitor: " + visitorName + "\nFeedback: " + feedbackText;
    }
}
public class Zoo_Management {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to Zootopia!");
        while (true) {
            System.out.println("1. Enter as Admin");
            System.out.println("2. Enter as a Visitor");
            System.out.println("3. View Special Deals");
            System.out.println("4.Exit");
            System.out.println("Enter your choice: ");
            int choice = input.nextInt();
            input.nextLine();
            if (choice == 1) {
                System.out.println("enter user_name:");
                String email_id = input.next();
                System.out.println("enter password:");
                String password = input.next();
                System.out.println("enter age:");
                int age = input.nextInt();
                System.out.println("enter mobile number:");
                String mobile_no = input.next();
                Admin a = new Admin("admin", age, mobile_no,email_id, password);
                if(a.loginAdmin()){
                }
                else{
                    break;
                }

                while (true) {
                    System.out.println("Admin Menu:");
                    System.out.println("1. Manage Attractions");
                    System.out.println("2. Manage Animals");
                    System.out.println("3.Schedule events");
                    System.out.println("4. Set Discounts");
                    System.out.println("5. Set Special Deals");
                    System.out.println("6. View Visitor Statistics");
                    System.out.println("7. View Feedback");
                    System.out.println("8. Exit");
                    System.out.println("enter your choice:");
                    int c1 = input.nextInt();
                    if (c1 == 1) {
                        System.out.println("1.Add Attraction");
                        System.out.println("2.View Attraction");
                        System.out.println("3.Modify Attraction");
                        System.out.println("4.Remove Attraction");
                        System.out.println("5.Exit");
                        System.out.println("enter your choice:");
                        int c2 = input.nextInt();
                        input.nextLine();
                        if (c2 == 1) {
                            System.out.println("enter Attraction name:");
                            String n = input.nextLine();
                            System.out.println("enter description:");
                            String description = input.nextLine();
                            System.out.println("enter price:");
                            double price = input.nextDouble();
                            a.addAttraction(n,description, price, true);
                        } else if (c2 == 2) {
                            a.viewAttractions();
                        } else if (c2 == 3) {
                            a.viewAttractions();
                            System.out.println("enter the attraction name which you want to modify");
                            String n1 = input.nextLine();
                            System.out.println("enter new description");
                            String newDescription = input.nextLine();
                            System.out.println("enter new price");
                            double newPrice = input.nextDouble();
                            a.modifyAttraction(n1, newDescription, newPrice);
                        } else if (c2 == 4) {
                            a.viewAttractions();
                            System.out.println("enter the name of the attraction you want to delete:");
                            String name1 = input.nextLine();
                            a.removeAttraction(name1);
                        } else if (c2 == 5) {
                            System.out.println("logged out");
                            break;
                        } else {
                            System.out.println("invalid input");
                        }
                    } else if (c1 == 2) {
                        System.out.println("Manage Animals:");
                        System.out.println("1. Add Animal");
                        System.out.println("2. Update Animal Details");
                        System.out.println("3. Remove Animal");
                        System.out.println("4. Exit");
                        System.out.println("Enter your choice:");
                        int c3 = input.nextInt();
                        input.nextLine();
                        if (c3 == 1) {
                            a.viewAttractions();
                            System.out.println("enter the attraction name:");
                            String at = input.nextLine();
                            System.out.println("Enter Animal Name:");
                            String animalName = input.nextLine();
                            System.out.println("Enter Animal Species:");
                            String animalSpecies = input.nextLine();
                            a.addAnimal(animalName, at, animalSpecies);
                        } else if (c3 == 2) {
                            System.out.println("enter the attraction name in which you want to modify the animal update ");
                            String n2=input.nextLine();
                            System.out.println("Enter the name of the animal you want to update:");
                            String animalToUpdate = input.nextLine();
                            System.out.println("enter new Description");
                            String d= input.nextLine();
                            a.modifyAnimal(n2,animalToUpdate,d);
                        } else if (c3 == 3) {
                            System.out.println("Enter the name of the attraction from which you want to remove the animal");
                            String n3= input.nextLine();
                            System.out.println("Enter the name of the animal you want to remove:");
                            String animalToRemove = input.nextLine();
                            a.removeAnimal(n3,animalToRemove);
                        } else if (c3 == 4) {
                            System.out.println("logged out");
                            break;
                        } else {
                            System.out.println("Invalid choice. Please select a valid option.");
                        }
                    } else if (c1 == 3) {
                        System.out.println("1.View Events");
                        System.out.println("2.Modify Events");
                        System.out.println("3.Add Events");
                        System.out.println("4.Remove event");
                        System.out.println("5.Exit");
                        System.out.println("enter the choice:");
                        int c4 = input.nextInt();
                        input.nextLine();
                        if (c4 == 1) {
                            a.viewEvents();
                        } else if (c4 == 2) {
                            System.out.println("enter the event name:");
                            String n5=input.nextLine();
                            System.out.println("Enter new Ticket_price");
                            double price= input.nextDouble();
                            input.nextLine();
                            a.modifyEvent(n5,true,price);
                        } else if (c4 == 3) {
                            System.out.println("enter the attraction name:");
                            String a4= input.nextLine();
                            System.out.println("Enter the event name");
                            String n7= input.nextLine();
                            System.out.println("enter the ticket price for event:");
                            double p= input.nextDouble();
                            a.addEvent(n7,a4,true,p);
                        } else if (c4 == 4) {
                            System.out.println("enter the event name you want to remove:");
                            String re=input.nextLine();
                            a.removeEvent(re);
                        } else if (c4 == 5) {
                            System.out.println("logged out");
                            break;
                        } else {
                            System.out.println("invalid input");
                        }
                    } else if (c1 == 4) {
                        System.out.println("1. Add Discount");
                        System.out.println("2. Modify Discount");
                        System.out.println("3. Remove Discount");
                        System.out.println("4. Exit");
                        System.out.println("enter the choice:");
                        int c5 = input.nextInt();
                        input.nextLine();
                        if (c5 == 1) {
                            System.out.println("enter the discount name:");
                            String dn=input.nextLine();
                            System.out.println("enter the discount code:");
                            String dc=input.nextLine();
                            System.out.println("enter the discount percentage:");
                            double dp=input.nextDouble();
                            a.setDiscount(dc,dn,dp);
                        } else if (c5 == 2) {
                            System.out.println("enter the discount code: ");
                            String dc1=input.nextLine();
                            System.out.println("enter the discount percentage:");
                            double dp1= input.nextDouble();
                            a.modifyDiscount(dc1,dp1);
                        } else if (c5 == 3) {
                            System.out.println("enter the discount name");
                            String dn=input.nextLine();
                            a.removeDiscount(dn);
                        } else if (c5 == 4) {
                            System.out.println("logged out");
                            break;
                        } else {
                            System.out.println("invalid input");
                        }
                    } else if (c1 == 5) {
                        System.out.println("1.Set special deal");
                        System.out.println("2.View special deal");
                        System.out.println("3.Remove special deal");
                        System.out.println("4.Exit");
                        System.out.println("enter the choice:");
                        int c6 = input.nextInt();
                        input.nextLine();
                        if (c6 == 1) {
                            System.out.println("enter the deal name:");
                            String deal=input.nextLine();
                            a.setSpecialDeal(deal);
                        } else if (c6 == 2) {
                            a.viewSpecialDeals();
                        } else if (c6 == 3) {
                            System.out.println("enter the deal to remove");
                            String deal_n= input.nextLine();
                            a.removeSpecialDeal(deal_n);
                        } else if (c6 == 4) {
                            System.out.println("logged out");
                            break;
                        } else {
                            System.out.println("invalid input");
                        }
                    } else if (c1 == 6) {
                        System.out.println("1.View visitor stats");
                        System.out.println("2.Exit");
                        System.out.println("enter the choice:");
                        int c7 = input.nextInt();
                        input.nextLine();
                        if (c7 == 1) {
                            a.viewVisitorStats();
                        } else if (c7 == 2) {
                            System.out.println("logged out");
                            break;
                        }
                    } else if (c1 == 7) {
                        System.out.println("1.View feedback");
                        System.out.println("2.Exit");
                        System.out.println("enter the choice:");
                        int c8 = input.nextInt();
                        input.nextLine();
                        if (c8 == 1) {
                            a.viewFeedback();
                        } else if (c8 == 2) {
                            System.out.println("logged out");
                            break;
                        } else {
                            System.out.println("invalid input");
                        }
                    } else if (c1 == 8) {
                        System.out.println("logged out");
                        break;
                    }
                }
            } if (choice == 2) {
                System.out.println("1.Register");
                System.out.println("2.Login");
                System.out.println("enter the choice:");
                int c9 = input.nextInt();
                input.nextLine();
                if (c9 == 1) {
                    System.out.println("Enter Visitor Name:");
                    String name = input.nextLine();
                    System.out.println("Enter Visitor Age:");
                    int age = input.nextInt();
                    System.out.println("Enter Visitor Phone Number:");
                    String mobile_no = input.next();
                    System.out.println("Enter Visitor Balance:");
                    double balance = input.nextDouble();
                    System.out.println("Enter Visitor Email:");
                    String email_id = input.next();
                    System.out.println("Enter Visitor Password:");
                    String password = input.next();
                    System.out.println("enter the experience Basic/Premium");
                    String experience = input.next();
                    Visitor v = new Visitor(name, age, mobile_no, email_id, password, balance, experience);
                    System.out.println("registration successful");
                }
                if (c9 == 2) {
                    System.out.println("enter the email_id");
                    String email_id = input.next();
                    System.out.println("enter the password");
                    String password = input.next();
                    System.out.println("enter the balance:");
                    double balance=input.nextDouble();
                    System.out.println("enter the experience basic/premium");
                    String experience= input.next();
                    Visitor v = new Visitor(email_id,password,balance,experience);
                    v.login_v(email_id, password);
                    while (true) {
                        System.out.println("Visitor Menu:");
                        System.out.println("1. Explore the Zoo");
                        System.out.println("2. Buy Membership");
                        System.out.println("3. Buy Tickets");
                        System.out.println("4. View Discounts");
                        System.out.println("5. View Special Deals");
                        System.out.println("6. Visit Animals");
                        System.out.println("7. Visit Attractions");
                        System.out.println("8. Leave Feedback");
                        System.out.println("9. Log Out");
                        System.out.println("enter the choice:");
                        int choice1 = input.nextInt();
                        if (choice1 == 1) {
                            v.exploreTheZoo();
                        } else if (choice1 == 2) {
                            v.buyMembership();
                        } else if (choice1 == 3) {
                            v.buyTickets();
                        } else if (choice1 == 4) {
                            v.viewAllDiscounts();
                        } else if (choice1 == 5) {
                            v.viewSpecialDeals();
                        } else if (choice1 == 6) {
                            v.visitAnimals();
                        } else if (choice1 == 7) {
                            v.visitAttractions();
                        } else if (choice1 == 8) {
                            v.leaveFeedback();
                        } else if (choice1 == 9) {
                            v.logout();
                            break;
                        } else {
                            System.out.println("invalid input");
                        }
                    }
                }
            }
            else if(choice==3){
                Visitor v=new Visitor();
                v.viewSpecialDeals();
            }
            else if(choice==4){
                break;
            }
            else{
                System.out.println("invalid input");
            }
        }
    }
}





