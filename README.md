# Jumpy
Jumpy is a Minecraft plugin that allows you to do double jumps, triple jumps, and more!
## Dependency
This plugin depends on [LuckPerms](https://luckperms.net/) as it uses a permission to disable additional jumps for players (`jumpy.disabled`).
## Permissions
`jumpy.use` - Allow the player to use Jumpy. The player can do a double jump. Default: `op`.   
`jumpy.max_jumps.<i>` - Define the number of additional jumps that the player is allowed to do with `i` between 2 and 10. `ex: jumpy.max_jumps.3`  
`jumpy.infinite` - Grants infinite jumps to the player. Default: `false`.
## Commands
`/jumpy on` - Enable the use of multiple jumps.  
`/jumpy off` - Disable the use of multiple jumps. (useful when you want to fly with `/fly`)  
`/jumpy reload` - Reload the config file.