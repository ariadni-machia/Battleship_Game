public class OverlapTilesException extends Exception{ // εξαίρεση: δεν πρέπει να τοποθετηθεί πλοίο σε κελί που ήδη έχει άλλο πλοίο
    public OverlapTilesException(){
        super ();
    }   
    public static void OverlapTiles(Type type)throws OverlapTilesException //μέθοδος που ελέγχει εαν πρέπει να πετάξεις την εξαίρεση
    {
        if(type == Type.Ship){ // ελέγχει εάν υπάρχει ήδη πλοίο στο κελί αυτό
            throw new OverlapTilesException();
        }
    }
}