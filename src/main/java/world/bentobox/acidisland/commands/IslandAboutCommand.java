package world.bentobox.acidisland.commands;

import java.util.List;

import world.bentobox.bentobox.api.commands.CompositeCommand;
import world.bentobox.bentobox.api.user.User;

public class IslandAboutCommand extends CompositeCommand {

    /**
     * About
     * @param islandCommand - parent command
     */
    public IslandAboutCommand(CompositeCommand islandCommand) {
        super(islandCommand, "about", "ab");
    }

    @Override
    public void setup() {
        setDescription("commands.island.about.description");
    }

    @Override
    public boolean execute(User user, String label, List<String> args) {
        user.sendRawMessage("关于 " + getAddon().getDescription().getName() + " " + getAddon().getDescription().getVersion() + ":");
        user.sendRawMessage("版权所有 (c) 2017 - 2020 tastybento");
        user.sendRawMessage("由 Jeansou 提供简体中文版本");
        user.sendRawMessage("访问 https://www.eclipse.org/legal/epl-2.0/ 以查看详细版权信息.");
        return true;
    }

}
