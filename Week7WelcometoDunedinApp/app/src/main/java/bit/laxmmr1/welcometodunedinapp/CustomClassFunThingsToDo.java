package bit.laxmmr1.welcometodunedinapp;

import android.graphics.drawable.Drawable;

/**
 * Created by laxmmr1 on 1/04/2016.
 */

//We need a separate Java class
public class CustomClassFunThingsToDo
{
    Drawable imagesForActivities;
    String namesForActivities;

    public CustomClassFunThingsToDo(String namesForActivities, Drawable imagesForActivities)
    {
        this.namesForActivities = namesForActivities;
        this.imagesForActivities = imagesForActivities;
    }//End here

    @Override
    public  String toString()
    {
        return namesForActivities;
    }
     //
}//End of Class
