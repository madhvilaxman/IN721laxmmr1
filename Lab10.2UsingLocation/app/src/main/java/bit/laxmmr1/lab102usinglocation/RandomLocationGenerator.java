package bit.laxmmr1.lab102usinglocation;

import java.util.Random;

/**
 * Created by laxmmr1 on 10/05/2016.
 */
public class RandomLocationGenerator {
    static Random rand;

    //Constructor
    public RandomLocationGenerator()
    {
    }

    //Method for Random Locations
    public static double generateLocation(int flag)
    {
        rand = new Random();
        boolean booleanPosNeg = rand.nextBoolean();

        //switch statement  to determine latitude or longitude
        double chosenValue = 0.00;
        switch (flag)
        {
            //Longitude
            case 1: chosenValue = rand.nextDouble() *180;
                break;
            //Latitude
            case 2: chosenValue = rand.nextDouble() *90;
                break;
        }//End Switch
        if ( booleanPosNeg == false )
        {
            chosenValue = -chosenValue;
        }
        return chosenValue;
        }//End Method
}//End Class
