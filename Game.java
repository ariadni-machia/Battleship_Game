import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
public class Game
{
    static final int M = 7; // Μέγεθος του πίνακα
    static Scanner input = new Scanner(System.in); // Scanner για να δέχεται δεδομένα από το πληκτρολόγιο
    public static void main(){
        String answer; // απάντηση του χρήστη από το πληκτρολόγιο
        boolean success = false; // επιτυχής τοποθέτηση πλοίου
        boolean ship1 = false, ship2 = false, ship3 = false, ship4 = false, ship5 = false; // μεταβλητές που δηλώνουν εαν έχουν τοποθετηθεί τα πλοία
        
        int shots = 0; // συνολικές βολές
        int maxShots = 35; // Μέγιστος αριθμός βολών μέχρι να τερματιστεί το παιχνίδι
        int fireShots[] = new int [2]; // συντεταγμένες για επίθεση

        System.out.println("     ____________________ NAVAL BATTLE ____________________\n");
        System.out.println("\t----------------- START GAME -----------------\n");
        Player user = new Player(); // δημιουργεία αντικείμενο Player για τον χρήστη
        Player computer = new Player(); //// δημιουργεία αντικείμενο Player για τον υπολογιστή
        System.out.print("Your name: ");
        user.setName( input.next() );
        Board.drawboards( user.getBoard().getPinakas(), computer.getBoard().getPinakas() ); // εμφάνιση πινάκων
        computer.placeAllShips();  // τυχαία τοποθέτηση των πλοίων για τον υπολογιστή
        // Τοποθέτηση πλοίων
        System.out.println("You can either place the ships at random or by giving coordinates and orientation.\n\t\t\tDo you want at random? (Y/N)");
        System.out.println("(If you want to place them at random, press 'Y', if you want by giving coordinates and orientation, press 'N'.)");
        if( randomPlace() ){ //τυχαία τοποθέτηση
           user.placeAllShips();
           Board.drawboards( user.getBoard().getPinakas(), computer.getBoard().getPinakas() ); 
        }
        else{ //τοποθετήση από τον χρήστη     
            do{    
                System.out.println("Which ship do you want to place?");
                if(ship5==false)
                    System.out.println("For Carrier (5 tiles) press 'C5' ");
                if(ship4==false)
                    System.out.println("For Battleship (4 tiles) press 'B4' ");
                if(ship3==false)
                    System.out.println("For Cruiser (3 tiles) press 'C3' ");
                if(ship2==false)
                    System.out.println("For Submarine (3 tiles) press 'S3' ");
                if(ship1==false)
                    System.out.println("For Destroyer (2 tiles) press 'D2' ");
                System.out.print("Answer: ");    
                answer = input.next();                
                switch(answer){
                    case "C5": case "c5":
                        if(ship5==false){
                            Carrier c5 = new Carrier(); 
                            do{
                                if ( user.placeShip(c5) ){
                                    success = true;
                                    Board.drawboards( user.getBoard().getPinakas(), computer.getBoard().getPinakas() );
                                    ship5 = true;
                                }
                                else
                                    success = false;                            
                            }while (success == false);
                        }
                        break;
                    case "B4": case "b4":
                        if(ship4==false){
                            Battleship b4 = new Battleship(); 
                            do{
                                if ( user.placeShip(b4) ){
                                    success = true;
                                    Board.drawboards( user.getBoard().getPinakas(), computer.getBoard().getPinakas() );
                                    ship4 = true;
                                }
                                else
                                    success = false;                            
                            }while (success == false);
                        }
                        break;
                    case "C3": case "c3":
                        if(ship3==false){
                            Cruiser c3 = new Cruiser(); 
                            do{
                                if ( user.placeShip(c3) ){
                                    success = true;
                                    Board.drawboards( user.getBoard().getPinakas(), computer.getBoard().getPinakas() );
                                    ship3 = true;
                                }
                                else
                                    success = false;                            
                            }while (success == false);
                        }
                        break;
                    case "S3": case "s3":
                        if(ship2==false){
                            Submarine s3 = new Submarine(); 
                            do{
                                if ( user.placeShip(s3) ){
                                    success = true;
                                    Board.drawboards( user.getBoard().getPinakas(), computer.getBoard().getPinakas() );
                                    ship2 = true;
                                }
                                else
                                    success = false;                            
                            }while (success == false);
                        }
                        break;
                    case "D2": case "d2":
                        if(ship1==false){
                            Destroyer d2 = new Destroyer(); 
                            do{
                                if ( user.placeShip(d2) ){
                                    success = true;
                                    Board.drawboards( user.getBoard().getPinakas(), computer.getBoard().getPinakas() );
                                    ship1 = true;
                                }
                                else
                                    success = false;                            
                            }while (success == false);
                        }
                        break;
                    default: //δεν θα πρέπει να φτάσει πότε εδώ, τότε υπάρχει πρόβλημα
                        System.out.println("Error! Your input is not accepted. Try again.");
                        break;
                }
                success =false;
            }while( (ship5 && ship4 && ship3 && ship2 && ship1)!=true );
        }
        
        //Επιθέσεις
        while( (user.getBoard()).allShipsSunk() == false && (computer.getBoard()).allShipsSunk() == false && shots<maxShots ){
            System.out.print("For attack: ");
            do{
                fireShots = getInput();
                success = user.fire(computer.getBoard(), fireShots); // κάνει επίθεση ο χρήστης στον υπολογιστή
            }while(success == false);
            shots++; 
            fireShots = getRandInput();
            computer.fire(user.getBoard(), fireShots); // κάνει επίθεση ο υπολογιστής στον χρήστη
            Board.drawboards( user.getBoard().getPinakas(), computer.getBoard().getPinakas() ); // εμφάνιση πινάκων
        }
        
        //Νικητής
        if( (user.getBoard()).allShipsSunk() ) {
            System.out.println("\t\t\tThe winner is: "+ computer.getName() );
        }
        else if( (computer.getBoard()).allShipsSunk() ){
            System.out.println("\t\t\tThe winner is: "+ user.getName() );
        }
        else{
            System.out.println("There is no winner. You made too many shots without sinking all your opponent's ships.");
        }
        
        //Τα στατιστικά κάθε παίκτη
        System.out.println("\nThe statistics of "+ user.getName() + ":" );
        user.getStats();
        System.out.println("\nThe statistics of "+ computer.getName() + ":" );
        computer.getStats();
        System.out.println("\n\t----------------- GAME OVER -----------------");
    }
    
