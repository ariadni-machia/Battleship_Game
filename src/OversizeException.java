public class OversizeException extends Exception{ //εξαίρεση: δεν πρέπει ένα πλοίο να βγει εκτός των ορίων του πίνακα
    public OversizeException(){
        super ();
    }  
    public static void Oversize(int x, int y)throws OversizeException //μέθοδος που ελέγχει εαν πρέπει να πετάξεις την εξαίρεση
    {
        if(x<0 || x>=Game.M || y<0 || y>=Game.M){ //ελέγχει εαν οι συντεταγμένες είναι εκτός ορίων
            throw new OversizeException();
        }
    }
}