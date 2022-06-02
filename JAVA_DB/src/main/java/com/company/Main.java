package com.company;

import org.hibernate.SessionFactory;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import static com.company.UserType.ADMIN;
import static com.company.UserType.REGULAR;


public class Main {


    private static final Scanner SC = new Scanner(System.in);
    private static SessionFactory sessionFactory;

    public static void main(String[] args) throws IOException {

        UserService service = new UserService();
        ItemService itemService = new ItemService();

        while (true){
            mainMenu();
            String logInChoise = SC.nextLine();
            switch (logInChoise){
                case "1":
                    //log in
                    System.out.print("Username: ");
                    String nickname = SC.nextLine();
                    System.out.print("Password: ");
                    String password = SC.nextLine();

                    Usertable loggedInUser = service.getUser(nickname, password);
                    if (loggedInUser != null) {
                        System.out.println("logged in successfully");
                        checkMenu(loggedInUser, service, itemService);
                    } else {
                        System.out.println("Your username or password is incorrect");
                    }
                    break;
                case "2":
                    // sign up
                    registerNewUser(service, itemService);
                    break;
                case "3":
                    // close
                    return;
                default:
                    System.out.println("Unknown command");
            }
        }

//        try {
//            sessionFactory = new Configuration().configure().buildSessionFactory();
//        }catch (HibernateException e){
//            System.out.println(e.getMessage());
//        }
//        Session session = sessionFactory.openSession();
//
////        com.company.Usertable usertable = new com.company.Usertable("Nick", "sunkus","Name", "Surnname", "ADMIN");
////        Transaction tx = session.beginTransaction();
////        session.save(usertable);
////        tx.commit();
//        com.company.Listings listings = new com.company.Listings("1", "Nick", "listing", 5f, "Kaunas", "Ramunas", "863784332", "notes");
//        Transaction tx = session.beginTransaction();
//        session.save(listings);
//        tx.commit();
//
//
//        session.close();

    }

    private static void mainMenu() {
        System.out.println("[1] Sign In");
        System.out.println("[2] Sign up");
        System.out.println("[3] Close");
    }

    private static void checkMenu(Usertable usertable, UserService service, ItemService itemService) throws IOException {
        if (usertable.getUsertype().equals(REGULAR)) {
            regularMenu(usertable, itemService);
        } else if (usertable.getUsertype().equals(ADMIN)) {
            adminMenu(usertable, service, itemService);
        }
    }

    private static void regularMenu(Usertable regularUser, ItemService itemService) {

        while (true) {
            printRegularMenu();
            String choise = SC.nextLine();
            switch (choise) {
                case "1":
                    //
                    addItem(itemService);
                    break;
                case "2":
                    //
                    editListing(itemService);
                    break;
                case "3":
                    //
                    removeListing(itemService);
                    break;
                case "4":
                    //
                    listingSold(itemService);
                    break;
                case "5":
                    //
                    List<Listings> notSoldItems = ItemService.getAllNotDeletedItems();
                    printListings(notSoldItems, regularUser);
                    break;
                case "6":
                    //
                    break;
                case "7":
                    //
                    listingReported(itemService);
                    break;
                case "8":
                    listingSearch(itemService, regularUser);
//                    List<com.company.Listings> searchedItems = com.company.ItemService.getSearchedListings();
//                    printListings(searchedItems, regularUser);
                    break;
                case "9":
                    return;
                default:
                    System.out.println("Unknown command");
                    break;
            }
        }
    }

