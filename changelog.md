# AGCraft Changelog History
## Version 1.7.8 - August 24, 2020
### Pathfinding AI Changes
- Custom monsters now carries a `hitlist`, allowing them to target any `EntityType` that is in its hitlist.
- Custom monsters also carries an `ignore list`, which makes them completely passive towards any `EntityType` in its `ignore list`. For example, if the `EntityType` `Player` is in a custom zombie's `ignore list`, then that zombie will be completely passive towards that `Player`.
- In addition, entities such as Iron Golems that are naturally aggressive towards any custom monster that has that entity in their hitlist will also be passive to them as well.
- Custom monsters can now be apart of `factions`. `Factions` allows differing custom monsters to be able to form teams.
   - Custom monsters will **never** attack other monsters that are either in the same faction or not part of the custom monster's list of rivaling factions, even if they are in their `hitlist`.
   - Custom monsters also carry a list of **rivaling factions**. Any mob that is not only in that mob's list of rivaling factions, but also in their hitlist will be hunted down by that monster.
   - Unless a custom monster has the `IGNORE_NON_FACTION_ENTITIES` flag enabled, that mob will also hunt down any monster that is in their hitlist but not in any faction.
      - The `IGNORE_NON_FACTION_ENTITIES` flag disables this behavior.
 - Monsters will now be **instantly aggravated** towards any player who hit that mob with a projectile, unless that player is in its `ignore list`.
 - Certain custom boss monsters now have a `PERSISTENT_ATTACKER` flag, which makes them less likely to forget their victim that moves outside of their natural hunting radius.

### Custom Mob Changes
#### EXP Drops
   - Custom experience drops are now supported in this plugin. Almost all custom monsters drop significantly more experience now!
#### Boss Bar
   -  All boss mobs in this server now carries a custom boss bar similar to Wither and EnderDragon fights in Minecraft.
   - These boss bars provide all players fighting that monster visual information about its total remaining health.
#### Mob Changes
   - Shrek is now a boss mob. His health has been buffed from 20 -> 30 HP.
   - Both Soul Destroyer and Soul Obliterator (custom Wither Skeleton types) is no longer aggressive towards vanilla Wither Skeletons in the Nether. However, they are still aggressive towards Doom Guy.
   - Buffed Doom Guy's health from 20 -> 60.
   - Buffed Barney the Dinosaur's health from 8 -> 16.
#### Spawn Weight Changes
   - Doom Guy's spawn weight increased from 3 -> 5.
   - Skull Kid's spawn weight decreased from 10 -> 5.

### Server Events
- A new server event, `Beat the Bruins`, have been added to reflect the upcoming College Football season! This event also makes full use of our custom Pathfinding API!
- For the first time ever, allied monsters (other than Golems and Wolves) have been added into the game! These allied monsters are themed after USC Trojans and will hunt down any hostile mob near them except Creepers.
- However, the UCLA Bruins have invaded the server! They are here to cause chaos and destruction to our server! Help your fellow trojans and defend this server from these nasty Bruins!
- More mobs and factions will be added in later to reflect this football season. Expect to see other Colleges battling each other in our server.

## Version 1.7.7 - July 31, 2020
This release contains many internal codebase improvements and additions. Significant noteworthy changes are:

   - Added permissions to our commands to programmatically determine which commands can or cannot be executed by regular players.
   - Created a new abstract class for our search trie library, as code used to manipulate our internal search trie is copied and pasted too frequently.
   - The /bible command is modified to run the BibleFetcher API on a seperate thread to minimize the chance of RESTful API calls from stalling the server.
   - Added a new admin-only command, /reloadconfigfile, to dynamically reload the config file without restarting the plugin.
   - All armor drops from Mythic mobs (monsters that usually possess a red nametag and hunts you on spawn) are now unbreakable, meaning that they never lose any durability.
   - Added a new "Strawberry Mocktail" which gives powerful Level II buffs for 30 minutes as a reward for taking part in our upcoming minigames.
   - A new config file parser, allowing the following server variables to changed dynamically, which are:
        - Participation Reward Type
           - Determines which set of participation rewards are automatically distributed to the player's account when the server is running on either MINIGAME or UHC mode.
        - Themed Server Event
            - Allows you to activate a specific themed server event such as the Fourth of July event.
        - Server Mode
            - Allows you to change which mode the server is operating in. Server Mode is a new addition to this plugin that dynamically disables this plugin's default commands and mob-spawning listeners when set to anything other than NORMAL. This is used primarily for running minigames, as this plugin still needs to be partially active for handling reward distributions and handle any minigame-specific custom events using the plugin's codebase.
        -  Amplified Spawn Chance
           - Allows you to dynamically control the monster-conversion chance for our custom mob plugin.
        - Amplified Debug Mode
           - Allows you to dynamically toggle our Custom Mob plugin's debug mode, which outputs mob damage information and changes the custom mob spawn chance to 100%.

