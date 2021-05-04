/*----------------------
Author: Nick Cantalupa
email: nmcant21@g.holycross.edu
Written: 9/17/19

This program calculates the distance between two coordinates by converting them to signed radian format and using the haversine formula. It then prompts you with an option to view the locations on a map. 

java Flight 42.124 N 72.534 W 78.234 S 39.628 E

-----------------------------*/


public class Flight {

	public static void main(String[ ] args) {
        
        final double RADIUS=3963.1617;      //Earth's radius
        
        double homeLatO;                     //Home Latitude Coordinate Original
        double homeLongO;                    //Home Longitude Coordinate Original
        char homeNS;                         //Home North or South
        char homeEW;                         //Home East or West
        
        double destLatO;                     //Destination Latitude Coordinate Original
        double destLongO;                    //Destination Longitude Coordinate Original
        char destNS;                         //Destination North or South
        char destEW;                         //Destination East or West
 
        double homeLat;                      //Home Latitude Coordinate Radians
        double homeLong;                     //Home Longitude Coordinate Radians
        double destLat;                      //Destination Latitude Coordinate Radians
        double destLong;                     //Destination Longitude Coordinate Radians


        

        homeLatO=Double.parseDouble(args[0]);
        homeNS=args[1].charAt(0);
        homeLongO=Double.parseDouble(args[2]);
        homeEW=args[3].charAt(0);
  
        destLatO=Double.parseDouble(args[4]);
        destNS=args[5].charAt(0);
        destLongO=Double.parseDouble(args[6]);
        destEW=args[7].charAt(0);           //Assigns the arguements to their correct variables
        



        
        System.out.println("Home Latitude is " + homeLatO + " " + homeNS);
        System.out.println("Home Longitude is " + homeLongO + " " + homeEW);
        System.out.println(" ");
        System.out.println("Destination Latitude is " + destLatO + " " + destNS);
        System.out.println("Destination Longitude is " + destLongO + " " + destEW);
        System.out.println(" ");
                                            //Prints out the original entered coordinates




        
        

        if ((homeLatO > 90.0 || homeLatO < 0.0) || (homeLongO > 180.0 || homeLongO < 0.0)){
            System.out.println("Sorry, the home coordinates you entered are not valid.");
        }
        if ((destLatO > 90.0 || destLatO < 0.0) || (destLongO > 180.0 || destLongO < 0.0)){
            System.out.println("Sorry, the destination coordinates you entered are not valid.");
        }
        if (homeNS != 'N' && homeNS != 'S'){
            System.out.println("Sorry, the home N/S hemisphere you entered are not valid.");
        }
        if (destNS != 'N' && destNS != 'S'){
            System.out.println("Sorry, the destination N/S hemisphere you entered are not valid.");
        }
        if (homeEW != 'E' && homeEW != 'W'){
            System.out.println("Sorry, the home E/W hemisphere you entered are not valid.");
        }
        if (destEW != 'E' && destEW != 'W'){
            System.out.println("Sorry, the destination E/W hemisphere you entered are not valid.");
        }
        if (
            (homeLatO > 90.0 || homeLatO < 0.0) || (homeLongO > 180.0 || homeLongO < 0.0) ||
            (destLatO > 90.0 || destLatO < 0.0) || (destLongO > 180.0 || destLongO < 0.0) ||
            (homeNS != 'N' && homeNS != 'S') ||
            (destNS != 'N' && destNS != 'S') ||
            (homeEW != 'E' && homeEW != 'W') ||
            (destEW != 'E' && destEW != 'W')){
            
            System.out.println("Because of invalid coordinates, the program will terminate.");
            System.exit(0);
        }                                   //Gives appropriate error messages for invalid entries
        


        
        

        if (homeNS=='S'){
            homeLatO=homeLatO*(-1.0);
        }
        if (homeEW=='W'){
            homeLongO=homeLongO*(-1.0);
        }
        
        if (destNS=='S'){
            destLatO=destLatO*(-1.0);
        }
        if (destEW=='W'){
            destLongO=destLongO*(-1.0);
        }                                   //Converts coordinates to signed degrees format



        
        

        
        homeLat=homeLatO*((Math.PI)/180.0);
        homeLong=homeLongO*((Math.PI)/180.0);
        destLat=destLatO*((Math.PI)/180.0);
        destLong=destLongO*((Math.PI)/180.0);
                                            //Converts coordinates to signed radians format




        double latDiff=(destLat-homeLat);
        double longDiff=(destLong-homeLong);
        double sinPart1;
        double sinPart2;
        double cosPart;
        double vVal;
        double dVal;                        //All variables used for the haversine formula below
        


        
        sinPart1=((Math.sin(latDiff))/2.0);
        sinPart2=((Math.sin(longDiff))/2.0);
        cosPart=(Math.cos(homeLat))*(Math.cos(destLat));
        vVal=((Math.pow(sinPart1,2))+(Math.pow(sinPart2,2)))*cosPart;
        dVal=2*RADIUS*(Math.atan2(Math.sqrt(vVal),Math.sqrt(1-vVal)));
                                          //Calculates the distance using haversine formula



        
        System.out.println("The distance from home to destination is " + dVal + " miles.\n");
                                          //Prints out the calculated distance



        

        System.out.println("Would you like to see a map of your trip?");
        
        String response= StdIn.readString();
        String url= ( "https://www.mapquestapi.com/staticmap/v4/getmap?"
                      + "key=t8xhSEnIxadDAwSL0fSuJer5JAWqGPZ5&size=400,400&type=map&"
                      + "bestfit=" + homeLatO + "," + homeLongO + "," + destLatO + "," + destLongO + "&"
                      + "pois=A," + homeLatO + "," + homeLongO + "%7CB," + destLatO + "," + destLongO + "&" 
                      + "polyline=color:0xff0000%7Cwidth:3%7C" + homeLatO + "," + homeLongO + "," + destLatO + "," + destLongO);

        if (response.equalsIgnoreCase("yes")){
                System.out.println("You said yes! \n");
                System.out.println("Right-click this link or copy it to your browser: ");
                System.out.println(url);
                StdDraw.picture(0.5, 0.5, url);  
            }else {
                System.out.println("Ok, goodbye!");
                System.exit(0);
        }                                                 //Asks if they want to see the map. If so, it opens the picture and gives link. If not it closes the application. 
            
        
        
        
        
        

         


    }//end of main

} //end of flight

