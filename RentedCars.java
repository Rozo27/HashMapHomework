import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class RentedCars {

    private ArrayList<String> plates;
    public RentedCars(){
        this.plates=new ArrayList<>();
    }
    public ArrayList<String> getPlates(){
        return this.plates;
    }
    public int getSize(){
        return this.plates.size();
    }
}
