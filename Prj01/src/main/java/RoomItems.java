

import Prj01Exeptions.NoNameExist;
import Prj01Exeptions.ValueAboveMaxIneger;
import Prj01Exeptions.ValueLessThanZero;

public class RoomItems {
    private String itemName;
    private int square;
    private int squareTo;


    public RoomItems(String itemName) throws NoNameExist {
        if (!itemName.trim().equals(""))
            this.itemName = itemName.trim();
        else
            throw new NoNameExist("Name of room item can't be empty. Please enter name.");
    }

    public RoomItems(String itemName, int square) throws ValueAboveMaxIneger, ValueLessThanZero, NoNameExist {

        if (!itemName.trim().equals(""))
            this.itemName = itemName.trim();
        else
            throw new NoNameExist("Name of room item can't be empty. Please enter name.");
        if (square > 0) {
            if (square < Integer.MAX_VALUE) {
                this.square = square;
            } else {
                throw new ValueAboveMaxIneger("Value of square is too large.\nPlease enter less value.");

            }
        } else {
            throw new ValueLessThanZero("Value of square is less than 0.\nValue you entered is " + square + "\nPlease enter value above 0.");
        }
    }

    public RoomItems(String itemName, int square, int squareTo) throws ValueAboveMaxIneger, ValueLessThanZero, NoNameExist {

        if (!itemName.trim().equals(""))
            this.itemName = itemName.trim();
        else
            throw new NoNameExist("Name of room item can't be empty. Please enter name.");
        if (square > 0) {
            if (square < Integer.MAX_VALUE) {
                this.square = square;
            } else {
                throw new ValueAboveMaxIneger("Value of square is too large.\nPlease enter less value.");

            }
        } else {
            throw new ValueLessThanZero("Value of square is less than 0.\nValue you entered is " + square + "\nPlease enter value above 0.");
        }
        if (squareTo > 0) {
            if (squareTo < Integer.MAX_VALUE) {
                this.squareTo = squareTo;
            } else {
                throw new ValueAboveMaxIneger("Value of square is too large.\nPlease enter less value.");
            }
        } else {
            throw new ValueLessThanZero("Value of square is less than 0.\nValue you entered is " + square + "\nPlease enter value above 0.");
        }
    }

    public String getItemName() {
        return itemName;
    }

    public int getSquare() {
        return square;
    }

    public int getSquareTo() {
        return squareTo;
    }
}
