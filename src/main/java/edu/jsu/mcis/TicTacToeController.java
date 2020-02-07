package edu.jsu.mcis;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TicTacToeController implements ActionListener {

    private final TicTacToeModel model;
    private final TicTacToeView view;
    
    /* CONSTRUCTOR */

    public TicTacToeController(int width) {
        
        /* Initialize model, view, and width */

        model = new TicTacToeModel(width);
        view = new TicTacToeView(this, width);
        
    }

    public String getMarkAsString(int row, int col) {       
        return (model.getMark(row, col).toString());       
    }
   
    public TicTacToeView getView() {       
        return view;       
    }

    public int getWidth() {
        return model.getWidth();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        // INSERT YOUR CODE HERE
        String name = ((JButton) event.getSource()).getName();
        int row = Integer.parseInt(name.substring(6,7));
        int col = Integer.parseInt(name.substring(7));
        System.out.println(row + " " + col);
        
        model.makeMark(row, col);
        view.updateSquares();
        
        TicTacToeModel.Result result = model.getResult();

        if (result != TicTacToeModel.Result.NONE) {

            view.disableSquares();
            view.showResult(result.toString());
        }
        else 
        {
            view.clearResult();
        }
    }
    
}
