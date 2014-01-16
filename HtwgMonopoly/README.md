# HTWG Monopoly
================
A Java implementation of the board-game **Monopoly** in a *HTWG style*.
## What
This Project is a playable game based on the famous Monopoly game from [Hasbro inc.](http://www.hasbro.com/).  
The game is slightly changed regarding streetnames, chance cards, etc.

## Why
The project was developed in the context of the lecture "Software Engineering" at the University of Applied Science Konstanz, Germany. (WS 13/14)  
The goal of this project was to learn and apply these following components:

* layered architecture
* observer pattern
* components and interfaces  

This implementation comes along with a *TUI* (text user interface) and *GUI* (graphical user interface).

#Documentation
See the current [Javadoc].

#Getting support
Feel free to e-mail us, if you have any furhter questions:

* [Steffen Gorenflo](stgorenf@htwg-konstanz.de)
* [Timotheus Ruprecht](tiruprec@htwg-konstanz.de)

## How
When you start the game, you have to type in the number with how many Players you want to play (2 -6). After you typed in the name for each Player, the Gui will start and you are ready to play the game.

* *Note*: You can switch playing everytime between the TUI and the GUI!

In case you forget the rules of monopoly, try consulting the [Wikipedia article](http://en.wikipedia.org/wiki/Monopoly_(game)) (or Google ;) ).

If everthing went fine, it should look like this:

![image](https://github.com/T1m1/de.htwg.se.monopoly/blob/master/HtwgMonopoly/doc/MonopolyGui.png)

* To start your turn, just press the *Würfeln*-Button or press 'd'.
* If your move is over, press the *Zug beenden*-Button or press 'b'.