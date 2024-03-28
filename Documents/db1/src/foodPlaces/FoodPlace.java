package foodPlaces;

public class FoodPlace {
    int id = 0;
    String name;
    String address;

    public FoodPlace(int id, String name, String address){
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public FoodPlace(String name, String address){
        this.name = name;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void printInfo(){
        System.out.println("--Įstaigos informacija--");
        System.out.println("ID: " + id);
        System.out.println("Pavadinimas: " + name);
        System.out.println("Adresas: " + address);
    }

    @Override
    public String toString() {
        return "FoodPlaces{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
