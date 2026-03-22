package sudoku.userinterface.logic;
import sudoku.userinterface.IUserInterfaceContract;

import java.io.IOException;

import sudoku.constants.GameState;
import sudoku.constants.Messages;
import sudoku.problemDomain.IStorage;

public class ControlLogic implements IUserInterfaceContract.EventListner {

    private IStorage storage;

    private IUserInterfaceContract.View view;

    public ControlLogic(IStorage storage, IUserInterfaceContract.View view) {
        this.storage = storage;
        this.view = view;
    }

    @Override
    public void onSudokuInput(int x, int y, int input) {
        try {
            SudokuGame getGameData = storage.getGameData();
            int[][] newGridState = getGameData.getCopyOfGridState();
            newGridState[x][y] = input;

            getGameData = new SudokuGame(
                GameLogic.checkForCompletion(newGridState),
                newGridState
            );

            storage.updateGameData(getGameData);

            view.updateSquare(x, y, input);

            if (getGameData.getGameState() == GameState.COMPLETE){
                view.showDialog(Messages.GAME_COMPLETE);
            }
        } catch (IOException e) {
            e.printStackTrace();
            view.showError(Messages.ERROR);

        }
    }

    @Override
    public void onDailogClick() {
        try {
            storage.updateGameData(
                GameLogic.getNewGame()
            );

            view.updateBoard(storage.getGameData());
        } catch (IOException e) {
            view.showError(Messages.ERROR);
        }
    }
}