## Version 1.7.6 - July 24, 2020
### Bug Fixes and changes
   - Fixed an issue where all Lightweight Boot variants were not giving armor point bonuses.
   - Custom mob spawning module will now accept monsters that are spawned through breeding.
   - All custom item lore will now have up to 4 words per line instead of 5 to reduce screen cluttering when reading their item descriptions.
   - Added somethin' special for Teddy in light of recent events ;)

### New Allied Monsters

   - Wolf
      1. Snowball
         - One of the two new custom wolf spawns in the game. Can be found anywhere where you can find wolves.
         - Stats:
             - Attack Damage: 8.0 / hit (increased to 10.0 if untamed)
             - Movement Speed: +20%
             - Armor Points: 9.0
             - Armor Toughness: 6.0
             - Knockback Resistance: 75%
             - Armor comes with Protection III, Blast Protection III, Feather Falling IV, Thorns II, Frost Walker III, and Depth Strider II.
             - Spawns with a permanent Resistance I buff, reducing his damage intake by 20%.
             - Spawn Weight: 90/100 (90% chance)

      2. Cookie
         - A much rarer, but more powerful wolf that could be found in this game.
         - Stats:
             - Health: 30.0
             - Attack Damage: 10.0 / hit (increased to 12.0 if untamed)
             - Movement Speed: +25%
             - Armor: 14.0
             - Armor Toughness: 8.0
             - Knockback Resistance: 50%
             - Armor comes with Protection III, Blast Protection V, Feather Falling IV, Thorns III, and Depth Strider V.
             - Spawns with permanent Regeneration I and Fire Resistance I buffs, allowing him to passively heal and survive any Lava / Fire Aspect / Flame attacks.
             - Spawn Weight: 10/100 (10% chance)

### New Monsters

    - Wither Skeleton
        - Soul Destroyer (Epic)
            - Health: 20
            - Attack Damage: 19.5
            - Armor: 11.0
            - Armor Toughness: 6.0
            - Knockback Resistance: 20%
            - Spawn Weight: 60/100 (60% chance)

        - Soul Obliterator (Legendary)
            - Health: 16.0
            - Attack Damage: 9.0 (Bow) + 18.0 (Instant Damage III) = 27.0
            - Armor: 11.0
            - Armor Toughness: 4.5
            - Knockback Resistance: 10%
            - Spawn Weight: 39/100 (39% chance)
            - Arrow inflicts Instant Damage III and Wither III for 10 seconds.

        - The Doom Slayer (Mythic)
            - Health: 30.0
            - Armor: 20.0
            - Armor Toughness: 21.0
            - Attack Damage: 32.0
            - Knockback Resistance: 20%
            - Spawn Weight: 1/100 (1% chance)
            - Hunts any player within 125 blocks away from him upon spawning.
            - Inflicts Fire Aspect II and Wither I when attacked.
            - Rare chance of dropping his head, which gives +10 health and +2.0 attack when equipped. However, your attack speed is slightly decreased while wearing this helmet.
        - All custom wither skeletons also have a rare chance of dropping an extra Wither Skeleton skull.

    - Zombie Pigman
        - Veteran Zombie Pigman (Epic)
            - Health: 20.0
            - Armor: 8.0
            - Armor Toughness: 4.0
            - Attack Damage: 20.25
            - Knockback Resistance: 15%
            - Spawn Weight: 93 / 100 (93% chance)

        - Akimbo Pigman (Legendary)
            - Health: 40.0
            - Armor: 4.0
            - Armor Toughness: 3.0
            - Attack Damage: 26.25
            - Spawn Weight: 6/100 (6% chance)
            - Inflicts Fire Aspect II.
            - Rare chance of dropping a 'Pigman Dagger' which can be wielded in your offhand to deal +4.0 points more damage.

        - The Terminator (Mythic)
            - Health: 12.0
            - Armor: 21.0
            - Armor Toughness: 12.0
            - Movement Speed: 40%
            - Attack Damage: 26.25
            - Spawn Weight: 1/100 (1% chance)
            - Hunts any player within 100 blocks away from his spawn point.
              - When hunting a player, he also can alert any other pigman near him to help hunt the player.
            - Dual wields the same golden swords as Akimbo
            - Spawns with and Speed I

    - Piglin
        - Piglin Hunter (Rare)
            - Health: 20.0
            - Armor: 3.0
            - Armor Toughness: 3.0
            - Movement Speed: 20%
            - Knockback Resistance: 15%
            - Attack Damage: 4.0
            - Spawn Weight: 55/100 (55% chance of spawning)
            - Rare chance of dropping a 'ShotBow'; a modified Crossbow that can be reloaded instantaneously allowing for rapid fire crossbow action. For balancing reasons, Mending cannot be applied to this bow.
            - Has a rare chance of dropping his head, which gives Projectile Protection II and +2.0 armor / armor toughness points.

        - Piglin Soldier (Epic)
            - Health: 20.0
            - Armor: 15.0
            - Armor Toughness: 7.0
            - Knockback Resistance: 30%
            - Movement Speed: 20%
            - Attack Damage: 15.75
            - Has a rare chance of dropping his head, which gives Protection II and +2.0 armor / armor toughness points.

        - Piglin Captain (Legendary)
            - Health: 40.0
            - Armor: 11.0
            - Armor Resistance: 6.5
            - Knockback Resistance: 20%
            - Attack Damage: 25.25
            - Has a rare chance of dropping his head, which gives Protection III and +3.0 armor and +3.5 armor toughness points. He can also drop his Golden Axe that has +10 base damage, but its golden so it won't last long.

