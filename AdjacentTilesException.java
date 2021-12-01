import java.util.ArrayList;
public class AdjacentTilesException extends Exception{ // εξαίρεση: τα πλοία πρέπει να έχουν μεταξύ τους απόσταση τουλάχιστον ένα κελί όχι τύπου "πλοίο"
    public AdjacentTilesException(){
        super ();
    }   
    public static void AdjacentTiles(ArrayList<Tile> neighborTiles)throws AdjacentTilesException{
        for(int k=0; k<neighborTiles.size(); k++) 
            {
                if( (neighborTiles.get(k)).getType() == Type.Ship ) // έλεγχος εξαίρεσης AdjacentTiles 
                {
                    throw new AdjacentTilesException(); 
                }
            }
    }
}