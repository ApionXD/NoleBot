package commands.admin;

import commands.util.Command;
import commands.util.CommandEvent;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.MessageChannel;
import util.BotEmbed;

public class ServerInfoCommand extends Command {
    public ServerInfoCommand() {
        name = "serverinfo";
        description = "Gets relevant server information.";
        helpDescription = "Sends information about your server. Information such as the owner, the total members, total role count, etc.";
        requiredPermission = 0;
        usages.add("serverinfo");
    }

    @Override
    public void onCommandReceived(CommandEvent event) {
        MessageChannel messageChannel = event.getChannel();

        Guild sentGuild = event.getGuild();

        EmbedBuilder embedBuilder = BotEmbed.getBotEmbed(event);

        embedBuilder.setThumbnail(event.getGuild().getIconUrl());
        embedBuilder.addField("Guild Name: ", sentGuild.getName(), true);
        embedBuilder.addField("Owner: ", sentGuild.getOwner().getEffectiveName(), true);
        embedBuilder.addField("Total Members: ", Integer.toString(sentGuild.getMembers().size()), true);
        embedBuilder.addField("Total Role count: ", Integer.toString(sentGuild.getRoles().size() - 1), true);
        embedBuilder.addField("Region: ", sentGuild.getRegionRaw(), true);
        embedBuilder.addField("Default Channel: ", sentGuild.getDefaultChannel().getAsMention(), true);
        messageChannel.sendMessage(embedBuilder.build()).queue();
    }
}