### Mob Changes:

    - Soul Eater (Formally known as Withering Skeleton)
        - This skeleton now spawns exclusively in the Nether, giving Overworld Players a break.
        - Because the Nether is not intended by Mojang to be noob friendly, he is given the following buffs:
             - Health -> Increased from 16 to 20 points
             - He is now given a permanent Fire Resistance buff.
             - His weapon is now equipped with Punch I.
             - His arrows now inflict Instant Damage III instead of Instant Damage II.
             - Spawn weight increased from 6 -> 30 (Has a 30/145 chance of spawning in the Nether).

    - Skull Kid (Formally known as ??????????)
        - This skeleton also now spawns exclusively in the Nether.
        - He has been given the following buffs:
            - Spawn Weight -> Increased from 2 -> 15 (Has a 15/145 chance of spawning in the Nether).
            - His hunt on spawn radius has been increased from 50 -> 100.
            - He is now given a permanent Fire Resistance and Speed I buff.
            - His arrows now inflict Poison IV for 8 seconds instead of Poison III.

    - Since Soul Eater no longer spawns in the overworld, the spawn weight of Hurtful Skeleton has been increased from 17 -> 18 (now has a 18% chance of spawning).
        - Annoying Skeleton's spawn weights have also been changed to 80/100 (80% chance of spawning).

