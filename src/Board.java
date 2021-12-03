import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
public class Board{
    private Tile[][] pinakas = new Tile[Game.M][Game.M];// M == rows && collums
    public Tile[][] getPinakas(){
        return pinakas;
    }
    public Board(){ //δημιουργός χωρις ορίσματα που αρχικοποιεί τον πίνακα με συντεταγμένες και κελιά τύπου Sea
        for(int i=0;i<Game.M;i++)
            for(int j=0;j<Game.M;j++)
                pinakas[i][j] = new Tile(i,j,Type.Sea);       
    }
    public static void drawboards(Tile[][] boadPlayer, Tile[][] boardComputer){
        //boolean hidden;
        System.out.println("\n     - - Y O U - - \t\t\t   - O P P O N E N T -");
        for (int i=-1;i<Game.M;i++){            
            for(int j=-1;j<Game.M;j++){ //εμφανίζει τον πίνακα του παίκτη
                if(i==-1 && j==-1)
                    System.out.print("   ");
                else if(i==-1)
                    System.out.print(j + "  ");
                else
                    if(j==-1)
                        System.out.print(i + "  ");
                    else
                        System.out.print(boadPlayer[i][j].draw(false) + "  "); //πίνακας παίκτη                            
            }
            System.out.print("\t\t");
            for(int j=-1;j<Game.M;j++){ //εμφανίζει τον πίνακα του αντιπάλου
                if(i==-1 && j==-1)
                    System.out.print("   ");
                else if(i==-1)
                    System.out.print(j + "  ");
                else
                    if(j==-1)
                        System.out.print(i + "  ");
                    else
                        System.out.print(boardComputer[i][j].draw(true) + "  "); // πίνακας αντιπάλου (υπολογιστή)                     
            }
            System.out.println();
        }
        System.out.println();
    } 
    public boolean allShipsSunk(){ //Μέθοδος που ελέγχει εαν υπάρχουν κελιά τύπου Ship
        for(int i=0;i<Game.M;i++)
            for(int j=0;j<Game.M;j++)
                if(pinakas[i][j].getType()==Type.Ship)
                    return false; //υπάρχει κελί που με πλοίο
        return true; // δεν υπάρχει άλλο κελί με πλοίο + Το παιχνίδι τελείωσε
    }
    public ArrayList<Tile> getAdjacentTiles(Tile t){ // Μέθοδος που επιστρέφει τα γειτονικά κελιά του κελιού στο όρισμα
       int tempX, tempY;                                          
       ArrayList<Tile> neighborTiles = new ArrayList<Tile>(); // Λίστα με τα γειτονικά κελιά του κελιού στο όρισμα
       for(int i=0;i<Game.M;i++){
            for(int j=0;j<Game.M;j++){
                tempX = t.getX() - i;
                tempY = t.getY() - j;
                if( (tempX == 1 && tempY == 0) || (tempX == 0 && tempY == 1) || (tempX == 0 && tempY == -1) || (tempX == -1 && tempY == 0) ){ //(πάνω κελί)||(αριστερά κελί)||(δεξιά κελί)||(κατω κελί)                       
                        neighborTiles.add( pinakas[i][j] );                                   
                }
            }            
       }
       return neighborTiles; 
    }
    public void placeAllShips(){ //Μέθοδος που τοποθετεί με τυχαίο τρόπο όλα τα πλοία στον πίνακα
        int rand[] = new int [2]; //συντεταγμένες
        Orientation orientation; // προσανατολισμός
        boolean success = false; // δείχνει εαν τοποθετήθηκε το πλοίο επιτυχώς
        Tile tile = new Tile(0,0,Type.Sea); // κελί για το χρησιμοποιηθεί στα ορίσματα της placeShip
        //δημιουργία των 5 πλοίων
        Destroyer shipDestroyer = new Destroyer();
        Submarine shipSubmarine =  new Submarine();
        Cruiser shipCruiser = new Cruiser();
        Battleship shipBattleship = new Battleship();
        Carrier shipCarrier = new Carrier(); 
        //τυχαία τοποθέτηση των πλοίων
        for(int i=0; i<5; i++){
            while(success!=true){  
                rand = Game.getRandInput(); // ο πίνακας rand αντιγράφει ένα πίνακα με 2 ακέραιες τιμές
                tile.setX(rand[0]);
                tile.setY(rand[1]);  
                if ( ThreadLocalRandom.current().nextInt(0,2) == 0 ) // τυχαίος προσανατολισμός
                    orientation = Orientation.horizontal;
                else 
                    orientation = Orientation.vertical;
                switch(i){   
                    case 4: 
                            success = shipDestroyer.placeShip(tile, orientation, this, true); 
                            break;
                    case 3: 
                            success = shipSubmarine.placeShip(tile, orientation, this, true);
                            break;
                    case 2: 
                            success = shipCruiser.placeShip(tile, orientation, this, true);
                            break;
                    case 1: 
                            success = shipBattleship.placeShip(tile, orientation, this, true);
                            break;
                    case 0: 
                            success = shipCarrier.placeShip(tile, orientation, this, true);
                            break;
                    default: // δεν θα πρέπει να φτάσει εδώ ποτέ, τότε υπάρχει πρόβλημα
                            System.out.println("Error! Something went wrong with the placement of the ships");
                            break;
                }
            }
            success = false;
        }         
    }
}