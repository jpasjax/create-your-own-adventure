public class NPC {
    String name;
    String description;
    String dialogue;

    public NPC(String name, String description, String dialogue) {
        this.name = name;
        this.description = description;
        this.dialogue = dialogue;
    }

    public void talk() {
        System.out.println(dialogue);
    }
}
