# Karma
Explanation of what all classes do.

Karma - The main class. Registers commands, events, runnables, etc.

API:

KarmaAPI - The main class of the Karma API. Uses singleton pattern.
KarmaKillChangeEvent - Event that triggers when a player is killed and the karma of the killer changes
KarmaLevelChangeEvent - Event that triggers when a players karma level changes

Commands:

AdminCommands - Contains the code for all Karma commands.

Config

CustomConfig - An object used as a wrapper for FileConfiguration objects that are not the config.yml
KarmaConfig - Contains objects from the config.yml and methods to help calculate karma changes, karma levels, etc. Uses singleton pattern
Messages - Contains messages for Karma

Data

DataStorage - Interface for classes that store data. Contains methods like sterilize and desterilize. 
DataStorageManager - Manages and initializes DataStorage objects
FlatFileStorage - An implementation of DataStorage for flat file storage (karma.yml)
MySQLStorage - An implementation of DataStorage for database storage using MySQL

Listeners

DamageListener - Listens for players damaging other players. Used for "yellow" karma feature
JoinLeaveListeners - Listens for players joining and leaving. Used for storing cached data
KillListener - Listener for players being killed by other players

Runnables

YellowTimer - Timer for yellow players

Scoreboard

KarmaScoreboard - Interface for classes that act as scoreboards and display karma levels
KarmaScoreboardManager - Manages and initializes KarmaScorebaord objects
VanillaScoreboard - An implementation of KarmaScoreboard that uses the Vanilla scoreboard to display karma levels in the tab menu
ValutScoreboard- An implementation of KarmaScoreboard that assigns Vault groups corresponding to karma levels. Not even started on

User

KarmaLevel - Enum containing karma levels
KarmaUser - Wrapper for player object that contains code for setting and modifying karma and karma levels
KarmaUserManager - Manager of karma users that contains maps and arrays of karma users to players