### Quality of Life Improvements
   - The /warp command now teleports all of the player's active (not sitting) tamed monsters and any animal the player is currently riding on from a 40-block radius with you to the warp location.
         - Occasionally (due to an unfixed bug in Minecraft's game engine) your animals will become invisible upon teleporting. If that happens, please leave and rejoin the server to see them again.
         - Once again, this only applies to:
             - Tamed animals that are not sitting
             - Any rideable animal (such as a horse or pig with saddle) that you are currently riding on.
   - All of frolf's potions now have item lore describing what the potion does in more detail.
   - Patched the server's software to the latest version of Spigot.

## Version 1.7.5 - July 20, 2020
### Mob Balancing Changes

#### Badass Zombie:
- Decreased health from 20.0 to 16.0
- Spawn weight increased from 12 to 13

#### Shadow Clone joojetsu
- Spawn weight decreased from 3 to 2.
- Decreased health from 20.0 to 14.0

#### Withering Skeleton
- Decreased health from 20.0 to 16.0

#### Enraged Spider
- Fire aspect level on the spider's Fire Venom Fang nerfed from 3 to 1.

#### Barney the Dinosaur
- Health decrased from 20.0 to 8.0
- Barney's leg and chest no longer gives an attack bonus.
- Increased attack bonus of Barney's totem from 5.0 to 7.0.
- Barney's boots now gives +1.0 attack bonus when worn.
- Speed bonus of Barney's boots decreased from 40.0 to 20.0. The removed speed bonus is instead applied onto Barney's held totem
   - Barney loses nearly half of his speed and 1/3 of his attack after dying once.
- Decreased Barney's movement speed bonus from 40.0 to 35.0.

#### ???????
- Reduced hunt on spawn range from 150 blocks to 50 blocks.

## Version 1.7.4 July 14, 2020
### New Commands:  
`/rewards`  
   - Use this to open the "Claim Rewards" interface. This is where you claim any unclaimed rewards waiting for you!New Features:  

### Changes
- Implemented an internal rewards system for our upcoming minigame nights! This allows me to distribute custom reward items to players for either participating or winning the minigame being played for that night.  
        - In addition, every active player in this server has been given 6 rewards, which are:  
            - x1 Advanced Golem Summoning Scroll  
            - x64 Golden Carrots  
            - x192 Paint the Skies Firework Rockets  
            - x1 Firework Launcher  
         ... as a small token of appreciation for bringing life into this server these past few weeks! <3  
- Modified the custom mob spawning plugin to support biome-specific mob spawns (meaning that there will be mobs that only spawns in a specific biome).  
- Added in two new biome-specific mobs in the game:  
    1. Shrek  
		- A modified zombie variant that only spawns in Swamp biomes.  
           - He is wearing a full set of leather armor that gives the same strength as Iron.  
           - With his trusty wooden axe, he can deal up to 15 points of damage to unarmored players, so watch out!  
           - Has a 30/130 (23%) chance of spawning, so better stay out of his swamp unless you are fully prepared!  
           
    2. ???????  
	     - A mysterious skeleton mob that only spawns in very mountainous areas of the world.  
	     - Extremely powerful, as he carries a Power VII bow and shoots highly poisonous arrows.  
	     - Carries custom leather armor that has the same strength as Netherite, but only the playerhead is enchanted. But he has 50 health, so he is very tanky and powerful.  
	     - Rare chance of spawning. Only has a 4/105 (3.8%) chance of spawning when you are in those mountainous areas, which is a good thing.  
	     - Will automatically hunt any random player within a 125 block radius upon spawning. You will be well notified by his presence to give you a chance to chicken out.Changes:  
 - Normalized weights of custom mob spawns. They are all now in a scale of 1 to 100.  
    - In addition, the weights for custom mob spawns have been rebalanced. Here are the current spawn rates for our universal custom mobs:
	    - Zombie:  
             Bukly Zombie - 85/100 (85%)   
			 Badass Zombie - 12/100 (12%)  
			 Shadow Clone joojetsu - 3/100 (3%)
		- Skeleton:  
		     Annoying Skeleton - 75/100 (75%)  
			 Skeleguard - 16/100 (16%)  
			 Withering Skeleton - 6/100 (6%)  
			 #agspotted - 3/100 (3%)  
		- Spider  
             Agressive Spider - 90/100 (90%)  
             Enraged Spider - 10/100 (10%)  
		- Husk  
             Wandering Husk - 85/100 (85%)  
             Fallen Pharaoh - 15/100 (15%)  
    ... TLDR: Shadow Clone joojetsu and #agspotted will spawn less frequently now.    - Badass Zombie no longer has Fire Aspect on their swords and their swords have been downgraded to Sharpness I.  
 - Shadow Clone joojetsu and #agspotted will now drop their Netherite Helmet, Chestplate, and Leggings as a rare drop.  
 - Shadow Clone joojetsu now has Knockback II on their Netherite Axe, which is a good thing as it gives you a chance to not be hit again by him.  
 - Stripped Firework ingredient lore from all future custom fireworks, meaning that you won't see a large blob of text when you hover your mouse over a firework.  
   frolf now sells two additional items:  
	 1. Enhanced Potion of Haste  
		 - Gives Haste II for 5 minutes (allows you to instamine Stone with efficiency V pickaxe)  
		 - Cost: 5 Diamonds, Max Stock: 2  
	2. Enhanced Potion of Luck
		- Gives Luck II for 5 minutes  
		- Cost: 5 Diamonds, Max Stock: 2  
- Max stock for some of frolf's items have been increased:  
	 1. Enchanted Golden Apple - (3 -> 5)   
	 2. Enhanced Potion of Strength - (1 -> 2)  
	 3. Enhanced Potion of Speed - (1 -> 2)  
	 4. Potion of Haste - (1 -> 3)  
	 5. Potion of Luck - (1 -> 3)

## Version 1.7.3 - July 3, 2020

**Fourth of July Update**

**Changelog (Will be in effect until 7/12/2020):**

- Added the following custom monsters to the game:   
   - Patriotic Zombie  
   - Patriotic Skeleton  
   - Patriotic Pillagers  
They will be sporting classic Red, White, and Blue armor pieces and a unique Helmet; all which has similar strength compared to a full set of Netherite Armor, despite being all Leather. They also have a high chance of dropping one of nine custom firework rockets (in bundles of 16), which can be used to light up the night sky! They have a 20% chance of spawning and are a bit bulky, so be prepared. Patriotic Pillagers are added into the game and will shoot custom firework rockets at you. Their crossbows are enchanted with multishot for comedic effect.  
- Phantoms will now explode into a firework upon spawning (and die instantly). 


## Version 1.7.1 - 1.7.2 - July 2, 2020

**Changes **
Added two new husk mobs, which are:  

-   Wandering Husk (Uncommon)
-   Fallen Pharaoh (Epic)

All wandering traders are now replaced by frolf. frolf is a custom wandering trader who trades emeralds and diamonds for very good loot, many of whom are unobtainable in the game. I figured it would be a good idea to have this since the custom mobs I have added has augmented the game's difficulty to extremes. Not only is this a way to have a greater use for your hard earned diamonds, this also gives you more power to deal with several of the OP mobs I have added. However, stock is limited and some things frolf offers are quite costly.Here are his trades:  

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

## Version 1.6.4.3 - June 27, 2020
**Changes**
- Began preparation for the upcoming nether update.
- Implemented a new console-only command, ``/removeoldnetherlocations``, which removes all current nether warp locations from the database. This command can only be ran by the administrator.

## Version 1.6.4.2 - June 24, 2020
**Changes**
- Added cactus to the `natural` category for the `/clearjunk` command.
- Increased scanning radius of the "check nearby monsters" component of the `/warp` command to 10.
- Added back the access level parameter in `/getlocations` to filter between private and public warps.
- Added auto completion capabilities for the `/getlocations` command for the new access specifier parameter.

## Version 1.6.4.1 - May 18, 2020
**Changes**
- All players are now given **six** *emergency warp tickets*, allowing them to use `/warp` when:
    -  The player is on fire
    -  The player's health drops below 80%.
- They will not work however when the player is within seven blocks of any hostile monster  (radius has been changed). This is to prevent you from accidentally wasting them.


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

## Version 1.6.3 - April 23, 2020
**Changes**
- Simplified the `/warp` command by removing the `tag` parameter. You can now use `/warp <name of location>` to warp to your named locations. In addition, `/warp home` still teleports you to your bed spawn, like always.
- -   Added green dye to the  `COMMON`  category of the  `/clearjunk`  command. Use  `/clearjunk`  to remove all rotten flesh, bones, string, green dye, and seeds from your inventory, which should be useful while using a cactus EXP farm!
-   Added a new category to the  `/clearjunk`  command,  `NETHER`. Use  `/clearjunk nether`  to remove all netherrack from your inventory.

## Version 1.6.2 - April 9, 2020
**Changes**
  - Implemented tab-autocompletion for all commands that has custom parameters. Use tab to cycle through parameters to make your life easier while using our commands.
  - Removed the secondary `access` parameter for `/getcoordinates` and `/removecoordinates`.

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
> - Seeds (wheat)
> 
> `NATURAL`  
> - Dirt  
> - Sand  
> - Gravel
> 
> `STONE`  
> - Cobblestone  
> - Stone  
> - Andesite  
> - Granite  
> - Diorite
> 
> `ARMOR`  
> - All golden, chainmail, and leather variants
> 
> `BREWING`  
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

## Versions 1.0 to 1.3 - March 23, 2020
- Implemented the core components used to aggregate Bible content from multiple RESTful APIs.
- Added support for fetching content from the ESV version of the Bible.
- Hooked the BibleFetcher code into a Spigot command, `/bible	`, which fetches requested books of the Bible from those online APIs and converts it into a written book for the player to read in Minecraft.