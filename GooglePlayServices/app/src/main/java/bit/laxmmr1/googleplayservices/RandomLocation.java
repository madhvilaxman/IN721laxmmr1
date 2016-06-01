package bit.laxmmr1.googleplayservices;

import java.util.Random;

/**
 * Created by laxmmr1 on 24/05/2016.
 */
public class RandomLocation
{
    private static final double longLimit = 180.00;
    private static final double latLimit = 90.00;

    //GENERATING RANDOM LOCATIONS
    public static double generateRandomLocation (int longitudeORlatitude)
    {
        Random rand = new Random();
        double location = 0.0;
        boolean flag = rand.nextBoolean();

        //switch statement
        switch (longitudeORlatitude)
        {
            case 0:
                location = rand.nextDouble() *longLimit;
                break;
            case 1:
                location = rand.nextDouble() * latLimit;
                break;
        }//End switch statement

        location = flag ? location : -location;
        return location;
    }//End method
}//End Class Random Location