    public static int[] getInput() // Διαβάζει δύο ακέραιους από το πληκτρολόγιο και τους επιστρέφει σε πίνακα
    {
        System.out.println("Give the coordinates: ");
        int x = input.nextInt();
        int y = input.nextInt();
        int[] coordinates = new int[2];
        coordinates[0] = x;
        coordinates[1] = y;
        
        return coordinates;
    }
    public static Orientation getOrientation() //Επιστρέφει τον προσανατολισμό  που δίνει ο παίκτης από το πληκτρολόγιο
    {
        int a=1;
        do{
            System.out.println("Type 'H' if you want the ship placed Horizontally or 'V' if you want the ship placed Vertically.");   
            char or = input.next().charAt(0);
            if(or == 'H' || or == 'h'){
                a=2;
                return Orientation.horizontal;
            }
            else if(or == 'V' || or == 'v'){
                a=2;
                return Orientation.vertical;
            }
            else{
                a=1;
                System.out.println("Your input is invalid.");
            }
        }while(a==1);
        return Orientation.vertical; // δεν θα φτάσει ποτέ εδώ, default τιμή σε περίπτωση λάθους
    }
    public static int[] getRandInput(){ //Δημιουργεί και επιστρέφει 2 τυχαίες ακέραιες τιμές εντός των ορίων
        int randnum[] = new int[2];
        randnum[0] = ThreadLocalRandom.current().nextInt(0,M); // τυχαίος ακέραιος αριθμός από το 0 εως και το Μ-1
        randnum[1] = ThreadLocalRandom.current().nextInt(0,M);
        return randnum;
    }
    public static boolean randomPlace(){ // Διαβάζει από το πληκτρολόγιο και επιστρέφει για Υ: true, ενώ για Ν: false
        boolean answerAccepted = false;
        do{                
            char answer = input.next().charAt(0);
            if(answer == 'Y' || answer == 'y'){
               return true; 
            }
            else if(answer == 'N' || answer == 'n'){
                return false;
            }
            else{
                System.out.println("Type 'Y' if you want to place the ships at random or 'N' if you want to place the ships by giving coordinates and orientation.");   
            }
        }while(answerAccepted == false);
        return false; // δεν θα φτάσει ποτέ εδώ, default τιμή σε περίπτωση λάθος
    }
}
