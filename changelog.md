
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

## Version 1.6.4.1 - May 18, 2020
**Changes**
- All players are now given **six** *emergency warp tickets*, allowing them to use `/warp` when:
    -  The player is on fire
    -  The player's health drops below 80%.
- They will not work however when the player is within seven blocks of any hostile monster  (radius has been changed). This is to prevent you from accidentally wasting them.

## Version 1.6.4.2 - June 24, 2020
**Changes**
- Added cactus to the `natural` category for the `/clearjunk` command.
- Increased scanning radius of the "check nearby monsters" component of the `/warp` command to 10.
- Added back the access level parameter in `/getlocations` to filter between private and public warps.
- Added auto completion capabilities for the `/getlocations` command for the new access specifier parameter.


## Version 1.6.4.3 - June 27, 2020
**Changes**
- Began preparation for the upcoming nether update.
- Implemented a new console-only command, ``/removeoldnetherlocations``, which removes all current nether warp locations from the database. This command can only be ran by the administrator.

## Version 1.6.5 - June 29, 2020

**Changes**

- Amplified the game difficulty by implementing a custom mob system.
- Added the following monsters into the game:

|Zombie|Skeleton|Spider|Snowman|Iron Golem|
|--|--|--|--|--|
| Uncommon Zombie | Uncommon Skeleton| Aggressive Spider|Frosty the Snowman|Advanced Golem 
| Badass Zombie| Hurtful Skeleton| Enraged Spider| Scruffy | John Jae |
| Ultimate Badass Zombie| Withering Skeleton|
|  | Ultimate Badass Skeleton|
- Each custom monster has their own tier, stats, and equipment. All monsters are designed to provide a much greater challenge in Minecraft's PVE combat.
- Modified the spawning algorithm to have a 15% chance of replacing a naturally spawned mob with one of the aforementioned custom monsters.
- Added custom mob types for golems and snowman to help the player defend their homes from these new threats.

## Version 1.7.1 - 1.7.2 - July 2, 2020

**Changes **
Added two new husk mobs, which are:  

-   Wandering Husk (Uncommon)
-   Fallen Pharaoh (Epic)

All wandering traders are now replaced by frolf. frolf is a custom wandering trader who trades emeralds and diamonds for very good loot, many of whom are unobtainable in the game.I figured it would be a good idea to have this since the custom mobs I have added has augmented the game's difficulty to extremes. Not only is this a way to have a greater use for your hard earned diamonds, this also gives you more power to deal with several of the OP mobs I have added. However, stock is limited and some things frolf offers are quite costly.Here are his trades:  

> Golden Carrots (bundles of 16)

-   3 Emeralds / bundle | Max Stock: 4

> Sponges (bundles of 4)

-   12 Emeralds / bundle | Max Stock: 2

> Enchanted Golden Apple

-   5 Diamonds / item | Max Stock: 3

> Netherite Ingot

-   12 Diamonds / Ingot | Max Stock: 1

> Enhanced Potion of Strength

-   Gives Strength II for 4 minutes
-   4 Diamonds / Potion | Max Stock: 1

> Enhanced Potion of Speed

-   Gives Speed II for 4 minutes
-   4 Diamonds / Potion | Max Stock: 1

> Potion of Haste

-   Gives Haste I for 8 minutes
-   3 Diamonds / Potion | Max Stock: 1

> Potion of Luck

-   Gives Luck I for 8 minutes
-   3 Diamonds / Potion | Max Stock: 1

> Summoning Scroll for Frosty the Snowman

-   Right click to summon Frosty the Snowman
-   16 Emeralds / Scroll | Max Stock: 1

> Summoning Scroll for Scruffy

-   Right click to summon Scruffy
-   32 Emeralds / Scroll | Max Stock: 1

> Summoning Scroll for Advanced Golem

-   Right click to summon the Advanced Golem
-   12 Diamonds / Scroll | Max Stock: 1

> Summoning Scroll for John Jae

-   Right click to summon John Jae
-   24 Diamonds / Scroll | Max Stock: 1


- Added functionality for custom summoning scrolls, which can be used to summon elite allied monsters instantaneously in the game. Frolf is the only NPC that sells those scrolls.

## Version 1.7.3 - July 3, 2020

**Fourth of July Update**

**Changelog (Will be in effect until 7/12/2020):**

- Added the following custom monsters to the game:   
   - Patriotic Zombie  
   - Patriotic Skeleton  
   - Patriotic Pillagers  
They will be sporting classic Red, White, and Blue armor pieces and a unique Helmet; all which has similar strength compared to a full set of Netherite Armor, despite being all Leather.They also have a high chance of dropping one of nine custom firework rockets (in bundles of 16), which can be used to light up the night sky!They have a 20% chance of spawning and are a bit bulky, so be prepared.Patriotic Pillagers are added into the game and will shoot custom firework rockets at you. Their crossbows are enchanted with multishot for comedic effect.  
- Phantoms will now explode into a firework upon spawning (and die instantly).  