    private static void adminMenu(Usertable adminUser, UserService userService, ItemService itemService) throws IOException {

        //try {
            while (true) {
                printAdminMenu();
                String choise = SC.nextLine();
                switch (choise) {
                    case "1":
                        //
                        addItem(itemService);
                        break;
                    case "2":
                        //
                        editListing(itemService);
                        break;
                    case "3":
                        //
                        removeListing(itemService);
                        break;
                    case "4":
                        //
                        listingSold(itemService);
                        break;
                    case "5":
                        //
                        List<Listings> allListingsAdmin = ItemService.getAllListingsAdmin();
                        //print
                        printListings(allListingsAdmin, adminUser);
                        break;
                    case "6":
                        yourListings(itemService, adminUser);
                        //
                        break;
                    case "7":
                        //
                        List<Listings> reportedListings = itemService.getAllReported();
                        printListings(reportedListings, adminUser);
                        break;
                    case "8":
                        //
                        List<Listings> soldItems = itemService.getAllSoldItems();
                        printListings(soldItems, adminUser);
                        break;
                    case "9":
                        //
                        listingOK(itemService);
                        break;
                    case "10":
                        addNewUser(userService);
                        break;
                    case "11":
                        listingSearch(itemService, adminUser);
                       // printListings(searchItems(itemService), adminUser);
                    case "12":
                        return;
                    default:
                        System.out.println("Unknown command");
                        break;
                }
            }
       // }catch (){
        //    System.out.println(e.getMessage());
       // }
    }

    private static void printAdminMenu(){
        System.out.println("[1]  Add listing");
        System.out.println("[2]  Edit listing");
        System.out.println("[3]  Remove listing");
        System.out.println("[4]  Buy item");
        System.out.println("[5]  Show all listings");
        System.out.println("[6]  Show your listings");
        System.out.println("[7]  Show reported listings");
        System.out.println("[8]  Show all sold items");
        System.out.println("[9]  Mark listing as OK");
        System.out.println("[10] Register new user");
        System.out.println("[11] Search items");
        System.out.println("[12] Main menu");
    }

    private static void printRegularMenu(){
        System.out.println("[1] Add listing");
        System.out.println("[2] Edit listing");
        System.out.println("[3] Remove listing");
        System.out.println("[4] Buy item");
        System.out.println("[5] Show all listings");
        System.out.println("[6] Show your listings");
        System.out.println("[7] Report listing");
        System.out.println("[8] Search listing");
        System.out.println("[9] Main menu");
    }

    private static void registerNewUser(UserService service, ItemService itemService){

        System.out.println("Registration");
        System.out.print("Your nickname: ");
        String nickname = SC.nextLine();
        System.out.print("Password: ");
        String password = SC.nextLine();
        System.out.print("Name: ");
        String name = SC.nextLine();
        System.out.print("Surname: ");
        String surname = SC.nextLine();
        SC.nextLine();

        Usertable regularUser = new Usertable(nickname, password, name, surname);
        service.addUser(regularUser);
        System.out.println("Registration successfull");

        regularMenu(regularUser, itemService);

    }

    private static void addItem(ItemService itemService){

        System.out.println("Listing id: ");
        String id = SC.nextLine();
        System.out.println("Your nickname: ");
        String nickname = SC.nextLine();
        System.out.print("Listing title: ");
        String title = SC.nextLine();
        System.out.print("Listing price: ");
        float price = SC.nextInt();
        SC.nextLine();
        System.out.print("City: ");
        String city = SC.nextLine();
        System.out.print("Your name: ");
        String contactName = SC.nextLine();
        System.out.print("Your phone number: ");
        String phoneNumber = SC.nextLine();
        System.out.print("Aditional notes: ");
        String notes = SC.nextLine();
        SC.nextLine();

        itemService.addListing(new Listings(id, nickname ,title, price, city, contactName, phoneNumber, notes));
    }

