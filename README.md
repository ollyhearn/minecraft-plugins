# minecraft-plugins
Simple minecraft plugins, that i wrote for my server

**AkdLogin**
Login plaugin, that uses SQLite database and MD5 hashsum to gurantee secure login. After logging in player will be teleported into a specified world. **Reques AkdGoTo**
Usage:
/register [pwd] [repeat pwd]
- Registers you with specified password and nickname as your login
/login [password]
- Logins you in if you already have registered

**AkdGoTo**
Simple plugin to jump between worlds in your server, or to send other players to theese worlds. Reques op rights.
/goto [world]
- Sends you to world, if this world exists
/goto [player] [world]
- Send specified player to world, if this world exists
/genworld [world]
- Creates new empty world with specified name
All theese commands reques OP rights to be executed.
