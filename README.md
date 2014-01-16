# HTWG Monopoly
================
A Java implementation of the board-game **Monopoly** in a *HTWG style*.
## What
This Project is a playable game based on the famous Monopoly game from [Hasbro inc.](http://www.hasbro.com/).  
The game is slightly changed regarding street names, chance cards, etc.

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
Feel free to e-mail us, if you have any further questions:

* [Steffen Gorenflo](mailto:stgorenf@htwg-konstanz.de)
* [Timotheus Ruprecht](mailto:tiruprec@htwg-konstanz.de)

## How
When you start the game, you have to type in the number with how many Players you want to play (2 -6). After you typed in the name for each Player, the Gui will start and you are ready to play the game.

* *Note*: You can switch playing after every round between the TUI and the GUI!

In case you forget the rules of monopoly, try consulting the [Wikipedia article](http://en.wikipedia.org/wiki/Monopoly_(game)) (or Google ;) ).

If everything went fine, it should look like this:

![image](http://github.com/T1m1/de.htwg.se.monopoly/raw/master/HtwgMonopoly/doc/MonopolyGui.png)

The buttons (or characters for the TUI) have following meanings:

* *Würfeln* or 'd':
    * Start the turn
* *Zug beenden* or 'b':
    * The turn of the current player finishes and it's the turn of the next player
* *kaufen* or 'y'
   * If the current player lands on a non occupied street, he has the opportunity to buy this street.
* *Hotel kaufen*
    * not yet implemented (Later the player can purchase Houses on his streets)
* *Freikaufen 200*
    * The current player can pay 200 € to get free from prison (has bugs)
* *Freikarte einlösen*
    * not yet implemented (Later the player can redeem a *Get out of Jail Free Card*)
