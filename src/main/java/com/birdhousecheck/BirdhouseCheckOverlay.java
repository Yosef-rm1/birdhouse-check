package com.birdhousecheck;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Objects;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.GameObject;
import net.runelite.api.WorldView;
import net.runelite.api.coords.LocalPoint;
import net.runelite.api.coords.WorldPoint;
import net.runelite.client.game.ItemManager;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.OverlayUtil;
import net.runelite.client.util.ImageUtil;

import static net.runelite.api.VarPlayer.BIRD_HOUSE_MEADOW_NORTH;
import static net.runelite.api.VarPlayer.BIRD_HOUSE_MEADOW_SOUTH;
import static net.runelite.api.VarPlayer.BIRD_HOUSE_VALLEY_NORTH;
import static net.runelite.api.VarPlayer.BIRD_HOUSE_VALLEY_SOUTH;

@Slf4j
public class BirdhouseCheckOverlay extends Overlay
{
    private final Client client;
    private final BirdhouseCheckConfig config;
    private final BirdhouseCheckPlugin plugin;
    private final ItemManager itemManager;

    // Cached seed image
    private BufferedImage seedImage;
    private int cachedSeedId;
    private int cachedSize;

    @Inject
    BirdhouseCheckOverlay(Client client, BirdhouseCheckConfig config, BirdhouseCheckPlugin plugin, ItemManager itemManager)
    {
        this.client = client;
        this.config = config;
        this.plugin = plugin;
        this.itemManager = itemManager;
        setPosition(OverlayPosition.DYNAMIC);
        setLayer(OverlayLayer.ABOVE_SCENE);
    }

    @Override
    public Dimension render(Graphics2D graphics)
    {
        if (!config.showSeededIcon())
        {
            return null;
        }

        WorldView wv = client.getTopLevelWorldView();
        if (wv == null)
        {
            return null;
        }

        // Update cached image if needed
        updateSeedImage();
        if (seedImage == null)
        {
            return null;
        }

        // Check each birdhouse location
        GameObject meadowNorth = plugin.getMeadowNorth();
        if (!Objects.isNull(meadowNorth))
        {
            int state = client.getVarpValue(BIRD_HOUSE_MEADOW_NORTH);
            if (isSeeded(state))
            {
                renderAtObject(graphics, wv, meadowNorth);
            }
        }

        GameObject meadowSouth = plugin.getMeadowSouth();
        if (!Objects.isNull(meadowSouth))
        {
            int state = client.getVarpValue(BIRD_HOUSE_MEADOW_SOUTH);
            if (isSeeded(state))
            {
                renderAtObject(graphics, wv, meadowSouth);
            }
        }

        GameObject valleyNorth = plugin.getValleyNorth();
        if (!Objects.isNull(valleyNorth))
        {
            int state = client.getVarpValue(BIRD_HOUSE_VALLEY_NORTH);
            if (isSeeded(state))
            {
                renderAtObject(graphics, wv, valleyNorth);
            }
        }

        GameObject valleySouth = plugin.getValleySouth();
        if (!Objects.isNull(valleySouth))
        {
            int state = client.getVarpValue(BIRD_HOUSE_VALLEY_SOUTH);
            if (isSeeded(state))
            {
                renderAtObject(graphics, wv, valleySouth);
            }
        }

        return null;
    }

    /**
     * Check if birdhouse state indicates it has been seeded.
     * State logic from BirdhouseOverlay:
     * - state == 0: no birdhouse built (unbuilt)
     * - state % 3 == 0 (and state != 0): seeded, waiting for birds
     * - else: built but needs seeds (unseeded)
     */
    private boolean isSeeded(int state)
    {
        return state > 0 && state % 3 == 0;
    }

    private void updateSeedImage()
    {
        int seedItemId = config.seedType().getItemId();
        int size = config.iconSize().getSize();

        if (seedImage == null || cachedSeedId != seedItemId || cachedSize != size)
        {
            BufferedImage image = itemManager.getImage(seedItemId, 5, false);
            if (image != null)
            {
                // Resize image
                seedImage = ImageUtil.resizeImage(image, size, size);
                cachedSeedId = seedItemId;
                cachedSize = size;
            }
        }
    }

    private void renderAtObject(Graphics2D graphics, WorldView wv, GameObject gameObject)
    {
        WorldPoint worldPoint = gameObject.getWorldLocation();
        LocalPoint lp = LocalPoint.fromWorld(wv, worldPoint);
        if (lp != null)
        {
            OverlayUtil.renderImageLocation(client, graphics, lp, seedImage, 150);
        }
    }
}