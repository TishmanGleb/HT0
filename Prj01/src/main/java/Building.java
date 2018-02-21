import java.util.ArrayList;
import Prj01Exeptions.NoNameExist;
import Prj01Exeptions.ValueAboveMaxIneger;
import Prj01Exeptions.ValueLessThanZero;
import Prj01Exeptions.IlluminanceTooMuchException;
import Prj01Exeptions.SpaceUsageTooMuchException;
import Prj01Exeptions.IlluminanceTooLowException;


public class Building {
    private ArrayList<Room> rooms = new ArrayList<>();
    private String buildingName;

    public Building(String buildingName) throws NoNameExist {
        if (!buildingName.trim().equals(""))
            this.buildingName = buildingName.trim();
        else throw new NoNameExist("Name of building can't be empty. Please enter name.");
    }

    protected void addRoom(String roomName, int square, int counWindow) throws ValueLessThanZero, ValueAboveMaxIneger, NoNameExist {
        if (!roomAlreadyExist(roomName))
            rooms.add(new Room(roomName, square, counWindow));
        else System.out.println("Room - " + roomName + " is already exist.");
    }

    public ArrayList<Object> getRoom(String roomName) throws Exception {
        for (Room i : rooms) {
            if (i.getNameRoom().equals(roomName) && validateRoom()) return i.getAllItems();
        }
        throw new Exception("Room with this name not found.");
    }

    //Check is room in building
    protected boolean roomAlreadyExist(String roomName) {
        for (Room i : rooms) {
            if (i.getNameRoom().equals(roomName)) return true;
        }
        return false;
    }

    protected boolean validateRoom() throws IlluminanceTooMuchException, SpaceUsageTooMuchException, IlluminanceTooLowException {
        for (Room i : rooms) {
            int illuminance = i.roomIlluminance();
            int freeSpace = i.freeSpacePercent();
            if (illuminance > 4000) {
                throw new IlluminanceTooMuchException("Illuminance is too much for room - " + i.getNameRoom() + "" +
                        "\nIlluminance must be above 300 and less then 4000.\nCurrent illuminance is " + illuminance + ".");
            }

            if (illuminance < 300) {
                throw new IlluminanceTooLowException("Illuminance is too low for room - " + i.getNameRoom() + "" +
                        "\nIlluminance must be above 300 and less then 4000.\nCurrent illuminance is " + illuminance + ".");
            }
            if (freeSpace < 30)
                throw new SpaceUsageTooMuchException("A lot of space is occupied by room items in room - " + i.getNameRoom() +
                        "\nSpace usage must be then 70%.\nCurrent Space usage is " + (100 - freeSpace) + "%.");
        }
        return true;
    }

    protected void describe() {
        System.out.println(buildingName);
        for (Room i : rooms) {
            System.out.println(" " + i.getNameRoom());
            System.out.println("  Освещённость = " + (i.roomIlluminance()) +
                    " (" + i.getCountWindows() + " окна по 700 лк, лампочки: " + i.LightBulbInfo().toString().trim() + ")");
            System.out.print("  Площадь = " + i.getSquare());
            if (i.calculateSquareItems() == 0) System.out.println(" м^2 (свободно 100%)");
            else {
                if (i.diffirence() != 0) {
                    System.out.print(" м^2 (занято " + i.calculateSquareItems() + "-" + i.allSquare());
                } else {
                    System.out.print(" м^2 (занято " + i.calculateSquareItems());
                }
                System.out.print(" м^2, гарантированно свободно " + i.freeSpaceNumbers() + " м^2 или "
                        + i.freeSpacePercent() + "% площади)\n");
            }
            i.describeRoomItems();
        }
    }


/*    public static void main(String[] args) throws Exception {
        Building building = new Building("First");
        building.addRoom("Комната 1", 50, 3);
        building.addRoom("Комната 2", 100, 4);
        building.getRoom("Комната 1").add(new LightBulb(200));
        building.getRoom("Комната 1").add(new LightBulb(250));
        building.getRoom("Комната 1").add(new LightBulb(50));
        building.getRoom("Комната 2").add(new LightBulb(250));
        building.getRoom("Комната 2").add(new LightBulb(50));
        building.getRoom("Комната 1").add(new RoomItems("Кресло мягкое и пушистое", 2));
        building.getRoom("Комната 1").add(new RoomItems("Кресло мягкое и пушистое", 1));
        building.getRoom("Комната 1").add(new RoomItems("Кресло мягкое и пушистое", 1, 3));
        building.describe();

    }*/


}
