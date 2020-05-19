
# AGCraft Changelog

## Versions 1.0 to 1.3 - March 23, 2020
- Implemented the core components used to aggregate Bible content from multiple RESTful APIs.
- Added support for fetching content from the ESV version of the Bible.
- Hooked the BibleFetcher code into a Spigot command, `/bible	`, which fetches requested books of the Bible from those online APIs and converts it into a written book for the player to read in Minecraft.

## Version 1.4 - March 28, 2020
**New Commands:**  
`/getCoordinates <player>`  

-   Returns the coordinates of the specified player in <X,Y,Z> format.
-   If no player is specified, it will print out your coordinates instead.

`/clearBibles`  

-   Removes all server-generated bibles from your inventory.

**Under the hood changes:**  

-   Modified the appearance of server-generated messages when using the  `/bible`  command.
-   Changed name of the project from BibleFetcher to AGCraft plugins, since this project is intended to be a custom suite of plugins made for the AGCraft server.

**Things planned in the next update:**  

-   The ability to track and see player deaths in this server.
-   The ability to tag, save, and lookup custom locations throughout this world.

## Version 1.5 - April 2, 2020
**New commands:**  
Clear Junk  

> `/clearJunk [list of classifiers]`  
> Completely removes items that are deemed useless (especially while mining) from your inventory.In this command, you can specify which class of junk items that can be removed from your inventory. As of right now, there are five classifications of junk items, which are:  
> `COMMON, NATURAL, STONE, ARMOR, BREWING`However, if you are lazy, you could also use  
> `/clearJunk all`  
> ... to remove  **all**  items listed below.The items that fall between each classifier are as follows:  
> `COMMON`  
> - Rotten Flesh  
> - Bone  
> - String  
> - Seeds (wheat)`NATURAL`  
> - Dirt  
> - Sand  
> - Gravel`STONE`  
> - Cobblestone  
> - Stone  
> - Andesite  
> - Granite  
> - Diorite`ARMOR`  
> - All golden, chainmail, and leather variants`BREWING`  
> - Spider eyes
> 
>  Examples:  
> `/clearJunk stone` removes all stonetypes listed above from your inventory.  
> `/clearJunk stone common`  removes all stonetypes and common mob drops from your inventory.  
> `/clearJunk`  with no parameters automatically defaults to removing common mobdrops

AutoSmelt  

> `/autosmelt`  
> Automatically smelts all iron and gold ores in your inventory._However, in order to use this command, you need to also have the necessary coal blocks, coal, and/or charcoal to smelt all of these ores._  
> In addition, using this command will not grant you any experience benefits that normally comes from smelting said ores manually.This command also greedily prioritizes coal blocks over coal / charcoal and will not give any change back.  
> In other words, if you have coal blocks in your inventory and you use  `/autosmelt`  to smelt a single iron ore,  
> it will consume that coal block to do so, and you will not get your 8 coal pieces back.A small price to pay for automation.

Bugfixes:  

-   Resolved spacing issues on certain sections of the Bible while using the  `/bible`  command.
-   It is no longer possible to use /getcoordinates on a player who is in a different dimension than you.

What is planned in the future:  

-   A death counter
-   A warp command that teleports people to either their bed spawn, other players, or player-specified locations of the world.
-   Ability to set locations for the warp command.

## Version 1.6 - April 7, 2020
**New Commands:**  
**Get Locations**  

> `/getlocations <visibility level>`

-   Outputs all registered warp locations (under the specified  `<visibility level>`) to your chat.
-   Visibility level can either be  `private`  or  `public`. `private`  warp locations can only be visible to you, while  `public`  warp locations can be accessed by anybody in this server.

**Set Location**  

> `/setlocation <location name> <visibility level>`

-   Registers your current position as a new warp location under the name,  `<location name>`. Doing this will allow  `/warp location <location name>`  to teleport you back to  `<location name>`. Fails if a location with the same name is already registered in the server.

**Remove Location**  

> `/removelocation <location name> <visibility level>`

-   Removes a named warp location under the specified  `<visibility level>`  from the database. Doing this will no longer allow you to warp to that removed location.

**Warp**  

> `/warp <tag> <location name>`

-   If the  `tag`  parameter is set to  `location`, using this command will warp you to a named location that is registered by  `/setlocation`.
-   If the  `tag`  parameter is set to  `home`, using this command will warp you back to your home bed.

**Changes**  

-   Added a new classifier,  `WEAPONS`  to the  `/clearjunk`  command. Appending this classifier to your list of junk items will now remove all unenchanted damaged bows and golden swords from your inventory.
-   The  `/clearjunk`  command will now target unenchanted and damaged weapons and armor now. This is to prevent your own gear from being accidentally removed from running this command.
-   Reformatted chat colors on certain command messages.

**Bug Fixes**  

-   Fixed an issue where you are not able to read the book of Philemon under any Bible version other than ESV.
-   Fixed an issue where using the  `/bible`  command incorrectly issues an  `An internal error occured while fetching this passage`  error message, even though nothing is wrong with the server or the plugin itself.

## Version 1.6.2 - April 9, 2020
**Changes**
  - Implemented tab-autocompletion for all commands that has custom parameters. Use tab to cycle through parameters to make your life easier while using our commands.
  - Removed the secondary `access` parameter for `/getcoordinates` and `/removecoordinates`.
 
## Version 1.6.3 - April 23, 2020
**Changes**
- Simplified the `/warp` command by removing the `tag` parameter. You can now use `/warp <name of location>` to warp to your named locations. In addition, `/warp home` still teleports you to your bed spawn, like always.
- -   Added green dye to the  `COMMON`  category of the  `/clearjunk`  command. Use  `/clearjunk`  to remove all rotten flesh, bones, string, green dye, and seeds from your inventory, which should be useful while using a cactus EXP farm!
-   Added a new category to the  `/clearjunk`  command,  `NETHER`. Use  `/clearjunk nether`  to remove all netherrack from your inventory.

## Version 1.6.4 - May 18, 2020
**Additions**
Added a death counter, which will be displayed below the player's name. This will publically list the amount of deaths took dating back from the moment you stepped foot into this server.

**Changes**
- The `/warp` command is **nerfed.** **This command will no longer work when:**
    - There are hostile mobs within a *ten* block radius from the player.
    -  The player is on fire
    -  The player's health drops below 80%.

**Future Additions**
- Minecraft will be more difficult in the future. I am planning of adding custom monsters and boss mobs into the server, as it has gotten a little too easy with the amount of OP armor we have.
- The addition of a broomball minigame, which allows you to create working broomball arenas in this server!