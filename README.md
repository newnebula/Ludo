# Ludo

A simulation of a board game known in English as "Ludo", in German "Mensch argere dich nicht", and in Polish as "Chinczyk".

## Build and run

```
javac -d bin src/*/*.java
cd bin
java ludoGUI.RunTheGame
```

## How to play

There are 4 different play modes:

* Single player (against a computer)
* Two players
* Four players
* Demo: watch four computer-controlled players playing.

Once launched, the game prompts to choose the play mode by pressing one of four given buttons.
If any of first three modes is chosen the game displays a text field to type the name(s) of the players.

Each player starts the game with four square-like pieces, situated in the corners of the board.
In the start configuration they will appear as if there were only one piece since they are piled on top of each other.

During each round a dice is automatically thrown for each player.
The result is displayed in the middle of the board. A player chooses a piece to move by clicking on it.
Pieces that can be moved according to the rules of the game will be activated and all other pieces will be disabled – clicking on them will have no effect.

IMPORTANT: a new piece can only start upon throwing 6! This means a player can only start playing after having thrown 6.

At the end of the game the results are appended to `GameResults.csv` in format "`player, points, time`".

## Rules of the game

Objective: to be the first to move all 4 pieces from starting point to the coloured home fields. 

A new piece can start the race only upon throwing 6. This piece makes at once a jump over 6 fields.
There is never a second throw after 6. Whenever a piece comes to stand on a field which is occupied by another piece, the other one will be sent back to start.
This rule holds even when the piece being sent back to start belongs to the actual player herself.

The pieces of other players can not access and therefore threaten the pieces which are already occupying the coloured finish-fields.
However a piece of the same colour can still be a threat and send the piece back to start even from the coloured field.
All four pieces must fit perfectly in the finish-fields.
Whenever the dice result exceeds the number of fields still ahead of the piece, this piece will bounce back and go several places backward to complete the number thrown.
In effect a piece might send one of a player's own pieces back to start or even exit the finish-fields.

Each piece in the finish-fields earns one point when the game is finished. The winner gets an additional bonus of 5 points.

Enjoy!
