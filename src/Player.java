import java.util.Scanner;
public class Player
{ 
    private String name = "Computer"; //όνομα παίκτη
    private int hits=0; // σύνολο βολών
    private int missed=0; // αστοχίες
    private int succ=0; //επιτυχημένες βολές
    private int rep=0; //επαναλήψεις  
    private Board board = new Board(); // αντικείμενο Board του παίκτη
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public Board getBoard(){
        return board;
    }
    public void placeAllShips(){ //τοποθετεί τυχαία τα πλοία
        board.placeAllShips(); // καλεί την placeAllShips() της Board
    }
    public boolean placeShip(Ship sh){ //τοποθετεί τα πλοία σύμφωνα με δεδομένα που δίνει ο χρήστης
        int[] coo = new int[2];
        coo = Game.getInput(); // δίνει ο χρήστης τις συντεταγμένες
        Tile Set_Tile = new Tile(coo[0], coo[1], Type.Sea);
        boolean ship = sh.placeShip(Set_Tile,Game.getOrientation(),board, false);// τοποθετείται στο πίνακα του παίκτη πλοίο στο κελί που δόθηκε αφού δωθεί και ο προσανατολισμός
        return ship;
    }
    public boolean fire( Board brd, int pin[] ) //πραγματοποιεί βολή στον πίνακα του αντιπάλου
    {
        int x,y; // συντεταγμένες        
        x=pin[0];
        y=pin[1];
        
        Tile[][] pinakas = brd.getPinakas();
        try{
            OversizeException.Oversize(x,y); // έλεγχος εξαίρεσης Oversize
        }
        catch(OversizeException e){
            System.out.println("The coordinates you've given are incompatible.");
            return false;
        }
   
        if ( (pinakas[x][y]).getType() == Type.Sea ){ // ελέγχει εάν αστόχησε
            (pinakas[x][y]).setType(Type.Miss);
            hits = hits +1;
            missed = missed +1;
            System.out.println("You missed, " + name);
        }
        else if (pinakas[x][y].getType() == Type.Ship){ // ελέγει εάν πέτυχε κομμάτι από το πλοίοι
            (pinakas[x][y]).setType(Type.Hit);
            hits = hits + 1;
            succ = succ + 1;
            System.out.println("You hit part of a ship. Well done, " + name);
        }            
        else if ( (pinakas[x][y]).getType() == Type.Miss ){ // ελέγχει εάν αστόχησε ξανά, σε κελί που έχει ξαναεπιθετεί
            missed = missed + 1;
            rep = rep + 1;
            hits = hits + 1;
            System.out.println("You hit a sea tile you have already hit " + name);
        }
        else{ // ελέγχει εάν πέτυχε ξανά, κομμάτι πλοίου που έχει ξαναχτυπήσει
            rep = rep +1;
            hits = hits +1;
            System.out.println("You have hit a ship tile you have alread hit " + name);
        }   
        return true;
    }    
    public void getStats(){ //εκτυπώνει τα στατιστικά του παίκτη        
        System.out.println("Hits: " + hits);
        System.out.println("Misses: " + missed);
        System.out.println("Succesful hits: " + succ);
        System.out.println("Repetitions: " + rep);        
    }
}
