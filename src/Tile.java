public class Tile{    
    private int x; //Συντεταγμένες
    private int y;    
    private Type type; //Τύπος κελιού
    //Καταστευαστής με 3 ορίσματα ( συντεταγμένες x και y και τον τύπο του κελιού)
    public Tile(int x, int y, Type type){
        this.x = x;
        this.y = y;
        this.type = type;
    }
    public Tile(){ }
    //Μέθοδος εκτύπωσης του συμβόλου του Τύπου(Type)
    public String draw(boolean hidden){   //hidden: true (πίνακας αντιπάλου) | false (πίνακας παίχτη)           
       switch(type){
            case Sea:
                return "~";
            case Ship:
                if(hidden==false)
                    return "s";
                else
                    return "~"; //ο αντίπαλος δεν βλέπει το πλοίο αλλα θάλασσα               ;
            case Hit:
                return "X";               
            case Miss:
                return "o";                
            default: // δεν θα πρέπει να φτάσει εδώ ποτέ, τότε υπάρχει πρόβλημα
                System.out.println("Error! Type: "+ type +" is not acceptable.");
                return "Error"; 
        }
    }
    //Getter μεθοδοι
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public Type getType(){
        return type;
    }
    //Setter μεθοδοι
    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }
    public void setType(Type type){
        this.type = type;
    }
}

//Τύπος
enum Type{
    Sea, Ship, Hit, Miss;
}