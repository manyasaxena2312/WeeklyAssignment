import java.util.*;

class ParkingSpot {

    String plate;
    long entryTime;

    ParkingSpot(String plate){
        this.plate = plate;
        entryTime = System.currentTimeMillis();
    }
}

public class ParkingLot {

    static int SIZE = 500;

    static ParkingSpot[] table = new ParkingSpot[SIZE];

    static int hash(String plate){
        return Math.abs(plate.hashCode()) % SIZE;
    }

    static int parkVehicle(String plate){

        int index = hash(plate);
        int probes = 0;

        while(table[index] != null){

            index = (index + 1) % SIZE;
            probes++;
        }

        table[index] = new ParkingSpot(plate);

        System.out.println(
                "Parked at spot " + index +
                        " probes: " + probes
        );

        return index;
    }

    static void exitVehicle(String plate){

        for(int i=0;i<SIZE;i++){

            if(table[i]!=null &&
                    table[i].plate.equals(plate)){

                long duration =
                        (System.currentTimeMillis()
                                - table[i].entryTime)/1000;

                table[i] = null;

                System.out.println(
                        "Exited spot "+i+
                                " duration "+duration+" sec"
                );
                return;
            }
        }
    }

    public static void main(String[] args) {

        parkVehicle("ABC123");
        parkVehicle("XYZ999");

        exitVehicle("ABC123");
    }
}
