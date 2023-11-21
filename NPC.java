public class NPC {
    public String name;
    public String description;
    public String dialouge;

    // Other properties...

    public NPC(String name, String description, String dialouge) {
        this.name = name;
        this.description = description;
        this.dialouge = dialouge;
        // Other properties...
    
    

    }

    public void talk() {
        System.out.println(dialouge);
    }
}