import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CarRentalSystem {

    private static Scanner sc = new Scanner(System.in);
    private HashMap<String, String> rentedCars =
            new HashMap<String, String>(100, 0.5f);
    private HashMap<String, RentedCars> hash=new HashMap<String, RentedCars>(100,0.5f);
    private static String getPlateNo() throws InputMismatchException{
        System.out.println("Introduceti numarul de inmatriculare:");
        String inm;
        try{
            inm=sc.nextLine();
        }catch (InputMismatchException e){
            throw new InputMismatchException();
        }
        return inm;
    }
    private int getCarsNo(String ownerName){
        return this.hash.get(ownerName).getSize();
    }
    private ArrayList<String> getCarsList(String ownerName){
        return this.hash.get(ownerName).getPlates();
    }
    private static String getOwnerName() {
        System.out.println("Introduceti numele proprietarului:");
        return sc.nextLine();
    }

    // search for a key in hashtable
    private boolean isCarRent(String plateNo) {
        return rentedCars.containsKey(plateNo);
    }

    // get the value associated to a key
    private String getCarRent(String plateNo) {
        if(this.isCarRent(plateNo)==false)
            return "Acest autovehicul nu este in sistemul nostru";
        return rentedCars.get(plateNo);
    }

    // add a new (key, value) pair
    private void rentCar(String plateNo, String ownerName) {
        if(this.isCarRent(plateNo)==false)
            rentedCars.put(plateNo, ownerName);
        else System.out.println("Aceasta masina nu poate fi adaugata, deoarece deja exista in sistemul nostru.");
    }

    // remove an existing (key, value) pair
    private void returnCar(String plateNo) {
        String rez=rentedCars.remove(plateNo);
        if(rez.equals(null))
            System.out.println("Stergerea nu s-a putut realiza");
        else System.out.println("Stergerea a fost realizata cu succes");
    }
    private int totalRented(){
        return this.rentedCars.size();
    }
    private static void printCommandsList() {
        System.out.println("help         - Afiseaza aceasta lista de comenzi");
        System.out.println("add          - Adauga o noua pereche (masina, sofer)");
        System.out.println("check        - Verifica daca o masina este deja luata");
        System.out.println("remove       - Sterge o masina existenta din hashtable");
        System.out.println("getOwner     - Afiseaza proprietarul curent al masinii");
        System.out.println("totalRented  - Afiseaza numarul de masini inchiriate");
        System.out.println("getCarsNo    - Afiseaza numarul de masini inchiriate de un anumit utilizator");
        System.out.println("getCarsList  - Afiseaza lista de masini inchiriate de un anumit utilizator");
        System.out.println("quit         - Inchide aplicatia");
    }

    public void run() {
        boolean quit = false;
        while(!quit) {
            System.out.println("Asteapta comanda: (help - Afiseaza lista de comenzi)");
            String command = sc.nextLine();
            switch(command) {
                case "help":
                    printCommandsList();
                    break;
                case "add":
                    try {
                        rentCar(getPlateNo(), getOwnerName());
                    }catch (InputMismatchException e){
                        System.out.println("Acesta nu este un sir de caractere valid.");
                        break;
                    }
                    break;
                case "check":
                    System.out.println(isCarRent(getPlateNo()));
                    break;
                case "remove":
                    returnCar(getPlateNo());
                    break;
                case "getOwner":
                    System.out.println(getCarRent(getPlateNo()));
                    break;
                case "quit":
                    System.out.println("Aplicatia se inchide...");
                    quit = true;
                    break;
                case "totalRented":
                    System.out.println("In colectie se gasesc "+totalRented()+" masini");
                    break;
                default:
                    System.out.println("Unknown command. Choose from:");
                    printCommandsList();
            }
        }
    }

    public static void main(String[] args) {

        // create and run an instance (for test purpose)
        new CarRentalSystem().run();

    }
}
