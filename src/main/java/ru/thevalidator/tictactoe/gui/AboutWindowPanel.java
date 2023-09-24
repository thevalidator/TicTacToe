/*
 * Copyright (C) 2023 thevalidator
 */

package ru.thevalidator.tictactoe.gui;

import java.awt.Desktop;
import java.awt.Dimension;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JEditorPane;
import javax.swing.event.HyperlinkEvent;

/**
 * @author thevalidator
 */
public class AboutWindowPanel extends JEditorPane {

    public AboutWindowPanel() {
        setContentType("text/html");
        setEditable(false);
        setPreferredSize(new Dimension(330, 290));
        setText("<hr>"
                + "<p>"
                + "Tic-tac-toe (American English), "
                + "noughts and crosses (Commonwealth English), "
                + "or Xs and Os (Canadian or Irish English) is "
                + "a game for two players who "
                + "take turns marking the spaces in a "
                + "three-by-three grid with X or O. "
                + "The player who succeeds in placing "
                + "three of their marks in a horizontal, "
                + "vertical, or diagonal row is the winner. "
                + "It is a solved game, with a forced draw "
                + "assuming best play from both players."
                + "<br/><br/><br/>"
                + "<a target=\"_blank\" href=\"https://icons8.com/icon/eTofvMVD4UUm/circle\">Circle</a> icon by <a target=\"_blank\" href=\"https://icons8.com\">Icons8</a>"
                + "<br/>"
                + "<a target=\"_blank\" href=\"https://icons8.com/icon/RvdYzOup1Xqx/multiply\">Multiply</a> icon by <a target=\"_blank\" href=\"https://icons8.com\">Icons8</a>"
                + "<br/><br/>"
                + "[thevalidator] - <a href=\"https://github.com/thevalidator/TicTacToe\">Github</a><br/>"
                + "2023, September<br/>"
                + "v1.0.0"
                + "</p>"
        );
        addHyperlinkListener((e) -> {
            if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                try {
                    Desktop.getDesktop().browse(e.getURL().toURI());
                } catch (IOException | URISyntaxException ex) {
                    Logger.getLogger(AboutWindowPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });
    }

}
