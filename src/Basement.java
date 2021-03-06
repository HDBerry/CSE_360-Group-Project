
/**
 * @author Connor Nelson
 */

public class Basement extends Rooms {

    public Basement() {
        roomIndex = 1;
        name = "Basement";
        description = Saves.loadDescription(name);
        exits = "Garage - Living Room";

        Interpreter interpreter = new Interpreter((Command)(input) -> {
            boolean handled = false;
            String[] args = input.toString().split(" ");

            if (handled = (args[0].equalsIgnoreCase("take") || args[0].equalsIgnoreCase("get") || args[0].equalsIgnoreCase("grab")) ) {
                boolean validItem = false;
                int itemIndex = -1;
                String skill = "";
                String name = "";

                if (args.length >= 2) {
                    if (input.toString().contains("vault")) {
                        validItem = true;
                        itemIndex = 19;
                        skill = "strength";
                        name = "rusty vault";
                    } else if (input.toString().contains("katana")) {
                        validItem = true;
                        itemIndex = 20;
                        skill = "reflex";
                        name = "katana";
                    } else if (input.toString().contains("keyboard")) {
                        validItem = true;
                        itemIndex = 21;
                        skill = "intelligence";
                        name = "keyboard";
                    } else if (input.toString().contains("ring")) {
                        validItem = true;
                        itemIndex = 22;
                        skill = "perception";
                        name = "diamond ring";
                    } else if (input.toString().contains("pot")) {
                        validItem = true;
                        itemIndex = 23;
                        skill = "dexterity";
                        name = "golden pot";
                    } else {
                        validItem = false;
                        Game.getController().write("What do you want to take? Try looking around.");
                    }
                    if (validItem) {
                        if (!Game.getCurrentCharacter().inventory().contains(Items.itemList.get(itemIndex).getName())) {
                            if (Game.getCurrentCharacter().skillCheck(skill)) {
                                Game.getCurrentCharacter().addItem(Items.itemList.get(itemIndex));
                                Game.getController().write("You take the " + name + " successfully");
                            } else {
                                Game.getController().handleFailure();
                            }
                        } else {
                            Game.getController().write("You already have the " + name);
                        }
                    }
                } else {
                    Game.getController().write("What do you want to grab?");
                }

            } else if (handled = args[0].equalsIgnoreCase("look")) {
                if (input.toString().contains("armor")) {
                    Game.getController().write("You notice a fancy Japanese sword");
                } else if (input.toString().contains("doll")) {
                    Game.getController().write("You notice some nice jewelery");
                } else if (input.toString().contains("furnace")) {
                    Game.getController().write("You notice some expensive looking pots and pans");
                } else if (input.toString().contains("vault")) {
                    Game.getController().write("It's a vault...");
                } else if (input.toString().contains("computer")) {
                    Game.getController().write("Computer, mouse, keyboard, and monitor--what more could you need?");
                }
            }
            return handled;
        });

        setInterpreter(interpreter);
    }

    @Override
    public void entry(Character character) {
        character.setIndex(roomIndex);
        Game.getController().updateRoom(character);
    }


}
