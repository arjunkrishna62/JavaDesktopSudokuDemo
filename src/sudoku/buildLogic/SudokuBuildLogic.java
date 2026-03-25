package sudoku.buildLogic;

import java.io.IOException;

import sudoku.computationLogic.GameLogic;
import sudoku.problemDomain.IStorage;
import sudoku.problemDomain.SudokuGame;
import sudoku.userinterface.IUserInterfaceContract;
import sudoku.userinterface.logic.ControlLogic;
import sudoku.persistence.LocalStorageImpl;

public class SudokuBuildLogic  {
    public static void build(IUserInterfaceContract.View userInterface) throws IOException {
        SudokuGame initialState;
        IStorage storage = new LocalStorageImpl();

        try {
            initialState = storage.getGameData();
        } catch (IOException e) {
            initialState = GameLogic.getNewGame();
            storage.updateGameData(initialState);
        }

        IUserInterfaceContract.EventListner uiLogic = new ControlLogic(storage, userInterface);

        userInterface.setListner(uiLogic);
        userInterface.updateBoard(initialState);
    }
}
