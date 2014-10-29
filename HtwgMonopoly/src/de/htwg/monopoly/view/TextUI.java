package de.htwg.monopoly.view;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.htwg.monopoly.controller.IController;
import de.htwg.monopoly.entities.impl.Player;
import de.htwg.monopoly.observer.Event;
import de.htwg.monopoly.observer.IObserver;
import de.htwg.monopoly.util.IMonopolyUtil;
import de.htwg.monopoly.util.MonopolyUtils;

public class TextUI implements IObserver {

    /* logger */
    private final Logger logger = LogManager.getLogger("htwgMonopoly");
    private Scanner in;

    /* internationalization */
    private ResourceBundle bundle = ResourceBundle.getBundle("Messages",
            Locale.GERMAN);

    private IController controller;

    private int configNumberOfPlayer;
    private String[] configNameOfPlayer;

    public void startGame() {

    	// print Hello screen
        printInitialisation();
        
        // read number and name of players
        in = new Scanner(System.in);
        if (!in.next().isEmpty()) {
            setNumberOfPlayer();
            setNameOfPlayers();
        }
        
        logger.info(IMonopolyUtil.START);
        
        // start actual game
        controller.startNewGame(configNumberOfPlayer, configNameOfPlayer);
    }

    public TextUI(IController controller) {
        this.controller = controller;
        controller.addObserver(this);
    }

    @Override
    public void update(Event e) {
        printTUI();
        startTurn();
    }

    @Override
    public void update(int e) {
        if (e == 1) {
            printRoll();
            onField();
            printAction();
            printOptions(2);

        }
        if (e == 0) {
            printTUI();
            startTurn();
        }
    }

    public void printInitialisation() {
        logger.info(IMonopolyUtil.GAME_NAME);
        logger.info("Herzlich Willkommen zu Monopoly!");
        logger.info("Um das Spiel zu starten, beliebigen Wert eingeben und bestätigen.");
    }

    /**
     * print information about dice
     */
    private void printRoll() {
        int diceResult = controller.getDice().getResultDice()
                % (controller.getField().getfieldSize() + 1);
        String out = MessageFormat.format(bundle.getString("tui_dice"),
                diceResult);
        logger.info(out);
    }

    public void onField() {
        String currentFile = controller.getField()
                .getCurrentField(controller.getCurrentPlayer()).toString();
        String out = MessageFormat.format(bundle.getString("tui_playfield"),
                currentFile);
        logger.info(out);
    }

    private void printOptions(int choose) {
        StringBuilder sb = new StringBuilder();
        sb.append(bundle.getString("tui_options"));
        for (String option : controller.getOptions(choose)) {
            sb.append("\n");
            sb.append(option);
        }
        logger.info(sb.toString());
    }

    public void printAction() {
        logger.info(controller.getMessage());
    }

    public void startTurn() {
        StringBuilder sb = new StringBuilder();
        String currentPlayer = controller.getCurrentPlayer().getName();

        sb.append(MessageFormat.format(bundle.getString("tui_player"),
                currentPlayer));

        sb.append(bundle.getString("tui_options"));
        for (String option : controller.getOptions(1)) {
            sb.append("\n");
            sb.append(option);

        }
        logger.info(sb.toString());

    }

    /**
     * function to read number of player
     */
    public int readNumberOfPlayer() {

        int tmpNumberOfPlayer = 0;

        if (in.hasNext()) {
			/* check if input an integer and in right interval */
            if (in.hasNextInt()) {
                tmpNumberOfPlayer = in.nextInt();
                in.nextLine();
            } else {
				/* TODO: alles weg */
                in.nextLine();
                return 0;
            }
        }

		/* check if input smaller as maximum of player and bigger as minimum */
        if (MonopolyUtils.verifyPlayerNumber(tmpNumberOfPlayer) == false) {
            return 0;
        }

		/* if scanned number correct, save it */
        return tmpNumberOfPlayer;
    }

    private void setNumberOfPlayer() {
        logger.info(IMonopolyUtil.Q_NUMBER_OF_PLAYER);
        int status  = readNumberOfPlayer();
        while (status == 0) {
            logger.info(IMonopolyUtil.ERR_NUMBER_OF_PLAYER);
            status  = readNumberOfPlayer();
        }
        this.configNumberOfPlayer = status;
    }

    private void setNameOfPlayers() {
        this.configNameOfPlayer = new String[configNumberOfPlayer];
        for (int i = 0; i < this.configNumberOfPlayer; i++) {
            logger.info("Player " + (i + 1) + " " + IMonopolyUtil.Q_NAME_PLAYER);
            if (in.hasNext()) {
                this.configNameOfPlayer[i] = in.nextLine();
            }

        }
    }

    /**
     * print TUI
     */
    private void printTUI() {
        /* TODO: Ausgabe formatieren */
        StringBuilder sb = new StringBuilder();
        StringBuilder streets = new StringBuilder();

        sb.append("\n_________________________________\n");
        sb.append(bundle.getString("player") + "\t|Budget\t|"
                + bundle.getString("ownership") + "\n");
        sb.append("-------\t|------\t|--------------\n");
        for (int i = 0; i < controller.getNumberOfPlayer(); i++) {

            Player player = controller.getPlayer(i);
            sb.append(player.getName() + "\t|" + player.getBudget() + "\t|"
                    + player.getOwnership() + "\n");
        }

        int z = IMonopolyUtil.TUI_HIGH;
        String[] zeichen = new String[z];
        z = 0;
        zeichen[z] = "|-------";
        zeichen[++z] = "|___x___";
        zeichen[++z] = "|       ";
        zeichen[++z] = "|_______";

        String x = "x";
        for (int zeile = 0; zeile < zeichen.length - 1; zeile++) {
            sb.append("\n");
            for (int i = 0; i < controller.getField().getfieldSize(); i++) {
                if (zeile == 1) {
                    zeichen[1] = zeichen[1].replace(x, "" + i);
                    x = "" + i;
                }
                sb.append(zeichen[zeile]);
            }
            sb.append("|");
        }
        for (int i = 0; i < controller.getField().getfieldSize(); i++) {
            streets.append(i).append("=")
                    .append(controller.getField().getFieldNameAtIndex(i))
                    .append("\n");
        }

        sb.append("\n").append(streets);
        logger.info(sb.toString());

    }

    /**
     * handle user input
     *
     * @param line
     * @return
     */
    public boolean processInputLine(String line) {
        boolean status = true;
        char[] l = line.toCharArray();
        if (!controller.isCorrectOption(line)) {
            logger.info(bundle.getString("tui_wrong_input"));
            return status;
        }
        switch (l[0]) {
            case 'd':
                // roll dice
                controller.startTurn();
                break;
            case 'b':
                // zug beenden
                controller.endTurn();
                printTUI();
                startTurn();
                break;
            case 'x':
                status = false;
                break;
            case 'y':
                if (controller.buyStreet()) {
                    logger.info(bundle.getString("tui_buy"));
                } else {
                    logger.info(bundle.getString("tui_no_money"));
                }
                controller.endTurn();
                printTUI();
                startTurn();
                break;
            case 'n':
                controller.endTurn();
                printTUI();
                startTurn();
                break;
            case 'f':
			/* temporary */
			/* TODO check if enough money */
                controller.getCurrentPlayer().decrementMoney(
                        IMonopolyUtil.FREIKAUFEN);
                controller.getCurrentPlayer().setInPrison(false);
                // zug beenden
                controller.endTurn();
                printTUI();
                startTurn();
                break;
            default:
                logger.info(bundle.getString("tui_wrong_input"));
        }
        return status;
    }
}
