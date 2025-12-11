package com.birdhousecheck;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("birdhousecheck")
public interface BirdhouseCheckConfig extends Config
{
	@ConfigItem(
			keyName = "iconSize",
			name = "Icon size",
			description = "The size of the icon displayed on birdhouses",
			position = 1
	)
	default IconSize iconSize()
	{
		return IconSize.MEDIUM;
	}

	@ConfigItem(
			keyName = "showSeededIcon",
			name = "Show Seeded Icon",
			description = "Display a seed icon on birdhouses that have been seeded",
			position = 2
	)
	default boolean showSeededIcon()
	{
		return true;
	}

	@ConfigItem(
			keyName = "seedType",
			name = "Seed icon type",
			description = "Which seed icon to display for seeded birdhouses",
			position = 3
	)
	default SeedType seedType()
	{
		return SeedType.HAMMERSTONE;
	}
}