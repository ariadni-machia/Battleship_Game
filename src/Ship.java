import java.util.*;
abstract class Ship
{
    private Tile Set_tile; //Κελί Έναρξης
    private Orientation orientation; //Προσανατολισμός
    protected int Ship_size; //Μέγεθος του πλοίου    
    /*
    public int getShip_size()
    {
        return Ship_size;
    } 
    */
    public boolean placeShip(Tile Set_tile,Orientation orientation,Board board, boolean verbose) //μέθοδος τοποθέτησης πλοίο βάση δεδομένων που δίνει ο χρήστης
        {
            this.Set_tile = Set_tile;
            this.orientation = orientation;  
            Tile[][] pin = board.getPinakas();
            // έλεγχος όλων των υποψήφιων κελιών για την τοποθέτηση πλοίου 
            try{      
                if(orientation == Orientation.vertical) // αν προσανατολισμός == κατακόρυφα
                {
                    for(int i=Set_tile.getX(); i<(Set_tile.getX()+ Ship_size); i++)
                    { 
                        OversizeException.Oversize( i, Set_tile.getY() ); // έλεγχος εξαίρεσης Oversize
                        OverlapTilesException.OverlapTiles( (pin[i][Set_tile.getY()]).getType() ); // έλεγχος εξαίρεσης OverlapTiles
                        AdjacentTilesException.AdjacentTiles( board.getAdjacentTiles( pin[i][Set_tile.getY()]) ); // έλεγχος εξαίρεσης AdjacentTiles
                    }
                }
                if(orientation == Orientation.horizontal){ // αν προσανατολισμός == οριζόντια
                    for(int j=Set_tile.getY(); j<(Set_tile.getY()+ Ship_size); j++)
                    { 
                        OversizeException.Oversize( Set_tile.getX(), j ); // έλεγχος εξαίρεσης Oversize
                        OverlapTilesException.OverlapTiles( (pin[Set_tile.getX()][j]).getType() ); // έλεγχος εξαίρεσης OverlapTiles
                        AdjacentTilesException.AdjacentTiles( board.getAdjacentTiles( pin[Set_tile.getX()][j]) ); // έλεγχος εξαίρεσης AdjacentTiles
                    }
                }
            }
            catch(OversizeException e)
            {
                if(verbose == false) 
                    System.out.println("Your input is invalid.");  
                return false;
            }
            catch(OverlapTilesException e)
            {
                if(verbose == false)            
                    System.out.println("There's already a ship on this tile.");  
                return false;
            }
            catch(AdjacentTilesException e)
            {
                if(verbose == false)
                    System.out.println("You cannot place your ship here."); 
                return false;
            }  
            /*catch(ArrayIndexOutOfBoundsException e)
            {
                if(verbose ==false)
                    System.out.println("ArrayIndexOutOfBoundsException."); 
                return false;  
            } */
            
            // τοποθέτηση του πλοίου (μετά των έλεγχο των 3 εξαιρέσεων)
            if(orientation == Orientation.vertical) // αν προσανατολισμός == κατακόρυφα
                for( int i=Set_tile.getX(); i<(Set_tile.getX()+ Ship_size); i++ )
                    ( pin[i][Set_tile.getY()] ).setType( Type.Ship );
            if(orientation == Orientation.horizontal) // αν προσανατολισμός == οριζόντια
                for( int j=Set_tile.getY(); j<(Set_tile.getY()+ Ship_size); j++ )
                    ( pin[Set_tile.getX()][j] ).setType( Type.Ship );
            if(verbose ==false)
                    System.out.println("You have succesfully placed a ship!");
            return true;
        }
}
//Προσανατολισμός
enum Orientation{
    vertical, horizontal;
}