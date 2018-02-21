
import Prj01Exeptions.NoNameExist;
import Prj01Exeptions.ValueAboveMaxIneger;
import Prj01Exeptions.ValueLessThanZero;

import java.util.ArrayList;

public class Room {
    private String nameRoom;
    private int square;
    private int countWindows;
    private ArrayList<Object> allItems = new ArrayList<>();


    public Room(String nameRoom, int square, int countWindows) throws ValueAboveMaxIneger, ValueLessThanZero, NoNameExist {
        if (!nameRoom.trim().equals(""))
            this.nameRoom = nameRoom.trim();
        else
            throw new NoNameExist("Name of room can't be empty. Please enter name.");
        if (square > 0) {
            if (square < Integer.MAX_VALUE) {
                this.square = square;
            } else {
                throw new ValueAboveMaxIneger("Value of square is too large.\nPlease enter less value.");

            }
        } else {
            throw new ValueLessThanZero("Value of square is less than 0.\nValue you entered is " + square + "\nPlease enter value above 0.");
        }

        if (countWindows > 0) {
            if (countWindows < Integer.MAX_VALUE) {
                this.countWindows = countWindows;
            } else {
                throw new ValueAboveMaxIneger("Value of count window is too large.\nPlease enter less value.");

            }
        } else {
            throw new ValueLessThanZero("Value of count window is less than 0.\nValue you entered is " + countWindows + "\nPlease enter value above 0.");
        }

    }

    public String getNameRoom() {
        return nameRoom;
    }

    public ArrayList<Object> getAllItems() {
        return allItems;
    }

    public int getSquare() {
        return square;
    }

    public int getCountWindows() {
        return countWindows;
    }

    //Light bulb describe
    public StringBuilder LightBulbInfo() {

        StringBuilder info = new StringBuilder();
        for (Object i : allItems) {
            if (i instanceof LightBulb)
                info.append(((LightBulb) i).getLightPower() + " лк, ");
        }
        if (info.length() == 0) return new StringBuilder("нет");
        else info.setLength(info.length() - 2);
        return info;
    }

    //Calculate illuminance with light bulb
    public int calculateLightBulbIlluminance() {
        int count = 0;
        for (Object i : allItems) {
            if (i instanceof LightBulb)
                count += ((LightBulb) i).getLightPower();
        }
        return count;
    }

    //Calculate illuminance with windows
    public int calculateWindowsIlluminance() {
        return countWindows * 700;
    }

    //Calculate room illuminance with windows and light bulbs
    public int roomIlluminance() {
        return calculateLightBulbIlluminance() + calculateWindowsIlluminance();
    }

    public int calculateSquareItems() {
        int count = 0;
        for (Object i : allItems) {
            if (i instanceof RoomItems) {
                count += ((RoomItems) i).getSquare();
            }
        }
        return count;
    }

    //Calculate diffirence between square
    public int diffirence() {
        int difference = 0;
        for (Object i : allItems) {
            if (i instanceof RoomItems) {
                if (((RoomItems) i).getSquareTo() != 0)
                    difference += Math.abs(((RoomItems) i).getSquareTo() - ((RoomItems) i).getSquare());
            }
        }
        return difference;
    }

    //Calculate room items square
    public int allSquare() {
        return diffirence() + calculateSquareItems();
    }

    //Calculate room free space
    public int freeSpacePercent() {
        return (int) Math.round((double) freeSpaceNumbers() / (double) getSquare() * 100);
    }

    public int freeSpaceNumbers() {
        return getSquare() - allSquare();
    }

    //Room item describe
    public void describeRoomItems() {
        if (calculateSquareItems() == 0) System.out.println("  Мебели нет");
        else {
            System.out.println("  Мебель:");
            for (Object i : allItems) {
                if (i instanceof RoomItems) {

                    if (((RoomItems) i).getSquareTo() == 0) {
                        System.out.println("   " + ((RoomItems) i).getItemName() +
                                " (площадь " + ((RoomItems) i).getSquare() + " м^2)");
                    } else {
                        System.out.println("   " + ((RoomItems) i).getItemName() +
                                " (площадь от " + ((RoomItems) i).getSquare() + " м^2 до " + ((RoomItems) i).getSquareTo() + " м^2)");
                    }

                }
            }
        }
    }

}
