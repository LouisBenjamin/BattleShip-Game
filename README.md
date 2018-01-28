# Battleship-Game
Simple Java program that allows user to play Battleship against a computer. It is a simplified version of the game and is played on a single 8x8 grid. 

Before the actual game, each player secretly places 6 ships and 4 grenades on the grid. Ships and grenades are a single position on the grid which are hidden to the other computer. Once both players have placed their ships and grenades, the game starts. Each player, in turn, “shoots a rocket” on the grid (i.e. calls a position). 

- If the rocket (the position called) falls on a position where there is nothing, then nothing happens, and the other player can shoot his/her rocket.
- If the rocket falls on a coordinate where the opponent (or the player) has a grenade, then the player loses a turn, and next time, the opponent will play twice in a row.
- If the rocket falls on a coordinate where the opponent (or the player...) has a ship, then that ship sinks. - If the rocket falls on a coordinate that has been called before, regardless of what was there before, nothing happens. (So for example, a grenade can only explode once).

The goal of the game is to sink all of your opponent’s ships before your opponent sinks yours.

I am not the owner of this text, I simply edited it.
All credit goes to Dr. Aiman Hanna and the ENCS Department of Concordia University.