    private static void printListings(List<Listings> items, Usertable user){
        if(user.getUsertype().equals(ADMIN)){
            for (Listings item : items){
                System.out.printf("Item Id: %s\n", item.getId());
                System.out.printf("Item: %s\n", item.getTitle());
                System.out.printf("Price: %s\n", item.getPrice());
                System.out.printf("City: %s\n", item.getCity());
                System.out.printf("Contact name: %s\n", item.getContactName());
                System.out.printf("Contact phone number: %s\n", item.getPhoneNumber());
                System.out.printf("Aditional notes: %s\n", item.getNotes());
                System.out.printf("Item reported: %s\n", item.isReport());
                System.out.printf("Item sold: %s\n", item.isSold());
                System.out.printf("Item deleted: %s\n", item.isDeleted());
                System.out.println();
            }
        }else{
            for (Listings item : items){
                System.out.printf("Item: %s\n", item.getTitle());
                System.out.printf("Price: %s\n", item.getPrice());
                System.out.printf("City: %s\n", item.getCity());
                System.out.printf("Contact name: %s\n", item.getContactName());
                System.out.printf("Contact phone number: %s\n", item.getPhoneNumber());
                System.out.printf("Aditional notes: %s\n", item.getNotes());
                System.out.println();
            }
        }
    }

    private static void editListing(ItemService itemService){

        System.out.print("Listing to edit ID: ");
        String itemId = SC.nextLine();
        System.out.print("Listing title: ");
        String title = SC.nextLine();
        System.out.print("Listing price: ");
        int price = SC.nextInt();
        SC.nextLine();
        System.out.print("City: ");
        String city = SC.nextLine();
        System.out.print("Your name: ");
        String contactName = SC.nextLine();
        System.out.print("Your phone number: ");
        String phoneNumber = SC.nextLine();
        System.out.print("Aditional notes: ");
        String notes = SC.nextLine();
        SC.nextLine();


        itemService.editListingg(itemId, title, price, city, contactName, phoneNumber, notes);

//        if (changedItem == null) {
//            System.out.printf("There is no listing with id: %s\n", itemId);
//        } else {
//            System.out.println("Listing successfylly updated");
//        }
    }

    private static void removeListing(ItemService itemService) {

        System.out.print("Listing to delete ID: ");
        String listingID = SC.nextLine();

        itemService.markAsDeleted(listingID);

    }
    private static void listingSold(ItemService itemService) {
        System.out.print("Listing to mark as sold ID: ");
        String listingID = SC.nextLine();

        itemService.markAsSold(listingID);
    }

    private static void listingReported(ItemService itemService) {
        System.out.print("Listing to report ID: ");
        String listingID = SC.nextLine();

        itemService.markAsReported(listingID);
    }

    private static void listingSearch(ItemService itemService, Usertable user) {
        System.out.print("Listing to search keywords: ");
        String search = SC.nextLine();

        //itemService.getSearchedListings(search);
        List<Listings> searchedItems = ItemService.getSearchedListings(search);
        printListings(searchedItems, user);
    }

    private static void yourListings(ItemService itemService, Usertable user) {
        System.out.print("Your nickname: ");
        String nickname = SC.nextLine();

        //itemService.getSearchedListings(search);
        List<Listings> yourListings = ItemService.getYourListings(nickname);
        printListings(yourListings, user);
    }

    private static void listingOK(ItemService itemService) {
        System.out.print("Listing to mark as ok ID: ");
        String listingID = SC.nextLine();

        itemService.markAsOK(listingID);
    }

    private static void addNewUser(UserService service) throws IOException {

        System.out.println("New user");
        System.out.print("Nickname: ");
        String nickname = SC.nextLine();
        System.out.print("Password: ");
        String password = SC.nextLine();
        System.out.print("Name: ");
        String name = SC.nextLine();
        System.out.print("Surname: ");
        String surname = SC.nextLine();
        SC.nextLine();
        UserType type = getTypeForNewUser();
        Usertable regularUser = new Usertable(nickname, password, name, surname, type);
        service.addUser(regularUser);
        System.out.println("User successfully registered");
    }

    private static UserType getTypeForNewUser() {

        System.out.println("Choose user type:");

        while (true) {
            System.out.println("[1] Regular");
            System.out.println("[2] Admin");
            String choice = SC.nextLine();

            switch (choice) {
                case "1":
                    return REGULAR;
                case "2":
                    return ADMIN;
                default:
                    System.out.println("Unknown command");
            }
        }
    }

}
