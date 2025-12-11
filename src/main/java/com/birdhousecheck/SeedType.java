package com.birdhousecheck;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SeedType
{
    HAMMERSTONE("Hammerstone", 5307),
    BARLEY("Barley", 5305),
    ASGARNIAN("Asgarnian", 5308),
    JUTE("Jute", 5306),
    YANILLIAN("Yanillian", 5309),
    KRANDORIAN("Krandorian", 5310),
    WILDBLOOD("Wildblood", 5311),
    POTATO("Potato", 5318),
    ONION("Onion", 5319),
    CABBAGE("Cabbage", 5324),
    TOMATO("Tomato", 5322),
    SWEETCORN("Sweetcorn", 5320),
    STRAWBERRY("Strawberry", 5323),
    WATERMELON("Watermelon", 5321),
    GUAM("Guam", 5291),
    MARRENTILL("Marrentill", 5292),
    TARROMIN("Tarromin", 5293),
    HARRALANDER("Harralander", 5294),
    RANARR("Ranarr", 5295),
    TOADFLAX("Toadflax", 5296),
    IRIT("Irit", 5297),
    AVANTOE("Avantoe", 5298),
    KWUARM("Kwuarm", 5299),
    SNAPDRAGON("Snapdragon", 5300),
    CADANTINE("Cadantine", 5301),
    LANTADYME("Lantadyme", 5302),
    DWARF_WEED("Dwarf Weed", 5303),
    TORSTOL("Torstol", 5304);

    private final String name;
    private final int itemId;

    @Override
    public String toString()
    {
        return name;
    }
}